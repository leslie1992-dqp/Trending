package com.example.trending.base;

public abstract class FirstPrenster<A extends FirstActivity,B extends FirstModel>{
    public A mA;
    public B mB;
    public FirstPrenster(){
        this.mB=getModelInstance();
    }
    public void bindView(A mA){
        this.mA=mA;
    }
    public void unbindView(){
        this.mA=null;
    }
    public abstract B getModelInstance();
}
