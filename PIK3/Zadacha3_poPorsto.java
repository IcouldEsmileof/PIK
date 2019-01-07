package com.musala.javacourse181112.PIK3.ZadachiZaKontrolno.Zadacha3;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.max;

public class Zadacha3_poPorsto {
    public static void main(String[] args) {
        int n1=10,n2=10,k=1;
        AtomicInteger boysCount=new AtomicInteger(n1);
        AtomicInteger girlsCount=new AtomicInteger(n2);
        Integerr integerBoysCount=new Integerr(n1);
        Integerr integerGirlsCount=new Integerr(n2);

        Thread boysThreadWithAtomicInteger=new Thread(new Runnable() {
            @Override
            public void run() {
                while(boysCount.get()>0){
                    if(boysCount.get()>=girlsCount.get()){
                        washNotSync("Boys",boysCount,k);
                    }
                }
            }
        });

        Thread girlsThreadWithAtomicInteger=new Thread(new Runnable() {
            @Override
            public void run() {
                while(girlsCount.get()>0){
                    if(girlsCount.get()>=boysCount.get()){
                        washNotSync("Girls",girlsCount,k);
                    }

                }

            }
        });

        boysThreadWithAtomicInteger.start();
        girlsThreadWithAtomicInteger.start();
        try {
            boysThreadWithAtomicInteger.join();
            girlsThreadWithAtomicInteger.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Atomic method ready");
        Thread boysThreadSync=new Thread(new Runnable() {
            @Override
            public void run() {
                while (integerBoysCount.getN() > 0) {
                    if (integerBoysCount.getN()>=integerGirlsCount.getN()){
                        washSync("Boys",integerBoysCount,k);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        Thread girldsThreadSync=new Thread(new Runnable() {
            @Override
            public void run() {
                while (integerGirlsCount.getN() > 0) {
                    if (integerBoysCount.getN()<=integerGirlsCount.getN()){
                        washSync("Girls",integerGirlsCount,k);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
        boysThreadSync.start();
        girldsThreadSync.start();
        try{
            boysThreadSync.join();
            girldsThreadSync.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Exit");
    }

    public static void washNotSync(String s,AtomicInteger n,int k){
        int l=n.get();
        n.set(max(l-k,0));
        System.out.println(s+"="+n.get());
    }
    synchronized public static void washSync(String s,Integerr n,int k){
        n.setN(max(n.getN()-k,0));
        System.out.println(s+"="+n.getN());
    }
}
class Integerr{
    int n;
    Integerr(int n){
        this.n=n;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
