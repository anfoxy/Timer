/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.myevent;

import com.google.gson.Gson;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anastasia
 */
public class ClientObserver implements IMyEvent{

  Socket cs;

  DataInputStream dis;
  DataOutputStream dos;
  Gson convert = new Gson();
  Model m = BModel.build();
  boolean isCient = true;
  
  public ClientObserver(Socket cs) {
    this.cs = cs;
    
    System.out.println("Подключился клиент \n");
    
    try {
      dos = new DataOutputStream(cs.getOutputStream());
    
      Thread t = new Thread(
      ()->{
      try {
        dis = new DataInputStream(cs.getInputStream());
        while (true) {          
          String obj;
          obj = dis.readUTF();
          Msg msg = convert.fromJson(obj, Msg.class);
          System.out.println("Получил " + msg);
          if(msg.getFlag()){
            m.runEvent(msg.getP(),msg.getId());
          }else m.setP(msg.getP(), msg.getId());

        }
        
      } catch (IOException ex) {}
       
      }
    );
    t.start();

    
    } catch (IOException ex) {
      Logger.getLogger(ClientObserver.class.getName()).log(Level.SEVERE, null, ex);
    }
    

  }
  
  @Override
  public void timerMoved(Model m, UUID id) {
    if(!m.getId().equals(id) && isCient)return ;
    ArrayList<TimeClass> p = m.getP();
    Msg msg = new Msg(id, p,false);
    
    String s = convert.toJson(msg);
    
    if(dos != null)
    {
      try {
        dos.writeUTF(s);
      } catch (IOException ex) {
        Logger.getLogger(ClientObserver.class.getName()).log(Level.SEVERE, null, ex);
      }}
    
  }

  @Override
  public void runEvent(ArrayList<TimeClass> p1, Model m, UUID id) {
    if(!m.getId().equals(id) && isCient)return ;
    ArrayList<TimeClass> p = p1;
    Msg msg = new Msg(id, p,true);

    String s = convert.toJson(msg);

    if(dos != null)
    {
      try {
        dos.writeUTF(s);
      } catch (IOException ex) {
        Logger.getLogger(ClientObserver.class.getName()).log(Level.SEVERE, null, ex);
      }}

  }
}
