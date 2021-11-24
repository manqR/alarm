package com.example.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmBroadCastReceiver extends BroadcastReceiver {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context,DestinationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"foxandroid")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Foxandroid Alarm Manager")
                .setContentText("Subscribe for android related content")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());
        Intent ix = new Intent(context, DestinationActivity.class);
        ix.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ix.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(ix);


        mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI);
        mediaPlayer.setLooping(true);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        mediaPlayer.start();
        long[] pattern = { 0, 1000, 1000 };
        vibrator.vibrate(pattern, 0);

        Toast.makeText(context, "Alarm triggered", Toast.LENGTH_LONG).show();


    }
}