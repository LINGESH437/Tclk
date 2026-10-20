package com.tamilclock.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RemoteViews;

import java.util.Calendar;

public class TamilClockWidget extends AppWidgetProvider {

    public static void updateAllWidgets(Context context) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        int[] ids = manager.getAppWidgetIds(new ComponentName(context, TamilClockWidget.class));
        Intent intent = new Intent(context, TamilClockWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        SharedPreferences prefs = context.getSharedPreferences("WidgetConfig", Context.MODE_PRIVATE);
        int textColor = prefs.getInt("text_color", Color.WHITE);
        int bgColor = prefs.getInt("bg_color", Color.BLACK);
        int alpha = prefs.getInt("bg_alpha", 255);

        int resolvedBgColor = Color.argb(alpha, Color.red(bgColor), Color.green(bgColor), Color.blue(bgColor));
        String tamilDateStr = TamilCalendarHelper.getTamilDateString(Calendar.getInstance());

        for (int widgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setTextColor(R.id.clock_time, textColor);
            views.setTextColor(R.id.clock_date, textColor);
            views.setTextColor(R.id.tv_tamil_date, textColor);
            views.setInt(R.id.widget_root, "setBackgroundColor", resolvedBgColor);
            views.setTextViewText(R.id.tv_tamil_date, tamilDateStr);

            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (Intent.ACTION_DATE_CHANGED.equals(action) ||
            Intent.ACTION_TIMEZONE_CHANGED.equals(action) ||
            Intent.ACTION_TIME_CHANGED.equals(action)) {
            updateAllWidgets(context);
        }
    }
}
