/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.client.view;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import com.mycompany.test.student;
import java.awt.Container;
import java.text.DecimalFormat;
import javax.swing.JFrame;

/**
 *
 * @author quang
 */
public class searchByName extends javax.swing.JPanel {

    /**
     * Creates new form search1
     */
    public searchByName() {
        initComponents();
        initAutocomplete();
        df = new DecimalFormat("#.00");
    }

    /**
     * This method is called from within the constructor to initialize the
     * form.Đấ WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Tên:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        listModel = new DefaultListModel();
        jList2.setModel(listModel);
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jScrollPane3.setViewportView(jScrollPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String input = jTextField1.getText();
        listModel.clear();
        if (!input.isEmpty()) {
            for (student stud : stu) {
                if (stud.getHoTen().toLowerCase().startsWith(input.toLowerCase())) {
                    listModel.addElement(stud.getMaSV() + "-" + stud.getHoTen() + "----" + df.format(stud.getGPA()));
                }
            }
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        if (!evt.getValueIsAdjusting()) { // Chỉ xử lý sự kiện khi không còn thay đổi
            JList<String> list = (JList<String>) evt.getSource(); // Lấy JList
            String selected = list.getSelectedValue().substring(0, 10); // Lấy giá trị đã chọn

            if (selected != null) {
                // Mở giao diện chỉnh sửa
                student tmp = null;
                for (student s : stu) {
                    if (selected.equals(s.getMaSV())) {
                        tmp = s;
                        break;
                    }
                }
                if (tmp != null) {
                    editStudentInfo editPanel = new editStudentInfo(tmp);
                    editPanel.setBounds(0, 150, 400, 250);
                    Container parent = this.getParent();
                    parent.remove(this);
                    parent.add(editPanel);
                    parent.revalidate();
                    parent.repaint();
                }
            }
        }
    }//GEN-LAST:event_jList2ValueChanged
    private void initAutocomplete() {
        listModel.clear();  // Xóa danh sách trước khi thêm tên mới
        if (stu != null) { // Đảm bảo rằng names không null
            for (student stud : stu) {
                listModel.addElement(stud.getMaSV() + "-" + stud.getHoTen() + "----" + df.format(stud.getGPA())); // Thêm từng tên vào danh sách
            }
        }
    }

    public void getStudents(ArrayList<student> data) {
        stu = data;
        initAutocomplete();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private DefaultListModel listModel;
    private ArrayList<student> stu;
    private DecimalFormat df;
}