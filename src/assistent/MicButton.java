/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assistent;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author hp
 */
public class MicButton extends javax.swing.JToggleButton {

    private ImageIcon dark;
    private ImageIcon light;

    public MicButton() {

        dark = new javax.swing.ImageIcon(getClass().getResource("/images/mic_deactive_dark.png"));
        light = new javax.swing.ImageIcon(getClass().getResource("/images/mic_deactive.png"));
        setDisabledIcon(dark);
        setIcon(light);
        setSize(60, 60);
        setBackground(new Color(0, 0, 0, 0));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(light);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(dark);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                changeIcon(isSelected());
            }

        });

    }

    public void setSelected(boolean select) {
        super.setSelected(select);
        changeIcon(select);
    }

    private void changeIcon(boolean select) {
        if (select) {
            dark = new javax.swing.ImageIcon(getClass().getResource("/images/mic_active_dark.png"));
            light = new javax.swing.ImageIcon(getClass().getResource("/images/mic_active.png"));
        } else {
            dark = new javax.swing.ImageIcon(getClass().getResource("/images/mic_deactive_dark.png"));
            light = new javax.swing.ImageIcon(getClass().getResource("/images/mic_deactive.png"));

        }
        setIcon(light);

    }

}
