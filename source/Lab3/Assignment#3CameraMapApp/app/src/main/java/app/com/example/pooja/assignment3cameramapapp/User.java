package app.com.example.pooja.assignment3cameramapapp;

/**
 * Created by pooja on 2/10/16.
 */
public class User {
    String name;
    //String userName;
    String emailId;
    String password;

    public User(String name,String emailId,String password)
    {
        this.name=name;
        this.emailId=emailId;
        this.password=password;
    }

    public User(String emailId,String password)
    {
        this.emailId=emailId;
        this.password=password;
    }
}
