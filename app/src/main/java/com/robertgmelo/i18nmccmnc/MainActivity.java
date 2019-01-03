package com.robertgmelo.i18nmccmnc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mPanelNetwork;
    private TextView mNetworkOperator;
    private TextView mSimOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPanelNetwork = (LinearLayout) findViewById(R.id.panelNetwork);
        mNetworkOperator = (TextView) findViewById(R.id.txtNetworkOperator);
        mSimOperator = (TextView) findViewById(R.id.txtSimOperator);

        TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String networkOperator = tel.getNetworkOperator();
        String simOperator = tel.getSimOperator();

        if (!TextUtils.isEmpty(networkOperator) || !TextUtils.isEmpty(simOperator)) {

            mPanelNetwork.setVisibility(View.VISIBLE);

            if (!TextUtils.isEmpty(networkOperator)) {
                int mcc = Integer.parseInt(networkOperator.substring(0, 3));
                int mnc = Integer.parseInt(networkOperator.substring(3));
                mNetworkOperator.setText(getString(R.string.network_operator, mcc, mnc));
                mNetworkOperator.setVisibility(View.VISIBLE);
            } else {
                mNetworkOperator.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(simOperator)) {
                int mcc = Integer.parseInt(simOperator.substring(0, 3));
                int mnc = Integer.parseInt(simOperator.substring(3));
                mSimOperator.setText(getString(R.string.sim_operator, mcc, mnc));
                mSimOperator.setVisibility(View.VISIBLE);
            } else {
                mSimOperator.setVisibility(View.GONE);
            }
        } else {
            mPanelNetwork.setVisibility(View.GONE);
        }
    }
}
