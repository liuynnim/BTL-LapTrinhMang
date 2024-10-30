/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model.control;

import com.mycompany.myserverpj.model.ClientThread;
import com.mycompany.myserverpj.model.PlayRoom;
import com.mycompany.myserverpj.server.Server;
import com.mycompany.shared.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author quang
 */
public class PlayRoomControl {

    private final Server server;
    private final ArrayList<PlayRoom> rooms;

    // chấp nhận yêu cầu khởi tạo phòng  
    // xử lý chấp nhận lời mời
    public PlayRoomControl(Server server) {
        this.server = server;
        rooms = new ArrayList<>();
    }

    //
    public void reqNewRoom(ClientThread player) throws IOException {
        // player yeu cau la dang ranh
        int maPhong = 1;
        Collections.sort(rooms, (PlayRoom p1, PlayRoom p2) -> Integer.compare(p1.getMaPhong(), p2.getMaPhong()));
        for (PlayRoom r : rooms) {
            if (r.getMaPhong() > maPhong) {
                break;
            } else if (r.getMaPhong() == maPhong) {
                maPhong++;
            }
        }
        PlayRoom room = new PlayRoom(maPhong, player);
        HashMap<String, String> data = new HashMap<>();
        data.put("maPhong", String.valueOf(room.getMaPhong()));
        rooms.add(room);
        player.getObjOut().writeObject(new Message("NEW_ROOM", data));
    }

    public void reqSendInvite(Message message, String playerInvite) throws IOException {
        //playerInvite la người gửi lời mời
        //xuất hiện ở đây để gửi đến người nhận là ai đã mời họ

        //lấy người nhận từ thông điệp người mời gửi đến
        String playerReceive = (String) message.getContent();

        //tìm phòng người mời đang ở để lấy mã phòng
        int maPhong = 0;
        for (PlayRoom room : rooms) {
            if (room.getPlayer1().getPlayer().getPlayerName().equals(playerInvite)) {
                maPhong = room.getMaPhong();
                break;
            }
        }

        // tìm ClientThread của người nhận
        for (ClientThread thread : server.getListClient()) {
            if (thread.getPlayer().getPlayerName().equals(playerReceive)) {
                // gửi lời mời đến người nhận
                // data gồm số phòng và tên người gửi
                HashMap<String, String> data = new HashMap<>();
                data.put("maPhong", String.valueOf(maPhong));
                data.put("inviter", playerInvite);
                thread.getObjOut().writeObject(new Message("INVITE", data));
                thread.getObjOut().flush();
                break;
            }
        }
    }

    public void respAcceptInvite(Message message, ClientThread client) throws IOException {
        String inviter = (String) message.getContent();

        for (PlayRoom room : rooms) {
            if (room.getPlayer1().getPlayer().getPlayerName().equals(inviter)) {
                if (room.getPlayer2() == null) {
                    room.setPlayer2(client);
                    room.getPlayer1().setStatus(false);
                    client.setStatus(false);
                    // gửi thông tin người chơi 2 về cho người chơi 1 để hiển thị
                    room.getPlayer1().getObjOut().writeObject(new Message("ACCEPTER", client.getPlayer()));
                    room.getPlayer1().getObjOut().flush();
                    // gửi thông tin về cho player 2 để vào phòng
                    client.getObjOut().writeObject(
                            new Message("INFO_ANOTHER_PLAYER", room.getPlayer1().getPlayer()));
                    client.getObjOut().flush();
                } else {
                    client.getObjOut().writeObject(
                            new Message("ROOM_FULL", null));
                    client.getObjOut().flush();
                }
                server.updateAllPlayers();
                break;
            }
        }
    }

    public void setReadyStatus(ClientThread client) throws IOException {
        for (PlayRoom room : rooms) {
            if (room.getPlayer1().equals(client)) {
                room.setStatusPlayer1(true);
                if (room.isStatusPlayer1() && room.isStatusPlayer2()) {
                    room.getPlayer1().getObjOut().writeObject(new Message("PLAY", null));
                    room.getPlayer1().getObjOut().flush();
                    room.getPlayer2().getObjOut().writeObject(new Message("PLAY", null));
                    room.getPlayer2().getObjOut().flush();
                    Playing playing = new Playing(room, 10);
                    playing.start();
                }
            } else if (room.getPlayer2().equals(client)) {
                room.setStatusPlayer2(true);
                if (room.isStatusPlayer1() && room.isStatusPlayer2()) {
                    room.getPlayer1().getObjOut().writeObject(new Message("PLAY", null));
                    room.getPlayer1().getObjOut().flush();
                    room.getPlayer2().getObjOut().writeObject(new Message("PLAY", null));
                    room.getPlayer2().getObjOut().flush();
                    Playing playing = new Playing(room, 10);
                    playing.start();
                }
            }
            break;
        }
    }

    //xử lý yêu cầu rời phòng chơi
    public void OutRoom(ClientThread client) {
        Iterator<PlayRoom> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            PlayRoom room = iterator.next();
            if (room.getPlayer1().equals(client)) {
                // cả 2 người chơi chuyển thành trạn thái đang rảnh
                client.setStatus(true);
                room.getPlayer2().setStatus(true);
                /* Chuyển người chơi 2 thành người chơi 1 vì 1 số logic chỉ kiểm
                tra người chơi 1 có tồn tại hay không
                */
                room.setPlayer1(room.getPlayer2());
                room.setPlayer2(null);
                if (room.getPlayer1() == null) {
                    iterator.remove(); // Xóa phòng an toàn khỏi danh sách
                }
                return;
            } else if (room.getPlayer2() != null && room.getPlayer2().equals(client)) {
                room.setPlayer2(null);
                client.setStatus(true);
                room.getPlayer1().setStatus(true);
                return;
            }
        }
    }

    // Luồng thực hiện quá trình chơi
    private class Playing extends Thread {

        private PlayRoom room;
        private int countdownTime;

        public Playing(PlayRoom room, int countdownTime) {
            this.room = room;
            this.countdownTime = countdownTime;
        }

        @Override
        public void run() {
            try {
                // gửi thông điệp cho người chơi nhận biết
                room.getPlayer1().getObjOut().writeObject(new Message("READY", null));
                room.getPlayer1().getObjOut().flush();
                room.getPlayer2().getObjOut().writeObject(new Message("READY", null));
                room.getPlayer2().getObjOut().flush();
                Thread.sleep(1000);

                while (countdownTime > 0) {
                    // Gửi thời gian đếm ngược cho cả hai người chơi
                    room.getPlayer1().getObjOut().writeObject(new Message("COUNTDOWN", countdownTime));
                    room.getPlayer1().getObjOut().flush();
                    room.getPlayer2().getObjOut().writeObject(new Message("COUNTDOWN", countdownTime));
                    room.getPlayer2().getObjOut().flush();

                    // Đợi 1 giây
                    Thread.sleep(1000);
                    countdownTime--;
                }

                // gửi yêu cầu dừng đếm và yêu cầu gửi kết quả
                room.getPlayer1().getObjOut().writeObject(new Message("TIME_UP", null));
                room.getPlayer1().getObjOut().flush();
                room.getPlayer2().getObjOut().writeObject(new Message("TIME_UP", null));
                room.getPlayer2().getObjOut().flush();

                // chờ kết quả trả về
                int retries = 0;
                int maxRetries = 50;  // Giới hạn số lần kiểm tra
                while (room.getPlayer1().getChoice() == -1 || room.getPlayer2().getChoice() == -1) {
                    Thread.sleep(100);  // Chờ 100ms giữa mỗi lần kiểm tra
                    retries++;
                }

                // ghi nhận kết quả
                int player1Answer = room.getPlayer1().getChoice();
                int player2Answer = room.getPlayer2().getChoice();
                room.setStatusPlayer1(false);
                room.setStatusPlayer2(false);
                // Kiểm tra kết quả và gửi thông báo thắng thua
                String result = determineWinner(player1Answer, player2Answer);
                // update điểm
                if (result.equals("HOA")) {
                    //người chơi 1
                    String ID1 = room.getPlayer1().getPlayer().getID();
                    float score1 = room.getPlayer1().getPlayer().updateScore(0.5f);
                    room.getPlayer1().getControl().updateScore(ID1, score1);
                    //người chơi 2
                    String ID2 = room.getPlayer2().getPlayer().getID();
                    float score2 = room.getPlayer2().getPlayer().updateScore(0.5f);
                    room.getPlayer2().getControl().updateScore(ID2, score2);
                    //gui kết quả
                    room.getPlayer1().getObjOut().writeObject(new Message("RESULT", "HOA"));
                    room.getPlayer1().getObjOut().flush();
                    room.getPlayer2().getObjOut().writeObject(new Message("RESULT", "HOA"));
                    room.getPlayer2().getObjOut().flush();
                } else if (result.equals("1")) {
                    String ID1 = room.getPlayer1().getPlayer().getID();
                    float score1 = room.getPlayer1().getPlayer().updateScore(1f);
                    room.getPlayer1().getControl().updateScore(ID1, score1);
                    //gui kết quả
                    room.getPlayer1().getObjOut().writeObject(new Message("RESULT", "THANG"));
                    room.getPlayer1().getObjOut().flush();
                    room.getPlayer2().getObjOut().writeObject(new Message("RESULT", "THUA"));
                    room.getPlayer2().getObjOut().flush();
                } else {
                    String ID2 = room.getPlayer2().getPlayer().getID();
                    float score2 = room.getPlayer2().getPlayer().updateScore(1f);
                    room.getPlayer2().getControl().updateScore(ID2, score2);
                    //gui kết quả
                    room.getPlayer1().getObjOut().writeObject(new Message("RESULT", "THUA"));
                    room.getPlayer1().getObjOut().flush();
                    room.getPlayer2().getObjOut().writeObject(new Message("RESULT", "THANG"));
                    room.getPlayer2().getObjOut().flush();
                }
                // reset choice
                room.getPlayer1().setChoice(-1);
                room.getPlayer2().setChoice(-1);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức xác định người thắng
    private String determineWinner(int answer1, int answer2) {
        if (answer1 == answer2) {
            return "HOA";
        } else if ((answer1 == 1 && answer2 == 3)
                || (answer1 == 3 && answer2 == 2)
                || (answer1 == 2 && answer2 == 1)
                || (answer1 != 0 && answer2 == 0)) {
            //yeu cau nhap nhat DB
            return "1";
        } else {
            return "2";
        }
    }
}
