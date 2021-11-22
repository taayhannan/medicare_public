package com.medicare.sisgninnotwork4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import com.medicare.sisgninnotwork4.ui.main.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmployeeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EmployeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmployeeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EmployeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmployeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmployeeFragment newInstance(String param1, String param2) {
        EmployeeFragment fragment = new EmployeeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_employee, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //for opening notification fragment

        Button button2=getView().findViewById(R.id.button_inform_students);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){


                Navigation.findNavController(view).navigate(
                        R.id.action_employeeFragment_to_notificationFragment);

                saveNotification(view);

            }

        });
        Button button3=getView().findViewById(R.id.button_add_mob);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Navigation.findNavController(view).navigate(
                        R.id.action_employeeFragment_to_ambulanceFragment);


                saveNo(view);

            }

        });


    }


    //yuuu
    public void saveNotification(View view)
    {

        String spatient = ((EditText)getView().findViewById(R.id.et_patient_name)).getText().toString();
        String sbloodgroup = ((EditText)getView().findViewById(R.id.et_blood_group)).getText().toString();
        long sdate = Long.parseLong(((EditText)getView().findViewById(R.id.et_date)).getText().toString());
        int stime = Integer.parseInt(((EditText)getView().findViewById(R.id.et_time)).getText().toString());





        if(spatient.isEmpty())
        {
            return;
        }
        else
        {

            Map<String,Object> dataToSave=new HashMap<String, Object>();
            dataToSave.put("patient",spatient);
            dataToSave.put("bloodgroup",sbloodgroup);
            dataToSave.put("date",sdate);
            dataToSave.put("time",stime);
            FirebaseDatabase.getInstance().getReference("INFO/NOT").push().setValue(dataToSave);

        }
    }
    public void saveNo(View view)
    {

        String contactNo = ((EditText)getView().findViewById(R.id.et_phno)).getText().toString();






        if(contactNo.isEmpty())
        {
            return;
        }
        else
        {

            Map<String,Object> nos=new HashMap<String, Object>();
            nos.put("nos",contactNo);

            FirebaseDatabase.getInstance().getReference("INFO/CONTACTS").push().setValue(nos);

        }
    }


}
