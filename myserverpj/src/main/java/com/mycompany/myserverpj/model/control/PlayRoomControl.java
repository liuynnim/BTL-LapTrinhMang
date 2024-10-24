/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model.control;

import com.mycompany.myserverpj.model.ClientThread;
import com.mycompany.myserverpj.model.PlayRoom;
import com.mycompany.myserverpj.server.Server;
import com.mycompany.shared.Message;
import com.mycompany.shared.Player;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author quang
 */
public class PlayRoomControl {

    private Server server;
    private ArrayList<PlayRoom> rooms;

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
            if (r.getMaPhong() == maPhong) {
                maPhong++;
            } else {
                break;
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
            if (room.getPlayer1().getPlayer().equals(playerInvite)) {
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
                break;
            }
        }
    }

    public void respAcceptInvite(Message message, ClientThread client) throws IOException {
        String inviter = (String) message.getContent();

        for (PlayRoom room : rooms) {
            if (room.getPlayer1().getPlayer().getPlayerName().equals(inviter)) {
                if (room.getPlayer2() != null) {
                    room.setPlayer2(client);
                    // gửi thông tin người chơi 2 về cho người chơi 1 để hiển thị
                    room.getPlayer1().getObjOut().writeObject(
                            new Message("ACCEPTER", room.getPlayer2().getPlayer())
                    );
                }
                else { //chua xử lý
                    client.getObjOut().writeObject(
                            new Message("ROOM_FULL", null));
                }
            }
        }
    }
}
