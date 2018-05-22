package com.example.jj.knx;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.jj.knx.Communication.Server;

import java.io.IOException;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
setSupportActionBar(toolbar);

        Button btn_Supervision = findViewById(R.id.btn_Supervision);
        btn_Supervision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SupervisionActivity.class);
                startActivity(i);
            }
        });

        Button btn_About = findViewById(R.id.btn_About);
        btn_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        Button btn_Hotkeys = findViewById(R.id.btn_Hotkeys);
        btn_Hotkeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, HotkeysActivity.class);
                startActivity(i);
            }
        });
    }
    //noti//


    public void sendNotification(View view) {

        Notification.Builder mBuilder =
                new Notification.Builder(this);

        //Create the intent that’ll fire when the user taps the notification//

        Intent intent = new Intent(MenuActivity.this, SupervisionActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.knx.org/hu/"));
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.app_logo);
        mBuilder.setContentTitle("Otthonfelügyelet");
        mBuilder.setContentText("A biztonsági lámpát felkapcsolták. Ellenőrizze!");
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.addAction(R.drawable.icon_back, "ELLENŐRZÉS", pendingIntent2);

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());
    }

    //noti//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
