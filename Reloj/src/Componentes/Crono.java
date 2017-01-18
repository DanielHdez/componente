/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.EventListener;
import javax.swing.Timer;
import javax.swing.JLabel;

/**
*
* @author francis
*/
public class Crono extends JLabel implements ActionListener, Serializable{

 // Subclase
 public class EventoAlarma extends java.util.EventObject{
 public EventoAlarma(Object source) {
    // this.source.toString();
     super(source);
 }

 }
 public interface AlarmaListener extends EventListener{
 void saltaAlarma (EventoAlarma ev);
 }
 private AlarmaListener receptor;
 private final Timer t;
 int min, seg;
 private String cuenta="",etiqueta="";
 // getter y setter

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta =  validarcuenta(cuenta);
    }

 public Crono (){
 t = new Timer (1000, this);
 t.start();
 }

 @Override
 public void actionPerformed(ActionEvent e) {
         if (seg == 59) {//si los segundos son iguales a 59
             seg = 0;//segundo vuelve a empezar en cero
             min++;//y aumenta un minuto
         }
         if (min == 59) {//si los minutos son iguales a 59
             min = 0;//minuto vuelve a empezar en cero
         }
         seg++;
         
          etiqueta=String.format("%02d", min) + ":" +
         String.format("%02d", seg);//se muestra en el jlabel
          
          setText(etiqueta);
          
          
      
          if (etiqueta.equals(getCuenta()))
          receptor.saltaAlarma(new EventoAlarma(this)); 
          repaint();
      
 }



 public void addAlarmaListener (AlarmaListener receptor){
 this.receptor = receptor;
 }
 public void removeAlarmaListener (AlarmaListener receptor){
 this.receptor = null;
 }

 static public String validarcuenta(String strHora){
 int  minuto, segundo;
 String hms[];
 hms = strHora.split(":");
 if (hms.length > 2) // No puede haber más de 3 "partes"
 return "";
 try{
 minuto = Integer.parseInt(hms[0].trim());
 segundo = Integer.parseInt(hms[1].trim());
 }
 catch(Exception e){
 return "";
 }

 if (minuto < 0 || minuto > 59 || segundo < 0 || segundo > 59)
 return "";
 else
 return (String.format("%02d", minuto) + ":" +
String.format("%02d", segundo));
 }

}