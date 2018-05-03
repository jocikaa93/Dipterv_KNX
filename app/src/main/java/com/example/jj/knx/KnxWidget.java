package com.example.jj.knx;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.jj.knx.Communication.Client;

import java.io.IOException;


/**
 * Implementation of App Widget functionality.
 */
public class KnxWidget extends AppWidgetProvider {

    public static final String ACTION = "ON";
    public static final String ACTION2 = "OFF";

    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.knx_widget);

        Intent intent1 = new Intent(context, KnxWidget.class);
        intent1.setAction(ACTION);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, 0);
        views.setOnClickPendingIntent(R.id.btn_WidgetLightsOn, pendingIntent1);
        appWidgetManager.updateAppWidget(appWidgetIds, views);


        intent1.setAction(ACTION2);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context, 0, intent1, 0);
        views.setOnClickPendingIntent(R.id.btn_WidgetLightsOff, pendingIntent2);
        appWidgetManager.updateAppWidget(appWidgetIds, views);


    }

    @Override
    public void onReceive(final Context context, Intent intent1) {
        super.onReceive(context, intent1);

        final Handler mHandler = new Handler();


        if (intent1.getAction().equals(ACTION)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Client.sendData(context, "1/1/1", "85");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        if (intent1.getAction().equals(ACTION2)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Client.sendData(context, "1/1/1", "0");
                    } catch (IOException e) {
                        e.printStackTrace();
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "asd", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }).start();
        }

    }
}

