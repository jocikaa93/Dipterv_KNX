package com.example.jj.knx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jj.knx.Communication.Client;
import com.example.jj.knx.Data.AddressMapClass;

import java.io.IOException;
import java.util.Map;

public class HotkeysActivity extends Activity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotkeys);

        Button btn_LightsOff = findViewById(R.id.btn_LightsOff);
        btn_LightsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(Map.Entry<Integer, String> a : AddressMapClass.getAddressMap().entrySet()) {
                            try {
                                Client.sendData(context, a.getValue(),"0");
                            } catch (IOException e) {
                                e.printStackTrace();
                                //TODO majd Joci megcsinálja
                            }
                        }
                    }
                }).start();
                /*                *//*
                Itt kell ellenőrizni egy iffel hogy megtörtént-e vagy sem
                 *//*
                Context context = getApplicationContext();
                CharSequence text = "Minden lámpa leoltva!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/
            }
        });

        Button btn_LightsOn = findViewById(R.id.btn_LightsOn);
        btn_LightsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(Map.Entry<Integer, String> a : AddressMapClass.getAddressMap().entrySet()) {
                            try {
                                Client.sendData(context, a.getValue(),"0");
                            } catch (IOException e) {
                                e.printStackTrace();
                                //TODO majd Joci megcsinálja
                            }
                        }
                    }
                }).start();
                /*                *//*
                Itt kell ellenőrizni egy iffel hogy megtörtént-e vagy sem
                 *//*
                Context context = getApplicationContext();
                CharSequence text = "Minden lámpa leoltva!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/
            }
        });

        Button btn_BackHotkeys = findViewById(R.id.btn_BackHotkeys);
        btn_BackHotkeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HotkeysActivity.super.finish();
            }
        });
    }
}
