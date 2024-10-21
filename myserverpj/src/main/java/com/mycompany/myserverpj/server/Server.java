package com.mycompany.myserverpj.server;

import com.mycompany.myserverpj.model.ClientThread;
import com.mycompany.myserverpj.model.PlayRoom;
import com.mycompany.myserverpj.model.control.ConnectDB;
import com.mycompany.myserverpj.model.control.PlayRoomControl;
import com.mycompany.shared.Player;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author quang
 */
public class Server {

    private ServerSocket server = null;
    private ConnectDB con = null;
    private List<ClientThread> listClient;
    private List<Player> listPlayer;
    private PlayRoomControl controlRoom;
    final private int port = 8080;

    public void openServer() {
        try {
            this.server = new ServerSocket(port);
            listClient = new CopyOnWriteArrayList<>();
            listPlayer = new CopyOnWriteArrayList<>();
            con = new ConnectDB();
            while (true) {
                listenning();
            }
        } catch (IOException e) {
        }
    }

    public void listenning() throws IOException {
        try {
            ClientThread client = new ClientThread(server.accept(), this);
            System.out.println("1 nguoi da dang nhap");
            listClient.add(client);
        } catch (IOException e) {
        }
    }

    // quan lý danh sach Thread và danh sách người chơi
    public void removePlayer(Player player, ClientThread client) {
        listPlayer.remove(player);
        listClient.remove(client);
        updateAllPlayers();
    }

    public void updateListPlayer(Player player) {
        if (player != null) {
            listPlayer.add(player);
            updateAllPlayers();
        }
    }

    public void updateAllPlayers() {
        // Gọi hàm cập nhật danh sách người chơi cho tất cả các client
        for(Player pl : listPlayer)
            System.out.println(pl.getPlayerName());
        for (ClientThread client : listClient) {
            client.updatePlayerList(listPlayer);
        }
    }

    public void removeClient(ClientThread client) {
        listClient.remove(client);
        // Cập nhật lại danh sách người chơi sau khi client rời đi
        updateAllPlayers();
    }

    // quan lý phòng chơi
    
    public List<Player> getListPlayer() {
        return listPlayer;
    }

    public PlayRoomControl getControlRoom() {
        return controlRoom;
    }
    
    
}
