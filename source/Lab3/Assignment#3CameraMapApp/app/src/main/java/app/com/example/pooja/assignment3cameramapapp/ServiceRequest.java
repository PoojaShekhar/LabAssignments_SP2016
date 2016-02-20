package app.com.example.pooja.assignment3cameramapapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pooja on 2/10/16.
 */
public class ServiceRequest {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://pooja2308.netau.net/";

    public void ServiceRequest(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing..");
        progressDialog.setMessage("Please wait ...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback callback) {
        progressDialog.show();
        new storeUserDataAsyncTask(user,callback).execute();

    }

    public void fetchUserDataFromBackground(User user, GetUserCallback callback) {
        progressDialog.show();
        new fetchUserDataAsyncTask(user,callback).execute();
    }

    private String getEncodedData(Map<String,String> data) {
        StringBuilder sb = new StringBuilder();
        for(String key : data.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(data.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if(sb.length()>0)
                sb.append("&");

            sb.append(key + "=" + value);
        }
        return sb.toString();
    }

    public class storeUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallback;

        public storeUserDataAsyncTask(User user, GetUserCallback callback)
        {
            this.user=user;
            this.userCallback=callback;

        }

        @Override
        protected Void doInBackground(Void... params) {

            Map<String,String> dataToSend=new HashMap<>();
            dataToSend.put("emailid",user.emailId);
            dataToSend.put("password",user.password);
            dataToSend.put("name",user.name);
            String encodeStr=getEncodedData(dataToSend);
            BufferedReader reader=null;
            try{
                URL url=new URL(SERVER_ADDRESS+"Register.php");
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStreamWriter writer=new OutputStreamWriter(httpURLConnection.getOutputStream());
                writer.flush();
                StringBuilder sb=new StringBuilder();
                reader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                line = sb.toString();
                Log.i("rndom check",line);

            }catch (Exception e)
            {
                e.printStackTrace();
            }finally {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;

        }



        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class fetchUserDataAsyncTask extends AsyncTask<Void,Void, User> {
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback callback) {
            this.user = user;
            this.userCallback = callback;

        }

        @Override
        protected User doInBackground(Void... params) {

            Map<String,String> dataToSend=new HashMap<>();
            dataToSend.put("emailid",user.emailId);
            dataToSend.put("password",user.password);
            String encodeStr=getEncodedData(dataToSend);
            BufferedReader reader=null;
            User returnedUser=null;
            try{
                URL url=new URL(SERVER_ADDRESS+"FetchData.php");
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStreamWriter writer=new OutputStreamWriter(httpURLConnection.getOutputStream());
                writer.write(encodeStr);
                writer.flush();
                StringBuilder responseStrbuilder=new StringBuilder();
                reader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while((line = reader.readLine()) != null)
                {
                    responseStrbuilder.append(line + "\n");
                }
                JSONObject responseuser=new JSONObject(responseStrbuilder.toString());
                if(responseuser==null)
                {
                    returnedUser=null;
                }
                else
                {
                    String name=responseuser.getString("username");
                    String emailid=responseuser.getString("emailid");
                    String password=responseuser.getString("password");

                    returnedUser=new User(emailid,password,name);

                }

                Log.i("rndom check",line);

            }catch (Exception e)
            {
                e.printStackTrace();
            }finally {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return returnedUser;
        }



        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }

    }
}



