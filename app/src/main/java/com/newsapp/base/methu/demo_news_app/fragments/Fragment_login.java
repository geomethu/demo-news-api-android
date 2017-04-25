package com.newsapp.base.methu.demo_news_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.newsapp.base.methu.demo_news_app.R;
import com.newsapp.base.methu.demo_news_app.globals.Globals;
import com.newsapp.base.methu.demo_news_app.interfaces.ILogin;

/**
 * Created by Methu on 4/23/2017.
 */

public class Fragment_login extends Fragment implements View.OnClickListener {
    ILogin _ILogin;
    private FirebaseAuth firebaseAuth;
    Button btn_login;
    EditText edtEmail, edtPassword;
    TextView tv_link_signup;

    public static Fragment_login newInstance() {
        Fragment_login fl = new Fragment_login();

        return fl;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _ILogin = (ILogin) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        btn_login = (Button) rootView.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        tv_link_signup = (TextView) rootView.findViewById(R.id.link_signup);
        tv_link_signup.setOnClickListener(this);
        edtEmail = (EditText) rootView.findViewById(R.id.et_login_email);
        edtPassword = (EditText) rootView.findViewById(R.id.et_login_password);
        firebaseAuth = FirebaseAuth.getInstance();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    boolean validate() {
        boolean res = true;
        if (!Globals.isValidEmail(edtEmail.getText().toString().trim())) {
            edtEmail.setError("Enter valid email");
            return false;
        }
        if (edtPassword.getText().toString().equalsIgnoreCase("") || edtPassword.getText().toString().trim().length() < 3) {

            return false;
        }

        return res;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                if(validate()) {
                    _ILogin.showhidePB(true);
                    (firebaseAuth.signInWithEmailAndPassword(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    _ILogin.showhidePB(false);
                                    if (task.isSuccessful()) {
                                        edtPassword.setText(null);
                                        edtEmail.setText(null);
                                        _ILogin.isAuthenticated(true);

                                    } else {

                                        if(task.getException().getMessage().equalsIgnoreCase(Globals.no_existing_account)) {
                                            Toast.makeText(getActivity(), "Details does't exist, Register first", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getActivity(), "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                }
                break;

            case R.id.link_signup:
                _ILogin.register(true, 2);
                break;

            default:
                break;
        }
    }
}
