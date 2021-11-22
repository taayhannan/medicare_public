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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AmbulanceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AmbulanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AmbulanceFragment extends Fragment {
    public  static View view;
//    private  static   ListView listView;

//   public static ArrayList<String> nos=new ArrayList<String>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AmbulanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AmbulanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AmbulanceFragment newInstance(String param1, String param2) {
        AmbulanceFragment fragment = new AmbulanceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        AmbulanceViewModel viewModel = ViewModelProviders.of(this).get(AmbulanceViewModel.class);

        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData2();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    ArrayList<String> nos=new ArrayList<String>();
                    for(DataSnapshot dataSnapshot12 :dataSnapshot.getChildren()){
                        nos.add(dataSnapshot12.child("nos").getValue(String.class));
                        ListView listView=(ListView) getView().findViewById(R.id.mob);
                        ArrayAdapter<String> listViewAdapter=new ArrayAdapter<String>(
                                getActivity(),android.R.layout.simple_list_item_1,nos
                        );

                        listView.setAdapter(listViewAdapter);






                    }



                }
            }});

        getActivity().setTitle("Ambulance Numbers");
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_ambulance,container,false);
//        mViewModel=ViewModelProviders.of(this).get(AmbulanceViewModel.class);
//        mViewModel.getListViewAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1);
//
//        ((ListView)view.findViewById(R.id.mob)).setAdapter(mViewModel.listViewAdapter);

        return view;
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
