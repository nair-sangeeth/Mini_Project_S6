package com.example.nair_sangeeth.mini_project_s6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegActivitySuccess extends AppCompatActivity implements View.OnClickListener {

    private Button cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_success);

        cont=(Button) findViewById(R.id.buttonContinue);
        cont.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==cont){
            startActivity(new Intent(RegActivitySuccess.this,First_Time_User_HomeScreenActivity.class));
        }
    }
}
