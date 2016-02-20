package app.com.example.pooja.assignment3cameramapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText fullName, emailId, Password;
    Button btnUploadPhoto, btnRegister;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fullName = (EditText) findViewById(R.id.etUsername);
        emailId = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        btnUploadPhoto = (Button) findViewById(R.id.btUploadPhoto);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnUploadPhoto.setOnClickListener(this);
        btnRegister.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btUploadPhoto:
                break;
            case R.id.btnRegister:
                String name=fullName.getText().toString();
                String emailid=emailId.getText().toString();
                String pwd=Password.getText().toString();

                User registeredUser=new User(name,emailid,pwd);
                registerUser(registeredUser);
                break;
        }
    }
    private void registerUser(User registeredUser )
    {
        ServiceRequest serviceRequest=new ServiceRequest();//
        serviceRequest.storeUserDataInBackground(registeredUser, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this,Login.class));

            }
        });
    }
}
