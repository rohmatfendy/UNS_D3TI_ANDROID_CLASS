package com.example.rohmatfendy.widgetgetar;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName thisWidget    = new ComponentName(context, MainActivity.class);

        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds){
            int number = (new Random().nextInt(100));
            Log.w("WidgetExample", String.valueOf(number));


            Intent intent = new Intent(context, MainActivity.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.activity_main);
            remoteViews.setTextViewText(R.id.textView, String.valueOf(number));

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.textView, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);

        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            Vibrator vibrator=(Vibrator) context.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(10000);

            CharSequence text = "Mulai Getar";
            Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);

            toast.show();
        }
        appWidgetManager.updateAppWidget(new ComponentName(context, MainActivity.class), views);
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,int button) {

        Vibrator vibrator =(Vibrator) context.getApplicationContext().getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(10000);

        CharSequence text = "Mulai Getar";
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
