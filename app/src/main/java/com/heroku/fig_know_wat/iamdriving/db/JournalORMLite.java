package com.heroku.fig_know_wat.iamdriving.db;

import com.j256.ormlite.field.DatabaseField;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alex on 13/12/15.
 */
public class JournalORMLite {
    /**
     * Class name will be tablename
     */
    @DatabaseField(generatedId = true, canBeNull = false)
    int _id;
    @DatabaseField(canBeNull = false)
    String phoneNumber;
    @DatabaseField(canBeNull = false)
    Date date;

    JournalORMLite() {
    }


    public JournalORMLite(String phoneNumber, long date) {
        this.phoneNumber = phoneNumber;
        this.date = new Date(date);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(
                "dd.MM.yyyy HH:mm:ss");
        return dateFormatter.format(date);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id);
        sb.append(", ").append(phoneNumber);
        sb.append(", ").append(getDate());
        return sb.toString();
    }
}
