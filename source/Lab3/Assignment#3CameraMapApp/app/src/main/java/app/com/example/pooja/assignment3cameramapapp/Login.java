package app.com.example.pooja.assignment3cameramapapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    UserLocalStore userLocalStore;
    Button btnLogin;
    EditText eEmailid,ePassword;
    TextView eRegisterLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userLocalStore=new UserLocalStore(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        eEmailid=(EditText)findViewById(R.id.etEmail);
        ePassword=(EditText)findViewById(R.id.etPassword);
        eRegisterLink=(TextView)findViewById(R.id.eRegisterMeLink);
        btnLogin=(Button)findViewById(R.id.btlogin);
        btnLogin.setOnClickListener(this);
        eRegisterLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btlogin:
                String emailid=eEmailid.getText().toString();
                String password=ePassword.getText().toString();
                User user=new User(emailid,password);
                authenticateUser(user);
                userLocalStore.storeUserData(user);
                userLocalStore.setLoggedInuser(true);
                break;
            case R.id.eRegisterMeLink:
                startActivity(new Intent(this,Register.class));
                break;

        }

    }
    private void authenticateUser(User user)
    {
       ServiceRequest serviceRequest=new ServiceRequest();//
        serviceRequest.fetchUserDataFromBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser==null)
                {
                    showErrorMessage();
                }
                else
                {
                    logUserIn(returnedUser);
                }
            }
        });


    }
    private void showErrorMessage()
    {
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect User Details");
        dialogBuilder.setPositiveButton("OK",null);
        dialogBuilder.show();
    }
    private void logUserIn(User returnedUser)
    {
       userLocalStore.storeUserData(returnedUser);
        userLocalStore.setLoggedInuser(true);
        startActivity(new Intent(this,MainActivity.class));
    }
}
