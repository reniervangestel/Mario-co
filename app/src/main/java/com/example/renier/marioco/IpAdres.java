package com.example.renier.marioco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class IpAdres extends Activity {


    Button volgende;
    EditText Ip;
    public String ip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_adres);

        Button volgende = (Button)findViewById(R.id.button);
        this.volgende = volgende;

        EditText Ip = (EditText)findViewById(R.id.editText6);
        this.Ip = Ip;



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ip_adres, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void geklikt(View view)
    {
        ip = Ip.getText().toString();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("ip", ip);
        startActivity(i);
        finish();
        System.out.println(ip);
    }

}
