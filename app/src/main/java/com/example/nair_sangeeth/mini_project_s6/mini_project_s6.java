package com.example.nair_sangeeth.mini_project_s6;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by nair_sangeeth on 3/10/17.
 */

public class mini_project_s6 extends Application {


    public void OnCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
