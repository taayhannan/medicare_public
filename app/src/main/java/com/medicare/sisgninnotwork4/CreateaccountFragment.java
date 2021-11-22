package com.medicare.sisgninnotwork4;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.medicare.sisgninnotwork4.ui.main.Rate;
import com.medicare.sisgninnotwork4.ui.main.SessionManager;

import java.util.HashMap;
import java.util.Map;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateaccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateaccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateaccountFragment extends Fragment {
    //
    private  static final String TAG="s";
    private static final String KEY_TITLE="title";
    private static final String KEY_DESCRIPTION="description";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private CollectionReference notebookRef=db.collection("users");
    private DocumentReference noteRef=db.document("users/info");
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText name, email_id, passwordcheck;
    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    public CreateaccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateaccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateaccountFragment newInstance(String param1, String param2) {
        CreateaccountFragment fragment = new CreateaccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Create Account");

        //firebase
        mAuth = FirebaseAuth.getInstance();
        email_id = (EditText) view.findViewById(R.id.editText_username);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        passwordcheck = (EditText) view.findViewById(R.id.editText_password);
        Button ahsignup = (Button) view.findViewById(R.id.button_signup_student);
        ahsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //


               saveRating(v);
                //
                String email = email_id.getText().toString();
                String password = passwordcheck.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(), "Enter Eamil Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {




                                    Navigation.findNavController(v).navigate(R.id.action_createaccountFragment_to_mainFragment);

                                    // Sign in success, update UI with the signed-in user's information

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


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
        final  View view
                = inflater.inflate(R.layout.fragment_createaccount, container, false);

        String [] values =
                {"A+","B+","AB+","O+","A-","B-","AB-","O-"};
        Spinner spinner = (Spinner) view.findViewById(R.id.bg);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        String [] values2 =
                {"yes","No"};
        Spinner spinner2 = (Spinner) view.findViewById(R.id.yn);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values2);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);

        String [] values3 =
                {"Employee","Student"};
        Spinner spinner3 = (Spinner) view.findViewById(R.id.emp_student);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values3);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter3);





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
    public void saveRating(View view)
    {
        EditText emailView = (EditText) getView().findViewById(R.id.editText_username);

        String  emailText = emailView.getText().toString();
        EditText AgeView = (EditText) getView().findViewById(R.id.age);

        int AgeText = Integer.parseInt( AgeView.getText().toString());

        Spinner bgView = (Spinner)getView().findViewById(R.id.bg);
        String bgText = bgView.getSelectedItem().toString();
//
//        Spinner bgView = (Spinner) getView().findViewById(R.id.bg);
//        String bgText="";
//        if(bgView.equals("A+"))
//            bgText="A+";
//        if(bgView.equals("A-"))
//            bgText="A-";
//        if(bgView.equals("B+"))
//            bgText="B+";
//        if(bgView.equals("B-"))
//            bgText="B-";
//        if(bgView.equals("AB+"))
//            bgText="AB+";
//        if(bgView.equals("AB-"))
//            bgText="AB-";
//        if(bgView.equals("O+"))
//            bgText="O+";
//        if(bgView.equals("O-"))
//            bgText="O-";


//        String  bgText = bgView.getTransitionName();
        Spinner dview=(Spinner)getView().findViewById(R.id.yn);
        String  dText = dview.getSelectedItem().toString();
        boolean dBool;
        if(dText.equals("yes"))
            dBool=true;
        else
            dBool=false;

        Spinner esView = (Spinner) getView().findViewById(R.id.emp_student);
        String esText = esView.getSelectedItem().toString();

//        String str="";
//        if(esView.equals("Employee"))
//            str="Employee";
//        else
//            str="Student";
//        SessionManager session = new SessionManager(getContext());
//        session.createLoginSession(emailText,esText);


        if(emailText.isEmpty())
        {
            return;
        }
        else
        {
//            Rate rate=new Rate(emailText,AgeText,bgText,dBool,esText);
//            notebookRef.add(rate);
            Map<String,Object> dataToSaveUser=new HashMap<String, Object>();
            dataToSaveUser.put("Email",emailText);
            dataToSaveUser.put("Age",AgeText);
            dataToSaveUser.put("Blood Group",bgText);
            dataToSaveUser.put("Can Donate",dBool);
            dataToSaveUser.put("Category",esText);
            FirebaseDatabase.getInstance().getReference("INFO/USER").push().setValue(dataToSaveUser);
        }
    }
}

