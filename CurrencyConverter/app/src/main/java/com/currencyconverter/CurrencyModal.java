package com.currencyconverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Falconnect on 11-08-2017.
 */


public class CurrencyModal {
    private String uniqueid;
    private String Currencyfee;
    private String CurrencyFrom;
    private String CurrencyTo;
    private String CurrencyFromamount;
    private String CurrencyToamount;
    private String Time;

    public CurrencyModal() {
    }

    /**
     * Modal class for Currency
     */
    public CurrencyModal(String uniqueid, String Currencyfee, String CurrencyFrom, String CurrencyTo, String CurrencyFromamount, String CurrencyToamount) {
        this.uniqueid = uniqueid;
        this.Currencyfee = Currencyfee;
        this.CurrencyFrom = CurrencyFrom;
        this.CurrencyTo = CurrencyTo;
        this.CurrencyFromamount = CurrencyFromamount;
        this.CurrencyToamount = CurrencyToamount;
        DateFormat df = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        this.Time =  df.format(Calendar.getInstance().getTime());
    }

    /**
     * Modal class for Currency GET and SET method
     */
    public String getuniqueid() {
        return uniqueid;
    }

    public void setuniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getCurrencyfee() {
        return Currencyfee;
    }

    public void setCurrencyfee(String Currencyfee) {
        this.Currencyfee = Currencyfee;
    }


    public String getCurrencyFrom() {
        return CurrencyFrom;
    }

    public void setCurrencyFrom(String CurrencyFrom) {
        this.CurrencyFrom = CurrencyFrom;
    }

    public String getCurrencyTo() {
        return CurrencyTo;
    }

    public void setCurrencyTo(String CurrencyTo) {
        this.CurrencyTo = CurrencyTo;
    }

    public String getCurrencyToamount() {
        return CurrencyToamount;
    }

    public void setCurrencyToamount(String CurrencyToamount) {
        this.CurrencyToamount = CurrencyToamount;
    }

    public String getCurrencyFromamount() {
        return CurrencyFromamount;
    }

    public String getTime() {
        return Time;
    }

    public void setCurrencyFromamount(String CurrencyFromamount) {
        this.CurrencyFromamount = CurrencyFromamount;
    }
}
