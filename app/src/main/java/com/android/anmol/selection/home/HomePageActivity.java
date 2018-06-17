package com.android.anmol.selection.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.anmol.selection.R;
import com.android.anmol.selection.message_list.MessageListFragment;

import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity {

    private static final String HOME_FRAGMENT_TAG = "HOME_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, MessageListFragment.newInstance(), HOME_FRAGMENT_TAG)
                .commit();
    }
}