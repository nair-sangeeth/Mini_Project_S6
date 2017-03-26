package com.example.nair_sangeeth.mini_project_s6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CarHome extends AppCompatActivity implements View.OnClickListener{
    private String RegistrationNum;
    private TextView BName,RNum;
    private DatabaseReference databaseReference,userRef,vehicleInfo,vehicleRef;
    private DatabaseReference BRef;
    private Button locationButton;
    private Button homeButton;
    private Button addDriver;
    private FirebaseUser firebaseUser;
    private String BrandName_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_home);

        Intent N=getIntent();
        RegistrationNum=N.getStringExtra("Registration Number");
        BName=(TextView) findViewById(R.id.BName);
        RNum=(TextView) findViewById(R.id.RegNum);
        addDriver=(Button) findViewById(R.id.buttonDriverADD);
        homeButton=(Button) findViewById(R.id.HomeButton);
        homeButton.setOnClickListener(this);
        addDriver.setOnClickListener(this);
        locationButton=(Button) findViewById(R.id.locationButton);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        userRef=databaseReference.child(firebaseUser.getUid());
        vehicleInfo=userRef.child("Vehicle Information");
        vehicleRef=vehicleInfo.child(RegistrationNum);
        BRef=vehicleRef.child("Brand Name");
        BRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String BrandName=dataSnapshot.getValue(String.class);
                BName.setText(BrandName);
                BrandName_Button=BrandName;
                RNum.setText(RegistrationNum);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v==homeButton){
            startActivity(new Intent(CarHome.this,First_Time_User_HomeScreenActivity.class));
        }
        else if (v==locationButton){
            startActivity(new Intent(CarHome.this,VehicleLocation.class).putExtra("BrandName",BrandName_Button));
        }
        else if (v==addDriver){
            startActivity(new Intent(CarHome.this,AddDriver.class).putExtra("RegNum",RegistrationNum));
        }
    }
}
