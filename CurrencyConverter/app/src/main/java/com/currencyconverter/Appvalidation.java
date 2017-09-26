package com.currencyconverter;

import android.widget.EditText;

/**
 * Created by Falconnect on 10-08-2017.
 */

public class Appvalidation {
    /**
     * Validate the Editext data is null or empty
     */
    public static boolean Number(EditText objName, String Value)
    {
        if (objName.getText().toString() == "")
        {
            objName.setError(Value);
            objName.requestFocus();
            return false;
        }

        /**
         * Validate the Editext data is Length and set error.
         */
        if (objName.getText().length() <= 0)
        {
            objName.setError(Value);
            objName.requestFocus();
            return false;
        }
        return true;
    }
}
