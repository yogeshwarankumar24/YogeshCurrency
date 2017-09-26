package com.currencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Recent_activity extends AppCompatActivity {

    public static List<CurrencyModal> Recent_Conversionlist = new ArrayList<CurrencyModal>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recent_activity);

        /**
         * List out all the recent currency conversion
         */
        ListView objListView = (ListView) findViewById(R.id.Listview);
        objListView.setAdapter(new RecentAdapter(Recent_activity.this,Recent_Conversionlist));
    }

    /**
     * Called when click back button
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,Homepage.class));
    }
}
