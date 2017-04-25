package com.newsapp.base.methu.demo_news_app.fragments;

import android.content.Context;
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

public class Fragment_register extends Fragment implements View.OnClickListener {
    Button btn_regiser;
    EditText edtEmail, edtPassword, edtConfirmPassword;
    private FirebaseAuth firebaseAuth;
    static ILogin _ILogin;

    public static Fragment_register newInstance() {
        Fragment_register fr = new Fragment_register();

        return fr;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            _ILogin = (ILogin) context;
        }catch (ClassCastException ex){
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
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        btn_regiser = (Button) rootView.findViewById(R.id.btn_register);
        btn_regiser.setOnClickListener(this);
        edtEmail = (EditText) rootView.findViewById(R.id.et_register_email);
        edtPassword = (EditText) rootView.findViewById(R.id.et_register_password);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                if (validate()) {
                    _ILogin.showhidePB(true);
                    (firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    _ILogin.showhidePB(false);
                                    if(task.isSuccessful()){
                                        edtPassword.setText(null);
                                        edtEmail.setText(null);
                                        _ILogin.isRegistered(true);
                                    }else{
                                        Toast.makeText(getActivity(), ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                break;
            default:
                break;
        }
    }

    boolean validate() {
        boolean res = true;
        if (!Globals.isValidEmail(edtEmail.getText().toString().trim())) {
            edtEmail.setError("Enter valid email");
            return false;
        }
        if (edtPassword.getText().toString().equalsIgnoreCase("") || edtPassword.getText().toString().trim().length() < 6) {
            edtPassword.setError("Length (6-8)");
            return false;
        }

        return res;
    }

    public void signIn(View v){
        _ILogin.register(true, 1);
    }

}

