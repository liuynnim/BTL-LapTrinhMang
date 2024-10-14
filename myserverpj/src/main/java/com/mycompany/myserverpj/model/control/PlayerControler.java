/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myserverpj.model.control;

import com.mycompany.shared.Message;
import com.mycompany.shared.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author quang
 */
// xu ly tac vu
// login - y tuong: tim doi tuong trong DB de xu ly hien thi sau, neu ko thay
// thi return Login FALSE
public class PlayerControler {

    private final ObjectOutputStream objOut;
    private final ObjectInputStream objIn;

    public PlayerControler(ObjectOutputStream objOut, ObjectInputStream objIn) {
        this.objOut = objOut;
        this.objIn = objIn;
    }

    // các hàm xử lý sự kiện client
    //xử lý login
    public Player login(Message message) throws IOException {
        HashMap<String, String> data
                = (HashMap<String, String>) message.getContent();
        Player player = getPlayer(data.get("username"),
                data.get("password"));
        if (player != null) {
            // Gửi phản hồi đăng nhập thành công
            objOut.writeObject(new Message("LOGIN_SUCCESS", player));
        } else {
            // Gửi phản hồi đăng nhập thất bại
            objOut.writeObject(new Message("LOGIN_FAILED", null));
        }
        objOut.flush();
        return null;
    }

    public boolean checkDuplicate(Message message) throws IOException {
        HashMap<String, String> data
                = (HashMap<String, String>) message.getContent();
        Set<String> key = data.keySet();
        String field = null;
        for (String element : key) {
            field = element;
            break;
        }
        if ("USERNAME".equals(field)) {
            if (checkDuplicates("username", data.get(field))) {
                System.out.println("trung roi");
                objOut.writeObject(new Message("YES", null));
            } else {
                System.out.println("khong trung");
                objOut.writeObject(new Message("NO", null));
            }
        } else if ("EMAIL".equals(field)) {
            if (checkDuplicates("email", data.get(field))) {
                objOut.writeObject(new Message("YES", null));
            } else {
                objOut.writeObject(new Message("NO", null));
            }
        } else if ("PLAYER_NAME".equals(field)) {
            if (checkDuplicates("playerName", data.get(field))) {
                objOut.writeObject(new Message("YES", null));
            } else {
                objOut.writeObject(new Message("NO", null));
            }
        }
        return true;
    }

    public boolean register(Message message) throws SQLException {
        HashMap<String, String> data
                = (HashMap<String, String>) message.getContent();
        return register(
                data.get("username"),
                data.get("password"),
                data.get("email"),
                data.get("playerName")
        );
    }

    //các hàm DAO làm việc với DB
    //lay doi tuong player
    private Player getPlayer(String username, String password) {
        try (Connection connection = ConnectDB.getConnection()) {
            String query = "SELECT * FROM player WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Player player = new Player(
                        resultSet.getString("ID"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("playerName"),
                        resultSet.getString("email"),
                        resultSet.getInt("score")
                );
                return player;
            } else {
                return null; // Không tìm thấy người chơi
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông tin lỗi
        }
        return null; // Nếu có lỗi xảy ra
    }

    //lay thong tin 3 player cao diem nhat
    private HashMap<String, HashMap<String, String>> getHighest() {
        HashMap<String, HashMap<String, String>> rs = new HashMap<>();
        try (Connection connection = ConnectDB.getConnection()) {
            String query = "SELECT * FROM player ORDER BY score DESC LIMIT 3";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            int rank = 1;
            while (resultSet.next()) {
                HashMap<String, String> playerData = new HashMap<>();
                playerData.put(
                        "playerName",
                        resultSet.getString("playerName")
                );
                rs.put(String.valueOf(rank), playerData);
                rank++;
            }
            return rs;
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông tin lỗi
        }
        return null;
    }

    // dang ky
    private boolean register(String username, String password, String email,
            String playerName) throws SQLException {
        try (Connection connection = ConnectDB.getConnection()) {
            String query = "INSERT INTO player "
                    + "(username, password, playerName, email) "
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, playerName);
            statement.setString(4, email);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // kiểm tra trung lặp
    private boolean checkDuplicates(String columnsName, String content) {
        try (Connection connection = ConnectDB.getConnection()) {
            String query = "SELECT * FROM player WHERE " + columnsName + " = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, content.trim());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
