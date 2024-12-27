package org.example;

import java.util.HashMap;
import java.util.Map;

public class PricingModel {
    private double basePrice; // базовая цена товара
    private Map<String, Integer> demand; // спрос по сегментам
    private Map<String, Integer> supply; // предложение по сегментам

    public PricingModel(double basePrice) {
        this.basePrice = basePrice;
        this.demand = new HashMap<>();
        this.supply = new HashMap<>();
    }

    public void setDemand(String segment, int quantity) {
        demand.put(segment, quantity);
    }

    public void setSupply(String segment, int quantity) {
        supply.put(segment, quantity);
    }

    public double calculatePrice() {
        int totalDemand = demand.values().stream().mapToInt(Integer::intValue).sum();
        int totalSupply = supply.values().stream().mapToInt(Integer::intValue).sum();
        if (totalSupply==0){ totalSupply++; }
        if (totalDemand==0){ totalDemand++; }

        if (totalDemand > totalSupply) {
            // Увеличиваем цену при высоком спросе
            return basePrice * (1 + (totalDemand - totalSupply) / (double) totalSupply);
        } else if (totalDemand < totalSupply) {
            // Уменьшаем цену при низком спросе
            return basePrice * (1 - (totalSupply - totalDemand) / (double) totalSupply);
        }
        return basePrice; // Если спрос равен предложению
    }
}
