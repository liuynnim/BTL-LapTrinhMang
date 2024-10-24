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
    private ClientThread player1;
    private ClientThread player2;

    public PlayRoom(int maPhong, ClientThread player1) {
        this.maPhong = maPhong;
        this.player1 = player1;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public ClientThread getPlayer1() {
        return player1;
    }

    public void setPlayer1(ClientThread player1) {
        this.player1 = player1;
    }

    public ClientThread getPlayer2() {
        return player2;
    }

    public void setPlayer2(ClientThread player2) {
        this.player2 = player2;
    }
    
}
