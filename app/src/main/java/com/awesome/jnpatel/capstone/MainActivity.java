/* Hello and welcome to our "Weather App" Capstone Project. This program was developed by CIS 436
   group students {Jaykumar Patel, Lynn Turnbull and Rafi Odisho} under professor John Baugh direction.
   The application is designed to help the people who visit the state of Michigan to get updated and
   accurate weather status for the top 10 most frequently visited cities. The application will update
   it's users with facts about the top 10 cities in addition to the current temperature, time, humidity
   and pressure. The user will have the opportunity to interact with application by rating his or her
   favorite city.

    @University of Michigan - Dearborn
    @CIS 436 Capstone Project "Weather App"
    @Student's: Jaykumar Patel
                Lynn Turnbull
                Rafi I. Odisho

    @Professor: John Baugh
    @August/24/2016
*
* */
package com.awesome.jnpatel.capstone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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

    // display info fragment when user select on the a city name,
    // it will choose to make a new activity and fragment depending on the device and what activity is open
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

    // inflating the menu xml to dispaly on the screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // display appropriate screen when appropriate menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
            this.id = id;
        //noinspection SimplifiableIfStatement
        if (id == R.id.aboutOurApp) {
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

    // setting a boolean to display temperature in Fahrenheit or Celcius
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

    // toast message will be display when user choose Date And Time menu option
    private void showSecondINputDialog() {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Toast.makeText(this, currentDateTimeString,
                Toast.LENGTH_LONG).show();
    }

    // message to display in the Dialog box
    String message = "This App will provide facts and Weather information about the most of the famous cities in the state of Michigan. \n \n"  +
            "Application Developed by:- \n" +
            "Students  :   Jaykumar Patel, Lynn Turnbull, Rafi Odisho. \n" +
            "Professor :   John Baugh \n \n" +
            "Sources:- \n" +
            "Weather feed is provided by OpenWeather.com \n" +
            "IndragniSoftSolutions Youtube Channel for the JASON tutorial \n" +
            "";
    // This dialog box will be displayed when user choose about the app in the menue
    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("App info");
        builder.setMessage(message);
        builder.setPositiveButton(" Done ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
