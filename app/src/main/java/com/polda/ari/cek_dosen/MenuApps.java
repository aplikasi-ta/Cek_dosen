package com.polda.ari.cek_dosen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MenuApps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_apps);
        Toast.makeText(getApplication(),"Data Dosen "+Frm_login.lattitude+" - "+Frm_login.longitude+"",Toast.LENGTH_LONG).show();
    }
}
