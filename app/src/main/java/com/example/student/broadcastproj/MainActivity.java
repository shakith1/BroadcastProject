package com.example.student.broadcastproj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String BROADCAST_ACTION = "";

    TextView textView;
    Button button;
    Reciever localListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtBroadcast);
        button = findViewById(R.id.btnBroadcast);
    }

    @Override
    protected void onStart() {
        super.onStart();
        localListner = new Reciever();
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        this.registerReceiver(localListner,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(localListner);
    }

    public void btnBroadcast(View view){
        if(view.getId() == R.id.btnBroadcast){
            BackgroundService.startAction(this.getApplicationContext());
        }
    }

    public class Reciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String currentText =  textView.getText().toString();
            String message = intent.getStringExtra("value");
            currentText += "\nRecieved " + message;
            textView.setText(currentText);
        }
    }
}
