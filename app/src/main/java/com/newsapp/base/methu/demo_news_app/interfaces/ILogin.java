package com.newsapp.base.methu.demo_news_app.interfaces;

/**
 * Created by Methu on 7/13/2016.
 */
public interface ILogin {
    void isAuthenticated(boolean res);
    void isRegistered(boolean res);
    void register(boolean res, int page);
    void showhidePB(boolean status);
}
