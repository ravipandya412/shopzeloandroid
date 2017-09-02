package com.example.user.shopzelo;

import android.content.SharedPreferences;
import android.os.StrictMode;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBConnector {

    static public final String URL_LINK = "https://stale-armors.000webhostapp.com/";
    static StrictMode.ThreadPolicy th = new StrictMode.ThreadPolicy.Builder().build();

    static String getname;
    static String getId;
    static String email;

    static String noShops;
    static ArrayList arrayList_product = new ArrayList();
    static  ArrayList arrayList_price = new ArrayList();
    static ArrayList arrayList_date = new ArrayList();


    ///////////////////////////////////send_user_signup_data_to_database//////////////////////////////////////////
    public static void user_signup(String name, String email, String address,String phoneno, String dob, String password) {
        System.out.println("Hello");
        HttpClient httpClient = new DefaultHttpClient();
        StrictMode.setThreadPolicy(th);

        try {
            name = URLEncoder.encode(name, "utf-8");
            password = URLEncoder.encode(password, "utf-8");
            address = URLEncoder.encode(address, "utf-8");
            dob = URLEncoder.encode(dob, "utf-8");

            String link = URL_LINK + "signup_user.php?name="+name+"&email=" + email + "&address=" + address + "&phone="+ phoneno+"&dob=" + dob + "&password=" + password;
            HttpGet httpGet = new HttpGet(link);
            httpClient.execute(httpGet);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
    }

    ///////////////////////////////////check_user_login_data//////////////////////////////////////////
    public static String check_login(String email, String password) {

        HttpClient httpClient = new DefaultHttpClient();
        StrictMode.setThreadPolicy(th);

        String status = "";

        try {
            email = URLEncoder.encode(email, "utf-8");
            password = URLEncoder.encode(password, "utf-8");

            String link = URL_LINK + "check_login.php?email=" + email + "&password=" + password;


            HttpGet httpGet = new HttpGet(link);
            HttpResponse httpResponse = httpClient.execute(httpGet);

            HttpEntity httpEntity = httpResponse.getEntity();

            InputStream inputStream = httpEntity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder builder = new StringBuilder();

            String line = null;


            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n");
            }
            String f = builder.toString();

            JSONObject jsonObject = new JSONObject(f);
            JSONArray jsonArray = jsonObject.optJSONArray("check");



            JSONObject jsonObject2 = jsonArray.getJSONObject(0);
            JSONObject jsonObject3 = jsonArray.getJSONObject(1);
            JSONObject jsonObject4 = jsonArray.getJSONObject(2);


            status = jsonObject2.optString("status").toString();
            getname = jsonObject3.optString("name").toString();
            getId = jsonObject4.optString("user_id").toString();


            System.out.println("status = " + status);
            System.out.println("Name = " + getname);
            System.out.println("Id = " + getId);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return status;
    }


    ///////////////////////////////////Add_to_cart_productInfo//////////////////////////////////////////
    public static void add_cart(String productname,String price) {
        //System.out.println("Hello");
        HttpClient httpClient = new DefaultHttpClient();
        StrictMode.setThreadPolicy(th);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());


        try {
            productname = URLEncoder.encode(productname, "utf-8");
            price = URLEncoder.encode(price, "utf-8");
            currentDate = URLEncoder.encode(currentDate, "utf-8");

            String link = URL_LINK + "add_cart.php?email="+email+"&pname="+productname+"&price="+price+"&pdate="+currentDate;
            HttpGet httpGet = new HttpGet(link);
            httpClient.execute(httpGet);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
    }

    public static String get_checkout_info(String email) {
        HttpClient httpClient = new DefaultHttpClient();
        StrictMode.setThreadPolicy(th);

        arrayList_product = new ArrayList();
        arrayList_price = new ArrayList();
        arrayList_date = new ArrayList();


        String status = "";


        try {
            HttpGet get2 = new HttpGet(URL_LINK + "checkout_detail.php?email=" + email);

            HttpResponse httpResponse = httpClient.execute(get2);
            HttpEntity httpEntity = httpResponse.getEntity();

            InputStream inputStream = httpEntity.getContent();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
            StringBuilder builder = new StringBuilder();

            String line = null;


            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n");
            }
            String f = builder.toString();

            int counter = 0;
            JSONObject jsonObject = new JSONObject(f);

            JSONArray jsonArray = jsonObject.optJSONArray("product");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                if (counter == 0) {
                    status = jsonObject2.optString("status").toString();
                    counter++;
                } else if (counter == 1) {
                    arrayList_product.add(jsonObject2.optString("pname").toString());
                    counter++;
                } else if (counter == 2) {
                    arrayList_price.add(jsonObject2.optString("pprice").toString());
                    counter++;
                } else if (counter == 3) {
                    arrayList_date.add(jsonObject2.optString("pdate").toString());
                    counter++;
                } else if (counter == 4) {
                    noShops = jsonObject2.optString("noshops").toString();
                    counter = 0;
                }

            }

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Exception here");
        }

        return status;


    }
    /////////Add Payment Information////////
    public static void go_payment(String email,String total_amount,String pdate, String card_num, String exp_date) {

        HttpClient httpClient = new DefaultHttpClient();
        StrictMode.setThreadPolicy(th);
        try {
            email = URLEncoder.encode(email, "utf-8");
            total_amount = URLEncoder.encode(total_amount, "utf-8");
            pdate = URLEncoder.encode(pdate, "utf-8");
            exp_date = URLEncoder.encode(exp_date, "utf-8");

            String link = URL_LINK + "go_payment.php?email=" + email + "&amount=" + total_amount + "&date_payment="+ pdate+"&credit_number=" + card_num+"&expiry_date=" + exp_date ;
            HttpGet httpGet = new HttpGet(link);
            httpClient.execute(httpGet);
        } catch (Exception e) {
            System.out.println("Error = " + e);
        }
    }




}




