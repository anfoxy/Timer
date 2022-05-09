/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.myevent;

import java.util.ArrayList;
import java.util.UUID;

public interface IMyEvent {
  void timerMoved(Model m, UUID id);
  void runEvent(ArrayList<TimeClass> p1,Model m, UUID id);

}
