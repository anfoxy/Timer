/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.myevent;

import java.awt.Point;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Anastasia
 */
public class Msg {
  UUID id;
  ArrayList<TimeClass> p;
  Boolean flag;

  public Msg(UUID id, ArrayList<TimeClass> p,Boolean flag) {
    this.id = id;
    this.p = p;
    this.flag=flag;
  }

  public void setFlag(Boolean flag) {
    this.flag = flag;
  }

  public Boolean getFlag() {
    return flag;
  }

  public UUID getId() {
    return id;
  }

  @Override
  public String toString() {
    String s = "Msg{" + "id= "+ id ;
    for (TimeClass time:p){
      s = s + "\n таймер №"+time.getIndTime() + "sec = "+time.getSec();
    }
    s+= '}';
    return s;
  }


  public ArrayList<TimeClass> getP() {
    return p;
  }

  public void setP(ArrayList<TimeClass> p) {
    this.p = p;
  }
  
  
  
}
