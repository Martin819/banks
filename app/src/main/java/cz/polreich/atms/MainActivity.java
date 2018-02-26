package cz.polreich.atms;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.polreich.atms.controller.Controller;
import cz.polreich.atms.model.airBank.Branch;

import static cz.polreich.atms.R.string.title_branches;
import static cz.polreich.atms.R.string.title_dashboard;
import static cz.polreich.atms.R.string.title_notifications;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static Context context;
    private RecyclerView mRecyclerView;
    private BranchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private AirBankService airBankService;
    private List<Branch> branchesList = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_atms:
                    mTextMessage.setText(title_branches);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String airbank_apikey = getResources().getString(R.string.airbank_apikey);
//        mTextMessage = (TextView) findViewById(message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mRecyclerView = (RecyclerView) findViewById(R.id.branches_list_recycler_view);
        mAdapter = new BranchesAdapter(branchesList, mRecyclerView);


        Controller controller = new Controller();
        controller.start(airbank_apikey, mAdapter);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    public static Context getContext(){
        return context;
    }

}
