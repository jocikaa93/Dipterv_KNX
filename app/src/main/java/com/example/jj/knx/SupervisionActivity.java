package com.example.jj.knx;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jj.knx.Communication.Client;
import com.example.jj.knx.Data.AddressMapClass;

import java.io.IOException;


public class SupervisionActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervision);
/*
        SharedPreferences IpSettings = PreferenceManager.getDefaultSharedPreferences(this);
        String ServerIp = IpSettings.getString("key_ip", "00");

        SharedPreferences PortSettings = PreferenceManager.getDefaultSharedPreferences(this);
        String ServerPort = PortSettings.getString("key_port","00");

        SharedPreferences IpSettings = getSharedPreferences("preferences_main.xml", MODE_PRIVATE);
        String ServerIp = (IpSettings.getString("key_ip","00"));

        SharedPreferences PortSettings = getSharedPreferences("preferences_main",  MODE_PRIVATE);
        String ServerPort = (PortSettings.getString("key_port","00"));


        TextView textView1 = (TextView)findViewById(R.id.TW1);
        textView1.setText(ServerPort);

        TextView textView2 = (TextView)findViewById(R.id.TW2);
        textView2.setText(ServerIp);

*/
        Button btn_BackSupervision = findViewById(R.id.btn_BackSupervision);
        btn_BackSupervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SupervisionActivity.super.finish();
            }
        });

        context = this;

        SeekBar SB1 = findViewById(R.id.SeekBar1);
        SeekBar SB2 = findViewById(R.id.SeekBar2);
        SeekBar SB3 = findViewById(R.id.SeekBar3);
        SeekBar SB4 = findViewById(R.id.SeekBar4);

        final EditText ET1 = findViewById(R.id.EditText1);
        final EditText ET2 = findViewById(R.id.EditText2);
        final EditText ET3 = findViewById(R.id.EditText3);
        final EditText ET4 = findViewById(R.id.EditText4);

       /* SB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                ET.setText(String.valueOf(progress));
                ET.setSelection(String.valueOf(progress).length());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
*/
        ET1.addTextChangedListener(textWatcherSample(ET1, SB1));
        ET2.addTextChangedListener(textWatcherSample(ET2, SB2));
        ET3.addTextChangedListener(textWatcherSample(ET3, SB3));
        ET4.addTextChangedListener(textWatcherSample(ET4, SB4));

        SB1.setOnSeekBarChangeListener(seekBarChangeListenerSample(ET1));
        SB2.setOnSeekBarChangeListener(seekBarChangeListenerSample(ET2));
        SB3.setOnSeekBarChangeListener(seekBarChangeListenerSample(ET3));
        SB4.setOnSeekBarChangeListener(seekBarChangeListenerSample(ET4));
    }


    private SeekBar.OnSeekBarChangeListener seekBarChangeListenerSample (final EditText editText) {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                editText.setText(String.valueOf(progress));
                editText.setSelection(String.valueOf(progress).length());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }

    private TextWatcher textWatcherSample(final EditText editText, final SeekBar seekBar) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editText.getText().length() > 0) {
                    int progress = Math.round(Float.parseFloat(editable.toString()));
                    seekBar.setProgress(progress);
                }
                else{
                    editText.setText(Integer.toString(0));
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Client.sendData(context, AddressMapClass.getAddress(editText.getId()), editText.getText().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "Nem sikerült", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                }).start();
            }
        };
    }

  }