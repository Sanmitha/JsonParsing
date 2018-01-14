package com.example.hp.jsonobjectapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hp on 09-12-2017.
 */

public class HttpConnection {
    public static String getData(String uri)
    {
        BufferedReader reader=null;
        HttpURLConnection connection = null;
        try
        {
            //Step1: creat the URL
            URL url=new URL((uri));
            //Step2 Get the HTTPurl Connection
             connection=(HttpURLConnection)url.openConnection();
            //Step3 set request parameters
            connection.setReadTimeout(1000);

            connection.setDoInput(true);
            connection.setConnectTimeout(15000);

            //step4 get the inputstream object
            InputStream is=connection.getInputStream();

            //read the data
            reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String data=null;
            String web="";
            while((data=reader.readLine())!=null) {
                web += data + "\n";

            }
            return web;//read the json response
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return  null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            //Step5 close the connection object

            if(reader!=null)
            {
                try
                {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return  null;
                }
            }
            connection.disconnect();

        }


    }



}
