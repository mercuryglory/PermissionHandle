package com.mercury.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int BAIDU_LOCATION = 100;
    public static final int READ_SD = 101;
    public static final int CAMERA = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void baidu(View view) {
        //如果当前运行的系统版本大于等于6.0，需要动态权限申请
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int code = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
            Log.v("permission6.0", code + "");
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
                    .PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission
                        .ACCESS_COARSE_LOCATION}, BAIDU_LOCATION);
            } else {
                startActivity(new Intent(this, LocationActivity.class));
            }

        }


    }

    public void readSD(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager
                    .PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission
                        .READ_EXTERNAL_STORAGE}, READ_SD);
            }
        }
    }

    public void photo(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager
                    .PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission
                        .CAMERA}, CAMERA);
            }
        }
    }

    public void test(View view) {
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
                Toast.makeText(this, "拒绝了", Toast.LENGTH_SHORT).show();
                break;
            case READ_SD:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "可以读取内存卡", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;

        }

    }
}
