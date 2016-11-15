package com.example.conofrei.listview;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class NotificationActivity extends AppCompatActivity {
    private final int notificationID = 123456789;
    private NotificationCompat.Builder notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }

    public void btNotify(View view) {
        EditText et = (EditText)findViewById(R.id.txtNotify);
        notification.setSmallIcon(R.drawable.desert);
        notification.setTicker(et.getText());
        notification.setWhen(new Date().getTime());
        notification.setContentText("Content Text");
        notification.setContentTitle("Content title");
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //builds in issues it
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(notificationID, notification.build());

    }
}
