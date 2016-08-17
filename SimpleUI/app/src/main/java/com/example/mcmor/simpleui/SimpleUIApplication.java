package com.example.mcmor.simpleui;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by mcmor on 2016-08-15.
 */
public class SimpleUIApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Drink.class);
        ParseObject.registerSubclass(DrinkOrder.class);
        ParseObject.registerSubclass(Order.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("QPlj5ZjlAdk0Gn8IS3jLlztea3wYqQII3JV7tGtl")
                .server("https://parseapi.back4app.com/")
                .clientKey("WKwmCx446jWqcEGPcLtvHWULFwry2ILwefkxw80Q")
                .build()
        );
    }
}
