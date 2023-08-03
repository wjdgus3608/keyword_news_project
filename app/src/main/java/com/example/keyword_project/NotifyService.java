package com.example.keyword_project;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifyService extends FirebaseMessagingService {


    private void buildNotifyChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Default Channel";
            String channelId = "default_channel";
            String channelDescription = "This is the default channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public void generateNoti(String title, String text, int icon) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            PermissionListener permissionListener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    sendNoti(title,text,icon);
                }

                @Override
                public void onPermissionDenied(List<String> deniedPermissions) {

                }
            };

                TedPermission.Builder builder = TedPermission.create()
                        .setPermissionListener(permissionListener)
                        .setPermissions(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ? Manifest.permission.POST_NOTIFICATIONS:Manifest.permission.ACCESS_NOTIFICATION_POLICY);

            builder.check();
        }
        else
            sendNoti(title,text,icon);


    }

    private void sendNoti(String title, String text, int icon) {
        String channelId = "default_channel";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // 알림을 클릭했을 때 실행될 동작을 설정합니다.
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        int PERMISSION_REQUEST_CODE = 123;

        // 알림을 생성하고 표시합니다.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(PERMISSION_REQUEST_CODE, builder.build());
        }
    }


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if(message.getNotification() != null){

            String title = message.getNotification().getTitle();
            String body = message.getNotification().getBody();

            buildNotifyChannel();
            generateNoti(title, body,R.drawable.icon_badge);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("my@@", "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }



    private void sendRegistrationToServer(String token){
        Map<String,Object> map = new HashMap<>();
        map.put("fcmToken",token);
        ApiCallClient.callUpdateSetting(GlobalData.mainContext,-1,map);
    }
}
