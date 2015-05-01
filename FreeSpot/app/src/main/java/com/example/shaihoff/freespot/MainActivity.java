package com.example.shaihoff.freespot;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.content.Intent;
import android.widget.Button;
import java.sql.Timestamp;
import java.util.Date;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.beardedhen.androidbootstrap.BootstrapButton;

import android.widget.RelativeLayout;
import android.widget.RemoteViews;


import org.json.JSONObject;

import static com.example.shaihoff.freespot.R.id.button;
import static com.example.shaihoff.freespot.R.layout.activity_main;

public class MainActivity extends Activity {
    @Override
//    static String P1;
//    static String P2;
//    static String P3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        //double [] darry = new double [3];
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="http://stone.md.huji.ac.il/huji/mobile/occupancy.php";

        Runnable runny = new Runnable() {
            @Override
            public void run() {

// Request a string response from the provided URL.
                java.util.Date date= new java.util.Date();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, (url + "?d=" + new Timestamp(date.getTime())),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                try
                                {
                                    JSONObject json = new JSONObject(response);
                                    JSONObject lab1= json.getJSONObject("Lab_1");
                                    JSONObject lab2= json.getJSONObject("Lab_2");
                                    JSONObject lab3= json.getJSONObject("Lab_3");
                                    // System.out.  (lab1 );


                                    double copasety1=Math.round((Integer.parseInt(lab1.getString("Used"))) / (1.0 * Integer.parseInt(lab1.getString("Capacity"))) * 10000)/100;
                                    double copasety2= Math.round((Integer.parseInt(lab2.getString("Used")) /(1.0* Integer.parseInt(lab2.getString("Capacity")))*10000) )/100;
                                    double copasety3= Math.round((Integer.parseInt(lab3.getString("Used")) / 1.0*Integer.parseInt(lab3.getString("Capacity"))) *10000 )/100;;



                                    System.out.println(Double.toString(copasety1));
                                    System.out.println(Double.toString(copasety2));
                                    System.out.println(Double.toString(copasety3));




                                    BootstrapButton p1_button = (BootstrapButton) findViewById(button);
                                         String p1= "New Aquarium A   "+Double.toString(copasety1)+"%";
                                   // String p1= "New Aquarium A   73.0%";
                                    p1_button.setText(p1);
                                    //myTextView.setText(p1);


                                    BootstrapButton p2_button = (BootstrapButton) findViewById(R.id.button2);
                                    String p2= "New Aquarium C   "+Double.toString(copasety2)+"%";
//                                    String p2= "New Aquarium C   28.2%";
                                    p2_button.setText(p2);

                                    BootstrapButton p3_button = (BootstrapButton) findViewById(R.id.button3);
                                         String p3= "Old  Aquarium       "+Double.toString(copasety3)+"%";
//                                    String p3= "Old  Aquarium       92.4%";
                                    p3_button.setText(p3);


                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                Log.d("", "");
                                Log.d("", "");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("", "error");
                    }
                });
// Add the request to the RequestQueue.
                queue.add(stringRequest);
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.root_view);
                rl.postDelayed( this, 5000);
            }
        };

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.root_view);
        rl.post( runny);
    }




//    public static String get_text_1()
//    {
//        return P1;
//
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onClick1(View v){
        Intent i = new Intent(this, screen_1.class);
        i.putExtra("Value1", "This value one for ActivityTwo ");
        i.putExtra("Value2", "This value two ActivityTwo");
        startActivity(i);
    }
    public void onClick2(View v){
        Intent i = new Intent(this, screen_2.class);
        i.putExtra("Value1", "This value one for ActivityTwo ");
        i.putExtra("Value2", "This value two ActivityTwo");
        startActivity(i);
    }

}