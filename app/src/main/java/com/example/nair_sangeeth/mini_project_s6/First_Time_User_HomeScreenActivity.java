package com.example.nair_sangeeth.mini_project_s6;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class First_Time_User_HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAddCar;
    private Button buttonlogout;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView textViewWelcome;
    private FirebaseUser firebaseUser;
    private String Welcome;
    private String username;
    private DatabaseReference databaseReference;
    private DatabaseReference childref,profileref;
    private Button buttonViewCar;
    private DatabaseReference firstNameref;
    private String[] userInfo=new String[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_user_homescreenactivity);


        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        buttonlogout=(Button) findViewById(R.id.logout);
        buttonViewCar=(Button) findViewById(R.id.findCar);
        buttonAddCar=(Button) findViewById(R.id.addcar);
        textViewWelcome=(TextView) findViewById(R.id.textViewWelMess);
        buttonAddCar.setOnClickListener(this);
        buttonlogout.setOnClickListener(this);
        buttonViewCar.setOnClickListener(this);
        childref=databaseReference.child(firebaseUser.getUid());
        profileref=childref.child("Profile Information");
        firstNameref=profileref.child("First Name");
        firstNameref.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               username=dataSnapshot.getValue(String.class);
               System.out.print(username);
               Toast.makeText(First_Time_User_HomeScreenActivity.this, username, Toast.LENGTH_SHORT).show();
               Welcome="Welcome "+username;
               textViewWelcome.setText(Welcome);
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
        if (v.equals(buttonAddCar)){
            startActivity(new Intent(First_Time_User_HomeScreenActivity.this,AddCar.class));
        }
        else if (v.equals(buttonlogout)){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(First_Time_User_HomeScreenActivity.this,Login.class));
        }
        else if (v.equals(buttonViewCar)){
            startActivity(new Intent(First_Time_User_HomeScreenActivity.this,SearchCar.class));
        }
    }
}
