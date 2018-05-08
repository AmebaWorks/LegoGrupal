package com.example.cristina.apparobot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Path extends AppCompatActivity {

    private TextView direction1;
    private TextView direction2;
    private TextView direction3;
    private TextView direction4;
    private EditText seg1;
    private EditText seg2;
    private EditText seg3;
    private EditText seg4;
    private String path;

    private Intent returnIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        returnIntent = new Intent();
        direction1 = (TextView) findViewById(R.id.Direction1);
        direction2 = (TextView) findViewById(R.id.Direction2);
        direction3 = (TextView) findViewById(R.id.Direction3);
        direction4 = (TextView) findViewById(R.id.Direction4);
        seg1 = (EditText) findViewById(R.id.seg1);
        seg2 = (EditText) findViewById(R.id.seg2);
        seg3 = (EditText) findViewById(R.id.seg3);
        seg4 = (EditText) findViewById(R.id.seg4);
        Button run = (Button) findViewById(R.id.ButtonRun);


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
                    setResult(Activity.RESULT_CANCELED, returnIntent);
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
                    returnIntent.putExtra("path", path);
                    setResult(Activity.RESULT_OK, returnIntent);

                }
                finish();
            }
        });
    }


    private void showDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Path.this, android.R.layout.select_dialog_singlechoice);
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
                AlertDialog.Builder builderInner = new AlertDialog.Builder(Path.this);

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
