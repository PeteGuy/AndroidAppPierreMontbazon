package com.montbazon.gg_shark;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import org.json.JSONObject;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//import org.json.simple.parser.JSONParser;


public class AsyncRecherche extends AsyncTask<String,Integer,JSONArray>
{

    Context ctx;

    public AsyncRecherche(Context ctx)
    {
        super();
        this.ctx = ctx;
    }

    @Override
    protected JSONArray doInBackground(String... params)
    {

        String title = params[0];
        //Log.i("JFL","https://www.cheapshark.com/api/1.0/games?title="+title+"&limit=60");

        URL url =null;
        try{
            url =new URL("https://www.cheapshark.com/api/1.0/games?title="+title+"&limit=60");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                final String s = readStream(in);
                JSONArray ja = new JSONArray(s);
                //Log.i("JFL", (String) ja.getJSONObject(1).get("gameID"));
                return ja;

            } catch (JSONException e) {
                e.printStackTrace();
            } finally{urlConnection.disconnect();
            }}
        catch(MalformedURLException e) {e.printStackTrace();
        }
        catch(IOException e) {e.printStackTrace();
        }

        /*
        OkHttpClient okHttpClient = new OkHttpClient();


        Request myGetRequest = new Request.Builder()
                .url("https://www.cheapshark.com/api/1.0/games?title="+title+"&limit=60")
                .build();

        okHttpClient.newCall(myGetRequest).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String text = response.body().string();
                final int statusCode = response.code();
                Log.i("JFL",text);

                try {
                    JSONArray jArray = new JSONArray(text);
                    Log.i("OUMF", jArray.get(0).toString());
                    return jArray;
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }


    });*/


        return null;
    }

    @Override
    protected void onPostExecute(JSONArray ja)
    {
        Intent deal = new Intent( ctx,DealList.class);
        deal.putExtra("jsonarray",ja.toString());
        deal.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(deal);



    }

    private String readStream(InputStream is)
    {

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return (String)bo.toString();
        } catch (IOException e) {
            return (String)"";
        }
    }





}
