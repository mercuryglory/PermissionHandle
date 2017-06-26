package com.mercury.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class LocationActivity extends AppCompatActivity {

    public LocationClient     mLocationClient = null;
    public BDLocationListener myListener      = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setNeedDeviceDirect(true);

        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            BDLocation a = location;
            int b = 3;
            if (location == null) {
                Log.e("baidu", null);
                return;
            }
            if (location.getProvince() != null) {
                Log.e("baidu", location.getProvince() + "\t" + location.getCity());
            } else {
                //                ToastUtil.longToast("无法定位，请在设置项中开启定位权限");
            }
            Log.e("type", location.getLocType() + "");

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {
            Log.e("baidu", s);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
