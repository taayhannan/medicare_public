package com.medicare.sisgninnotwork4;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AmbulanceViewModel extends ViewModel {
    private static final DatabaseReference REF =
            FirebaseDatabase.getInstance().getReference("INFO/CONTACTS");




    private final FirebaseQueryLiveData liveData2 = new FirebaseQueryLiveData(REF);


    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData2() {
        return liveData2;
    }

}
