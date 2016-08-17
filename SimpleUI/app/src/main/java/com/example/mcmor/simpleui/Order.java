package com.example.mcmor.simpleui;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by mcmor on 2016-08-09.
 */
@ParseClassName("Order")
public class Order extends ParseObject {
    final static String NOTE_COL = "note";
    final static String STOREINFO = "storeInfo";
    final static String DRINKORDERS_COL = "drinkOrderList";

    public int getTotal() {
        int total = 0;
        for(DrinkOrder drinkOrder : getDrinkOrderList()) {
            total += drinkOrder.getlNumber() * drinkOrder.getDrink().getlPrice();
            total += drinkOrder.getmNumber() * drinkOrder.getDrink().getmPrice();
        }

        return total;
    }

    public String getNote() {
        return getString(NOTE_COL);
    }

    public void setNote(String note) {
        this.put(NOTE_COL, note);
    }

    public String getStoreInfo() {
        return getString(STOREINFO);
    }

    public void setStoreInfo(String storeInfo) {
        this.put(STOREINFO, storeInfo);
    }

    public List<DrinkOrder> getDrinkOrderList() {
        return getList(DRINKORDERS_COL);
    }

    public void setDrinkOrderList(List<DrinkOrder> drinkOrderList) {
        this.put(DRINKORDERS_COL, drinkOrderList);
    }

    public static ParseQuery<Order> getQuery() {
        return ParseQuery.getQuery(Order.class)
                .include(DRINKORDERS_COL)
                .include(DRINKORDERS_COL + '.' + DrinkOrder.DRINK_COL);
    }
}
