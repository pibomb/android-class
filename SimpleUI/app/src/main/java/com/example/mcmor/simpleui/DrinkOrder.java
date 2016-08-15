package com.example.mcmor.simpleui;

/**
 * Created by mcmor on 2016-08-14.
 */
public class DrinkOrder {
    Drink drink;
    int lNumber = 0;
    int mNumber = 0;
    String ice = "REGULAR";
    String sugar = "REGULAR";
    String note = "";

    public DrinkOrder(Drink drink) {
        this.drink = drink;
    }
}
