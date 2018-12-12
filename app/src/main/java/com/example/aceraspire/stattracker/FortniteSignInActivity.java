package com.example.aceraspire.stattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FortniteSignInActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String userinfo;
    EditText userinfoInput;
    TextView output;
    Button submit;
    private int textboxid;
    private String url;
    private static RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortnight_signin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        requestQueue = Volley.newRequestQueue(this);

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
        submit = (Button) findViewById(R.id.FN_send);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userinfo = userinfoInput.getText().toString();
                url = "https://api.fortnitetracker.com/v1/profile/pc/" + userinfoInput;
                System.out.println(url);
                startAPICall();
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

    /**
     * the API call method
     */
    void startAPICall(){
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            output.setText(response.toString()); //set text for text view
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    output.setText("Ooopsie");
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<>();
                    params.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
                    params.put("TRN-Api-Key", "c97b3250-39ed-4e3f-a6dc-f558ad1afab7");
                    return params;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
            startActivity(new Intent(getApplicationContext(), OverwatchActivity.class));
        } else if (id == R.id.nav_fortnite_signin) {
            // Handle the Fortnite Screens
            startActivity(new Intent(getApplicationContext(), FortniteSignInActivity.class));
        } else if (id == R.id.nav_fortnite_stats) {
            startActivity(new Intent(getApplicationContext(), FortniteStatsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
