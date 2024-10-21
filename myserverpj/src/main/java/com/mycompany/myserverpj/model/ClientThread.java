/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model;

import com.mycompany.shared.Message;
import com.mycompany.shared.Player;
import com.mycompany.myserverpj.model.control.PlayerControler;
import com.mycompany.myserverpj.server.Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quang
 */
public class ClientThread extends Thread {

    private final Socket clientSocket;
    private final Server server;
    private PlayerControler control;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;
    private Player player;
    private boolean status;

    public ClientThread(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.status = true;
        start();
    }

    public ObjectOutputStream getObjOut() {
        return objOut;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void run() {
        try {
            objOut = new ObjectOutputStream(clientSocket.getOutputStream());
            objIn = new ObjectInputStream(clientSocket.getInputStream());
            control = new PlayerControler(objOut);
            while (true) {
                // Chờ thông điệp từ client
                Message message = (Message) objIn.readObject();
                System.out.println(message.getType() + " " + (message.getContent() != null ? message.getContent().toString() : "Content is null"));
                // Xử lý yêu cầu đăng nhập
                if (message.getType().equals("LOGIN")) {
                    player = control.login(message);
                    if (player != null) {
                        boolean check = true;
                        for (Player pl : server.getListPlayer()) {
                            if (player.getID().equals(pl.getID())) {
                                objOut.writeObject(new Message("LOGIN_FAILED", null));
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            objOut.writeObject(new Message("LOGIN_SUCCESS", player));
                            player.setStatus(status);
                            Thread.sleep(1000);
                            server.updateListPlayer(player);
                        }
                    } else {
                        objOut.writeObject(new Message("LOGIN_FAILED", null));
                    }
                    objOut.flush();
                } else if (message.getType().equals("CHECK_DUP")) {
                    control.checkDuplicate(message);
                } else if (message.getType().equals("REGISTER")) {
                    control.register(message);
                } else if (message.getType().equals("THREE_HIGHEST")) {
                    control.getThreeHighest();
                } else if (message.getType().equals("GET_RANK")) {
                    control.getRankPlayer(message);
                } else if (message.getType().equals("DISCONNEC")) {
                    closeConnection();
                } else if(message.getType().equals("NEW_ROOM")) {
                    server.getControlRoom().reqNewRoom(this);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (SQLException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // cập nhật danh sách người chơi
    public void updatePlayerList(List<Player> listPlayer) {
        try {
            objOut.writeObject(new Message("LIST_PLAYER", new ArrayList<>(listPlayer)));
            objOut.flush();
        } catch (IOException e) {
        }
    }

    // đóng kết nối
    private void closeConnection() {
        try {
            try (clientSocket) {
                server.removePlayer(player, this);
            }
            objOut.close();
            objIn.close();
        } catch (IOException e) {
        }
    }
}
