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
import java.util.ArrayList;

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
        PlayRoom room = new PlayRoom(rooms.size()+1);
        room.addPlayer(player);
        if(rooms.add(room)) {
            try {
                player.getObjOut().writeObject(new Message("NEW_ROOM",null));
            } catch (IOException e) {
            }
        }
    }
}
