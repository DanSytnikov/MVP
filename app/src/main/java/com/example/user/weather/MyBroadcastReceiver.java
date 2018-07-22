package com.example.user.weather;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


import java.lang.ref.WeakReference;


/**
 * Created by student on 07.07.18.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TYPE = "type";
    private static final int ID_ACTION_PLAY = 0;
    private static final int ID_ACTION_STOP = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Reciver", Toast.LENGTH_LONG).show();
        int type = ID_ACTION_PLAY;
        switch (type) {
            case ID_ACTION_PLAY:
                // выполнение полученного намерения
                context.startService(new Intent(context, MyIntentService.class));
                break;
        }
    }

}


