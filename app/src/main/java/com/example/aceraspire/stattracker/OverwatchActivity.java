package com.example.aceraspire.stattracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;


import org.json.JSONObject;

import java.net.URL;

public class OverwatchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private String userinfo;
    EditText userinfoInput;
    TextView output;
    Button submit;
    ImageView playerIcon;
    ImageView rankIcon;
    private String url;
    /** something idk */
    private static RequestQueue requestQueue;
    /** Json String */
    public String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overwatch_signin);
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
        userinfoInput = (EditText)findViewById(R.id.ow_input);
        playerIcon = (ImageView)findViewById(R.id.ow_ppic);
        rankIcon = (ImageView)findViewById(R.id.ow_rankicon);
        output = (TextView)findViewById(R.id.ow_output);
        submit = (Button) findViewById(R.id.ow_send);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userinfo = userinfoInput.getText().toString();
                /** the url for the api call */
                url = "https://ow-api.com/v1/stats/pc/us/" + userinfo + "/profile";

                System.out.println(url);
                startAPICall();
            }
        });

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
    void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            jsonString = response.toString();
                            JsonParser parser = new JsonParser();
                            JsonObject profile = parser.parse(jsonString).getAsJsonObject();
                            ow_profile oww = new ow_profile();
                            try {
                                oww.setName(profile.get("icon").getAsString());
                            } catch (NullPointerException e) {
                                output.setText("Invalid Name, ID, or Private Profile" + "\n" + "\nInput format is Name-1123 ");
                                return;
                            }
                            oww.setIcon(profile.get("icon").getAsString());
                            oww.setName(profile.get("name").getAsString());
                            oww.setLevel(profile.get("level").getAsInt());
                            oww.setRating(profile.get("rating").getAsInt());
                            oww.setPrestige(profile.get("prestige").getAsInt());
                            oww.setGamesWon(profile.get("gamesWon").getAsInt());
                            oww.setRatingIcon(profile.get("ratingIcon").getAsString());
                            loadImageFromUrl(oww.getIcon(), playerIcon);
                            loadImageFromUrl(oww.getRatingIcon(), rankIcon);
                            output.setText("Name: " + oww.getName() +
                                    "\nLevel: " + (oww.getPrestige() * 100 + oww.getLevel()) +
                                    "\nGames Won: " +oww.getGamesWon()  +
                                    "\nRating: " + oww.getRating()); //set text for text view
                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    output.setText("Ooopsie");
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void loadImageFromUrl(String icon, ImageView here) {
        try {
            Picasso.with(this).load(icon).into(here);
        } catch (IllegalArgumentException e) {
            Picasso.with(this).load(R.mipmap.ic_launcher).into(here);
            return;
        }
        Picasso.with(this).load(icon).into(here);
    }
    public String getJsonString() {
        return jsonString;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_overwatch_signin) {
            // Handle the overwatch tab action
            startActivity(new Intent(getApplicationContext(), OverwatchActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
