package com.example.nair_sangeeth.mini_project_s6;

import android.content.Intent;
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


    private Button buttonCont;
    public FirebaseUser user;
    public TextView Bname;
    public TextView RNum;
    public TextView Hid;
    public String RegNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_added);
        Bname=(TextView) findViewById(R.id.BName);
        RNum=(TextView) findViewById(R.id.RegNum);
        Hid=(TextView) findViewById(R.id.HID);
        Intent i=getIntent();
        RegNumber= i.getStringExtra("RegNumber");
        String BName=i.getStringExtra("BName");
        String HID=i.getStringExtra("HID");
        buttonCont=(Button) findViewById(R.id.buttonContinue);
        buttonCont.setOnClickListener(this);
        RNum.setText(RegNumber);
        Bname.setText(BName);
        Hid.setText(HID);
        



    }

    @Override
    public void onClick(View view) {
        if (view==buttonCont){
        startActivity(new Intent(CarAdded.this,AddDriver.class).putExtra("RegNum",RegNumber));
        }

    }
}
