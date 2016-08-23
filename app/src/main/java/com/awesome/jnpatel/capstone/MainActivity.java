package com.awesome.jnpatel.capstone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends Activity implements home_Fragment.HomeListener {

    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);

        if (fragmentContainer != null) {
            InfoFragment info = new InfoFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            info.setCity(id);
            ft.replace(R.id.fragment_container, info);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent(this, InfoActivity.class);
            intent.putExtra(InfoActivity.EXTRA_ID, (int)id);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
            this.id = id;
        //noinspection SimplifiableIfStatement
        if (id == R.id.change_city) {
            showInputDialog();
        }
        else if (id == R.id.dateAndTime)
        {
            showSecondINputDialog();
        }
        else if (id == R.id.menu_c)
        {
            Toast.makeText(this, "Temperature will show in Celcius", Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.menu_f)
        {
            Toast.makeText(this, "Temperature will show in Fahrenheit", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public static boolean convertTemp() {
        if (id == R.id.menu_c)
        {
            return true;
        }
        else if (id == R.id.menu_f)
        {
            return false;
        }

        return false;
    }

    private void showSecondINputDialog() {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Toast.makeText(this, currentDateTimeString,
                Toast.LENGTH_LONG).show();
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("App info");
        builder.setPositiveButton("This Application was Developed by CIS-436 group student's" +
                " Jaykumar Patel, Lynn Turnbull, Rafi Odisho under professor John Baugh supervision. \n" +
                "This capston project will provide waether service feed for most of the famous cities in the state of Michigan.\n" +
                "", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

}
