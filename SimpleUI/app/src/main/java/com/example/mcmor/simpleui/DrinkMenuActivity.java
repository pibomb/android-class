package com.example.mcmor.simpleui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class DrinkMenuActivity extends AppCompatActivity implements DrinkOrderDialog.OnFragmentInteractionListener {

    ListView drinkMenuListView;
    TextView totalTextView;

    String[] names = {"冬瓜紅茶", "玫瑰鹽奶蓋紅茶", "珍珠紅茶拿鐵", "紅茶拿鐵"};
    int[] lPrices = {35, 45, 55, 45};
    int[] mPrices = {25, 35, 45, 35};
    int[] imageIds = {R.drawable.drink1, R.drawable.drink2, R.drawable.drink3, R.drawable.drink4};

    int total = 0;
    List<Drink> drinkList = new ArrayList<Drink>();
    List<DrinkOrder> drinkOrderList = new ArrayList<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);

        setData();

        drinkMenuListView = (ListView) findViewById(R.id.drinkMenuListView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        drinkMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drink drink = (Drink) parent.getAdapter().getItem(position);
//                total += drink.mPrice;
//                totalTextView.setText(String.valueOf(total));
                showDrinkOrderDialog(drink);
            }
        });

        setupDrinkMenu();

        Log.d("DEBUG", "DrinkMenuActivity OnCreate");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setData() {
        for (int i = 0; i < names.length; i++) {
            Drink drink = new Drink();
            drink.name = names[i];
            drink.lPrice = lPrices[i];
            drink.mPrice = mPrices[i];
            drink.imageId = imageIds[i];
            drinkList.add(drink);
        }
    }

    private void setupDrinkMenu() {
        DrinkAdapter adapter = new DrinkAdapter(this, drinkList);
        drinkMenuListView.setAdapter(adapter);
    }

    public void done(View view) {
        Intent intent = new Intent();
        intent.putExtra("result", String.valueOf(total));

        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view) {
        Intent intent = new Intent();

        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void showDrinkOrderDialog(Drink drink) {
        DrinkOrder order = null;
        for(DrinkOrder drinkOrder : drinkOrderList) {
            if(drinkOrder.drink.name.equals(drink.name)) {
                order = drinkOrder;
                break;
            }
        }

        if(order == null)
            order = new DrinkOrder(drink);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        DrinkOrderDialog dialog = DrinkOrderDialog.newInstance(order);

//        ft.replace(R.id.root, dialog);
//        ft.commit();
        dialog.show(ft, "DrinkOrderDialog");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Log.d("DEBUG", "DrinkMenuActivity OnStart");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "DrinkMenu Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.mcmor.simpleui/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("DEBUG", "DrinkMenuActivity OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("DEBUG", "DrinkMenuActivity OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "DrinkMenu Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.mcmor.simpleui/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        Log.d("DEBUG", "DrinkMenuActivity OnStop");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DEBUG", "DrinkMenuActivity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("DEBUG", "DrinkMenuActivity OnRestart");
    }

    @Override
    public void onDrinkOrderResult(DrinkOrder drinkOrder) {
        boolean flag = false;

        for(int i = 0; i < drinkOrderList.size(); i++) {
            if(drinkOrderList.get(i).drink.name.equals(drinkOrder.drink.name)) {
                drinkOrderList.set(i, drinkOrder);
                flag = true;
                break;
            }
        }

        if(!flag)
            drinkOrderList.add(drinkOrder);

        updateTotalTextView();
    }

    private void updateTotalTextView() {
        Log.d("DEBUG", "updateTotalTextView()");

        int total = 0;
        for(DrinkOrder drinkOrder : drinkOrderList) {
            total += drinkOrder.lNumber * drinkOrder.drink.lPrice;
            total += drinkOrder.mNumber * drinkOrder.drink.mPrice;
        }

        totalTextView.setText(String.valueOf(total));
    }
}