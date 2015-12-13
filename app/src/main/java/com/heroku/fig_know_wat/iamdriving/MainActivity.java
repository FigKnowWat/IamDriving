package com.heroku.fig_know_wat.iamdriving;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.heroku.fig_know_wat.iamdriving.utils.PreferencesManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    PreferencesManager preferencesManager;

    @ViewById(R.id.btn_toggle_driving)
    ToggleButton drivingButton;

    @ViewById(R.id.checkbox_send_sms)
    CheckBox sendSmsCheckBox;

    @ViewById(R.id.edit_sms_text)
    EditText smsText;

    @TextChange(R.id.edit_sms_text)
    void onTextChangesOnHelloTextView() {
        if (preferencesManager != null) {
            preferencesManager.setSmsText(smsText.getText().toString());
        }
    }

    @AfterViews
    void initUI() {
        preferencesManager = new PreferencesManager(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drivingButton.setChecked(preferencesManager.isDriving());
        smsText.setText(preferencesManager.getSmsText());
        drivingButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferencesManager.setIsDriving(isChecked);
            }
        });

        sendSmsCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferencesManager.setIsSendSms(isChecked);
                smsText.setEnabled(isChecked);
            }
        });
        sendSmsCheckBox.setChecked(preferencesManager.isSendSms());
        smsText.setEnabled(preferencesManager.isSendSms());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
