package cz.polreich.banks.activity;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.polreich.banks.AppDatabase;
import cz.polreich.banks.fragments.ATMsListFragment;
import cz.polreich.banks.fragments.BranchesListFragment;
import cz.polreich.banks.adapter.BranchesAdapter;
import cz.polreich.banks.R;
import cz.polreich.banks.fragments.MapFragment;
import cz.polreich.banks.model.airBank.AirBankBranch;
import cz.polreich.banks.service.AirBankService;

public class HomeActivity extends AppCompatActivity implements
        BranchesListFragment.OnFragmentInteractionListener,
        ATMsListFragment.OnFragmentInteractionListener,
        MapFragment.OnFragmentInteractionListener {

    private final String DEBUG_TAG_INFO = "[INFO     ] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_ERROR = "[    ERROR] " + this.getClass().getSimpleName();
    private final String DEBUG_TAG_WARNING = "[ WARNING ] " + this.getClass().getSimpleName();
    private TextView mTextMessage;
    private static Context context;
    private RecyclerView mRecyclerView;
    private BranchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AirBankService airBankService;
    private List<AirBankBranch> branchesList = new ArrayList<>();
    private Toolbar mainToolbar;
    private BranchesListFragment blf = new BranchesListFragment();
    private static AppDatabase database;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_branches:
                    switchToBranchesList();
                    return true;
                case R.id.navigation_atms:
                    switchToATMsList();
                    return true;
                case R.id.navigation_map:
                    switchToMapList();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        mainToolbar.setTitle(R.string.title_branches);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_branches);
        new Thread(() -> {
            database = AppDatabase.getInstance(this);
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_toolbar_settings_button: {
                SettingsActivity.start(this.getApplicationContext());
                break;
            }
            // case blocks for other MenuItems (if any)
        }
        return false;
    }

    public static Context getContext(){
        return context;
    }

    public static AppDatabase getDatabase() {
        return database;
    }

    public void switchToBranchesList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, blf).commit();
        mainToolbar.setTitle(R.string.title_branches);
    }

    public void switchToATMsList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ATMsListFragment()).commit();
        mainToolbar.setTitle(R.string.title_atms);
    }

    public void switchToMapList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
        mainToolbar.setTitle(R.string.title_map);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
