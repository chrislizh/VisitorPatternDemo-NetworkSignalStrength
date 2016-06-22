package com.example.chrisli.visitorpatterndemo_networksignalstrength;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvSignalStrength_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSignalStrength_ = (TextView) findViewById(R.id.tvSignalStrength);
    }

    public void btnGetNetworkStrengthOnClick(View view) {
        Integer wifiSignalStrength = NetworkInfo.getWifiSignalStrength(this);
        Integer cellularSignalStrength = NetworkInfo.getCellularSignalStrength(this);
        String signalStrength = "";
        if (wifiSignalStrength != null) {
            signalStrength += "Wifi Signal Strength: RSSI = " + wifiSignalStrength + "db";
        }
        if (!signalStrength.isEmpty()) {
            signalStrength += "\n";
        }
        if (cellularSignalStrength != null) {
            signalStrength += "Cellular Signal Strength: " + cellularSignalStrength;
        }
        if (signalStrength.isEmpty()) {
            signalStrength = "Network signal strength not available";
        }
        if (tvSignalStrength_ != null) {
            tvSignalStrength_.setText(signalStrength);
        }
    }


}
