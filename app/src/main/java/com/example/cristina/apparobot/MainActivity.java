package com.example.cristina.apparobot;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;
    private  BufferedWriter outBT;
    static final int TAKE_A_RESULT = 2;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button up = (Button) findViewById(R.id.buttonUp);
        Button down = (Button) findViewById(R.id.buttonDown);
        Button right = (Button) findViewById(R.id.buttonRight);
        Button left = (Button) findViewById(R.id.buttonLeft);
        Button increaseVolume = (Button) findViewById(R.id.buttonMasVol);
        Button decreaseVolume = (Button) findViewById(R.id.buttonMenosVol);
        Button exit = (Button) findViewById(R.id.buttonExit);
        Button pause = (Button) findViewById(R.id.buttonPause);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outBT.write("Up\r\n");
                    outBT.flush();
                    Log.i("he enviado el exiiiit","exiiit");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outBT.write("Down\r\n");
                    outBT.flush();
                    Log.i("he enviado el dowwwwn","down");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outBT.write("Right\r\n");
                    outBT.flush();
                    Log.i("he enviado el riiihgt","right");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outBT.write("Left\r\n");
                    outBT.flush();
                    Log.i("he enviado el leeeeft","Leeft");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        decreaseVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outBT.write("DecreaseVolume\r\n");
                    outBT.flush();
                    Log.i("he enviado el exiiiit","bajo volll");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        increaseVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outBT.write("IncreaseVolume\r\n");
                    outBT.flush();
                    Log.i("he enviado el exiiiit","subo voll");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outBT.write("Exit\r\n");
                    outBT.flush();
                    Log.i("he enviado el exiiiit","exit");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outBT.write("Pause\r\n");
                    outBT.flush();
                    Log.i("he enviado el exiiiit","pausaaa");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            // Device does not support Bluetooth
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }

            BluetoothDevice mmDevice = mBluetoothAdapter.getRemoteDevice("00:16:53:08:E5:00");
            BluetoothSocket mmSocket = null;
            try {
                mmSocket = mmDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                mmSocket.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //writer
            InputStreamReader inBT;
            if(mmSocket != null) {
                try {
                    outBT = new BufferedWriter(new OutputStreamWriter(mmSocket.getOutputStream()));
                    String stri = "Hola Caracola";
                    outBT.write("Hola\r\n");
                    outBT.flush();
                    Log.i("estoy escribiendo y escribo ","la srting hola");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inBT = new InputStreamReader(mmSocket.getInputStream());
                    BufferedReader read = new BufferedReader(inBT);
                    String line;
                    line = read.readLine();
                    Log.i("esto es lop que recivo", " "+line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Menu toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_park:
                Intent intent = new Intent(this, Path.class);
                startActivityForResult(intent, TAKE_A_RESULT);
                return true;
            case R.id.action_path:
                Intent intentPark = new Intent(this, Parking.class);
                startActivity(intentPark);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == TAKE_A_RESULT) {
            if(resultCode == RESULT_OK)
            {
                path = data.getStringExtra("path");
                Log.i("he recibido ", " esto: "+path);
            }
        }
    }
}