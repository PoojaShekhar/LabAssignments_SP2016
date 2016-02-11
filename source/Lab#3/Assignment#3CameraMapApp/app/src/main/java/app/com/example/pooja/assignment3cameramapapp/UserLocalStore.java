package app.com.example.pooja.assignment3cameramapapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pooja on 2/10/16.
 */
public class UserLocalStore {

    public  static final String SP_NAME="userdetails";
    SharedPreferences userlocalPreferences;

    public UserLocalStore(Context context)
    {
        userlocalPreferences=context.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData(User user)
    {
        SharedPreferences.Editor spEditor=userlocalPreferences.edit();
        spEditor.putString("name",user.name);
        spEditor.putString("emailid",user.emailId);
        spEditor.putString("password",user.password);

    }

    public User getLoggedInUser()
    {
        String name=userlocalPreferences.getString("name", "");
        String emailId=userlocalPreferences.getString("emailid","");
        String passwd=userlocalPreferences.getString("password","");

        User storedUser=new User(name,emailId,passwd);
        return storedUser;

    }
    public void setLoggedInuser(boolean loggedIn)
    {
        SharedPreferences.Editor spEditor=userlocalPreferences.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();

    }
    public boolean getLoggedInAuthenticationStatus()
    {
        if(userlocalPreferences.getBoolean("loggedIn",false)==true)
        {
            return true;

        }
        else return false;
    }

    public void clearAllData()
    {
        SharedPreferences.Editor spEditor=userlocalPreferences.edit();
        spEditor.clear();
        spEditor.commit();
    }


}


