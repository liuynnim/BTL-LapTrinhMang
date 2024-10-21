/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model;

import java.util.ArrayList;
import com.mycompany.myserverpj.model.control.PlayRoomControl;
/**
 *
 * @author quang
 */
public class PlayRoom {
    private int maPhong;
    private ArrayList<ClientThread> players;

    public PlayRoom(int maPhong) {
        this.maPhong = maPhong;
    }
    
    public boolean addPlayer(ClientThread player) {
        if(players.size()<2)
        {
            players.add(player);
            return true;
        }
        return false;
    }
}
