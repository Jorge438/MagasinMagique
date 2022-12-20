package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    private void decreaseSellIn(Item item){
        //si l'objet n'est pas de la Kryptonite alors on diminue le sellIn
        if (!item.name.equals("Kryptonite")) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void increaseOrDecreaseQuality(String action, Item item, int nb){
        if(action.equals("increase")){
            //si sa quality est inférieur à 50 et donc peut être augmentée une nouvelle fois
            if (item.quality < 50) {
                //on augmente la qualité de 1
                item.quality = item.quality + nb;
            }
        }
        if(action.equals("decrease")){
            // si la qualité et supérieur à 0
            if (item.quality > 0) {
                //si l'objet n'est pas de la kryptonite
                if (!item.name.equals("Kryptonite")) {
                    // la qualité baisse de 1
                    item.quality = item.quality - nb;
                }
            }
        }
    }

    private void dealComteQuality(Item item){
        int valueIncreaseComte = 1;
        if(item.name.equals("Comté")){
            if(item.sellIn > 0){
                //La qualité augmente
                increaseOrDecreaseQuality("increase",item,valueIncreaseComte);
            } else{
                //la qualité augmente deux fois plus vite
                increaseOrDecreaseQuality("increase",item,valueIncreaseComte*2);
            }
        }
    }

    private void dealPassVIPQuality(Item item){
        if(item.name.equals("Pass VIP Concert")){
            if(item.sellIn>10){
                increaseOrDecreaseQuality("increase",item,1);
            }
            if (item.sellIn<11 && item.sellIn>5){
                increaseOrDecreaseQuality("increase",item,2);
            }
            if (item.sellIn<6 && item.sellIn>0){
                increaseOrDecreaseQuality("increase",item,3);
            }
            if (item.sellIn<1 && item.quality != 0){
                increaseOrDecreaseQuality("decrease",item, item.quality);
            }
        }
        if(item.quality>50){
            item.quality = 50;
        }
    }

    private void dealPouvoirsMagiquesQuality(Item item){
        int valueDecreasePouvoirsMagiques = 2;
            if(item.sellIn > 0){
                //La qualité augmente
                increaseOrDecreaseQuality("decrease",item,valueDecreasePouvoirsMagiques);
            } else{
                //la qualité augmente deux fois plus vite
                increaseOrDecreaseQuality("decrease",item,valueDecreasePouvoirsMagiques*2);
            }
    }

    private void dealOtherItemQuality(Item item){
        int valueDecreaseOtherItem = 1;
        if(item.sellIn > 0){
            increaseOrDecreaseQuality("decrease",item,valueDecreaseOtherItem);
        }else {
            increaseOrDecreaseQuality("decrease",item,valueDecreaseOtherItem*2);
        }
    }

    public void updateItems() {

        for (int i = 0; i < items.length; i++) {
            switch (items[i].name){
                case "Comté":
                    //la qualité du comté est gérée
                    dealComteQuality(items[i]);
                    //Le sellIn Baisse
                    decreaseSellIn(items[i]);
                    break;
                case "Pass VIP Concert":
                    //la qualité du pass est gérée
                    dealPassVIPQuality(items[i]);
                    //Le sellIn Baisse
                    decreaseSellIn(items[i]);
                    break;
                case "Kryptonite":
                    //Le sellIn Baisse
                    decreaseSellIn(items[i]);
                    break;
                case "Pouvoirs magiques":
                    //la qualité des pouvoirs magiques sont gérées
                    dealPouvoirsMagiquesQuality(items[i]);
                    //Le sellIn Baisse
                    decreaseSellIn(items[i]);
                    break;
                default:
                    //la qualité baisse
                    dealOtherItemQuality(items[i]);
                    //Le sellIn Baisse
                    decreaseSellIn(items[i]);
                    break;
            }
        }
    }
}
