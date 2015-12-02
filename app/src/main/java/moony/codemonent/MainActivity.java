package moony.codemonent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import moony.codemonent.fragment.FourFragment;
import moony.codemonent.fragment.JokeFragment;
import moony.codemonent.fragment.NewsFragment;
import moony.codemonent.fragment.ThreeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private Fragment fragment;
    private int curFrag;
    private static final int INDEX_NEWS = 0;
    private static final int INDEX_JOKE = 1;
    private static final int INDEX_TE = 2;
    private static final int INDEX_AE = 3;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        initFrag();
    }

    public void initFrag() {
        curFrag = INDEX_NEWS;
        fragment = new NewsFragment();

        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }

    public void showFrag(int index) {
        Log.e("index", index + "  " + curFrag);
        if (index == curFrag) {
            return;
        }
        switch (index) {
            case INDEX_NEWS:
                fragment = new NewsFragment();
                break;
            case INDEX_JOKE:
                fragment = new JokeFragment();
                break;
            case INDEX_TE:
                fragment = new ThreeFragment();
                break;
            case INDEX_AE:
                fragment = new FourFragment();
                break;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();

        curFrag = index;

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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            showFrag(INDEX_NEWS);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            showFrag(INDEX_JOKE);
        } else if (id == R.id.nav_slideshow) {
            showFrag(INDEX_TE);
        } else if (id == R.id.nav_manage) {
            showFrag(INDEX_AE);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        //是否抽屉点中
        item.setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
