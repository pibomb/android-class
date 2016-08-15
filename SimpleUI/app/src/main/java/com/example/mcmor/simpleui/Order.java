package com.example.mcmor.simpleui;

import java.util.List;

/**
 * Created by mcmor on 2016-08-09.
 */
public class Order {
    String note;
    String storeInfo;
    String drink;
    List<DrinkOrder> drinkOrderList;

    public int getTotal() {
        int total = 0;
        for(DrinkOrder drinkOrder : drinkOrderList) {
            total += drinkOrder.lNumber * drinkOrder.drink.lPrice;
            total += drinkOrder.mNumber * drinkOrder.drink.mPrice;
        }

        return total;
    }
}
