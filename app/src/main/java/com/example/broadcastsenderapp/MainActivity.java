package com.example.broadcastsenderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    int count;

    private BroadcastReceiver mInnerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if("com.package.broadcast.ACTION_SEND".equals(intent.getAction())){
                String intentExtra = intent.getStringExtra("com.broadcast1.EXTRA_DATA");

                mTextView.setText("Inner Broadcast received: "+ intentExtra + count );
                count++;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
    }
    public void sendBroadcast(View view)
    {
        Intent intent = new Intent("com.package.broadcast.ACTION_SEND");
        intent.putExtra("com.broadcast1.EXTRA_DATA"," Hello from sender app...");

        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("com.package.broadcast.ACTION_SEND");
        registerReceiver(mInnerReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mInnerReceiver);
    }
}