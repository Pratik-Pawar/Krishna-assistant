/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev;

import database.DBmanager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class TextPanel extends javax.swing.JPanel implements KeyListener {

    /**
     * Creates new form TetPanel
     */
    private boolean isChanged[];
    private int id, data_size;
    private ChangeListener action;

    public TextPanel(int noOfEmptyTextField, ChangeListener action_) {  // This constractor will add no. of empty string in text boxs as per ginven noFoEmptyTextField
        initComponents();
        this.data_size = noOfEmptyTextField;
        action = action_;

        isChanged = new boolean[data_size];
        for (int i = 0; i < data_size; i++) {
            JTextField text = new JTextField(18); //adding textfield as per size and setting text as pre given data
            text.setName("" + i);
            add(text);
            isChanged[i] = false;     //Setting that no change is happen to textField
        }

    }

    public void updateToDB(String nameOfTable, PreparedStatement update[], PreparedStatement insert) //this function is for TextPanle of multiples data or textFiled
    {
        try {

            if (id == 0) //as id is zero it means the data is inserted newly by user
            {
                for (int i = 0; i < data_size; i++) {
                    JTextField data = (JTextField) getComponent(i + 1);
                    insert.setString(i + 1, data.getText());
                    data.addKeyListener(this);
                    isChanged[i] = false;
                }

                insert.execute();
                id = DBmanager.getLastRowId(nameOfTable);
            } else //Checking for changed data
            {
                for (int i = 0; i < data_size; i++) {
                    update[i].setInt(2, id);        //setting id in database for reference of row
                    if (isChanged[i]) //If there is change in textField
                    {

                        JTextField data = (JTextField) getComponent(i + 1);
                        update[i].setString(1, data.getText());
                        update[i].execute();

                        data.addKeyListener(this);
                        isChanged[i] = false;

                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(TextPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This is special case function design for Singular cmd/reply data. Only
     * call on updating to Singular cmd/reply data
     */
    public void updateToDB_S() {

        if (id == 0) //as id is zero it means the data is inserted newly by user
        {
            String cmd = ((JTextField) getComponent(1)).getText();
            String reply = ((JTextField) getComponent(2)).getText();
            try {
                DBmanager.getPS("INSERT INTO group_ (name,type) VALUES(NULL,0)").execute();       //Creating group to link cmd and reply
                id = DBmanager.getLastRowId("group_");

                PreparedStatement ps = DBmanager.getPS("INSERT INTO cmd (data,group_id) VALUES(?,?)");      //Inserting cmd data and linking to group which created in above line
                ps.setString(1, cmd);
                ps.setInt(2, id);
                ps.execute();
                ps.close();

                ps = DBmanager.getPS("INSERT INTO reply (data,group_id) VALUES(?,?)");      //Inserting reply data and linking to group which created in above line
                ps.setString(1, reply);
                ps.setInt(2, id);
                ps.execute();
                ps.close();

                //Adding key Listener to detect change in JTextFild
                ((JTextField) getComponent(1)).addKeyListener(this);
                ((JTextField) getComponent(2)).addKeyListener(this);
            } catch (SQLException ex) {
                Logger.getLogger(TextPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else //As id is not 0 means data may have to update is there is change
        {
            if (isChanged[0]) //If there is change in cmd textField
            {
                String cmd = ((JTextField) getComponent(1)).getText();
                try {

                    PreparedStatement ps = DBmanager.getPS("UPDATE cmd SET data = ? WHERE group_id = ?");
                    ps.setString(1, cmd);
                    ps.setInt(2, id);
                    ps.execute();

                    //Adding key Listener to detect change in JTextFild
                    isChanged[0] = false;
                    ((JTextField) getComponent(1)).addKeyListener(this);

                } catch (SQLException ex) {
                    Logger.getLogger(TextPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (isChanged[1]) //If there is change in reply textField
            {
                String reply = ((JTextField) getComponent(2)).getText();
                try {
                    PreparedStatement ps = DBmanager.getPS("UPDATE reply SET data = ? WHERE group_id = ?");
                    ps.setString(1, reply);
                    ps.setInt(2, id);
                    ps.execute();
                    //Adding key Listener to detect change in JTextFild
                    isChanged[1] = false;
                    ((JTextField) getComponent(2)).addKeyListener(this);

                } catch (SQLException ex) {
                    Logger.getLogger(TextPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public TextPanel(ResultSet rs, int data_size, ChangeListener action_) {       //This constractor will add data from ResultSet given from database ie. It will add data from database
        initComponents();
        this.data_size = data_size;
        action = action_;

        isChanged = new boolean[data_size];

        try {

            id = rs.getInt("id");
            for (int i = 1; i <= data_size; i++) {
                JTextField text = new JTextField(rs.getString(i), 18); //adding textfield as per size and setting text as pre given data
                text.setName("" + (i - 1));             //Setting textField position in textPanel
                text.addKeyListener(this);
                add(text);
                isChanged[i - 1] = false;     //Setting that no change is happen to textField
            }

        } catch (SQLException ex) {
            Logger.getLogger(TextPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return select.isSelected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        select = new javax.swing.JCheckBox();

        select.setFocusable(false);
        add(select);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox select;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {

        JTextField source = (JTextField) e.getSource();
        isChanged[Integer.parseInt(source.getName())] = true;     //setting isChanged to true as per position of textField storn in it's name
        action.onChange();
        source.removeKeyListener(this);     //Removieng keyListener , so it will no exe. this function on every key typed

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
