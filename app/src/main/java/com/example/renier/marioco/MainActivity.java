package com.example.renier.marioco;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements OnItemSelectedListener {


    Spinner spinner;
    ListView service;
    ArrayAdapter<String> adapter;
    List<String> list;
    TextView serviceinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();
        list.add("Riolering");
        list.add("Dak Lekkage");
        list.add("Prinses In Nood");

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        this.spinner = spinner;

        TextView serviceinfo = (TextView)findViewById(R.id.Veld1);
        this.serviceinfo = serviceinfo;

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {

        switch(position){
            case 0: //Riolering
                System.out.println("Riolering");

                serviceinfo.setText("Uw toiletproblemen in mum van tijd verholpen!");

                break;
            case 1: //Dak Lekkages
                System.out.println("Dak Lekkages");

                serviceinfo.setText("Valt alles in het water? Wij helpen u uit de brand!");

                break;
            case 2: //Prinses In Nood
                System.out.println("Prinses In Nood");

                serviceinfo.setText("Wij vinden het juiste kasteel voor u!");
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

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
