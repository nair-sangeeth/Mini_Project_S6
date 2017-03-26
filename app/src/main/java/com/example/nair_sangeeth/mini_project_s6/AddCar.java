package com.example.nair_sangeeth.mini_project_s6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCar extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase database=FirebaseDatabase.getInstance();

    private EditText Brand;
    private EditText RegistrationNum;
    private EditText HardwareID;
    private Button CarSubmit;
    private String BrandName;
    public String RegNumber;
    private String HardwareId;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    public FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        user=firebaseAuth.getInstance().getCurrentUser();

        CarSubmit=(Button) findViewById(R.id.AddCarButton);
        Brand=(EditText) findViewById(R.id.CarBrand);
        RegistrationNum=(EditText) findViewById(R.id.CarRegNum);
        HardwareID=(EditText) findViewById(R.id.HardwareID);
        CarSubmit.setOnClickListener(this);


        databaseReference=FirebaseDatabase.getInstance().getReference();//database object

        }

    public void AddVehicle(){
        BrandName=Brand.getText().toString().trim();
        RegNumber=RegistrationNum.getText().toString().trim();
        HardwareId=HardwareID.getText().toString().trim();

        UserInfo userInfo=new UserInfo();
        userInfo.VehicleInfo(BrandName,RegNumber,HardwareId);
        DatabaseReference childref= databaseReference.child(user.getUid());
        DatabaseReference vehicleRef=childref.child("Vehicle Information");
        DatabaseReference uniqID=vehicleRef.child(RegNumber);
        DatabaseReference brandName=uniqID.child("Brand Name");
        DatabaseReference RegNum=uniqID.child("Registration Number");
        DatabaseReference HardwareID=uniqID.child("Hardware ID");
        brandName.child("Brand Name").setValue(BrandName);
        RegNum.child("Registration Number").setValue(RegNumber);
        HardwareID.child("HID").setValue(HardwareId);
        Toast.makeText(this,"Vehicle Added", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        if (view==CarSubmit){
            AddVehicle();
            startActivity(new Intent(AddCar.this,CarAdded.class).putExtra("RegNumber",RegNumber).putExtra("BName",BrandName).putExtra("HID",HardwareId));
        }
    }
}
