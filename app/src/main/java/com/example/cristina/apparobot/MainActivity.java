package com.example.cristina.apparobot;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_ENABLE_BT = 1;
    static final int TAKE_A_RESULT = 2;

    BufferedWriter outBT;
    InputStreamReader inBT;

    //path
    String path;
    Button up;
    Button down;
    Button right;
    Button left;
    Button increaseVolume;
    Button decreaseVolume;
    Button exit;
    Button pause;

    //parking
    ImageView delantera;
    ImageView trasera;
    Button upPark ;
    Button downPark ;
    Button rightPark ;
    Button leftPark ;
    Button backPark ;
    Button pausePark ;

    //main
    TextView direction1;
    TextView direction2;
    TextView direction3;
    TextView direction4;
    EditText seg1;
    EditText seg2;
    EditText seg3;
    EditText seg4;
    Button run;
    Asynctask at;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main();
        bluetoothConection();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });

    }

    public void up(){
        try {
            outBT.write("Up\r\n");
            outBT.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void down(){
        try {
            outBT.write("Down\r\n");
            outBT.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void right(){
        try {
            outBT.write("Right\r\n");
            outBT.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void left(){
        try {
            outBT.write("Left\r\n");
            outBT.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        try {
            outBT.write("Pause\r\n");
            outBT.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parking(){
        setContentView(R.layout.activity_parking);

        delantera = (ImageView) findViewById(R.id.imgDelantero);
        trasera = (ImageView) findViewById(R.id.imgTrasero);
        upPark = (Button) findViewById(R.id.buttonUpPark);
        downPark = (Button) findViewById(R.id.buttonDownPark);
        rightPark = (Button) findViewById(R.id.buttonRightPark);
        leftPark = (Button) findViewById(R.id.buttonLeftPark);
        backPark = (Button) findViewById(R.id.buttonBackPark);
        pausePark = (Button) findViewById(R.id.buttonPausePark);

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
                pause();

                try {
                    outBT.write("Parking\r\n");
                    outBT.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                showDialog(direction1);
            }
        });

        direction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(direction2);
            }
        });

        direction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(direction3);
            }
        });

        direction4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(direction4);
            }
        });



        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isNull(seg1) || isNull(seg2) || isNull(seg3) || isNull(seg4) || isNull(direction1) || isNull(direction2) || isNull(direction3) || isNull(direction4)
                        || direction1.getText().toString().equals("CHOICE") || direction2.getText().toString().equals("CHOICE")
                        || direction3.getText().toString().equals("CHOICE") || direction4.getText().toString().equals("CHOICE"))
                {
                    Toast toast1 = Toast.makeText(getApplicationContext(),"Error, a cell has null", Toast.LENGTH_SHORT);
                    toast1.show();
                    main();
                }
                else
                {
                    String [][]arrayOfPath;
                    arrayOfPath = new String[4][2];
                    arrayOfPath[0][0] = seg1.getText().toString();
                    arrayOfPath[1][0] = seg2.getText().toString();
                    arrayOfPath[2][0] = seg3.getText().toString();
                    arrayOfPath[3][0] = seg4.getText().toString();
                    arrayOfPath[0][1] = direction1.getText().toString();
                    arrayOfPath[1][1] = direction2.getText().toString();
                    arrayOfPath[2][1] = direction3.getText().toString();
                    arrayOfPath[3][1] = direction4.getText().toString();
                    @SuppressLint("StaticFieldLeak")
                    AsynctaskPath startPath = new AsynctaskPath(arrayOfPath, outBT)
                    {
                        @Override
                        protected void onPostExecute(Object array) {
                            pause();
                            main();
                        }

                        @Override
                        protected void onProgressUpdate(String... values) {
                            //imagen
                        }
                    };
                    startPath.executeOnExecutor(Asynctask.THREAD_POOL_EXECUTOR);
                }
            }
        });
    }
    //MOVERLO A CLASE ESTATICA JUNTO CON SHOW DIALOG
    public boolean isNull(TextView editable)
    {
        if(editable.getText() != null && !editable.getText().toString().equals(""))
        {
            return false;
        }
        return true;
    }

    @SuppressLint("StaticFieldLeak")
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
            if(mmSocket != null) {
                try {
                    outBT = new BufferedWriter(new OutputStreamWriter(mmSocket.getOutputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    inBT = new InputStreamReader(mmSocket.getInputStream());
                    //initialize asynctask to be reading all time
                    at = new Asynctask(inBT) {
                        @Override
                        protected void onProgressUpdate(String... values) {
                            //update the images of parking
                            if(values[0].equals("1"))
                            {
                                delantera.setImageResource(R.drawable.arriba1);
                            }
                            else if(values[0].equals("2"))
                            {
                                delantera.setImageResource(R.drawable.arriba2);
                            }
                            else if(values[0].equals("3"))
                            {
                                delantera.setImageResource(R.drawable.arriba3);
                                pause();
                            }
                            else if(values[0].equals("4"))
                            {
                                trasera.setImageResource(R.drawable.abajo3);
                                pause();
                            }
                            else
                            {
                                delantera.setImageResource(R.drawable.distance);
                                trasera.setImageResource(R.drawable.distanceback);
                            }
                        }
                    };
                    at.execute();

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
                    outBT.write("Parking\r\n");
                    outBT.flush();
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

    private void showDialog(final TextView txtV){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Up");
        arrayAdapter.add("Down");
        arrayAdapter.add("Right");
        arrayAdapter.add("Left");

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

                if(strName.equals("Up")){
                    txtV.setText("Up");
                }
                if(strName.equals("Down")){
                    txtV.setText("Down");
                }

                if(strName.equals("Right")){
                    txtV.setText("Right");
                }

                if(strName.equals("Left")){
                    txtV.setText("Left");
                }
            }
        });
        builder.show();
    }
}