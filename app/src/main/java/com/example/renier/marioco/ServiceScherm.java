package com.example.renier.marioco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ServiceScherm extends Activity {


    String Gekozen;
    TextView titel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_scherm);

        Intent hoofdscherm = getIntent();
        Gekozen = hoofdscherm.getStringExtra("Gekozen");


        TextView titel = (TextView)findViewById(R.id.text);
        this.titel = titel;
        titel.setText(Gekozen);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service_scherm, menu);
        return true;

    }

    public void Volgende (View view)
    {
        Intent i = new Intent(this, AanvraagScherm.class);
        startActivity(i);
        finish();
    }

    public void Terug (View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
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
}
