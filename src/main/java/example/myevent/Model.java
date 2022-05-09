/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.myevent;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Anastasia
 */
public class Model {
    
  ArrayList<TimeClass> p = new ArrayList<>();
  ArrayList<IMyEvent> allObserver = new ArrayList<>();
  
  UUID id = UUID.randomUUID();
  Connection c;

  void connect() {
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:D:/sqlitestudio-3.3.3/timer.db");
      System.out.println("Opened database");
    } catch (ClassNotFoundException ex) {
      System.out.println("Driver not found");
    } catch (SQLException ex) {
      System.out.println("DB not found");
    }
  }
  private void delDB(int ids) {
    try (PreparedStatement statement = this.c.prepareStatement(
            "DELETE FROM Table_timerLab WHERE int_id = ?")) {
      statement.setInt(1, ids);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateDB(int ids, int sec) {
    try (PreparedStatement statement = this.c.prepareStatement(
            "UPDATE Table_timerLab SET int_sec=? WHERE int_id=?")) {
      statement.setInt(1, sec);
      statement.setInt(2, ids);
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  ArrayList<TimeClass> getDB () {
    if(c != null) {
      try {
        Statement st = c.createStatement();
        ResultSet r = st.executeQuery("select * from Table_timerLab");

        while(r.next()) {
          p.add(new TimeClass(r.getInt("int_sec"), r.getInt("int_id"), r.getString("str_str")));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return p;
  }

  public void addDB(int id_b, int sec, String msg) {
    if(c != null) {
      try {
        PreparedStatement pst = c.prepareStatement("Insert INTO Table_timerLab(int_id, int_sec, str_str) VALUES(?,?,?)");
        pst.setInt(1, id_b);
        pst.setInt(2, sec);
        pst.setString(3, msg);
        pst.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }
  }
  public Model(){
    connect();

  }
  void moveTimeEvrnt(UUID _id){
    for (IMyEvent iMyEvent : allObserver) {
      iMyEvent.timerMoved(this, _id);
    }
  }

  void runEvent(ArrayList<TimeClass> p1,UUID _id){
    for (IMyEvent iMyEvent : allObserver) {
      iMyEvent.runEvent(p1, this, _id);
    }
  }
  public ArrayList<TimeClass> getP() {
    return p;
  }


  public void setP(ArrayList<TimeClass> p) {
    this.p = p;
    moveTimeEvrnt(id);
  }
  
  public void setP(ArrayList<TimeClass> p, UUID _id) {
    if(!id.equals(_id)) {
      this.p = p;
      moveTimeEvrnt(_id);
    }
  }
  
  void addObserver(IMyEvent e) {
    allObserver.add(e);
  }
  void update() {

    for (TimeClass t : p) {

      t.setSec(t.getSec()-1);
      updateDB(t.getIndTime(),t.getSec());
    }
  }
  Boolean checkTime( UUID _id) {
    Boolean r = false;
    for(int i=0; i<p.size();i++){
      if (p.get(i).getSec()==0){
        p.get(i).setFlag(true);
        runEvent(p,_id);
        delDB(i);
        p.remove(i);
        r= true;
      }
    }
    return r;
  }
  public UUID getId() {
    return id;
  }
  
  
}
