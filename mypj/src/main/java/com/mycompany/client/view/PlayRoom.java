/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.client.view;

import com.mycompany.client.control.ClientSocket;
import com.mycompany.shared.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author quang
 */
public final class PlayRoom extends javax.swing.JPanel {

    /**
     * Creates new form PlayRoom
     *
     * @param frame
     * @param list
     * @param client
     * @param mainPanel
     * @param maPhong
     */
    public PlayRoom(JFrame frame, ArrayList<String> list, ClientSocket client, MainPanel mainPanel, String maPhong) {
        initComponents();
        this.mainPanel = mainPanel;
        this.frame = frame;
        this.listPlayer = new ArrayList<>();
        this.listPlayer.addAll(list);
        this.client = client;
        this.maPhong = maPhong;
        listModel = new DefaultListModel<>();
        playerList = new JList<>(listModel);
        statusReady = false;
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        setThisPlayerInfo();
        while (maPhong.length() < 3) {
            maPhong = "0" + maPhong;
        }
        jLabel1.setText("Phòng " + maPhong);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel1.setText("Phòng 001");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("CHUẨN BỊ");

        jButton1.setText("KÉO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("BÚA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("BAO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Mời");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Thoát");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Chơi");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton5)
                .addGap(97, 97, 97)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(jButton4))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6)
                            .addComponent(jButton2))
                        .addGap(33, 33, 33)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(46, 46, 46))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        choice = 1;
        changeColorButton(jButton1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        choice = 2;
        changeColorButton(jButton2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        showInviteDialog();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // them yeu cau thoat ra de nguoi con lai cap nhat thong tin
        client.outRoom();
        frame.remove(this);
        frame.add(mainPanel);
        frame.revalidate();
        frame.repaint();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(!statusReady) {
            client.ready(maPhong);
            statusReady = true;
            jButton6.setBackground(new Color(105, 105, 105));
        } else {
            client.noready(maPhong);
            statusReady = false;
            jButton6.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        choice = 3;
        changeColorButton(jButton3);
    }//GEN-LAST:event_jButton3ActionPerformed

    public void setThisPlayerInfo() {
        jTextArea1.setText(
                client.getPlayer().getPlayerName()
                + "\nScore: "
                + String.valueOf(client.getPlayer().getScore())
        );
    }
    
    
    // hiển thị bảng danh sách người chơi và mời chơi
    private void showInviteDialog() {
        // Tạo cửa sổ nhỏ (JDialog)
        JDialog inviteDialog = new JDialog(frame, "Chọn người chơi để mời", true);
        inviteDialog.setSize(300, 400);
        inviteDialog.setLocationRelativeTo(this);

        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Cho phép chọn một người chơi

        JScrollPane scrollPane = new JScrollPane(playerList);
        inviteDialog.add(scrollPane, BorderLayout.CENTER);
        playerList.revalidate();
        playerList.repaint();
        // Thêm nút "Mời" vào dialog
        JButton sendInviteButton = new JButton("Mời");
        sendInviteButton.addActionListener((var e) -> {
            String selectedPlayer = playerList.getSelectedValue();  // Lấy người chơi được chọn
            if (selectedPlayer != null) {
                client.sendInvite(selectedPlayer);
                inviteDialog.dispose();  // Đóng cửa sổ nhỏ sau khi mời
            } else {
                JOptionPane.showMessageDialog(inviteDialog, "Vui lòng chọn một người chơi!");
            }
        });

        inviteDialog.add(sendInviteButton, BorderLayout.SOUTH);

        inviteDialog.setVisible(true);
    }

    public void setListPlayer(ArrayList<String> listPlayer) {
        this.listPlayer.clear();
        System.out.println(this.listPlayer.size());
        this.listPlayer.addAll(listPlayer);
        System.out.println(this.listPlayer.size());
        updatePlayerListModel();
    }

    private void updatePlayerListModel() {
        if(listModel != null) listModel.clear();  // Xóa dữ liệu cũ
        for (String player : listPlayer) {
            listModel.addElement(player);  // Thêm tên người chơi mới vào danh sách
        }
        playerList.revalidate();
        playerList.repaint();
    }

    public void setAnotherPlayer(Player anotherPlayer) {
        this.anotherPlayer = anotherPlayer;
        if (this.anotherPlayer != null) {
            jLabel2.setText("SẴN SÀNG");
            jTextArea2.setText(
                    this.anotherPlayer.getPlayerName()
                    + "\nScore: "
                    + String.valueOf(this.anotherPlayer.getScore())
            );
        }
    }
    
    // Các thao tác chơi game
    
    // ẩn nút chơi khi bắt đầu chơi và thay đổi Label thành "Bắt Đầu"
    public void hidePlayButtonAndChangeLabel() {
        jButton6.setVisible(false);
        jLabel2.setText("Bắt Đầu");
    }
    
    //Thay đổi hiển thị bộ đếm {
    public void chanceTime(String time) {
        jLabel2.setText(time);
    }
    
    //ghi nhân lựa chọn
    
    //thay đổi màu lựa chọn
    //tham số là nút vừa chọn
    //thực hiện đổi màu các nút còn lại khac mau voi nut dang chon
    
    private void changeColorButton(JButton button) {
        jButton1.setBackground(Color.WHITE);
        jButton2.setBackground(Color.WHITE);
        jButton3.setBackground(Color.WHITE);
        button.setBackground(new Color(105, 105, 105));
    }
    
    // lấy ra lựa chọn
    /*
    1 - kéo
    2 - búa
    3 - bao
    */
    
    public int getChoice() {
        return choice;
    }
    //update sau khi nhận kết quả
    public void updateAnotherPlayerScore(float score) {
        anotherPlayer.updateScore(score);
        setAnotherPlayer(anotherPlayer);
    }
    // reset lại phòng
    public void resetRoom() {
        // chuyển trạng thái sẵn sàng về false
        statusReady = false;
        // hiển thị lại nút "Chơi"
        jButton6.setVisible(true);
        jButton6.setBackground(Color.WHITE);
        //reset màu nút
        jButton1.setBackground(Color.WHITE);
        jButton2.setBackground(Color.WHITE);
        jButton3.setBackground(Color.WHITE);
    }
    
    private JList<String> playerList;
    private boolean statusReady;
    private String maPhong;
    private final ClientSocket client;
    private Player anotherPlayer;
    private ArrayList<String> listPlayer;
    private final JFrame frame;
    private DefaultListModel<String> listModel;
    private final MainPanel mainPanel;
    private int choice;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

    private void sendInviteToPlayer(String selectedPlayer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
