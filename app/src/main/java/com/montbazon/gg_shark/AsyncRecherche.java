package com.montbazon.gg_shark;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

//import org.json.simple.parser.JSONParser;


public class AsyncRecherche extends AsyncTask<String,Integer, String[]>
{

    Context ctx;


    public AsyncRecherche(Context ctx)
    {
        super();
        this.ctx = ctx;
    }

    @Override
    protected String[] doInBackground(String... params)
    {

        //ArrayList<JSONArray> jarray = new ArrayList();

        String[] resultat = new String[3];

        //Log.i("JFL","https://www.cheapshark.com/api/1.0/games?title="+title+"&limit=60");



        URL url =null;
        URL url2=null;
        URL url3=null;
        try{
            url =new URL("https://www.cheapshark.com/api/1.0/deals?title="+params[0]+"&limit=60&storeID=1");
            url2 =new URL("https://www.cheapshark.com/api/1.0/deals?title="+params[0]+"&limit=60&storeID=7");
            url3 =new URL("https://www.cheapshark.com/api/1.0/deals?title="+params[0]+"&limit=60&storeID=25");
            //Log.i("Pierre","https://www.cheapshark.com/api/1.0/deals?title="+params[0]+"&limit=60&storeID="+params[1]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
            HttpURLConnection urlConnection3 = (HttpURLConnection) url3.openConnection();

            try
            {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                InputStream in2 = new BufferedInputStream(urlConnection2.getInputStream());
                InputStream in3 = new BufferedInputStream(urlConnection3.getInputStream());


                 resultat[0]= readStream(in);
                 resultat [1]= readStream(in2);
                resultat[2] = readStream(in3);




                Log.i("Pierre",resultat[0]+"ibib");
                Log.i("Pierre",resultat[1]+"ubo");
                Log.i("Pierre","oih"+resultat[2]);

                /*JSONArray ja = new JSONArray(s);
                JSONArray ja2 = new JSONArray(s2);
                JSONArray ja3 = new JSONArray(s3);*/


                /*jarray.add(ja);
                jarray.add(ja2);
                jarray.add(ja3);
                //Log.i("JFL", jarray.toString());
                //return ja;*/

                return resultat;

            } catch (Exception e) {
                e.printStackTrace();
            } finally{urlConnection.disconnect();
            }}
        catch(MalformedURLException e) {e.printStackTrace();
        }
        catch(IOException e) {e.printStackTrace();
        }


        return resultat;


    }

    @Override
    protected void onPostExecute(String[] resultat)
    {
        Intent deal = new Intent( ctx, DealListActivity.class);
        deal.putExtra("r1",resultat[0]);
        deal.putExtra("r2",resultat[1]);
        deal.putExtra("r3",resultat[2]);

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
