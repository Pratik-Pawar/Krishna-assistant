/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev;

import database.DBmanager;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.DataLine;

/**
 *
 * @author Administrator
 */
public class DataEditorPanel extends javax.swing.JPanel {

    /**
     * Creates new form DataEditorPanel
     */
    
    String title;
    int data_size,deleteList[],deleteCount;
    boolean isAllDeleted=false;
    ChangeListener action;
    
    
    public DataEditorPanel(String title_,int size,ChangeListener action_) {
        title=title_;        //setting title to panel
        data_size=size;     //setting no of textfield for TextPanel
        action=action_;

        initComponents();
    }

    
    public DataEditorPanel() {
        
        initComponents();
        
    }
    

    @Override
    public void setEnabled(boolean enable)
    {
            super.setEnabled(enable);
            add.setEnabled(enable);
            delete.setEnabled(enable);
            delete_all.setEnabled(enable);
            if(!enable)     //delete all data only when disabling DataEditorPanel
                deleteAllData();
       
    }
    public void initFromDB(String initQuery){
        isAllDeleted=false; 
        deleteAllData();        //deleting all data befour adding data form DB
        try {

            ResultSet rs = DBmanager.exeQuery(initQuery);
            
            while(rs.next())
            {
                addTextPanel(rs);         //Adding TextPanel with data from one row of database
            }
        
        deleteList = new int[data_list_panel.getComponentCount()];      //Setting array size to no. of TextPanel 
        
            
        } catch (SQLException ex) {
            Logger.getLogger(DataEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
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

        add = new component.BlueButton();
        delete = new component.BlueButton();
        delete_all = new component.BlueButton();
        data_list_scroll_pane = new javax.swing.JScrollPane();
        data_list_panel = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, title, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 16))); // NOI18N

        add.setText("+");
        add.setFocusCycleRoot(true);
        add.setFont(add.getFont().deriveFont(add.getFont().getSize()+20f));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_icon.png"))); // NOI18N
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        delete_all.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del_icon.png"))); // NOI18N
        delete_all.setText("All");
        delete_all.setFocusCycleRoot(true);
        delete_all.setFont(delete_all.getFont().deriveFont(delete_all.getFont().getSize()+4f));
        delete_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_allActionPerformed(evt);
            }
        });

        data_list_panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        data_list_scroll_pane.setViewportView(data_list_panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(data_list_scroll_pane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delete_all, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_all, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(data_list_scroll_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

        TextPanel data = new TextPanel(data_size,action);
        
        Dimension dim;
        dim=data_list_panel.getPreferredSize();
        dim.height=dim.height+(data.getPreferredSize().height); 

        data_list_panel.setPreferredSize(dim);
        data_list_panel.add(data);
        data_list_panel.revalidate();

        action.onChange();

        
    }//GEN-LAST:event_addActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed

        Dimension dim = data_list_panel.getPreferredSize();
        for(int i=data_list_panel.getComponentCount()-1 ; i>=0 ; i--){        //to delete elements loop iterate in reverse direction
            TextPanel data = (TextPanel)data_list_panel.getComponent(i);
            if(data.isSelected()){     //check select checkbox of every TextPanel in cmd_panel do delete


                int id = data.getId();
                if(id!=0)       //if id is 0 it means data is newly added by user and not exits in DB. Hence no need to remove it from DB
                    deleteList[deleteCount++] = data.getId();   //adding id to delete from db    

                dim.height-=data.getPreferredSize().height;  //dreasing size of data_list_panel
                data_list_panel.remove(data);
                
                
                
            }
            
        }
        
        data_list_panel.setPreferredSize(dim);
        data_list_panel.revalidate();
        data_list_panel.repaint();

        action.onChange();
    }//GEN-LAST:event_deleteActionPerformed

    private void delete_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_allActionPerformed
        deleteAllData();
        isAllDeleted=true;        
        action.onChange();
    }//GEN-LAST:event_delete_allActionPerformed

    
    public void updateToDB(String nameOfTable,PreparedStatement update[],PreparedStatement insert,PreparedStatement delete,PreparedStatement deleteAll){
        
        if(isAllDeleted)        //if Delete all button is pressed
        {
            try {
            
                deleteAll.execute();
                isAllDeleted=false;        
                
                
            } catch (SQLException ex) {
                Logger.getLogger(DataEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else        //As user not press delete all button. Therefore checking if there are deleted some data
        {
            for(int i=0;i<deleteCount;i++)
            {
                try {

                    delete.setInt(1, deleteList[i]);        //Setting id for row to delete in delete PreparedStatement
                    delete.execute();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(DataEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
          
        //updating changed field
        for(int i=0;i<data_list_panel.getComponentCount();i++)
        {
            ((TextPanel)data_list_panel.getComponent(i)).updateToDB(nameOfTable,update,insert);
        }

        deleteCount=0;  //After Deleting there will no data to delete
        deleteList = new int[data_list_panel.getComponentCount()];      //Setting array size to no. of TextPanel 

    }
/**
*   This is special case function design for Singular cmd/reply data. 
*   Only call on updating to Singular cmd/reply data
*/
 
    public void updateToDB_S()
    {
         
        if(isAllDeleted)        //if Delete all button is pressed
        {
            try {
                DBmanager.getPS("DELETE FROM group_ WHERE type = 0").execute();
                isAllDeleted=false;        
            } catch (SQLException ex) {
                Logger.getLogger(DataEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else        //As user not press delete all button. Therefore checking if there are deleted some data
        {
            for(int i=0;i<deleteCount;i++)
            {
                try {

                    PreparedStatement ps = DBmanager.getPS("DELETE FROM group_ where group_id = ?");        //Setting id for row to delete in delete PreparedStatement
                    ps.setInt(1, deleteList[i]);
                    ps.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(DataEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for(int i=0;i<data_list_panel.getComponentCount();i++)
        {
            ((TextPanel)data_list_panel.getComponent(i)).updateToDB_S();
        }

        deleteCount=0;  //After Deleting there will no data to delete
        deleteList = new int[data_list_panel.getComponentCount()];      //Setting array size to no. of TextPanel 
    }
            
    
    private void deleteAllData(){
        data_list_panel.setPreferredSize(new Dimension(0,0));
        data_list_panel.removeAll();
        data_list_panel.revalidate();
        data_list_panel.repaint();
        
    }
    private void addTextPanel(ResultSet data)       //This will add textPanel as per from database
    {
        TextPanel text = new TextPanel(data,data_size,action);
        
        Dimension dim;
        dim=data_list_panel.getPreferredSize();
        dim.height=dim.height+(text.getPreferredSize().height); 

        data_list_panel.setPreferredSize(dim);
        data_list_panel.add(text);
        data_list_panel.revalidate();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public component.BlueButton add;
    private javax.swing.JPanel data_list_panel;
    private javax.swing.JScrollPane data_list_scroll_pane;
    private component.BlueButton delete;
    private component.BlueButton delete_all;
    // End of variables declaration//GEN-END:variables
}
