package com.awesome.jnpatel.capstone;
/**
 * Created by jnpatel on 7/15/16.
 */
import android.app.Activity;
import android.os.Bundle;

public class InfoActivity extends Activity {
    public static final String EXTRA_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //multithreading
        fragmentInfo();
    }

    public void fragmentInfo()
    {
        InfoFragment infoFragment = (InfoFragment)
                getFragmentManager().findFragmentById(R.id.info_frag);

        int infoId = (int) getIntent().getExtras().get(EXTRA_ID);
        infoFragment.setCity(infoId);
    }

}
