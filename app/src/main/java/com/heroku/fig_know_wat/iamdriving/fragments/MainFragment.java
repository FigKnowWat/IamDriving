package com.heroku.fig_know_wat.iamdriving.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.heroku.fig_know_wat.iamdriving.R;
import com.heroku.fig_know_wat.iamdriving.utils.PreferencesManager;

import org.androidannotations.annotations.EFragment;

/**
 * Created by Alex on 13/12/15.
 */
@EFragment
public class MainFragment extends Fragment {

    PreferencesManager preferencesManager;

    ToggleButton drivingButton;
    CheckBox sendSmsCheckBox;
    EditText smsText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //init views
        drivingButton = (ToggleButton) view.findViewById(R.id.btn_toggle_driving);
        sendSmsCheckBox = (CheckBox) view.findViewById(R.id.checkbox_send_sms);
        smsText = (EditText) view.findViewById(R.id.edit_sms_text);

        //init preferences manager
        preferencesManager = new PreferencesManager(getActivity().getApplicationContext());

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

        smsText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (preferencesManager != null) {
                    preferencesManager.setSmsText(smsText.getText().toString());
                }
            }
        });
    }
}
