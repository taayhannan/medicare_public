package com.medicare.sisgninnotwork4;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.medicare.sisgninnotwork4.ui.main.Contact;
import com.medicare.sisgninnotwork4.ui.main.MainFragment;
import com.medicare.sisgninnotwork4.ui.main.Notificationsforstudents;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {



    public static RecyclerView recycle;
   public static  List<Notificationsforstudents> list;
public  static  String varBloodGroup;


    //firebase
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }



    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Welcome to Medicare");
        Button button = getView().findViewById(R.id.button_ambulance);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_notificationFragment_to_ambulanceFragment);
            }
        });

        Button button2 = (Button) getView().findViewById(R.id.button_signout);
        mAuth = FirebaseAuth.getInstance();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

            }
        });
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null)
                {

                    Navigation.findNavController(view).navigate(
                            R.id.action_notificationFragment_to_mainFragment);


                }
            }
        };
//        //Recycler
//         rvContacts = (RecyclerView) getView().findViewById(R.id.rvContacts);
//
//        // Initialize contacts public ContactsAdapter adapter;
//        //   public List<Contact> contacts;
////        List<Notificationsforstudents> contacts = Notificationsforstudents.createNotList(tv1,tv2,tv3,tv4);
//        // Create adapter passing in the sample user data
//
//        // Set layout manager to position the items
//        rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
//        // Inflate the layout for this fragment
//        HotStockViewModel viewModel = ViewModelProviders.of(this).get(HotStockViewModel.class);
//
//        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();
//
//        liveData.observe(this, new Observer<DataSnapshot>() {
//            @Override
//            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null) {
//                    for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
//
//                        Notificationsforstudents value = dataSnapshot1.getValue(Notificationsforstudents.class);
//                       Notificationsforstudents fire = new Notificationsforstudents();
//                        String patient = value.getmPatient();
//                        String bloodgroup = value.getmBg();
//                        String date = value.getmDate();
//                        String time = value.getmTime();
//                        fire.setmPatient(patient);
//                        fire.setmBg(bloodgroup);
//                        fire.setmDate(date);
//                        fire.setmTime(time);
//                        contacts.add(fire);
//
//                    }
//////                    // update the UI here with values in the snapshot
////                    tv1 = dataSnapshot.child("patient").getValue(String.class);
////                    tv2 = dataSnapshot.child("bloodgroup").getValue(String.class);
////                    tv3 = dataSnapshot.child("date").getValue(String.class);
////                    tv4 = dataSnapshot.child("time").getValue(String.class);
////                    List<Notificationsforstudents> contacts = Notificationsforstudents.createNotList(tv1,tv2,tv3,tv4);
////
////
////                    ContactsAdapter adapter = new ContactsAdapter(contacts);
////                    // Attach the adapter to the recyclerview to populate items
////                    rvContacts.setAdapter(adapter);
//
//
//
//                }
//            }
//        });
        recycle = (RecyclerView) getView().findViewById(R.id.rvContacts);
        HotStockViewModel viewModel = ViewModelProviders.of(this).get(HotStockViewModel.class);

        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                String emcheck=(com.medicare.sisgninnotwork4.ui.main.MainFragment.inputEmail).getText().toString();
                if(dataSnapshot.child("USER")!=null){
                    for(DataSnapshot dataSnapshot2:dataSnapshot.child("USER").getChildren()){
                        if((dataSnapshot2.child("Email").getValue(String.class)).equals(emcheck))
                        {
                            varBloodGroup=dataSnapshot2.child("Blood Group").getValue(String.class);
                        }
                    }
                }

                if (dataSnapshot.child("NOT") != null) {
                    list = new ArrayList<Notificationsforstudents>();
                    for(DataSnapshot dataSnapshot1 :dataSnapshot.child("NOT").getChildren()){
//                        Notificationsforstudents value = dataSnapshot1.getValue(Notificationsforstudents.class);
                        if((dataSnapshot1.child("bloodgroup").getValue(String.class)).equals(varBloodGroup)) {
                            Notificationsforstudents fire = new Notificationsforstudents();

                            fire.setmPatient(dataSnapshot1.child("patient").getValue(String.class));
                            fire.setmBg(dataSnapshot1.child("bloodgroup").getValue(String.class));
                            fire.setmDate(dataSnapshot1.child("date").getValue(Long.class));
                            fire.setmTime(dataSnapshot1.child("time").getValue(Integer.class));
                            list.add(fire);
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, getContext());
                            recycle.setLayoutManager(new LinearLayoutManager(getActivity()));

                            /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                            // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

                            recycle.setAdapter(recyclerAdapter);
                        }



                    }


                }}});








    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notification, container, false);




    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
