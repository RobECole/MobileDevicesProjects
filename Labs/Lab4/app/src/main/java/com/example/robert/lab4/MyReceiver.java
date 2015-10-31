package com.example.robert.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;


/**
 * Created by Robert on 27-Oct-2015.
 */
public class MyReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){
        int charging = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        int temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);

        String healthStr, chargingStr, pluggedStr, tempStr;
        if(health == BatteryManager.BATTERY_HEALTH_GOOD){
            healthStr = "Good";
        }
        else{
            healthStr = "Malade";
        }

        if(charging == BatteryManager.BATTERY_STATUS_CHARGING){
            chargingStr = "Charging";
        }
        else {
            chargingStr = "Discharging";
        }

        if(plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB){
            pluggedStr = "Plugged in";
        }
        else{
            pluggedStr = "Unplugged";
        }

        tempStr = (temp / 10) + " C";

        String text = "\nCharging: " + chargingStr + "\nHealth: " + healthStr +
                "\nPlug: " + pluggedStr + "\nTemperature: " + tempStr;

        Notification.Builder bob = new Notification.Builder(context)
                .setContentTitle("Lab 4: Battery Status")
                .setContentText(text)
                .setSmallIcon(R.drawable.icon);

        Notification noti = new Notification.BigTextStyle(bob)
                .bigText(text)
                .build();

        NotificationManager manny = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        manny.notify(0,noti);

    }


}
