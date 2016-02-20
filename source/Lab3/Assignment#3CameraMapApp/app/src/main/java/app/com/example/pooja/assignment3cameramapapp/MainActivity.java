package app.com.example.pooja.assignment3cameramapapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLocateMe;
    Button btnLogOut;
    UserLocalStore userLocalStore;
    EditText etusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLocateMe=(Button)findViewById(R.id.btnLocateMe);
        btnLogOut=(Button)findViewById(R.id.btnLogOut);
        btnLocateMe.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        etusername=(EditText)findViewById(R.id.mainUsername);
        userLocalStore=new UserLocalStore(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public void onStart()
    {
        super.onStart();
        if(authentication()==true)
        {
            displayUserName();
        }
        else
        {
            startActivity(new Intent(MainActivity.this,Login.class));
        }

    }
    private void displayUserName()
    {
        User user=userLocalStore.getLoggedInUser();
        etusername.setText(userLocalStore.getLoggedInUser().name);
    }
    private boolean authentication()
    {
        return userLocalStore.getLoggedInAuthenticationStatus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLocateMe:
                break;
            case R.id.btnLogOut:
                userLocalStore.clearAllData();
                userLocalStore.setLoggedInuser(false);
                break;
        }
    }
}
