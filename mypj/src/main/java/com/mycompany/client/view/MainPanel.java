/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.client.view;

import com.mycompany.client.control.ClientSocket;
import com.mycompany.client.model.ClientState;
import com.mycompany.shared.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author quang
 */
public class MainPanel extends javax.swing.JPanel {

    /**
     * Creates new form MainPanel
     *
     * @param client
     */
    public MainPanel(ClientSocket client) {
        initComponents();
        this.client = client;
        listPlayer = new ArrayList<>();
        TakeInfo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jTextArea1ComponentHidden(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jScrollPane3.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList2);

        jButton1.setText("Tạo Phòng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Bảng Xếp Hạng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(28, 28, 28)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTextArea1ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1ComponentHidden

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            client.newRoom();
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (client.getState() != ClientState.NEW_ROOM) {
            try {
                Thread.sleep(50); // Giảm thời gian chờ
            } catch (InterruptedException e) {
            }
        }
        playRoom = new PlayRoom(
                (JFrame) this.frame,
                listPlayer,
                client,
                this,
                (String) client.getData().get("maPhong")
        );
        playRoom.setListPlayer(listPlayer);
        playRoom.setBounds(0, 0, 400, 300);
        playRoom.setVisible(true);
        this.frame.remove(this);
        this.frame.add(playRoom);
        this.frame.revalidate();
        this.frame.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TakeInfo() {
        // hiển thị 3 người chơi có điểm cao nhất
        //tạo và lấy data từ server về
        HashMap<String, HashMap<String, String>> data;
        client.getThreeHighest();
        while (client.getState() != ClientState.THREE_HIGHEST) {
            try {
                Thread.sleep(50); // Giảm thời gian chờ
            } catch (InterruptedException e) {
            }
        }
        data = (HashMap<String, HashMap<String, String>>) client.getData();
        // tạo format để hiển thị 
        String format = "Hạng %d: %-15s%s%s\n";
        int totalLength = 50; // Tổng chiều dài dải ký tự
        int pointLength = 10;  // Giữ một khoảng đủ cho điểm (10 ký tự)
        String player1 = data.get("1").get("playerName");
        String score1 = data.get("1").get("score");

        String player2 = data.get("2").get("playerName");
        String score2 = data.get("2").get("score");

        String player3 = data.get("3").get("playerName");
        String score3 = data.get("3").get("score");

        // loại bỏ ScrollPanel của phần hiển thị 3 người chơi cao điểm và thông tin các nhân
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        //tạo kích thước của phần thông tin 3 người chơi cao điểm
        jTextArea1.setPreferredSize(new Dimension(280, 86));
        //hiển thị thông tin 3 người chơi
        jTextArea1.setText(
                String.format(
                        format,
                        1,
                        player1,
                        " ".repeat(totalLength - player1.length() - pointLength),
                        score1)
                + "\n"
                + String.format(
                        format,
                        2,
                        player2,
                        " ".repeat(totalLength - player2.length() - pointLength),
                        score2)
                + "\n"
                + String.format(
                        format,
                        3,
                        player3,
                        " ".repeat(totalLength - player3.length() - pointLength),
                        score3)
        );
        // hiển thị thông tin của người chơi
        setInfoPlayer();
        getListPlayer();
    }

    public void showInviteDialog(String inviter, String maPhong) {
        // Tạo cửa sổ nhỏ (JDialog)
        JDialog inviteDialog = new JDialog(frame, "Mời chơi", true);
        inviteDialog.setSize(200, 100);
        inviteDialog.setLocationRelativeTo(this);

        JTextArea infoInviter = new JTextArea();
        infoInviter.setText("Người chơi " + inviter + " mời bạn chơi!");
        inviteDialog.add(infoInviter, BorderLayout.CENTER);
        // Thêm nút "Mời" vào dialog
        JButton acceptBt = new JButton("Chấp Nhận");
        acceptBt.addActionListener((ActionEvent e) -> {
            client.acceptInvite(inviter);
            playRoom = new PlayRoom(
                    (JFrame) this.frame,
                    listPlayer,
                    client,
                    this,
                    maPhong);

            inviteDialog.dispose();
        });

        inviteDialog.add(acceptBt, BorderLayout.SOUTH);

        inviteDialog.setVisible(true);
    }

    // hàm hiển thị thông tin cá nhân
    // viết riêng vì cập nhật lại môi khi điểm thay đổi
    public void setInfoPlayer() {
        String myName = client.getPlayer().getPlayerName();
        client.getRank(client.getPlayer().getID());
        HashMap<String, String> data;

        while (client.getState() != ClientState.GET_RANK) {
            try {
                Thread.sleep(50); // Giảm thời gian chờ
            } catch (InterruptedException e) {
            }
        }
        data = (HashMap<String, String>) client.getData();
        String score = data.get("score");
        String rank = data.get("rank");
        jTextArea2.setText(
                "Tên người chơi:\n" + myName
                + "\n" + "score: " + score
                + "\nRank: " + rank);
    }

    private void getListPlayer() {
        client.getListPlayers();
        while (client.getState() != ClientState.LIST_PLAYER) {
            try {
                Thread.sleep(50); // Giảm thời gian chờ
            } catch (InterruptedException e) {
            }
        }
        setListPlayer((HashMap<String, HashMap<String, String>>) client.getData());
    }

    // Hiển thị danh sách người chơi
    public void setListPlayer(HashMap<String, HashMap<String, String>> data) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        HashMap<String, Boolean> playerStatusMap = new HashMap<>(); // Lưu tên người chơi và trạng thái

        // Giả sử client.getListPlayerData() trả về kiểu HashMap<String, HashMap<String, String>>
        HashMap<String, HashMap<String, String>> playerDataMap = data;
        listPlayer.clear();
        if (playerDataMap != null) {
            for (Map.Entry<String, HashMap<String, String>> entry : playerDataMap.entrySet()) {
                HashMap<String, String> playerInfo = entry.getValue();

                String playerName = playerInfo.get("playerName");
                String statusStr = playerInfo.get("status");
                boolean status = Boolean.parseBoolean(statusStr);

                // Bỏ qua nếu là chính người chơi hiện tại
                if (playerName.equals(client.getPlayer().getPlayerName())) {
                    continue;
                }
                if (status) {
                    listPlayer.add(playerName);
                }
                listModel.addElement(playerName); // Thêm tên vào listModel
                playerStatusMap.put(playerName, status); // Thêm trạng thái vào map
            }
            if (playRoom != null) {
                playRoom.setListPlayer(listPlayer);
            }
        }

        // Cập nhật JList
        jList1.setModel(listModel);
        jList1.setCellRenderer(new CustomListRenderer(playerStatusMap)); // Truyền bản đồ trạng thái vào renderer
        jList1.revalidate();
        jList1.repaint();
        jScrollPane3.revalidate();
        jScrollPane3.repaint();
    }

    public class CustomListRenderer extends DefaultListCellRenderer {

        private final Map<String, Boolean> playerStatusMap; // Bản đồ lưu tên và trạng thái

        public CustomListRenderer(Map<String, Boolean> playerStatusMap) {
            this.playerStatusMap = playerStatusMap;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            // Sử dụng mặc định để render phần tử
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Kiểm tra nếu value là String
            if (value instanceof String playerName) {
                // Lấy trạng thái từ Map bằng playerName
                Boolean status = playerStatusMap.get(playerName);

                // Đặt tên người chơi cho label
                label.setText(playerName);

                // Điều chỉnh màu nền dựa trên trạng thái
                if (!isSelected && status != null) {
                    if (status) {
                        label.setBackground(Color.GREEN);
                    } else {
                        label.setBackground(Color.RED);
                    }
                }
            }

            return label;
        }
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setPlayRoomAnotherPlayer(Player anotherPlayer) {
        playRoom.setAnotherPlayer(anotherPlayer);
    }

    public void showPlayRoom() {
        playRoom.setBounds(0, 0, 400, 300);
        playRoom.setVisible(true);
        this.frame.remove(this);
        this.frame.add(playRoom);
        this.frame.revalidate();
        this.frame.repaint();
    }

    //trả về playRoom để xử lý bên client ngắn hơn
    public PlayRoom getPlayRoom() {
        return playRoom;
    }

    private JFrame frame;
    private final ClientSocket client;
    private PlayRoom playRoom;
    private ArrayList<String> listPlayer;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
