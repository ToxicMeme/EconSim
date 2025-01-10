package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    ArrayList<Resource> allResources = new ArrayList<>();
    ArrayList<Planet> allPlanets = new ArrayList<>();
    GlobalMarket globalMarket = new GlobalMarket();
    Random rand = new Random();

    public Game() throws InterruptedException {
        allResources.add(new Resource(Resource.ResourceName.NRGC, Resource.ResourceType.BASE, 10.0));
        allResources.add(new Resource(Resource.ResourceName.MINE, Resource.ResourceType.BASE, 10.0));
        allResources.add(new Resource(Resource.ResourceName.FOOD, Resource.ResourceType.BASE, 10.0));
        allResources.add(new Resource(Resource.ResourceName.ALOY, Resource.ResourceType.SECONDARY, 10.0));
        allResources.add(new Resource(Resource.ResourceName.TMC, Resource.ResourceType.SECONDARY, 10.0));
        allResources.add(new Resource(Resource.ResourceName.INFL, Resource.ResourceType.ABSTRACT, 10.0));
        allResources.add(new Resource(Resource.ResourceName.UNTY, Resource.ResourceType.ABSTRACT, 10.0));
        allResources.add(new Resource(Resource.ResourceName.SPTS, Resource.ResourceType.ABSTRACT, 10.0));
        allResources.add(new Resource(Resource.ResourceName.DRKM, Resource.ResourceType.STRATEGIC, 10.0));
        allResources.add(new Resource(Resource.ResourceName.NANI, Resource.ResourceType.STRATEGIC, 10.0));

        allPlanets.add(new Planet("Орион", Planet.PlanetType.ROCKY));
        allPlanets.add(new Planet("Ujyjkeke-35b", Planet.PlanetType.GREEN));
        allPlanets.add(new Planet("Elysium-11", Planet.PlanetType.BALANCE));
        allPlanets.add(new Planet("Vespera Prime", Planet.PlanetType.FACTORY));

        for(Planet planet : allPlanets){
            System.out.println(planet.toString() +
                    "\n" + planet.description +
                    "\n - Количество минералов: " + planet.mineCapacity +
                    "\n - Количество еды: " + planet.basicFood);
        }

        while (true) {
            //Resource resource = allResources.get(rand.nextInt(allResources.size()));
            //handlingGlobalMarket(resource);
            Thread.sleep(2000);
        }
    }

    public void handlingGlobalMarket(Resource resource) {
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
