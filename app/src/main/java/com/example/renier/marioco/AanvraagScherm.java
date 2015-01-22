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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class AanvraagScherm extends Activity {

    EditText naam;
    EditText Straat;
    EditText Telefoon;
    EditText email;
    private ServerCommunicator serverCommunicator;
    String Gekozen;
    String ip ="145.101.90.12";
    int port = 4444;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aanvraag_scherm);

        EditText naam = (EditText) findViewById(R.id.editText);
        EditText Straat = (EditText) findViewById(R.id.editText2);
        EditText Telefoon = (EditText) findViewById(R.id.editText3);
        EditText email = (EditText) findViewById(R.id.editText4);


        Intent hoofdscherm = getIntent();
        Gekozen = hoofdscherm.getStringExtra("Gekozen");
        System.out.println(Gekozen);


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

        JSONArray aanvraaglijst = new JSONArray();
        JSONObject aanvraag = new JSONObject();
        try {
            aanvraaglijst.put(new JSONObject().put("servicenaam","test"));
                    //Gekozen.toString()));

            JSONObject obj2 = new JSONObject();
            obj2.put("kopernaam", naam.getText().toString());
            obj2.put("koperadres", Straat.getText().toString());
            obj2.put("kopertelnr", Telefoon.getText().toString());
            obj2.put("koperemail", email.getText().toString());

            aanvraaglijst.put(obj2);

            aanvraag.put("aanvraag", aanvraaglijst);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        this.serverCommunicator = new ServerCommunicator(this,
                ip, port, aanvraag);





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
