package com.demo.model;

import java.io.InputStream;

/**
 * Created by Enzo Cotter on 2019/6/8.
 */
public class Picture {

    private int pid;
    private String pName;
    private InputStream stream;



    public Picture(){}


    public void setId(int pid) {
        this.pid = pid;
    }
    public int getId() {
        return pid;
    }

    public void setName(String pname) {
        this.pName = pname;
    }

    public String getName() {
        return pName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }
    public Picture(int pid, String pName, InputStream stream) {
        this.pid = pid;
        this.pName = pName;
        this.stream= stream;
    }




}
