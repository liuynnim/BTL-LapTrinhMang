package com.mycompany.myserverpj.server;

import com.mycompany.myserverpj.model.ClientThread;
import com.mycompany.myserverpj.model.control.ConnectDB;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
    private ArrayList<ClientThread> listClient;
    final private int port = 8080;

    public void openServer() {
        try {
            this.server = new ServerSocket(port);
            listClient = new ArrayList<>();
            con = new ConnectDB();
            while (true) {
                listenning();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenning() throws IOException {
        try {
            ClientThread client = new ClientThread(server.accept());
            System.out.println("1 nguoi da dang nhap");
            listClient.add(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
