package com.medicare.sisgninnotwork4.ui.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.medicare.sisgninnotwork4.NotificationFragment;
import com.medicare.sisgninnotwork4.R;

import java.util.HashMap;

import androidx.navigation.Navigation;

public class MainFragment extends Fragment {
    //


    private MainViewModel mViewModel;
    //Firebase
    private static final String TAG = "";
    public static EditText inputEmail, inputPassword;
   private Button studentLogin;
    private FirebaseAuth mAuth;
    private CheckBox category;
    public   static     boolean checked;
    private ProgressBar progressBar;
//    FirebaseAuth.AuthStateListener mAuthListner;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
//        mAuth.addAuthStateListener(mAuthListner);
        checked=false;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //firebase
        mAuth = FirebaseAuth.getInstance();
        //check the current user
        /*i changed the line*/
        /*maybe i should remove it while trying to make alogin for employees*/
//        if (mAuth.getCurrentUser() != null) {
//            Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_employeeFragment);
//            }


        inputEmail = (EditText) getView().findViewById(R.id.editText_username_loginpage);
        inputPassword = (EditText) getView().findViewById(R.id.editText_password_loginpage);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar_loginpage);
         studentLogin = (Button) getView().findViewById(R.id.button_not);
        mAuth = FirebaseAuth.getInstance();

        Button button2 = getView().findViewById(R.id.button_cac);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_mainFragment_to_createaccountFragment);


            }
        });
        category=(CheckBox) getView().findViewById(R.id.cbCategory);
        category.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checked = ((CheckBox) v).isChecked();


            }
        });




            studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userLoginEmail = inputEmail.getText().toString();
                String userLoginPassword = inputPassword.getText().toString();

                if(!TextUtils.isEmpty(userLoginEmail)&& !TextUtils.isEmpty(userLoginPassword)) {
                    loginUser(userLoginEmail, userLoginPassword);
                }else{
                    Toast.makeText(getActivity(), "Failed Login: Empty Inputs are not allowed", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    private void loginUser(final String userLoginEmail, final String userLoginPassword) {
        mAuth.signInWithEmailAndPassword(userLoginEmail, userLoginPassword)
                .addOnCompleteListener((Activity)getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            if(checked)
                            Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_employeeFragment);
                            else
                                Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_notificationFragment);
                        }else{
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            return;
                        }
                    }
                });

//        mAuthListner = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if (firebaseAuth.getCurrentUser() != null) {
//                    Navigation.findNavController(getView()).navigate(
//                            R.id.action_mainFragment_to_createaccountFragment);
//
//
//                }
//            }
//        };


        // create account


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}