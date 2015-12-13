package com.heroku.fig_know_wat.iamdriving;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

    @ViewById(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;

    @TextChange(R.id.edit_sms_text)
    void onTextChangesOnHelloTextView() {
        if (preferencesManager != null) {
            preferencesManager.setSmsText(smsText.getText().toString());
        }
    }

    @AfterViews
    void initUI() {
        preferencesManager = new PreferencesManager(getApplicationContext());

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

        setupNavigationView();
        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        ((NavigationView) findViewById(R.id.navigation_view)).setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_main:
//                        startActivity(new Intent().setClass(MainActivity.this, FloatingActionButtonActivity.class));
//                        MainActivity.this.finish();
                        break;

                    case R.id.navigation_item_journal:
//                        startActivity(new Intent().setClass(MainActivity.this, InputTextActivity.class));
//                        MainActivity.this.finish();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
