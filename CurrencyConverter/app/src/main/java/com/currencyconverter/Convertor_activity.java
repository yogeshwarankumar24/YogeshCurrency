package com.currencyconverter;
import android.accessibilityservice.AccessibilityService;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.currencyconverter.CommonFunctions.CurrenyCommissioncount;
import static com.currencyconverter.CommonFunctions.df;
import static com.currencyconverter.Recent_activity.Recent_Conversionlist;

public class Convertor_activity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Declaration of progress bar
     */
    AppProgress objAppProgress;
    EditText Edit_FromAmount;
    TextView Txt_AmountEUR, Txt_AmountUSD,Txt_Amountname,Txt_Amountname2;
    LinearLayout Layout_Lastconvertion;
    TextView Txt_FromName, Txt_ToName;
    TextView Txt_Last_Commission, Txt_Last_debited, Txt_Last_Converted, Txt_Last_From, Txt_Last_To;
    String Processing_Amount,Processing_From,Processing_To;
    int Convertioncount;
    private AppSharedPreferences objAppSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convertor_activity);
        /**
         * Declaration of view and actions
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        objAppSharedPreferences = new AppSharedPreferences(this);
        Txt_Amountname = (TextView) findViewById(R.id.Amountname);
        Txt_Amountname2 = (TextView) findViewById(R.id.Amountname2);
        Txt_AmountEUR = (TextView) findViewById(R.id.AmountEUR);
        Txt_AmountUSD = (TextView) findViewById(R.id.AmountUSD);
        Layout_Lastconvertion = (LinearLayout) findViewById(R.id.Layout_Lastconvertion);
        Txt_FromName = (TextView) findViewById(R.id.Txt_FromName);
        Txt_ToName = (TextView) findViewById(R.id.Txt_ToName);
        Edit_FromAmount = (EditText) findViewById(R.id.Edit_FromAmount);
        Txt_Last_Commission = (TextView) findViewById(R.id.Txt_Last_Commission);
        Txt_Last_Converted = (TextView) findViewById(R.id.Txt_Last_Converted);
        Txt_Last_From = (TextView) findViewById(R.id.Txt_Last_From);
        Txt_Last_To = (TextView) findViewById(R.id.Txt_Last_To);
        Txt_Last_debited  = (TextView) findViewById(R.id.Txt_Last_Debited);
        TextView Txt_Viewmore = (TextView) findViewById(R.id.Txt_Viewmore);
        Txt_Viewmore.setOnClickListener(this);
        Txt_Viewmore.setTag("Viewmore");
        Txt_FromName.setTag("Option");
        Txt_ToName.setTag("Option2");
        Txt_FromName.setOnClickListener(this);
        Txt_ToName.setOnClickListener(this);
        Button But_Convert = (Button) findViewById(R.id.But_Convert);
        But_Convert.setOnClickListener(this);
        But_Convert.setTag("Convert");
        Convertioncount = objAppSharedPreferences.GetInt("Convertioncount");
        objAppProgress = new AppProgress(this);
        for (CurrencyvalueModal objvalue : Homepage.objlistCurrency) {
            if (Txt_Amountname.getText().equals(objvalue.getCurrencyname())) {
                Txt_AmountEUR.setText(objvalue.getCurrencyamount().toString());
            } else if (Txt_Amountname2.getText().equals(objvalue.getCurrencyname())) {
                Txt_AmountUSD.setText(objvalue.getCurrencyamount().toString());
            }
        }
        Loadlast();
    }
    /**
     * Method calls when click something inside the view
     */
    @Override
    public void onClick(View view) {
        if (view.getTag().toString().equals("Convert")) {
            PressConvertButton();
        } else if (view.getTag().toString().equals("Viewmore")) {
            PressViewmoreButton();
        } else if (view.getTag().toString().equals("Option")) {
            Alertlist();
        }else if (view.getTag().toString().equals("Option2")) {
            Alertlist2();
        }
    }
    /**
     * Method calls when click Convert button
     */
    private void PressConvertButton() {
        boolean valid = Appvalidation.Number(Edit_FromAmount, "Enter a valid amount");
        Processing_From = Txt_FromName.getText().toString();
        Processing_To = Txt_ToName.getText().toString();
        if (valid && Validate_Editext()) {
            Processing_Amount = Edit_FromAmount.getText().toString();
            Service_StartConvertion();
        }
    }
    /**
     * Method calls when showing List of currency
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
                if(!Txt_ToName.getText().toString().equals(items[item])) {
                    Txt_FromName.setText(items[item]);
                    Txt_Amountname.setText(items[item]);
                    Txt_AmountEUR.setText(Homepage.objlistCurrency.get(item).getCurrencyamount());
                }
                else {
                    CommonFunctions.Error_Dialog(Convertor_activity.this,"Cannot proceed with same Curreny Types");
                }
            }
        });
        alert = builder.create();
        alert.show();
    }
    /**
     * Method calls when showing List of currency
     */
    void Alertlist2()
    {
        AlertDialog alert;
        final ArrayList<String> itemsdef = new ArrayList<String>();
        for (CurrencyvalueModal objvalue: Homepage.objlistCurrency) {
            itemsdef.add(objvalue.getCurrencyname());
        }
        final String[] items = itemsdef.toArray(new String[itemsdef.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                if(!Txt_FromName.getText().toString().equals(items[item])) {
                    Txt_ToName.setText(items[item]);
                    Txt_Amountname2.setText(items[item]);
                    Txt_AmountUSD.setText(Homepage.objlistCurrency.get(item).getCurrencyamount());
                }
                else {
                    CommonFunctions.Error_Dialog(Convertor_activity.this,"Cannot proceed with same Curreny Types");
                }
            }
        });
        alert = builder.create();
        alert.show();
    }
    /**
     * Validate the edittext when the amount is sufficent to transfer or not
     */
    Double finalvalue;
    private boolean Validate_Editext()
    {
        for (CurrencyvalueModal objvalue: Homepage.objlistCurrency) {
            if(Processing_From.equals(objvalue.getCurrencyname()))
            {
                Double Presentvalue = Double.valueOf(objvalue.getCurrencyamount().toString());
                Double Currentvalue = Presentvalue - Double.valueOf(Edit_FromAmount.getText().toString());
                if(Convertioncount > CurrenyCommissioncount)
                    finalvalue = (Currentvalue - ((Double.valueOf(Edit_FromAmount.getText().toString()))* CommonFunctions.CurrenyCommission)/100);
                else
                    finalvalue = Currentvalue;
                if(finalvalue >= 0)
                    return true;
                else {
                    CommonFunctions.Error_Dialog(Convertor_activity.this,"Insufficient funds");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Called when click back button
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,Homepage.class));
    }

    /**
     * Show last transfer details
     */
    private void PressViewmoreButton() {
        startActivity(new Intent(this, Recent_activity.class));
    }
    /**
     * Api calls to convert the currency
     */
    private void Service_StartConvertion() {

        objAppProgress.Show();
        StringRequest postRequest =
                new StringRequest(Request.Method.GET ,ServerURLS.Convertcurrency+Processing_Amount+"-"+Processing_From+"/"+Processing_To+"/latest", Service_Convertion_Success(), CommonFunctions.Service_Convertion_Error(Convertor_activity.this,objAppProgress)) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        return params;
                    }
                };
        Volley.newRequestQueue(this).add(postRequest);
    }

    /**
     * Get response from the api and handel
     */
    private Response.Listener<String> Service_Convertion_Success()
    {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("response", "  "+ response);
                    Convertioncount++;
                    objAppSharedPreferences.PutInt("Convertioncount",Convertioncount);
                    JSONObject jsonResponse = new JSONObject(response);
                    String Amountstr = jsonResponse.get("amount").toString();
                    String Currencystr = jsonResponse.get("currency").toString();
                    Populate_Currency(Amountstr);
                }
                catch (NullPointerException e){
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * Populate the currency values and detect the commission fee
     * Transfer the currency to another currency based on selection
     */
    String Commfee;
    private void Populate_Currency(String Amount) {
        Txt_Last_From.setText("From : " + Processing_From);
        Txt_Last_To.setText("To : " + Processing_To);
        Txt_Last_Converted.setText("Transfered  Currency : " + Amount  +" "+ Processing_To);
        Txt_Last_debited.setText("Debited  Currency : " + Processing_Amount.toString() +" "+ Processing_From);
        for (CurrencyvalueModal objvalue : Homepage.objlistCurrency) {
            if (Processing_From.equals(objvalue.getCurrencyname())) {
                Double Presentvalue = Double.valueOf(objvalue.getCurrencyamount().toString());
                Txt_AmountEUR.setText(df.format(Presentvalue - Double.valueOf(Processing_Amount.toString())));
                objvalue.setCurrencyamount(df.format(Presentvalue - Double.valueOf(Processing_Amount.toString())));
                if (Convertioncount > CurrenyCommissioncount) {
                    Commfee = df.format((Double.valueOf(Processing_Amount.toString()) * CommonFunctions.CurrenyCommission) / 100);
                    Txt_Last_Commission.setText("Commission fee : " + Commfee  +" "+ Processing_From);
                    Double Presentvaluefee = Double.valueOf(objvalue.getCurrencyamount().toString());
                    Txt_AmountEUR.setText(df.format(Presentvaluefee - ((Double.valueOf(Processing_Amount.toString())) * CommonFunctions.CurrenyCommission) / 100));
                    objvalue.setCurrencyamount(df.format(Presentvaluefee - ((Double.valueOf(Processing_Amount.toString())) * CommonFunctions.CurrenyCommission) / 100));
                } else {
                    Commfee = "00.00";
                    Txt_Last_Commission.setText("Commission fee : " + "00.00"  +" "+ Processing_From);
                }
                break;
            }
        }
        for (CurrencyvalueModal objvalue : Homepage.objlistCurrency) {
            if (Processing_To.equals(objvalue.getCurrencyname())) {
                Double Presentvalue2 = Double.valueOf(objvalue.getCurrencyamount().toString());
                Txt_AmountUSD.setText(df.format(Presentvalue2 + Double.valueOf(Amount)));
                objvalue.setCurrencyamount(df.format(Presentvalue2 + Double.valueOf(Amount)));
                break;
            }
        }
        String uniqueid = String.valueOf (Recent_Conversionlist.size()+1);
        Recent_Conversionlist.add(new CurrencyModal(uniqueid,Commfee,Processing_From,Processing_To,Processing_Amount,Amount));
        objAppProgress.Hide();
    }
    /**
     * Populate the Recent currency values if available
     */
    void Loadlast()
    {
        if(Recent_Conversionlist.size() > 0) {
            Txt_Last_From.setText("From : " + Recent_Conversionlist.get(Recent_Conversionlist.size() - 1).getCurrencyFrom());
            Txt_Last_To.setText("To : " + Recent_Conversionlist.get(Recent_Conversionlist.size() - 1).getCurrencyTo());
            Txt_Last_Converted.setText("Transfered  Currency : " + Recent_Conversionlist.get(Recent_Conversionlist.size() - 1).getCurrencyToamount()+" " + Recent_Conversionlist.get(Recent_Conversionlist.size() - 1).getCurrencyTo());
            Txt_Last_debited.setText("Debited  Currency : " + Recent_Conversionlist.get(Recent_Conversionlist.size() - 1).getCurrencyFromamount()+" " + Recent_Conversionlist.get(Recent_Conversionlist.size() - 1).getCurrencyFrom());
            Txt_Last_Commission.setText("Commission fee : " + Recent_Conversionlist.get(Recent_Conversionlist.size() - 1).getCurrencyfee() +" " + Recent_Conversionlist.get(Recent_Conversionlist.size() - 1).getCurrencyFrom());
        }
    }


}
