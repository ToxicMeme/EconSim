package org.example;


import java.util.ArrayList;

public class GlobalMarket {
    ArrayList<MarketRequest> toSell = new ArrayList<>();
    ArrayList<MarketRequest> toBuy = new ArrayList<>();

    public GlobalMarket() {

    }

    public void show() {
        System.out.println("Продажа\n"+ toSell);
        System.out.println("Покупка\n"+ toBuy);
    }
}
