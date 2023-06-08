package models;

import com.google.gson.annotations.Expose;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SablonaProPrvek {
    @Expose(serialize = true ,deserialize = true)
    private String name;
    @Expose(serialize = true,deserialize = true)
    private double parametr1;
    @Expose(serialize = true,deserialize = true)
    private double parametr2;

    public SablonaProPrvek(String name,double parametr1,double parametr2){
        this.name=name;
        this.parametr1=parametr1;
        this.parametr2=parametr2;
    }
    public String getName(){return  name;}
    public double getParametr1(){return parametr1;}
    public  double getParametr2(){return parametr2;}
}
