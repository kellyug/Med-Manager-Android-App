package com.andronicus.med_manager.editprofile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andronicus.med_manager.R;

public class EditProfileActivity extends AppCompatActivity {

    /*
    * Helper method to start this activity
    * */
    public static Intent newIntent(@NonNull Context context){
        return new Intent(context,EditProfileActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_edit_profile);
    }
}
