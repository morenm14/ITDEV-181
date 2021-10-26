package com.example.smsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phoneNumber, message;
    Button button;
    private static final int SMS_REQUEST_CODE = 1;
    private static final int READ_PHONE_STATE_REQUEST_CODE = 0;
    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPendingIntent, deliveredPendingIntent;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.phone);
        message = findViewById(R.id.message);
        button = findViewById(R.id.button);

        sentPendingIntent = PendingIntent.getBroadcast(this,0,new Intent(SENT),0);
        deliveredPendingIntent = PendingIntent.getBroadcast(this,0,new Intent(DELIVERED),0);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // We do not have this permission. Let's ask the user
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},READ_PHONE_STATE_REQUEST_CODE);
        }



        button.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS) ==
                    PackageManager.PERMISSION_GRANTED){
                //send message
                sendSMS();
            }else{
                //request permissions
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.SEND_SMS},SMS_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }else if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.SEND_SMS)){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Please allow to send SMS Messages")
                    .setTitle("Permission Required");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.SEND_SMS}, SMS_REQUEST_CODE);
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "No Permission Granted", Toast.LENGTH_SHORT).show();
                }
            });

            alertDialog.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(MainActivity.this, "SMS Sent", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(MainActivity.this, "Generic Failure", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(MainActivity.this, "No Service", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(MainActivity.this, "Null PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(MainActivity.this, "Radio off", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };

        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(MainActivity.this, "SMS Delivered", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(MainActivity.this, "SMS NOT delivered", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        };

        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver, new IntentFilter(DELIVERED));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(smsSentReceiver);
        unregisterReceiver(smsDeliveredReceiver);

    }

    public void sendSMS(){
        String phone = phoneNumber.getText().toString();
        String smsMessage = message.getText().toString();

        if (!phone.isEmpty() && !smsMessage.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,smsMessage,sentPendingIntent,deliveredPendingIntent);
            phoneNumber.setText(null);
            message.setText(null);
        }else{
            Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }

    }
}