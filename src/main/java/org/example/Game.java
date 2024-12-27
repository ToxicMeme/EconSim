package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    ArrayList<Resource> allResources = new ArrayList<>();

    public Game() throws InterruptedException {
        allResources.add(new Resource(Resource.ResourceName.NRGC, Resource.ResourceType.BASE, 10.0));
        allResources.add(new Resource(Resource.ResourceName.MINE, Resource.ResourceType.BASE, 10.0));
        allResources.add(new Resource(Resource.ResourceName.FOOD, Resource.ResourceType.BASE, 10.0));
        allResources.add(new Resource(Resource.ResourceName.ALOY, Resource.ResourceType.SECONDARY, 10.0));
        allResources.add(new Resource(Resource.ResourceName.TMC, Resource.ResourceType.SECONDARY, 10.0));
        allResources.add(new Resource(Resource.ResourceName.INFL, Resource.ResourceType.ABSRACT, 10.0));
        allResources.add(new Resource(Resource.ResourceName.UNTY, Resource.ResourceType.ABSRACT, 10.0));
        allResources.add(new Resource(Resource.ResourceName.SPTS, Resource.ResourceType.ABSRACT, 10.0));
        allResources.add(new Resource(Resource.ResourceName.DRKM, Resource.ResourceType.STRATEGIC, 10.0));
        allResources.add(new Resource(Resource.ResourceName.NANI, Resource.ResourceType.STRATEGIC, 10.0));

        GlobalMarket globalMarket = new GlobalMarket();

        while (true) {
            Random rand = new Random();

            Resource resource = allResources.get(rand.nextInt(allResources.size()));
            PricingModel pricingModel = new PricingModel(resource.basePrice);


            if (!globalMarket.toSell.isEmpty()) {
                pricingModel.setSupply(resource.toString(), calculateMarketQuantity(resource, globalMarket.toSell));
            }

            if (!globalMarket.toBuy.isEmpty()) {
                pricingModel.setDemand(resource.toString(), calculateMarketQuantity(resource, globalMarket.toBuy));
            }

            int prob = rand.nextInt(2);
            switch (prob) {
                case 0:
                    globalMarket.toSell.add(new MarketRequest(MarketRequest.RequestType.SELL, resource,
                            rand.nextInt(99)+1, pricingModel.calculatePrice()));
                    break;
                case 1:
                    globalMarket.toBuy.add(new MarketRequest(MarketRequest.RequestType.BUY, resource,
                            rand.nextInt(99)+1, pricingModel.calculatePrice()));
                    break;
                default:
                    break;
            }

            globalMarket.show();
            System.out.println("------------------------------------------");
            Thread.sleep(2000);
        }
    }

    public int calculateMarketQuantity(Resource resource, ArrayList<MarketRequest> globalMarket) {
        int quantity = 0;
        for (MarketRequest marketRequest : globalMarket){
            if (marketRequest.resource == resource) {
                quantity += marketRequest.quantity;
            }
        }
        return quantity;
    }
}
