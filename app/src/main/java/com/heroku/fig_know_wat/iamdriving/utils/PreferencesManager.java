package com.heroku.fig_know_wat.iamdriving.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.heroku.fig_know_wat.iamdriving.R;

/**
 * Created by Alex on 13/12/15.
 */
public class PreferencesManager {
    private Context context = null;
    private SharedPreferences.Editor editor;

    //constants
    private static final String I_AM_DRIVING_PREFERENCE = "I_AM_DRIVING_PREFERENCE";
    private static final String SMS_TEXT = "SMS_TEXT";
    private static final String IS_DRIVING = "IS_DRIVING";
    private static final String IS_SEND_SMS = "IS_SEND_SMS";


    public PreferencesManager(Context context) {
        this.context = context;
        SharedPreferences settings = context.getSharedPreferences(I_AM_DRIVING_PREFERENCE, 0);
        editor = settings.edit();
    }

    public void setSmsText(String smsText) {
        editor.putString(SMS_TEXT, smsText);
        editor.commit();
    }

    public void setIsDriving(boolean isDriving) {
        editor.putBoolean(IS_DRIVING, isDriving);
        editor.commit();
    }

    public void setIsSendSms(boolean isSendSms) {
        editor.putBoolean(IS_SEND_SMS, isSendSms);
        editor.commit();
    }

    public String getSmsText() {
        return context.getSharedPreferences(I_AM_DRIVING_PREFERENCE, 0).getString(SMS_TEXT, context.getString(R.string.sms_text_default));
    }

    public boolean isDriving() {
        return context.getSharedPreferences(I_AM_DRIVING_PREFERENCE, 0).getBoolean(IS_DRIVING, false);
    }

    public boolean isSendSms() {
        return context.getSharedPreferences(I_AM_DRIVING_PREFERENCE, 0).getBoolean(IS_SEND_SMS, false);
    }
}
