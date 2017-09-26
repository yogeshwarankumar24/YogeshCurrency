package com.currencyconverter;

/**
 * Created by Falconnect on 11-08-2017.
 */


public class CurrencyvalueModal {
    private String uniqueid;
    private String Currencyname;
    private String Currencyamount;

    public CurrencyvalueModal() {
    }
    /**
     * Modal class for Currency
     */
    public CurrencyvalueModal(String uniqueid, String Currencyname, String Currencyamount) {
        this.uniqueid = uniqueid;
        this.Currencyname = Currencyname;
        this.Currencyamount = Currencyamount;
    }

    /**
     * Modal class for Currency value GET and SET method
     */
    public String getuniqueid() {
        return uniqueid;
    }

    public void setuniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getCurrencyname() {
        return Currencyname;
    }

    public void setCurrencyname(String Currencyname) {
        this.Currencyname = Currencyname;
    }

    public String getCurrencyamount() {
        return Currencyamount;
    }

    public void setCurrencyamount(String Currencyamount) {
        this.Currencyamount = Currencyamount;
    }
}
