package com.example.renier.marioco;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.renier.marioco.Preferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements OnItemSelectedListener {


    Spinner spinner;

    List<String> list;
    TextView serviceinfo;
    public static String serverIp = "145.101.90.12";
    public static int serverPort = 4444;
    ArrayList<String> serviceLijst;
    public static ArrayList<JSONObject> beknopteInformatielijst;
    public String informatiebeknopt = null;
    public static String servicenaam;
    Button volgende;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button volgende = (Button)findViewById(R.id.button);
        this.volgende = volgende;
        TextView serviceinfo = (TextView)findViewById(R.id.Veld1);

        this.serviceinfo = serviceinfo;

        serviceLijst = new ArrayList<String>();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("servicelijst", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = null;
        try {
            try {
                // Het ip adres die ik hieronder aanmaakt moet het ip adres zijn van de server waaruit die alles moet ophalen
                response = new ServerCommunicator(serverIp,
                        serverPort, jsonObject.toString()).execute().get();

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        if (response == null) {

            Toast.makeText(this, "Verbinding met de server niet mogelijk.", Toast.LENGTH_LONG).show();
        } else {
            // Haal de null naam weg van de JSONArray (Voorkomt error)
            String jsonFix = response.replace("null", "");

            JSONArray JArray = null;
            try {
                JArray = new JSONArray(jsonFix);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject jObject = null;
            String value = null;
            serviceLijst = new ArrayList<String>();

            for (int i = 0; i < JArray.length(); i++) {
                try {
                    jObject = JArray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    value = jObject.getString("naam");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                serviceLijst.add(value);

            }
            // haaalt de beknopte informatie op.
            beknopteInformatielijst = new ArrayList<JSONObject>();
            JSONObject beknoptjObject = new JSONObject();
            try {
                for (int i = 0; i < serviceLijst.size(); i++) {
                    beknoptjObject.put("informatiebeknopt", serviceLijst.get(i));
                    try {
                        try {
                            informatiebeknopt = new ServerCommunicator(serverIp,
                                    serverPort, beknoptjObject.toString()).execute().get();

                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    String infoFix = informatiebeknopt.replace("null", "");
                    JSONObject fixedjObject = new JSONObject(infoFix);
                    beknopteInformatielijst.add(fixedjObject);

                    Log.i("informatiebeknopt", infoFix);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        // Hieronder maak ik de spinner aan en geef ik aan waaaruit hij de informatie moet halen. Dit komt uit de server.
        spinner = (Spinner)findViewById(R.id.spinner);

        spinner
                .setAdapter(new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        serviceLijst));

        spinner
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0,
                                               View arg1, int position, long arg3) {
                        // TODO Auto-generated method stub
                        // geeft aan waar de text moet komen in de homefragment. DIt komt te staan in de textview Textservice.
                        TextView beknopteinfo = (TextView)findViewById(R.id.Veld1);

                        try {
                            beknopteinfo.setText(beknopteInformatielijst.get(position).getString("informatiebeknopt"));
                            servicenaam = serviceLijst.get(position);

                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0,
                               View arg1, int position, long arg3) {
        // TODO Auto-generated method stub
        // geeft aan waar de text moet komen in de homefragment. DIt komt te staan in de textview Textservice.
        TextView beknopteinfo = (TextView)findViewById(R.id.Veld1);

        try {
            beknopteinfo.setText(beknopteInformatielijst.get(position).getString("informatiebeknopt"));
            servicenaam = serviceLijst.get(position);

        } catch (Exception e) {

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    public void geklikt(View view)
    {

        Intent i = new Intent(MainActivity.this, ServiceScherm.class);
        i.putExtra("naam",servicenaam.toString());
        i.putExtra("ip",serverIp);
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