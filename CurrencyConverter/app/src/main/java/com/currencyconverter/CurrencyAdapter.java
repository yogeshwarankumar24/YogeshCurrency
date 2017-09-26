package com.currencyconverter;

/**
 * Created by Falconnect on 11-08-2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class CurrencyAdapter extends BaseAdapter {
    private Context context;
    private List<CurrencyvalueModal> launchmodel;

    public CurrencyAdapter(Context context, List<CurrencyvalueModal> launchmodel) {
        this.context = context;
        this.launchmodel = launchmodel;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return launchmodel.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return launchmodel.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        // TODO Auto-generated method stub
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.currencygrid, null);
        TextView Amountname = (TextView) convertView.findViewById(R.id.Amountname);
        Amountname.setText(launchmodel.get(position).getCurrencyname());
        TextView Amount = (TextView) convertView.findViewById(R.id.Amount);
        Amount.setText(launchmodel.get(position).getCurrencyamount());
        System.out.println("--LIst Size In Inflater--" + launchmodel.size());
        return convertView;
    }
}
