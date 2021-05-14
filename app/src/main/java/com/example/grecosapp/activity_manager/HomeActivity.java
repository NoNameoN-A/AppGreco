package com.example.grecosapp.activity_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.grecosapp.R;
import com.example.grecosapp.data.IP_METHODS;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        /**********Hooks***********/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar_home);

        /**********Tool Bar***********/
        setSupportActionBar(toolbar);

        /**********Navigation Drawer Menu***********/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_google_maps:
                intent = new Intent(HomeActivity.this, GoogleMaps.class);
                break;
            case R.id.nav_credits:
                intent = new Intent(HomeActivity.this, CreditsActivity.class);
                break;
            case R.id.whois_ip:
                intent = new Intent(HomeActivity.this, WhoisIPActivity.class);
                break;
            case R.id.nav_profile:
                intent = new Intent(HomeActivity.this, ProfileActivity.class);
                break;
            case R.id.nav_logout:
                intent = new Intent(HomeActivity.this, LogoutActivity.class);
                break;
            case R.id.nav_share:
                intent = new Intent(HomeActivity.this, ShareActivity.class);
                break;
            case R.id.nav_rate:
                intent = new Intent(HomeActivity.this, RateActivity.class);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        startActivity(intent);
        return true;
    }

    public void goto_whois(View view) {
        startActivity(new Intent(HomeActivity.this, WhoisIPActivity.class));
    }

    public void goto_vpn(View view) {
        startActivity(new Intent(HomeActivity.this, VPNCheckActivity.class));
    }

    public void goto_proxy(View view) {
        startActivity(new Intent(HomeActivity.this, ProxyCheckActivity.class));
    }

    public void goto_tor(View view) {
        startActivity(new Intent(HomeActivity.this, TorCheckActivity.class));
    }

    public void goto_isp(View view) {
        startActivity(new Intent(HomeActivity.this, ISPCheckActivity.class));
    }

    public void goto_blacklist(View view) {
        startActivity(new Intent(HomeActivity.this, CheckBlacklistActivity.class));
    }

    public void goto_geo(View view) {
        // NUOVO
         Uri gmmIntentUri = Uri.parse("geo: 38.115821838378906, 13.359760284423828");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
         mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

        // VECCHIO
        startActivity(new Intent(HomeActivity.this, GoogleMaps.class));
    }

    public void goto_bot(View view) {
        startActivity(new Intent(HomeActivity.this, CheckBOTActivity.class));
    }
}