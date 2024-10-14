/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model;

import com.mycompany.shared.Message;
import com.mycompany.shared.Player;
import com.mycompany.myserverpj.model.control.PlayerControler;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            control = new PlayerControler(objOut, objIn);
            while (true) {
                // Chờ thông điệp từ client
                Message message = (Message) objIn.readObject();
                System.out.println(message.getType()+" "+message.getContent().toString());
                // Xử lý yêu cầu đăng nhập
                if (message.getType().equals("LOGIN")) {
                    player = control.login(message);
                }
                else if(message.getType().equals("CHECK_DUP")) {
                    control.checkDuplicate(message);
                }
                else if(message.getType().equals("REGISTER")) {
                    control.register(message);
                }
                
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (SQLException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //login 
   

}
