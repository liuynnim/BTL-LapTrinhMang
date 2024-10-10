/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.client.control;

import com.mycompany.shared.Message;
import com.mycompany.client.model.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author quang
 */
public class ClientSocket {

    private Socket clientSocket;
    private Player player;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;
    private final String serverHost = "127.0.0.1";
    private final int serverPort = 8080;

    public ClientSocket() {
    }

    public Socket requestConnection() throws IOException {
        try {
            clientSocket = new Socket(serverHost, serverPort);
            this.objOut = new ObjectOutputStream(clientSocket.getOutputStream());
            this.objIn = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            return null;
        }
        return clientSocket;
    }

    public boolean Login(String username, String password) throws IOException, ClassNotFoundException {
        HashMap<String, String> data = new HashMap<>();

        data.put("username", username);
        data.put("password", password);
        try {
            objOut.writeObject(new Message("LOGIN", data));
            objOut.flush();
            System.out.println("thong diep gui het roi");
        } catch (IOException e) {
        }
        try {
            Message mess = (Message) objIn.readObject();
            if ("LOGIN_FALSE".equals(mess.getType())) {
                return false;
            } else {
                this.player = (Player) mess.getContent();
            }
        } catch (IOException | ClassNotFoundException e) {
        }
        return true;
    }
}
