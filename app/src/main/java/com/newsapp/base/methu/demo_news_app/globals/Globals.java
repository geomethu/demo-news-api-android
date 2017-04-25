package com.newsapp.base.methu.demo_news_app.globals;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Methu on 4/23/2017.
 */

public class Globals {

    public static String no_existing_account = "There is no user record corresponding to this identifier. The user may have been deleted.";
    public static String path = "https://newsapi.org/v1/";

    public static String articals = "articles?apiKey=89488e46044f46f9b8424851aabcf298&source=the-next-web";
    public static String technology = "sources?category=technology";

    //Email address Validation
    public final static boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}
