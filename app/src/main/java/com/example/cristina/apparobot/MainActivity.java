package com.example.cristina.apparobot;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_ENABLE_BT = 1;
    BufferedWriter outBT;
    static final int TAKE_A_RESULT = 2;
    String path;
    Button up;
    Button down;
    Button right;
    Button left;
    Button increaseVolume;
    Button decreaseVolume;
    Button exit;
    Button pause;

    Button upPark ;
    Button downPark ;
    Button rightPark ;
    Button leftPark ;
    Button backPark ;
    Button pausePark ;

    TextView direction1;
    TextView direction2;
    TextView direction3;
    TextView direction4;
    EditText seg1;
    EditText seg2;
    EditText seg3;
    EditText seg4;
    Button run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main();
        bluetoothConection();
    }

    public void up()
    {
        try {
            outBT.write("Up\r\n");
            outBT.flush();
            Log.i("he enviado el exiiiit", "exiiit");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void down(){
        try {
            outBT.write("Down\r\n");
            outBT.flush();
            Log.i("he enviado el dowwwwn","down");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void right(){
        try {
            outBT.write("Right\r\n");
            outBT.flush();
            Log.i("he enviado el riiihgt","right");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void left(){
        try {
            outBT.write("Left\r\n");
            outBT.flush();
            Log.i("he enviado el leeeeft","Leeft");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        try {
            outBT.write("Pause\r\n");
            outBT.flush();
            Log.i("he enviado el exiiiit","pausaaa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void main(){
        setContentView(R.layout.activity_main);

        //Initialize main buttons
        up = (Button) findViewById(R.id.buttonUp);
        down = (Button) findViewById(R.id.buttonDown);
        right = (Button) findViewById(R.id.buttonRight);
        left = (Button) findViewById(R.id.buttonLeft);
        increaseVolume = (Button) findViewById(R.id.buttonMasVol);
        decreaseVolume = (Button) findViewById(R.id.buttonMenosVol);
        exit = (Button) findViewById(R.id.buttonExit);
        pause = (Button) findViewById(R.id.buttonPause);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                up();
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                down();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                right();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left();
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
                pause();
            }
        });

    }

    public void parking(){
        setContentView(R.layout.activity_parking);

        upPark = (Button) findViewById(R.id.buttonUpPark);
        downPark = (Button) findViewById(R.id.buttonDownPark);
        rightPark = (Button) findViewById(R.id.buttonRightPark);
        leftPark = (Button) findViewById(R.id.buttonLeftPark);
        backPark = (Button) findViewById(R.id.buttonBackPark);
        pausePark = (Button) findViewById(R.id.buttonPausePark);

        try {
            outBT.write("Parking\r\n");
            System.out.print("JAJA estoy en parking");
        } catch (IOException e) {
            e.printStackTrace();
        }

        upPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                up();
            }
        });
        downPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               down();
            }
        });
        rightPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                right();
            }
        });
        leftPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left();
            }
        });

        pausePark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               pause();
            }
        });
        backPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("vuelvo atras","back");
                pause();
                main();
            }
        });

    }

    public void path(){

        setContentView(R.layout.activity_path);

        direction1 = (TextView) findViewById(R.id.Direction1);
        direction2 = (TextView) findViewById(R.id.Direction2);
        direction3 = (TextView) findViewById(R.id.Direction3);
        direction4 = (TextView) findViewById(R.id.Direction4);
        seg1 = (EditText) findViewById(R.id.seg1);
        seg2 = (EditText) findViewById(R.id.seg2);
        seg3 = (EditText) findViewById(R.id.seg3);
        seg4 = (EditText) findViewById(R.id.seg4);
        run = (Button) findViewById(R.id.ButtonRun);

        direction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        direction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        direction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        direction4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(seg1.getText().toString().equals(null) || seg1.getText().toString().equals("")
                        || seg1.getText().toString().equals(null) || seg1.getText().toString().equals("")
                        || seg2.getText().toString().equals(null) || seg2.getText().toString().equals("")
                        || seg3.getText().toString().equals(null) || seg3.getText().toString().equals("")
                        || direction1.getText().toString().equals(null) || direction1.getText().toString().equals("") || direction1.getText().toString().equals("choise")
                        || direction2.getText().toString().equals(null) || direction2.getText().toString().equals("") || direction2.getText().toString().equals("choise")
                        || direction3.getText().toString().equals(null) || direction3.getText().toString().equals("") || direction3.getText().toString().equals("choise")
                        || direction4.getText().toString().equals(null) || direction4.getText().toString().equals("") || direction4.getText().toString().equals("choise"))
                {
                    main();
                    Toast toast1 = Toast.makeText(getApplicationContext(),"Error, a cell has null", Toast.LENGTH_SHORT);
                }
                else
                {
                    //preguntar a Jaime
                    path = direction1.getText().toString() +":"+ seg1.getText().toString() + ","+
                            direction2.getText().toString() +":"+ seg2.getText().toString() + ","+
                            direction3.getText().toString() +":"+ seg3.getText().toString() + ","+
                            direction4.getText().toString() +":"+ seg4.getText().toString() + ".";
                    Log.i("este es lo que envio", "c  "+path);
                    pause();
                   main();

                }
                finish();
            }
        });
    }

    public void bluetoothConection(){
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
    public boolean onCreateOptionsMenu(Menu menu)    {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_park:
                try {
                    outBT.write("Pause\r\n");
                    outBT.flush();
                    /*outBT.write("Parking\r\n");
                    outBT.flush();*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
                parking();
                return true;
            case R.id.action_path:
                try {
                    outBT.write("Pause\r\n");
                    outBT.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                path();
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

    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("UP");
        arrayAdapter.add("DOWN");
        arrayAdapter.add("RIGHT");
        arrayAdapter.add("LEFT");

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(MainActivity.this);

                if(strName.equals("UP")){
                    direction1.setText("UP");
                }
                if(strName.equals("DOWN")){
                    direction2.setText("DOWN");
                }

                if(strName.equals("RIGHT")){
                    direction3.setText("RIGHT");
                }

                if(strName.equals("LEFT")){
                    direction4.setText("LEFT");
                }
            }
        });
        builder.show();
    }
}