package com.example.slatejack.smartfrim1.data;

import java.util.List;

public class Result {
    public String Obj;
    public int Num;
    public List<Sensor> Payload;

    public String getObj() {
        return Obj;
    }

    public void setObj(String obj) {
        Obj = obj;
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public List<Sensor> getPayload() {
        return Payload;
    }

    public void setPayload(List<Sensor> payload) {
        Payload = payload;
    }
}
