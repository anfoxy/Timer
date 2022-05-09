/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.myevent;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anastasia
 */
public class RunTime {

    Model m = BModel.build();
    UUID _id = UUID.randomUUID();

/*    public  RunTime(){
        System.out.println("RunTime ok!");
        try {
            ip = InetAddress.getLocalHost();
            cs = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        m.addObserver(this);
    }*/

    public  RunTime(){
        Thread t = new Thread(
                () -> {
                    while (true) {

                        if (m.getP().size()>0) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(TimePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (m.checkTime(_id)) {
                                System.out.println("Ok");
                                m.update();
                            }else {
                                m.update();
                            }
                            m.moveTimeEvrnt(_id);

                        }else {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
        );
        t.start();
    }

  /*  @Override
    public void pointMoved(Model m, UUID id) {
       if(flag) run();
    }*/

   /* void run(){
        flag= false;
        Thread t = new Thread(
                () -> {
                    while (true) {

                        if (m.getP().size()>0) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(TimePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if (m.checkTime()) {
                                System.out.println("Ok");
                            }else {
                                m.update();
                            }
                                m.movePointEvrnt(_id);

                        }else {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("size "+m.getP().size());
                        }
                    }

                }
        );
        t.start();

    }*/
}
