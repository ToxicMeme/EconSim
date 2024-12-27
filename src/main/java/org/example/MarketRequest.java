package org.example;

public class MarketRequest {
    public Resource resource;
    public int quantity;
    private double pricePerUnit;

    public MarketRequest(RequestType type, Resource resource, int quantity, double pricePerUnit){
        this.resource = resource;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    enum RequestType
    {
        SELL, BUY;
    }

    @Override
    public String toString(){
        return "Ресурс: " + resource.toString() + " Количество: " + quantity + " По цене: "+ pricePerUnit*quantity + "\n";
    }
}
