package com.example.renier.marioco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.renier.marioco.Preferences;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import com.example.renier.marioco.ServerCommunicator;


public class AanvraagScherm extends Activity {

    EditText naam;
    EditText Straat;
    EditText Telefoon;
    EditText email;
    private ServerCommunicator serverCommunicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aanvraag_scherm);

        EditText naam = (EditText) findViewById(R.id.editText);
        EditText Straat = (EditText) findViewById(R.id.editText2);
        EditText Telefoon = (EditText) findViewById(R.id.editText3);
        EditText email = (EditText) findViewById(R.id.editText4);

        this.naam = naam;
        this.Straat = Straat;
        this.Telefoon = Telefoon;
        this.email = email;

        String[] prefs = Preferences.getInstance(this).getCustomerInfoPreferences();
        if(prefs[0] != null)
            this.naam.setText(prefs[0]);
        if(prefs[1] != null)
            this.Straat.setText(prefs[1]);
        if(prefs[2] != null)
            this.Telefoon.setText(prefs[2]);
        if(prefs[3] != null)
            this.email.setText(prefs[3]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_aanvraag_scherm, menu);
        return true;
    }

    public void Annuleren(View view)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void Aanvragen(View view)
    {
        Context context = getApplicationContext();
        CharSequence text = "Succesvol aangevraagt!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

        String message = "er is een bestelling geplaatst door:  " + naam.getText().toString() + "   Het adres is : " + Straat.getText().toString();

        this.serverCommunicator = new ServerCommunicator( this, "145.101.89.247",4444,message  );





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
