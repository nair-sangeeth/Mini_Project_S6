package com.example.nair_sangeeth.mini_project_s6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

import java.util.Map;

public class SearchCar extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private Button SearchButton;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_car);

        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        textView=(TextView) findViewById(R.id.Searchbox);
        SearchButton=(Button) findViewById((R.id.searchButton));
        SearchButton.setOnClickListener(this);
    }


    void SearchCar(){
        DatabaseReference userref=databaseReference.child(user.getUid());
        DatabaseReference vehicle=userref.child("Vehicle Information");
        final String RegistrationNum=textView.getText().toString().trim();
        DatabaseReference userVehicle=vehicle.child(RegistrationNum);
        DatabaseReference RegNumRef=userVehicle.child("Registration Number");

        RegNumRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String Number=dataSnapshot.getValue(String.class);

                if (Number.equals(RegistrationNum)){
                    Toast.makeText(SearchCar.this,"Search Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SearchCar.this,CarHome.class).putExtra("Registration Number",RegistrationNum));
                }
                else{
                    Toast.makeText(SearchCar.this,"Not Found",Toast.LENGTH_SHORT).show();
                    //this option wont work because child node will not be read
                }

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
        if (view==SearchButton){
            Toast.makeText(this,"Button Press Recorded",Toast.LENGTH_SHORT).show();
            SearchCar();
        }
    }
}
