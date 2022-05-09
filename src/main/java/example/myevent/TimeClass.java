package example.myevent;

import java.io.DataInputStream;
import java.io.IOException;
/**
 *
 * @author Anastasia
 */
public class TimeClass {
    private int sec;
    private int indTime;
    private String info;
    private Boolean flag;

    public TimeClass(int sec,int indTime, String info) {
        this.sec = sec;
        this.indTime = indTime;
        this.flag = false;
        this.info = info;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }



    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setIndTime(int indTime) {
        this.indTime = indTime;
    }

    public int getIndTime() {
        return indTime;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getSec() {
        return sec;
    }

    @Override
    public String toString() {
        return "TimeClass{" +
                "sec=" + sec +
                '}';
    }
}

