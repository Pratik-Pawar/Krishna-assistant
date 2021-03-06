/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev;

import database.DBmanager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class DevMode extends javax.swing.JFrame {

    /**
     * Inner classes for to make disabling some elements when there is change in
     * data of DataEditorPanel
     */
    class SingularChangeListener implements ChangeListener {

        @Override
        public void onChange() {
            singularUpdateButton.setEnabled(true);
        }

    }

    class SimpleGroupChangeListener implements ChangeListener {

        public void onChange() {
            simpleGroupList.setEnabled(false);
            simpleGroupAdd.setEnabled(false);
            simpleGroupRename.setEnabled(false);
            simpleGroupList.repaint();
            simpleGroupUpdate.setEnabled(true);
            simpleGroupUpdate.repaint();
        }
    }

    class arguGroupChangeListener implements ChangeListener {

        public void onChange() {
            arguGroupList.setEnabled(false);
            arguGroupAdd.setEnabled(false);
            arguGroupRename.setEnabled(false);
            arguGroupList.repaint();
            arguGroupUpdate.setEnabled(true);
            arguGroupUpdate.repaint();
        }
    }

    private database.DBmanager db;
    private int simpleGroupId;
    private int arguGroupId;
    private arguGroupChangeListener arguGroupCL = new arguGroupChangeListener();
    private SimpleGroupChangeListener simpleGroupCL = new SimpleGroupChangeListener();

    /**
     * Creates new form Dev
     */
    public DevMode() {
        initComponents();

        singularOkImg.setVisible(false);
        simpleGroupOkImg.setVisible(false);
        arguGroupOkImg.setVisible(false);

        SingularPanle.initFromDB("SELECT cmd.data, reply.data , group_.group_id AS id FROM cmd JOIN group_ JOIN reply WHERE cmd.group_id=group_.group_id AND cmd.group_id =reply.group_id AND group_.type=0;");
        initSimpleGroupList();
        initArguGroupList();
    }

    private void initSimpleGroupList() {
        simpleGroupList.removeAllItems();
        try {
            ResultSet rs = DBmanager.exeQuery("SELECT name From group_ WHERE type=1");
            while (rs.next()) {
                simpleGroupList.addItem(rs.getString(1));
            }

            if (simpleGroupList.getItemCount() == 0) //If list is empty then disable simpleGroup components
            {
                simpleGroupSetEnabled(false);
            } else {
                simpleGroupSetEnabled(true);        //else enable simpleGroup components
            }
        } catch (SQLException ex) {
            Logger.getLogger(DevMode.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initArguGroupList() {

        try {

            arguGroupList.removeAllItems();
            ResultSet rs = DBmanager.exeQuery("SELECT name From group_ WHERE type=2");
            while (rs.next()) {
                arguGroupList.addItem(rs.getString(1));
            }

            if (arguGroupList.getItemCount() == 0) //If list is empty then disable arguGroup components
            {
                arguGroupSetEnabled(false);
            } else {
                arguGroupSetEnabled(true);        //else enable arguGroup components
            }

        } catch (SQLException ex) {
            Logger.getLogger(DevMode.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        SingularPanle = new dev.DataEditorPanel("Commands & Replys",2,new SingularChangeListener());
        singularUpdateButton = new component.BlueButton();
        singularOkImg = new javax.swing.JLabel();
        singularReset = new component.BlueButton();
        jPanel1 = new javax.swing.JPanel();
        groupedReply = new dev.DataEditorPanel("Replys",1,simpleGroupCL);
        groupedCmd = new dev.DataEditorPanel("Commands",1,simpleGroupCL);
        simpleGroupList = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        simpleGroupUpdate = new component.BlueButton();
        simpleGroupAdd = new component.BlueButton();
        simpleGroupDelete = new component.BlueButton();
        simpleGroupRename = new component.BlueButton();
        simpleGroupOkImg = new javax.swing.JLabel();
        simpleGroupReset = new component.BlueButton();
        jPanel3 = new javax.swing.JPanel();
        arguAndAction = new dev.DataEditorPanel("Argument & Action",2,arguGroupCL);
        arguReply = new dev.DataEditorPanel("Replys",1,arguGroupCL);
        arguCmd = new dev.DataEditorPanel("Commands",1,arguGroupCL);
        arguGroupList = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        arguGroupUpdate = new component.BlueButton();
        arguGroupAdd = new component.BlueButton();
        arguGroupDelete = new component.BlueButton();
        arguGroupRename = new component.BlueButton();
        arguGroupOkImg = new javax.swing.JLabel();
        blueButton1 = new component.BlueButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Devlopement mode");
        setBackground(new java.awt.Color(22, 3, 3));
        setMinimumSize(new java.awt.Dimension(1250, 700));

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel2.setLayout(null);

        SingularPanle.setPreferredSize(new java.awt.Dimension(27, 323));
        jPanel2.add(SingularPanle);
        SingularPanle.setBounds(328, 105, 530, 367);

        singularUpdateButton.setText("Update");
        singularUpdateButton.setEnabled(false);
        singularUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singularUpdateButtonActionPerformed(evt);
            }
        });
        jPanel2.add(singularUpdateButton);
        singularUpdateButton.setBounds(570, 530, 70, 32);

        singularOkImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ok.png"))); // NOI18N
        jPanel2.add(singularOkImg);
        singularOkImg.setBounds(730, 510, 48, 50);

        singularReset.setText("Reset");
        singularReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                singularResetActionPerformed(evt);
            }
        });
        jPanel2.add(singularReset);
        singularReset.setBounds(430, 530, 70, 32);

        jTabbedPane1.addTab("Single Command", jPanel2);

        jPanel1.setLayout(null);
        jPanel1.add(groupedReply);
        groupedReply.setBounds(657, 119, 300, 350);
        jPanel1.add(groupedCmd);
        groupedCmd.setBounds(273, 119, 290, 350);

        simpleGroupList.setVerifyInputWhenFocusTarget(false);
        simpleGroupList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpleGroupListActionPerformed(evt);
            }
        });
        jPanel1.add(simpleGroupList);
        simpleGroupList.setBounds(688, 44, 208, 26);

        jLabel1.setText("Group    :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(584, 49, 49, 16);

        simpleGroupUpdate.setText("Update");
        simpleGroupUpdate.setEnabled(false);
        simpleGroupUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpleGroupUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(simpleGroupUpdate);
        simpleGroupUpdate.setBounds(570, 520, 70, 32);

        simpleGroupAdd.setText("+");
        simpleGroupAdd.setFont(simpleGroupAdd.getFont().deriveFont(simpleGroupAdd.getFont().getSize()+6f));
        simpleGroupAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpleGroupAddActionPerformed(evt);
            }
        });
        jPanel1.add(simpleGroupAdd);
        simpleGroupAdd.setBounds(914, 39, 41, 32);

        simpleGroupDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_icon.png"))); // NOI18N
        simpleGroupDelete.setFont(simpleGroupDelete.getFont().deriveFont(simpleGroupDelete.getFont().getSize()+6f));
        simpleGroupDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpleGroupDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(simpleGroupDelete);
        simpleGroupDelete.setBounds(967, 39, 42, 32);

        simpleGroupRename.setText("Rename");
        simpleGroupRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpleGroupRenameActionPerformed(evt);
            }
        });
        jPanel1.add(simpleGroupRename);
        simpleGroupRename.setBounds(1021, 41, 77, 32);

        simpleGroupOkImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ok.png"))); // NOI18N
        jPanel1.add(simpleGroupOkImg);
        simpleGroupOkImg.setBounds(690, 510, 48, 50);

        simpleGroupReset.setText("Reset");
        simpleGroupReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpleGroupResetActionPerformed(evt);
            }
        });
        jPanel1.add(simpleGroupReset);
        simpleGroupReset.setBounds(440, 520, 63, 32);

        jTabbedPane1.addTab("Grouped Commands", jPanel1);

        jPanel3.setLayout(null);

        arguAndAction.setPreferredSize(new java.awt.Dimension(27, 323));
        jPanel3.add(arguAndAction);
        arguAndAction.setBounds(350, 120, 520, 360);

        arguReply.setMinimumSize(new java.awt.Dimension(241, 350));
        jPanel3.add(arguReply);
        arguReply.setBounds(880, 120, 300, 360);
        jPanel3.add(arguCmd);
        arguCmd.setBounds(30, 120, 310, 360);

        arguGroupList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arguGroupListActionPerformed(evt);
            }
        });
        jPanel3.add(arguGroupList);
        arguGroupList.setBounds(773, 34, 208, 26);

        jLabel2.setText("Group    :");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(678, 39, 49, 16);

        arguGroupUpdate.setText("Update");
        arguGroupUpdate.setEnabled(false);
        arguGroupUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arguGroupUpdateActionPerformed(evt);
            }
        });
        jPanel3.add(arguGroupUpdate);
        arguGroupUpdate.setBounds(570, 520, 70, 32);

        arguGroupAdd.setText("+");
        arguGroupAdd.setFont(arguGroupAdd.getFont().deriveFont(arguGroupAdd.getFont().getSize()+6f));
        arguGroupAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arguGroupAddActionPerformed(evt);
            }
        });
        jPanel3.add(arguGroupAdd);
        arguGroupAdd.setBounds(999, 28, 41, 35);

        arguGroupDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_icon.png"))); // NOI18N
        arguGroupDelete.setFont(arguGroupDelete.getFont().deriveFont(arguGroupDelete.getFont().getSize()+6f));
        arguGroupDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arguGroupDeleteActionPerformed(evt);
            }
        });
        jPanel3.add(arguGroupDelete);
        arguGroupDelete.setBounds(1052, 28, 46, 35);

        arguGroupRename.setText("Rename");
        arguGroupRename.setPreferredSize(new java.awt.Dimension(77, 35));
        arguGroupRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arguGroupRenameActionPerformed(evt);
            }
        });
        jPanel3.add(arguGroupRename);
        arguGroupRename.setBounds(1110, 28, 77, 35);

        arguGroupOkImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ok.png"))); // NOI18N
        jPanel3.add(arguGroupOkImg);
        arguGroupOkImg.setBounds(680, 510, 50, 50);

        blueButton1.setText("Reset");
        blueButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blueButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(blueButton1);
        blueButton1.setBounds(440, 520, 63, 32);

        jTabbedPane1.addTab("Argument with Action", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1205, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void simpleGroupListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpleGroupListActionPerformed
        Object item = simpleGroupList.getSelectedItem();
        if (item != null) //if itme will be not null only when there is some item in JComboBox
        {
            simpleGroupId = DBmanager.getGroupId(item.toString());
            groupedCmd.initFromDB("SELECT data , rowid AS id FROM cmd WHERE group_id=" + simpleGroupId);
            groupedReply.initFromDB("SELECT data , rowid AS id FROM reply WHERE group_id=" + simpleGroupId);
        }
        simpleGroupUpdate.setEnabled(false);

    }//GEN-LAST:event_simpleGroupListActionPerformed

    private void arguGroupListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arguGroupListActionPerformed

        Object item = arguGroupList.getSelectedItem();
        if (item != null) //if itme will be not null only when there is some item in JComboBox
        {
            arguGroupId = DBmanager.getGroupId(item.toString());
            arguCmd.initFromDB("SELECT data , rowid AS id FROM cmd WHERE group_id=" + arguGroupId);
            arguAndAction.initFromDB("SELECT argu,action , rowid AS id FROM arguments WHERE group_id=" + arguGroupId);
            arguReply.initFromDB("SELECT data , rowid AS id FROM reply WHERE group_id=" + arguGroupId);
        }
        arguGroupUpdate.setEnabled(false);

    }//GEN-LAST:event_arguGroupListActionPerformed

    private void simpleGroupUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpleGroupUpdateActionPerformed

        simpleGroupUpdate.setEnabled(false);

        groupedCmd.updateToDB(
                "cmd",
                //Update PS for changed data
                new PreparedStatement[]{DBmanager.getPS("UPDATE cmd SET data= ? WHERE rowid = ?")},
                //Insert PS for newly added data by user 
                DBmanager.getPS("INSERT INTO cmd (data,group_id) VALUES(?," + simpleGroupId + ") "),
                //Delete PS    
                DBmanager.getPS("DELETE FROM cmd WHERE rowid = ?"),
                //Delete All PS
                DBmanager.getPS("DELETE FROM cmd WHERE group_id = " + simpleGroupId)
        );

        groupedReply.updateToDB(
                "reply",
                //Update PS for changed data
                new PreparedStatement[]{DBmanager.getPS("UPDATE reply SET data= ? WHERE rowid = ?")},
                //Insert PS for newly added data by user 
                DBmanager.getPS("INSERT INTO reply (data,group_id) VALUES(?," + simpleGroupId + ") "),
                //Delete PS    
                DBmanager.getPS("DELETE FROM reply WHERE rowid = ?"),
                //Delete All PS
                DBmanager.getPS("DELETE FROM reply WHERE group_id = " + simpleGroupId)
        );

        simpleGroupList.setEnabled(true);
        simpleGroupAdd.setEnabled(true);
        simpleGroupRename.setEnabled(true);

        new Thread(() -> {      //Showing green ok tick for successful update
            try {
                simpleGroupOkImg.setVisible(true);
                Thread.sleep(1500);
                simpleGroupOkImg.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(DevMode.class.getName()).log(Level.SEVERE, null, ex);
            }

        }).start();

    }//GEN-LAST:event_simpleGroupUpdateActionPerformed

    private void singularUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singularUpdateButtonActionPerformed
        singularUpdateButton.setEnabled(false);
        SingularPanle.updateToDB_S();

        new Thread(() -> {      //Showing green ok tick for successful update
            try {
                singularOkImg.setVisible(true);
                Thread.sleep(1000);
                singularOkImg.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(DevMode.class.getName()).log(Level.SEVERE, null, ex);
            }

        }).start();

    }//GEN-LAST:event_singularUpdateButtonActionPerformed

    private void arguGroupUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arguGroupUpdateActionPerformed

        arguGroupUpdate.setEnabled(false);

        arguCmd.updateToDB(
                "cmd",
                //Update PS for changed data
                new PreparedStatement[]{DBmanager.getPS("UPDATE cmd SET data = ? WHERE rowid = ?")},
                //Insert PS for newly added data by user 
                DBmanager.getPS("INSERT INTO cmd (data,group_id) VALUES(?," + arguGroupId + ") "),
                //Delete PS    
                DBmanager.getPS("DELETE FROM cmd WHERE rowid = ?"),
                //Delete All PS
                DBmanager.getPS("DELETE FROM cmd WHERE group_id = " + arguGroupId)
        );

        arguAndAction.updateToDB(
                "arguments",
                //Update PS for changed data
                new PreparedStatement[]{
                    DBmanager.getPS("UPDATE arguments SET argu = ? WHERE rowid = ?"),
                    DBmanager.getPS("UPDATE arguments SET action = ? WHERE rowid = ?"),},
                //Insert PS for newly added data by user 
                DBmanager.getPS("INSERT INTO arguments (argu,action,group_id) VALUES(?,?," + arguGroupId + ") "),
                //Delete PS    
                DBmanager.getPS("DELETE FROM arguments WHERE rowid = ?"),
                //Delete All PS
                DBmanager.getPS("DELETE FROM arguments WHERE group_id = " + arguGroupId)
        );

        arguReply.updateToDB(
                "reply",
                //Update PS for changed data
                new PreparedStatement[]{DBmanager.getPS("UPDATE reply SET data = ? WHERE rowid = ?")},
                //Insert PS for newly added data by user 
                DBmanager.getPS("INSERT INTO reply (data,group_id) VALUES(?," + arguGroupId + ") "),
                //Delete PS    
                DBmanager.getPS("DELETE FROM reply WHERE rowid = ?"),
                //Delete All PS
                DBmanager.getPS("DELETE FROM reply WHERE group_id = " + arguGroupId)
        );

        arguGroupList.setEnabled(true);
        arguGroupAdd.setEnabled(true);
        arguGroupRename.setEnabled(true);

        new Thread(() -> {      //Showing green ok tick for successful update
            try {
                arguGroupOkImg.setVisible(true);
                Thread.sleep(1500);
                arguGroupOkImg.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(DevMode.class.getName()).log(Level.SEVERE, null, ex);
            }

        }).start();

    }//GEN-LAST:event_arguGroupUpdateActionPerformed

    private void arguGroupAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arguGroupAddActionPerformed
        boolean condition;

        do {
            condition = false;
            String newName = JOptionPane.showInputDialog("Enter name of new Group  :  ");
            if (newName != null) {
                try {
                    PreparedStatement ps = DBmanager.getPS("INSERT INTO group_ (name,type) VALUES (?,2)");
                    ps.setString(1, newName);
                    ps.execute();

                    if (arguGroupList.getItemCount() == 0) //Enabling group components only when initially arguGroupList was empty
                    {
                        arguGroupSetEnabled(true);
                    }

                    arguGroupList.addItem(newName);
                    arguGroupList.setSelectedItem(newName);

                } catch (SQLException ex) {
                    int error = ex.getErrorCode();
                    if (error == 19) //Unique constraint error
                    {
                        JOptionPane.showMessageDialog(null, "The group ' " + newName + " ' alredy exist. \nEnter diffetent Name.");;
                        condition = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Database error. Can't create group");
                    }

                }
            }

        } while (condition);

    }//GEN-LAST:event_arguGroupAddActionPerformed

    private void simpleGroupAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpleGroupAddActionPerformed

        boolean condition;
        do {

            condition = false;
            String newName = JOptionPane.showInputDialog("Enter name of new Group  :  ");
            if (newName != null) {
                try {
                    PreparedStatement ps = DBmanager.getPS("INSERT INTO group_ (name,type) VALUES (?,1)");
                    ps.setString(1, newName);
                    ps.execute();

                    if (simpleGroupList.getItemCount() == 0) //Enabling group components only when initially simpleGroupList was empty
                    {
                        simpleGroupSetEnabled(true);
                    }

                    simpleGroupList.addItem(newName);
                    simpleGroupList.setSelectedItem(newName);

                } catch (SQLException ex) {

                    int error = ex.getErrorCode();
                    if (error == 19) //Unique constraint error
                    {
                        JOptionPane.showMessageDialog(null, "The group ' " + newName + " ' alredy exist. \nEnter diffetent Name.");;
                        condition = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Database error. Can't create group");
                    }

                }

            }

        } while (condition);
    }//GEN-LAST:event_simpleGroupAddActionPerformed

    private void simpleGroupDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpleGroupDeleteActionPerformed
        // TODO add your handling code here:
        int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure to delete '" + simpleGroupList.getSelectedItem() + "' group ?", "Delete Group", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (userResponse == 0) //User clicked on ok
        {
            try {
                PreparedStatement ps = DBmanager.getPS("DELETE FROM group_ WHERE group_id = ?");
                ps.setInt(1, simpleGroupId);
                ps.execute();
                simpleGroupList.removeItemAt(simpleGroupList.getSelectedIndex());
                simpleGroupAdd.setEnabled(true);

                if (simpleGroupList.getItemCount() == 0) //if user deleted all groups then disable group components
                {
                    simpleGroupSetEnabled(false);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DevMode.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_simpleGroupDeleteActionPerformed

    private void simpleGroupRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpleGroupRenameActionPerformed

        boolean condition;

        do {
            condition = false;
            String newName = JOptionPane.showInputDialog("Enter new name of group : ", simpleGroupList.getSelectedItem());
            if (newName != null) {
                try {

                    PreparedStatement ps = DBmanager.getPS("UPDATE group_ SET name = ? WHERE group_id = ?");
                    ps.setString(1, newName);
                    ps.setInt(2, simpleGroupId);
                    ps.execute();

                    //After remaning group calling initSimpleGroup to make change visible
                    initSimpleGroupList();
                    simpleGroupList.setSelectedItem(newName);

                } catch (SQLException ex) {

                    int error = ex.getErrorCode();
                    if (error == 19) //Unique constraint error
                    {
                        JOptionPane.showMessageDialog(null, "The group ' " + newName + " ' alredy exist. \nEnter diffetent Name.");;
                        condition = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Database error. Can't rename group");
                    }

                }
            }

        } while (condition);
    }//GEN-LAST:event_simpleGroupRenameActionPerformed

    private void arguGroupDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arguGroupDeleteActionPerformed
        int userResponse = JOptionPane.showConfirmDialog(null, "Are you sure to delete '" + arguGroupList.getSelectedItem() + "' group ?", "Delete Group", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (userResponse == 0) //User clicked on ok
        {
            try {
                PreparedStatement ps = DBmanager.getPS("DELETE FROM group_ WHERE group_id = ?");
                ps.setInt(1, arguGroupId);
                ps.execute();

                arguGroupList.removeItemAt(arguGroupList.getSelectedIndex());

                arguGroupAdd.setEnabled(true);

                if (arguGroupList.getItemCount() == 0) //if user deleted all groups then disable group components
                {
                    arguGroupSetEnabled(false);
                }

            } catch (SQLException ex) {
                Logger.getLogger(DevMode.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_arguGroupDeleteActionPerformed

    private void arguGroupRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arguGroupRenameActionPerformed

        boolean condition = false;

        do {
            condition = false;
            String newName = JOptionPane.showInputDialog("Enter new name of group : ", arguGroupList.getSelectedItem());
            if (newName != null) {
                try {

                    PreparedStatement ps = DBmanager.getPS("UPDATE group_ SET name = ? WHERE group_id = ?");
                    ps.setString(1, newName);
                    ps.setInt(2, arguGroupId);
                    ps.execute();

                    //After remaning group calling initSimpleGroup to make change visible
                    initArguGroupList();
                    arguGroupList.setSelectedItem(newName);

                } catch (SQLException ex) {

                    int error = ex.getErrorCode();
                    if (error == 19) //Unique constraint error
                    {
                        JOptionPane.showMessageDialog(null, "The group ' " + newName + " ' alredy exist. \nEnter diffetent Name.");
                        condition = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Database error. Can't rename group");
                    }

                }
            }

        } while (condition);
    }//GEN-LAST:event_arguGroupRenameActionPerformed

    private void blueButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blueButton1ActionPerformed

        arguGroupUpdate.setEnabled(false);
        arguGroupList.setEnabled(true);
        arguGroupAdd.setEnabled(true);
        arguGroupRename.setEnabled(true);

        arguGroupListActionPerformed(null);

    }//GEN-LAST:event_blueButton1ActionPerformed

    private void simpleGroupResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpleGroupResetActionPerformed
        simpleGroupListActionPerformed(null);
        simpleGroupList.setEnabled(true);
        simpleGroupUpdate.setEnabled(false);
        simpleGroupAdd.setEnabled(true);
        simpleGroupRename.setEnabled(true);
    }//GEN-LAST:event_simpleGroupResetActionPerformed

    private void singularResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_singularResetActionPerformed

        singularUpdateButton.setEnabled(false);
        SingularPanle.initFromDB("SELECT cmd.data, reply.data , group_.group_id AS id FROM cmd JOIN group_ JOIN reply WHERE cmd.group_id=group_.group_id AND cmd.group_id =reply.group_id AND group_.type=0;");

    }//GEN-LAST:event_singularResetActionPerformed

    private void simpleGroupSetEnabled(boolean enable) {
        groupedCmd.setEnabled(enable);
        groupedReply.setEnabled(enable);

        simpleGroupReset.setEnabled(enable);
        simpleGroupDelete.setEnabled(enable);
        simpleGroupRename.setEnabled(enable);
        simpleGroupList.setEnabled(enable);
    }

    private void arguGroupSetEnabled(boolean enable) {
        arguCmd.setEnabled(enable);
        arguAndAction.setEnabled(enable);
        arguReply.setEnabled(enable);

        blueButton1.setEnabled(enable);
        arguGroupDelete.setEnabled(enable);
        arguGroupRename.setEnabled(enable);
        arguGroupList.setEnabled(enable);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DevMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevMode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DevMode().setVisible(true);
            }
        });
    }

    private javax.swing.JTextField test;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private dev.DataEditorPanel SingularPanle;
    private dev.DataEditorPanel arguAndAction;
    private dev.DataEditorPanel arguCmd;
    private component.BlueButton arguGroupAdd;
    private component.BlueButton arguGroupDelete;
    private javax.swing.JComboBox<String> arguGroupList;
    private javax.swing.JLabel arguGroupOkImg;
    private component.BlueButton arguGroupRename;
    private component.BlueButton arguGroupUpdate;
    private dev.DataEditorPanel arguReply;
    private component.BlueButton blueButton1;
    private dev.DataEditorPanel groupedCmd;
    private dev.DataEditorPanel groupedReply;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private component.BlueButton simpleGroupAdd;
    private component.BlueButton simpleGroupDelete;
    private javax.swing.JComboBox<String> simpleGroupList;
    private javax.swing.JLabel simpleGroupOkImg;
    private component.BlueButton simpleGroupRename;
    private component.BlueButton simpleGroupReset;
    private component.BlueButton simpleGroupUpdate;
    private javax.swing.JLabel singularOkImg;
    private component.BlueButton singularReset;
    private component.BlueButton singularUpdateButton;
    // End of variables declaration//GEN-END:variables
}
