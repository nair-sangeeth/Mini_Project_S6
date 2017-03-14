package com.example.nair_sangeeth.mini_project_s6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CarAdded extends AppCompatActivity implements View.OnClickListener {


    private ListView VehicleDetails;
    private ArrayList<String> Vehicle_Details=new ArrayList<String>();
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    private Button buttonCont;
    private DatabaseReference VehicleRef;
    public FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_added);
        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getInstance().getCurrentUser();
        VehicleDetails=(ListView) findViewById(R.id.VehicleDetailsList);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.activity_car_added,Vehicle_Details);
        VehicleDetails.setAdapter(arrayAdapter);

        buttonCont=(Button) findViewById(R.id.buttonContinue);
        buttonCont.setOnClickListener(this);
        VehicleRef=databaseReference.child("Vehicle Information");

        VehicleRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value=dataSnapshot.getValue(String.class);
                Vehicle_Details.add(value);
                arrayAdapter.notifyDataSetChanged();
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
    public void onClick(View view) {
        if (view==buttonCont){

        }

    }
}
