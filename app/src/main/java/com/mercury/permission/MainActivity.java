package com.mercury.permission;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int BAIDU_LOCATION = 100;
    public static final int READ_SD        = 101;
    public static final int CAMERA         = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void baidu(View view) {
        //如果当前运行的系统版本大于等于6.0，需要动态权限申请
        //        if (checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
        //                PackageManager.PERMISSION_GRANTED) {
        //            startActivity(new Intent(this, LocationActivity.class));
        //            return;
        //        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .ACCESS_COARSE_LOCATION}, BAIDU_LOCATION);
            Log.e("baidu", "deny");
        } else {
            Log.e("baidu", "agree");
            startActivity(new Intent(this, LocationActivity.class));
        }

        //        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        //            int code = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        //            Log.v("permission6.0", code + "");
        //            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) !=
        // PackageManager
        //                    .PERMISSION_GRANTED) {
        //                requestPermissions(new String[]{Manifest.permission
        //                        .ACCESS_COARSE_LOCATION}, BAIDU_LOCATION);
        //            } else {
        //                startActivity(new Intent(this, LocationActivity.class));
        //            }
        //
        //        }
        Log.e("view", view.getId() + "");


    }

    public void readSD(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .READ_EXTERNAL_STORAGE}, READ_SD);
        }

        Log.e("view", view.getId() + "");
    }

    public void photo(View view) {
        //        if (checkCallingOrSelfPermission(Manifest.permission.CAMERA) ==
        //                PackageManager.PERMISSION_GRANTED) {
        //            startActivity(new Intent(this, CameraActivity.class));
        //            return;
        //        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager
                .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .CAMERA}, CAMERA);
        } else {
            startActivity(new Intent(this, CameraActivity.class));
        }
        Log.e("view", view.getId() + "");
    }

    public void test(View view) {
        alertWindow();
        //        startActivity(new Intent(this, WebViewActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case BAIDU_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startActivity(new Intent(this, LocationActivity.class));
                else
                    Toast.makeText(this, "拒绝了", Toast.LENGTH_SHORT).show();
                break;
            case READ_SD:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "可以读取内存卡", Toast.LENGTH_SHORT).show();
                }
                break;
            case CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startActivity(new Intent(this, CameraActivity.class));
                else
//                    Toast.makeText(this, "拒绝拍照", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;

        }

    }

    private void alertWindow() {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent
                .FLAG_UPDATE_CURRENT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notify);
//        remoteViews.setImageViewResource(R.id.iv_image, R.mipmap.ic_launcher);
//        remoteViews.setTextViewText(R.id.tv1, "123");
//        remoteViews.setTextViewText(R.id.tv2, "456");

        Notification notification = new NotificationCompat.Builder(MainActivity.this)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                .setPriority(Notification.PRIORITY_HIGH)
//                .setVibrate(new long[0])
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("标题")
//                .setContentText("内容")
                .setCustomContentView(remoteViews)
                .setContentIntent(pendingIntent)
                .setFullScreenIntent(pendingIntent, false)
//                .addAction(R.mipmap.ic_launcher, "菜单1", pendingIntent)
                .build();
//        notification.contentView = remoteViews;
        manager.notify(1, notification);


    }
}
