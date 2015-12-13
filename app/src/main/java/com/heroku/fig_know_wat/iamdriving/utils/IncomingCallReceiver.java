package com.heroku.fig_know_wat.iamdriving.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.internal.telephony.ITelephony;
import com.heroku.fig_know_wat.iamdriving.db.DatabaseHelper;
import com.heroku.fig_know_wat.iamdriving.db.JournalORMLite;
import com.j256.ormlite.dao.Dao;

import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * Created by Alex on 13/12/15.
 */
public class IncomingCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PreferencesManager preferencesManager = new PreferencesManager(context);
        if (!preferencesManager.isDriving()) {
            return;
        }

        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class c = Class.forName(tm.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            ITelephony telephonyService = (ITelephony) m.invoke(tm);
            Bundle bundle = intent.getExtras();
            String phoneNumber = bundle.getString("incoming_number");
            Log.e("INCOMING", phoneNumber);
            if (phoneNumber != null) {
                telephonyService.endCall();
                Log.e("HANG UP", phoneNumber);
                String event = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                if (event.equals("RINGING")) {
                    if (preferencesManager.isSendSms()) {
                        sendSms(phoneNumber, preferencesManager.getSmsText());
                    }
                    saveIncomingCall(context, phoneNumber);
                }
            }
        } catch (Exception e) {
            Log.e("Phone Receive Error", " " + e);
        }
    }

    private void sendSms(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
        Log.e("Send SMS", "number: " + phoneNumber + "; message:" + message);
    }

    private void saveIncomingCall(Context context, String phoneNumber) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            Dao<JournalORMLite, Integer> dao = databaseHelper.getDao();
            JournalORMLite phoneCall = new JournalORMLite(phoneNumber, System.currentTimeMillis());
            dao.create(phoneCall);
            Log.e("Save to db", phoneCall.toString());
            Log.e("cell counts", String.valueOf(dao.countOf()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
