/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.myevent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Anastasia
 */
public class Server {

  Model m = BModel.build();

  int port = 3124;
  InetAddress ip;
  ServerSocket ss;
  
  
  public Server() {
      try {
      ip = InetAddress.getLocalHost();
    
      ServerSocket ss = new ServerSocket(port, 0, ip);
      System.out.println("Run server!");
      RunTime r = new RunTime();


      while (true) {        
        Socket cs = ss.accept();
        ClientObserver co = new ClientObserver(cs);
        co.isCient = false;
        m.addObserver(co);
      }
      
      
    } catch (IOException ex) {
      System.out.println("no run server!");
    }

  
  }

  
  

  public static void main(String[] args) {
    new Server();
  }
  
}
