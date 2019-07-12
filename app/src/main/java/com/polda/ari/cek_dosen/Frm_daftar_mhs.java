package com.polda.ari.cek_dosen;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Frm_daftar_mhs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_daftar_mhs);
    }

    private void getJSON(final String id){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Frm_dt_toko.this,"Menampilkan Data","Tunggu Sebentar...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Toast.makeText(getApplication(),"Data "+s,Toast.LENGTH_LONG).show();
                showDetail(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> nama_lok = new HashMap<>();
                nama_lok.put(Config.TAG_KODE, id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.UPLOAD_URL_DT, nama_lok);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showDetail(String json){

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int i = 0; i<result.length(); i++){
                JSONObject c = result.getJSONObject(i);

                String kode = c.getString(Config.TAG_KODE);
                String nama = c.getString(Config.TAG_NAMA_TOKO);
                String alamat = c.getString(Config.TAG_ALAMAT_TOKO);
                String ktp = c.getString(Config.TAG_NO_KTP);
                String hp = c.getString(Config.TAG_NO_HP);

                txt_nama_l.setText(nama);
                txt_alamat_l.setText(alamat);
                txt_kode_cust.setText(kode);
                txt_no_ktp.setText(ktp);
                txt_no_hp.setText(hp);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}