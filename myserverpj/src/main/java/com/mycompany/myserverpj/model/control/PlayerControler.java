/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model.control;

import com.mycompany.myserverpj.model.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author quang
 */
// xu ly tac vu
// login - y tuong: tim doi tuong trong DB de xu ly hien thi sau, neu ko thay
// thi return Login FALSE
public class PlayerControler {
    
    //lay doi tuong player
    public Player getPlayer(String username, String password) {
        try (Connection connection = ConnectDB.getConnection()) {
            String query = "SELECT * FROM player WHERE username = ? "
                    + "AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Player player = new Player(
                    resultSet.getNString(1), 
                    resultSet.getNString(2), 
                    resultSet.getNString(3), 
                    resultSet.getNString(4), 
                    resultSet.getNString(5), 
                    resultSet.getInt(6)
                    );
            return player;    
        } catch (Exception e) {
        }
        return null;
    }
}
