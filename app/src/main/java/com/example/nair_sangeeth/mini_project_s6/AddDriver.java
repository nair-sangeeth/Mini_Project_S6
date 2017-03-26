package com.example.nair_sangeeth.mini_project_s6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDriver extends AppCompatActivity implements View.OnClickListener{

    private EditText DriverMail;
    private Button DriverReg;
    private String email;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        DriverMail=(EditText) findViewById(R.id.editTextdriverMail);
        DriverReg=(Button) findViewById(R.id.buttonDReg);
        DriverReg.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();


    }

    public void RegisterDriver(){

        Intent i=getIntent();
        String RegNum=i.getStringExtra("RegNum");
        databaseReference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef=databaseReference.child(firebaseUser.getUid());
        DatabaseReference vehicleRef=userRef.child("Vehicle Information");
        DatabaseReference regRef=vehicleRef.child(RegNum);
        DatabaseReference driverRef=regRef.child("Driver Information");
        DatabaseReference driveremail=driverRef.child("Driver Email");
        email=DriverMail.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter a Valid Email",Toast.LENGTH_SHORT).show();
            return;
        }

        driveremail.child("Mail").push().setValue(email);
        Toast.makeText(this,"Driver Added",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddDriver.this,RegActivitySuccess.class));


    }

    @Override
    public void onClick(View v) {
        if (v==DriverReg){
            RegisterDriver();
        }

    }
}
