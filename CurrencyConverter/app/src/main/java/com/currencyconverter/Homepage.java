package com.currencyconverter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.currencyconverter.CommonFunctions.df;

public class Homepage extends AppCompatActivity implements View.OnClickListener {
    /**
     * Home page that list out all the avaliable currency and balance
     */
    public static List<CurrencyvalueModal> objlistCurrency = new ArrayList<CurrencyvalueModal>();
    LinearLayout Layout_Addmoney,Layout_Addcurrency;
    TextView Txt_selectcurrency;
    GridView objGridView;
    EditText Edit_Amount;
    TextView Txt_Amountname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        objGridView = (GridView) findViewById(R.id.Gridview);
        if(objlistCurrency.size() == 0) {
            LoadDefaultvalue();
            AppSharedPreferences objAppSharedPreferences = new AppSharedPreferences(this);
            objAppSharedPreferences.PutInt("Convertioncount",0);
        }
        objGridView.setAdapter(new CurrencyAdapter(Homepage.this,objlistCurrency));
        Button But_Convert = (Button) findViewById(R.id.But_Convert);
        But_Convert.setOnClickListener(this);
        But_Convert.setTag("Convert");
        Button But_viewall = (Button) findViewById(R.id.But_viewall);
        But_viewall.setOnClickListener(this);
        But_viewall.setTag("Viewmore");
        Button But_Addmoney = (Button) findViewById(R.id.But_Addmoney);
        But_Addmoney.setOnClickListener(this);
        But_Addmoney.setTag("But_Addmoney");
        Button But_Addcurrency = (Button) findViewById(R.id.But_Addcurrency);
        But_Addcurrency.setOnClickListener(this);
        But_Addcurrency.setTag("But_Addcurrency");
        Button But_Cancel = (Button) findViewById(R.id.But_Cancel);
        But_Cancel.setOnClickListener(this);
        But_Cancel.setTag("Cancel_money");
        Button But_Cancel2 = (Button) findViewById(R.id.But_Cancel2);
        But_Cancel2.setOnClickListener(this);
        But_Cancel2.setTag("Cancel_currency");
        Button But_Submit = (Button) findViewById(R.id.But_Submit);
        But_Submit.setOnClickListener(this);
        But_Submit.setTag("Submit_money");
        Button But_Submit2 = (Button) findViewById(R.id.But_Submit2);
        But_Submit2.setOnClickListener(this);
        But_Submit2.setTag("Submit_currency");
        Txt_Amountname = (TextView) findViewById(R.id.Txt_Amountname);
        Txt_Amountname.setOnClickListener(this);
        Txt_Amountname.setTag("Txt_Amountname");

        Layout_Addmoney = (LinearLayout) findViewById(R.id.Layout_Addmoney);
        Layout_Addcurrency = (LinearLayout) findViewById(R.id.Layout_Addcurrency);
        Txt_selectcurrency = (TextView) findViewById(R.id.Txt_selectcurrency);
        Txt_selectcurrency.setOnClickListener(this);
        Txt_selectcurrency.setTag("Txt_selectcurrency");
        Edit_Amount = (EditText) findViewById(R.id.Edit_Amount);
    }
    /**
     * Showing list of currency avaliable
     */
    void Alertlist()
    {
        AlertDialog alert;
        ArrayList<String> itemsdef = new ArrayList<String>();
        for (CurrencyvalueModal objvalue: Homepage.objlistCurrency) {
            itemsdef.add(objvalue.getCurrencyname());
        }
        final String[] items = itemsdef.toArray(new String[itemsdef.size()]);
        //(String[]) itemsdef.toArray();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                    Txt_Amountname.setText(items[item]);
            }
        });
        alert = builder.create();
        alert.show();
    }
    /**
     * Method calls when click something inside the view
     */
    @Override
    public void onClick(View view) {
        if (view.getTag().toString().equals("Convert")) {
            startActivity(new Intent(this,Convertor_activity.class));
        } else if (view.getTag().toString().equals("Viewmore")) {
            startActivity(new Intent(this,Recent_activity.class));
        } else if (view.getTag().toString().equals("But_Addmoney")) {
            Layout_Addmoney.setVisibility(View.VISIBLE);
        } else if (view.getTag().toString().equals("But_Addcurrency")) {
            Layout_Addcurrency.setVisibility(View.VISIBLE);
        }else if (view.getTag().toString().equals("Cancel_money")) {
            Layout_Addmoney.setVisibility(View.GONE);
        } else if (view.getTag().toString().equals("Cancel_currency")) {
            Layout_Addcurrency.setVisibility(View.GONE);
        }else if (view.getTag().toString().equals("Submit_money")) {
            PressSubmitMoney();
        } else if (view.getTag().toString().equals("Submit_currency")) {
            PressSubmitCurrency();
        } else if (view.getTag().toString().equals("Txt_selectcurrency")) {
            LoadCurrency();
        } else if (view.getTag().toString().equals("Txt_Amountname")) {
            Alertlist();
        }
    }

    /**
     * Called when click back button
     */
    @Override
    public void onBackPressed() {
        if (Layout_Addcurrency.getVisibility() == View.VISIBLE)
            Layout_Addcurrency.setVisibility(View.GONE);
        else if (Layout_Addmoney.getVisibility() == View.VISIBLE)
            Layout_Addmoney.setVisibility(View.GONE);
        else
            moveTaskToBack(true);
    }

    /**
     * Called when click Submit and adding money to the wallet
     */
    private void PressSubmitMoney() {
        if (Txt_Amountname.getText().toString() == null || Txt_Amountname.getText().toString().isEmpty()) {
            CommonFunctions.Error_Dialog(Homepage.this,"Please select the Currency to proceed.");
        } else {
            boolean valid = Appvalidation.Number(Edit_Amount, "Enter a valid amount");
            if (valid) {
                for (CurrencyvalueModal objvalue : Homepage.objlistCurrency) {
                    if (Txt_Amountname.getText().equals(objvalue.getCurrencyname())) {
                        Double Presentvalue = Double.valueOf(objvalue.getCurrencyamount().toString());
                        objvalue.setCurrencyamount(df.format(Presentvalue + Double.valueOf(Edit_Amount.getText().toString())));
                        break;
                    }
                }
                Layout_Addmoney.setVisibility(View.GONE);
            }
        }
    }
    /**
     * Called when click Submit and adding currency to the wallet
     */
    void LoadCurrency()
    {
        final ArrayList<String> itemsdef = new ArrayList<String>();
        for (CurrencyvalueModal objvalue: Homepage.objlistCurrency) {
            itemsdef.add(objvalue.getCurrencyname());
        }
        final String[] currencyCodes = {"AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BOV", "BRL", "BSD", "BTN", "BWP", "BYR", "BZD", "CAD", "CDF", "CHE", "CHF", "CHW", "CLF", "CLP", "CNY", "COP", "COU", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS", "INR", "IQD", "IRR", "ISK", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LTL", "LVL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRO", "MUR", "MVR", "MWK", "MXN", "MXV", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "SSP", "STD", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "USN", "USS", "UYI", "UYU", "UZS", "VEF", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XBA", "XBB", "XBC", "XBD", "XCD", "XDR", "XFU", "XOF", "XPD", "XPF", "XPT", "XTS", "XXX", "YER", "ZAR", "ZMW"};
        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Make your selection");
        builder.setItems(currencyCodes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                if(!itemsdef.contains(currencyCodes[item])) {
                    Txt_selectcurrency.setText(currencyCodes[item]);
                }
                else {
                    CommonFunctions.Error_Dialog(Homepage.this,"All ready Exist");
                }
            }
        });
        alert = builder.create();
        alert.show();

    }
    /**
     * Called when click Submit and adding money to the wallet
     */
    private void PressSubmitCurrency()
    {
        if(Txt_selectcurrency.getText().toString() == null || Txt_selectcurrency.getText().toString().isEmpty())
        {
            CommonFunctions.Error_Dialog(Homepage.this,"Please select the Currency to proceed.");
        } else {
            String id = String.valueOf(objlistCurrency.size() + 1);
            objlistCurrency.add(new CurrencyvalueModal(id, Txt_selectcurrency.getText().toString(), "00.00"));
            objGridView.setAdapter(new CurrencyAdapter(Homepage.this, objlistCurrency));
            Layout_Addcurrency.setVisibility(View.GONE);
        }
    }
    /**
     * Loading default currency values
     */
    void LoadDefaultvalue()
    {
        objlistCurrency.add(new CurrencyvalueModal("1","EUR","1000.00"));
        objlistCurrency.add(new CurrencyvalueModal("2","USD","00.00"));
        objlistCurrency.add(new CurrencyvalueModal("3","JPY","00.00"));
    }
}
