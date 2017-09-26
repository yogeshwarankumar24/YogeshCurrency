package com.currencyconverter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

import java.text.DecimalFormat;

/**
 * Created by Falconnect on 10-08-2017.
 */

public class CommonFunctions {


    /**
     * Static values to access commission inside app
     */
    public static double CurrenyCommission = 0.70;
    /**
     * Static values to access count after start detecting commission inside app
     */
    public static int CurrenyCommissioncount = 5;
    /**
     * Static values to access Double format inside app
     */
    public static DecimalFormat df = new DecimalFormat("#.##");
    /**
     * Static method which calls while api getting error
     */
    public static Response.ErrorListener Service_Convertion_Error(final Activity objActivity, final AppProgress objAppProgress)
    {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                objAppProgress.Hide();
                error.printStackTrace();
                VolleyLog.d("", "Error: " + error.getMessage());
                NetworkError_Dialog(objActivity);
            }
        };
    }
    /**
     * Static method which Shows when the Api get error
     */
    public static void NetworkError_Dialog(final Activity objActivity){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(objActivity,R.style.Theme_AppCompat_Light_Dialog_Alert);
        alertDialogBuilder.setTitle("Network Error");
        alertDialogBuilder.setMessage("Please Try Again Later.");
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                       // alertDialog.hide();
                    }
                });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    /**
     * Static method which calls to showing some contents
     */
    public static void Error_Dialog(final Activity objActivity,final String Content){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(objActivity,R.style.Theme_AppCompat_Light_Dialog_Alert);
        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage(Content);
        final AlertDialog alertDialog;
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                       // alertDialog.hide();
                    }
                });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
