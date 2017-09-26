package com.currencyconverter;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by Falconnect on 10-08-2017.
 */

public class AppProgress {

    /**
     * Progress bar loading when task awaiting.
     */
    ProgressDialog objProgressDialog;
    public AppProgress(Activity objActivity)
    {

        objProgressDialog = new ProgressDialog(objActivity);
        objProgressDialog.setMessage("Processing...");
        objProgressDialog.setIndeterminate(false);
        objProgressDialog.setCancelable(false);
    }
    /**
     * Progress bar Showing
     */
    public void Show()
    {
        objProgressDialog.show();
    }

    /**
     * Progress bar Hiding
     */
    public void Hide()
    {
        objProgressDialog.hide();
    }
}
