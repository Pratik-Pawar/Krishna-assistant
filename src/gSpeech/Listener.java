/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gSpeech;

import com.goxr3plus.speech.microphone.Microphone;
import com.goxr3plus.speech.recognizer.GSpeechDuplex;
import com.goxr3plus.speech.recognizer.GoogleResponse;
import com.goxr3plus.speech.recognizer.Languages;
import com.goxr3plus.speech.recognizer.Recognizer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.sourceforge.javaflacencoder.FLACFileWriter;

/**
 *
 * @author Administrator
 */
public class Listener extends com.goxr3plus.speech.recognizer.GSpeechDuplex implements com.goxr3plus.speech.recognizer.GSpeechResponseListener{

    private Microphone mic;
    private JTextField output;
    public Listener(JTextField text_) {
        
        super("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
        
        output=text_;
        mic = new Microphone(FLACFileWriter.FLAC);
        setLanguage("en-us");   //Language for speech recognizer
        addResponseListener(this);
        
        
    }
    
   
     
    public void startListening()
    {
        try {
            recognize(mic.getTargetDataLine(),mic.getAudioFormat());
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            
    }

    public void onResponse(GoogleResponse gr) {

        output.setText(gr.getResponse());      
        
        if(gr.isFinalResponse())
        {    
            stopSpeechRecognition();
        }
    }
    
    public void stopListenig(){
        stopSpeechRecognition();
    }
    
}
