package com.example.aceraspire.stattracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class FortniteSignInActivity extends Home
        implements NavigationView.OnNavigationItemSelectedListener {
    String userinfo;
    EditText userinfoInput;
    TextView output;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortnight_signin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * userinfoinput is the user info
         * output is the text box display
         * submit is the button
         * userinfo is the userinfoinput saved to String
         * url is url for the api call
         *  !!! api call not implemented !!!
         */
        userinfoInput = (EditText)findViewById(R.id.FN_user);
        output = (TextView)findViewById(R.id.FN_output);
        submit = (Button)findViewById(R.id.FN_send);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userinfo = userinfoInput.getText().toString();
                String url = "https://ow-api.com/v1/stats/pc/us/" + userinfo + "/profile";
                output.setText(url);

            }
        });

        /*
        final Button fortniteStats = findViewById(R.id.stats);
        fortniteStats.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
            }
        });

        final Button owStats = findViewById(R.id.button2);
        owStats.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
            }
        });
        */

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fortnite, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_overwatch_signin) {
            // Handle the overwatch tab action
            startActivity(new Intent(getApplicationContext(), OverwatchSignInActivity.class));
        } else if (id == R.id.nav_fortnite_signin) {
            // Handle the Fortnite Screens
            startActivity(new Intent(getApplicationContext(), FortniteSignInActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
