package com.medicare.sisgninnotwork4;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.medicare.sisgninnotwork4.ui.main.MainFragment;
import com.medicare.sisgninnotwork4.ui.main.Notificationsforstudents;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NotificationFragment.OnFragmentInteractionListener,AmbulanceFragment.OnFragmentInteractionListener,CreateaccountFragment.OnFragmentInteractionListener,EmployeeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);




    }
    @Override
    public void onFragmentInteraction(Uri uri) {
    }


}
