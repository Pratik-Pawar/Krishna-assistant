/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gSpeech;

import assistent.AssistFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Administrator
 */
public class Speaker extends com.goxr3plus.speech.synthesiser.SynthesiserV2 {

    private Player player;

    public Speaker() {

        super("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
        setLanguage("en-in");
        setSpeed(0.9);
        setPitch(1);

    }

    public void startSpeaking(String text) {

        try {
            player = new Player(getMP3Data(text));
            player.play();

        } catch (JavaLayerException | IOException ex) {
            Logger.getLogger(AssistFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void stopSpeaking() {

        if (player != null) {
            player.close();
        }
    }

}
