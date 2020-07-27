package com.alaminkarno.volleyapidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        URL = "https://jsonplaceholder.typicode.com/todos/";

        //JSONStringRequest();

        //JSONObjectRequest();

        JSONArrayRequest();
    }

    private void JSONArrayRequest() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Log.d("karno","User ID: "+jsonObject.getInt("userId")
                                +"\nID: "+jsonObject.getInt("id")
                                +"\nThe response is "+jsonObject.getString("title")
                                +"\nCompleted: "+jsonObject.getBoolean("completed"));

                        Toast.makeText(MainActivity.this, "User ID: "+jsonObject.getInt("userId")
                                +"\nID: "+jsonObject.getInt("id")
                                +"\nThe response is "+jsonObject.getString("title")
                                +"\nCompleted: "+jsonObject.getBoolean("completed"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void JSONStringRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Response is: "+response.substring(0,500), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void JSONObjectRequest() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(MainActivity.this,
                            "User ID: "+response.getInt("userId")
                                    +"\nID: "+response.getInt("id")
                                    +"\nThe response is "+response.getString("title")
                                    +"\nCompleted: "+response.getBoolean("completed"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}