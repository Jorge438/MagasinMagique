package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    private void updateQuality(Item item){
        // si l'objet n'est pas un Comté ni un Pass VIP
        if (!item.name.equals("Comté") && !item.name.equals("Pass VIP Concert")) {
            increaseOrDecreaseQuality("decrease",item);
        } else {
            // sinon on gère le cas du Comté et du Pass VIP
            increaseOrDecreaseQuality("increase",item);

                // ensuite on gère les régles spécifique au Pass VIP
                // si l'objet est un Pass Vip
                if (item.name.equals("Pass VIP Concert")) {
                    updatePassVIPConcertQuality(item);
                }
            }
        }

    private void updatePassVIPConcertQuality(Item passVIP){
        // si son SellIn et inférieur à 11
        if (passVIP.sellIn < 11) {
            increaseOrDecreaseQuality("increase",passVIP);
        }
        // si son SellIn et inférieur à 6
        if (passVIP.sellIn < 6) {
            increaseOrDecreaseQuality("increase",passVIP);
        }
    }

    private void increaseOrDecreaseQuality(String action, Item item){
        if(action.equals("increase")){
            //si sa quality est inférieur à 50 et donc peut être augmentée une nouvelle fois
            if (item.quality < 50) {
                //on augmente la qualité de 1
                item.quality = item.quality + 1;
            }
        }
        if(action.equals("decrease")){
            // si la qualité et supérieur à 0
            if (item.quality > 0) {
                //si l'objet n'est pas de la kryptonite
                if (!item.name.equals("Kryptonite")) {
                    // la qualité baisse de 1
                    item.quality = item.quality - 1;
                }
                if(item.name.equals("Pouvoirs magiques")){
                    item.quality = item.quality - 1;
                }
            }
        }
    }

    private void decreaseSellIn(Item item){
        //si l'objet n'est pas de la Kryptonite alors on diminue le sellIn
        if (!item.name.equals("Kryptonite")) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void updateSellIn(Item item){
        decreaseSellIn(item);

        //Si l'objet à un sellIn inférieur à 0
        if (item.sellIn < 0) {

            //Si l'objet n'est pas un Comté
            if (!item.name.equals("Comté")) {
                    //Si l'objet n'est pas un pass VIP
                    if (!item.name.equals("Pass VIP Concert")) {
                            if (item.quality > 0) {
                                if (!item.name.equals("Kryptonite")) {
                                    item.quality = item.quality - 1;
                                }
                            }
                    }
                    //Sinon si c'est un Pass VIP la qualité passe à 0
                    else {
                            item.quality = item.quality - item.quality;
                    }
            }
            //Sinon si c'est un un Comté on augmente la qualité de 1
            else {
                    increaseOrDecreaseQuality("increase",item);
            }

        }
    }

    public void updateItems() {

        for (int i = 0; i < items.length; i++) {

            // PARTIE QUALITY
            // PARTIE QUALITY
            updateQuality(items[i]);

            // PARTIE SELLIN
            // PARTIE SELLIN
            updateSellIn(items[i]);
        }
    }
}
