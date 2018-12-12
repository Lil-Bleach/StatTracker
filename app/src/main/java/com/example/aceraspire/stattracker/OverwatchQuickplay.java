package com.example.aceraspire.stattracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class OverwatchQuickplay extends OverwatchSignInActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overwatch_quickplay);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        testView = (TextView)findViewById(R.id.textView2);
        try {
            testView.setText(profile.get("gamesWon"));
        } catch (Exception e) {
            testView.setText("ERROR");
        }

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
        } else if (id == R.id.nav_fortnite_stats) {
            startActivity(new Intent(getApplicationContext(), FortniteStatsActivity.class));
        } else if (id == R.id.nav_overwatch_quickplay) {
            startActivity(new Intent(getApplicationContext(), OverwatchQuickplay.class));
        } else if (id == R.id.nav_overwatch_competitive) {
            startActivity(new Intent(getApplicationContext(), OverwatchCompetitiveActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
