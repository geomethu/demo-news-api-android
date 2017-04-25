package com.newsapp.base.methu.demo_news_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.newsapp.base.methu.demo_news_app.activities.DashboardActivity;
import com.newsapp.base.methu.demo_news_app.activities.MainActivity;
import com.newsapp.base.methu.demo_news_app.fragments.Fragment_login;
import com.newsapp.base.methu.demo_news_app.fragments.Fragment_register;
import com.newsapp.base.methu.demo_news_app.interfaces.ILogin;

public class LoginActivity extends AppCompatActivity implements ILogin{

    FragmentTransaction ft;
    FragmentManager fragmentManager;
    Fragment_login _fragment_login;
    Fragment_register _fragment_register;
    static ProgressDialog progressDoalog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //progress dialog
        progressDoalog = new ProgressDialog(LoginActivity.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.setTitle("Synchronizing");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(true);

        if (savedInstanceState == null) {
            _fragment_login = new Fragment_login().newInstance();
            _fragment_register = new Fragment_register().newInstance();
        }
        displayFragment(true, 1);
    }

    protected void displayFragment(boolean tag, int page) {

        ft = getSupportFragmentManager()
                .beginTransaction();

        if (tag == true && page == 1) {
            if (_fragment_login.isAdded()) {
                ft.show(_fragment_login);
            } else {
                ft.disallowAddToBackStack();
                ft.replace(R.id.fragment_login, _fragment_login, "A");
            }
        }
        //Register
        if (tag == true && page == 2) {
            if (_fragment_register.isAdded()) {
                ft.show(_fragment_register);
            } else {
                ft.disallowAddToBackStack();
                ft.replace(R.id.fragment_login, _fragment_register, "B");
            }
        }


        ft.commit();
    }

    @Override
    public void isAuthenticated(boolean res) {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();
    }

    @Override
    public void isRegistered(boolean res) {
        displayFragment(res, 1);
    }

    @Override
    public void register(boolean res, int page) {
        displayFragment(res, page);
    }

    @Override
    public void showhidePB(boolean status) {
        if(status == true) {
            progressDoalog.show();
        }else{
            progressDoalog.dismiss();
        }
    }
}
