package com.currencyconverter;

/**
 * Created by Falconnect on 11-08-2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class RecentAdapter extends BaseAdapter {
    private Context context;
    private List<CurrencyModal> launchmodel;

    public RecentAdapter(Context context, List<CurrencyModal> launchmodel) {
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
        convertView = mInflater.inflate(R.layout.recent_list, null);
        TextView Txt_Last_From = (TextView) convertView.findViewById(R.id.Txt_Last_From);
        Txt_Last_From.setText("From : "+ launchmodel.get(position).getCurrencyFrom());
        TextView Txt_Last_To = (TextView) convertView.findViewById(R.id.Txt_Last_To);
        Txt_Last_To.setText("To : "+ launchmodel.get(position).getCurrencyTo());
        TextView Txt_Last_Converted = (TextView) convertView.findViewById(R.id.Txt_Last_Converted);
        Txt_Last_Converted.setText("Transfered Currency : "+ launchmodel.get(position).getCurrencyToamount() +" "+ launchmodel.get(position).getCurrencyTo());
        TextView Txt_Last_Debited = (TextView) convertView.findViewById(R.id.Txt_Last_Debited);
        Txt_Last_Debited.setText("Debited Currency : "+ launchmodel.get(position).getCurrencyFromamount() +" "+ launchmodel.get(position).getCurrencyFrom());
        TextView Txt_Last_Commission = (TextView) convertView.findViewById(R.id.Txt_Last_Commission);
        Txt_Last_Commission.setText("Commission fee : "+ launchmodel.get(position).getCurrencyfee() +" "+ launchmodel.get(position).getCurrencyFrom());
        TextView Txt_Last_time = (TextView) convertView.findViewById(R.id.Txt_Last_time);
        Txt_Last_time.setText("Transfered on : " + launchmodel.get(position).getTime());
        System.out.println("--LIst Size In Inflater--" + launchmodel.size());
        return convertView;
    }
}
