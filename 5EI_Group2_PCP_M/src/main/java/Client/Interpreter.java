/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Graphics.*;
import java.awt.Component;
import java.io.*;
import java.util.*;
import javax.swing.JList;
import javax.swing.JPanel;
import repository.Repository;
import pacchetti.*;

/**
 *
 * @author 5ei
 */
public class Interpreter extends Thread{

    private byte[] packet;
    private Messaggistica mex;

    public Interpreter(byte[] packet, Messaggistica mex) {
        this.packet = packet;
        this.mex = mex;
    }

    @Override
    public void run() {

        Byte opcode= packet[0];
        
        String op = Byte.toString(opcode);
        System.out.println(op);
        switch (op) {
            case ("20"):
                System.out.println("dio");
                Packet20.interpretaP(packet, Login.alias);
                break;

            case ("11"):
                Packet11 a = new Packet11();
                a.interpretaP(packet);
                break;

            case ("5"):
                ArrayList<String> dati = Packet05.interpretaP(packet);
                mex.getjTextArea1().setText(mex.getjTextArea1().getText() + "\n" + dati.get(0) + ": " + dati.get(1));
                break;

            case ("01"):
                ArrayList<String> data = Packet01.interpretaP(packet);
                mex.getjTextArea1().setText(mex.getjTextArea1().getText() + "\n" + data.get(0) + ": " + data.get(1));
                break;

            case ("51"):
                ArrayList lista = Packet51.interpretaP(packet, this.mex);
                
                
                System.out.println(lista);
                break;

            case ("255"):
                Packet255 e = new Packet255();
                e.interpretaP(packet);
                break;
        }
    }
}
