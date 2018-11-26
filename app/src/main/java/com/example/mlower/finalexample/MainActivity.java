package com.example.mlower.finalexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public enum LoginSuccess {
        login(1),
        password(2),
        success(0);

        private int intValue;
        LoginSuccess(int value) {
            intValue = value;
        }
        public int getIntValue(){
            return intValue;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        final TextView txtLogin = (TextView) findViewById (R.id.loginText);
        final TextView txtPassword = (TextView) findViewById (R.id.passwordText);
        Button btnLogin = (Button) findViewById (R.id.btnLogin);

        btnLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast toastError = null;

                switch(CheckLogin (txtLogin.getText().toString (), txtPassword.getText ().toString ())){
                    case login:
                        toastError.makeText (getApplicationContext (),getString (R.string.errMessageLogin), toastError.LENGTH_LONG).show ();
                        txtLogin.requestFocus ();
                    break;
                    case password:
                        toastError.makeText (getApplicationContext (),getString (R.string.errMessagePassword), toastError.LENGTH_LONG).show ();
                        txtPassword.requestFocus ();
                        break;
                    default:
                        toastError.makeText(getApplicationContext (), "Success", toastError.LENGTH_LONG).show ();
                }
            }
        });
    }
    LoginSuccess CheckLogin(String txtLogin, String txtPassword){
        String holdLogin = "Michael";
        String holdPassword = "Lower";

        if (!(holdLogin.equals(txtLogin))){
            return LoginSuccess.login;
        }
        if (!(holdPassword.equals(txtPassword))){
            return LoginSuccess.password;
        }
        return LoginSuccess.success;
    }
}
