package com.example.renier.marioco;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class AanvraagScherm extends Activity {


    private ServerCommunicator serverCommunicator;
    String servicenaam;
    String ip ="145.101.90.12";
    int port = 4444;
    String serverIp;

    private static String naam;
    private static String adres;
    private static String telefoon;
    private static String email;

    String responseFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aanvraag_scherm);





        Intent hoofdscher1= getIntent();
        servicenaam = hoofdscher1.getStringExtra("servicenaam");
        serverIp = hoofdscher1.getStringExtra("serverIp");
        System.out.println(servicenaam + "aanvraag scherm");




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



        plaatsBestelling();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }


    private void plaatsBestelling() {
        final EditText koperNaam = (EditText) findViewById(R.id.editText);
        final EditText koperAdres = (EditText) findViewById(R.id.editText2);
        final EditText koperTelefoon = (EditText) findViewById(R.id.editText3);
        final EditText koperEmail = (EditText) findViewById(R.id.editText4);

        naam = koperNaam.getText().toString();
        adres = koperAdres.getText().toString();
        telefoon = koperTelefoon.getText().toString();
        email = koperEmail.getText().toString();

        JSONObject bestelling = new JSONObject();
        JSONObject service = new JSONObject();
        JSONObject gegevens = new JSONObject();
        JSONArray bestelArray = new JSONArray();

        try {
            service.put("servicenaam", servicenaam.toString());
            gegevens.put("kopernaam", naam);
            gegevens.put("koperadres", adres);
            gegevens.put("kopertelnr", telefoon);
            gegevens.put("koperemail", email);

            bestelArray.put(service);
            bestelArray.put(gegevens);

            bestelling.put("aanvraag", bestelArray);

        } catch (JSONException e) {

        }
        String response = null;

        try {
            try {
                // Dit IP adres moet IP adres van server zijn.
                response = new ServerCommunicator(serverIp,
                        port, bestelling.toString()).execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        if(response == null)
        {
            Toast.makeText(this, "Server is momenteel niet bereikbaar", Toast.LENGTH_LONG).show();
        }
        else{
            responseFix = response.replace("null", "");

            Toast.makeText(this, responseFix, Toast.LENGTH_LONG).show();


        }




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
