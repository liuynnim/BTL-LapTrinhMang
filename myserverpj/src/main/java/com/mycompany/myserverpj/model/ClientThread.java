/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model;

import com.mycompany.shared.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myserverpj.model.control.PlayerControler;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author quang
 */
public class ClientThread extends Thread {

    private final Socket clientSocket;
    private Player player;
    private PlayerControler control;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;
    private boolean status;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.status = true;
        start();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void run() {
        try {
            objOut = new ObjectOutputStream(clientSocket.getOutputStream()); 
            objIn = new ObjectInputStream(clientSocket.getInputStream()); 
            while (true) {
                // Chờ thông điệp từ client
                Message message = (Message) objIn.readObject();
                System.out.println("vẫn ok");
                // Xử lý yêu cầu đăng nhập
                if (message.getType().equals("LOGIN")) {
                    HashMap<String, String> data
                            = (HashMap<String, String>) message.getContent();
                    control = new PlayerControler();
                    this.player = control.getPlayer(data.get("username"),
                            data.get("password"));
                    if (player != null) {
                        // Gửi phản hồi đăng nhập thành công
                        objOut.writeObject(new Message("LOGIN_SUCCESS", player));
                    } else {
                        // Gửi phản hồi đăng nhập thất bại
                        objOut.writeObject(new Message("LOGIN_FAILED", null));
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

}
