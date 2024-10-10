/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myserverpj.model.control.PlayerControler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author quang
 */
public class ClientThread extends Thread{
    private Socket clientSocket;
    private Player player;
    private PlayerControler control;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;
    private boolean status;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.status = true;
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
            objIn = new ObjectInputStream(clientSocket.getInputStream());
            objOut = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectMapper objectMapper = new ObjectMapper();
            Message message = (Message) objIn.readObject();
            // login
            if(message.getType().equals("LOGIN")) {
                Map<String, String> JSON;
                JSON = objectMapper.(message.getContent(),
                        new TypeReference<Map<String, String>>() {});
                control = new PlayerControler();
                control.getPlayer(JSON.get("username"), 
                        JSON.get("password"));
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
    
    
}
