package org.example;

public class Resource {
    public double basePrice;
    public ResourceName name;

    public Resource(ResourceName name, ResourceType resourceType, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    enum ResourceName {
        NRGC("Энергокредиты"), MINE("Минералы"), FOOD("Пища"),
        ALOY("Сплавы"), TMC("Технические материалы"), INFL("Влияние"),
        UNTY("Единство"), SPTS("Научные очки"), DRKM("Темная материя"),
        NANI("Наниты");
        private final String name;
        ResourceName(String name){
            this.name = name;
        }
        public String getName(){ return name;}
    }

    enum ResourceType
    {
        BASE,
        SECONDARY,
        ABSTRACT,
        STRATEGIC
    }

    @Override
    public String toString(){
        return name.getName();
    }
}

