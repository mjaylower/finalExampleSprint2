package com.example.mlower.finalexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainClassList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main_class_list);

        final Switch swDegreeCert = findViewById (R.id.switchDegreeCert);
        final Spinner spDegree = findViewById (R.id.spinnerDegree);
        final Spinner spCert = findViewById (R.id.spinnerCert);
        final Button btnNext = findViewById (R.id.btnNext);
        final TextView txtCert = findViewById (R.id.textViewCert);
        final TextView txtDegree = findViewById (R.id.textViewMajor);

        final EditText firstName = findViewById (R.id.firstName);
        final EditText lastName = findViewById (R.id.lastName);
        final EditText phone = findViewById (R.id.phone);

        final Spinner spMonth = findViewById (R.id.spinnerMonth);
        final EditText txtDay = findViewById (R.id.days);
        final EditText txtYr = findViewById (R.id.year);

        firstName.requestFocus();

        swDegreeCert.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spDegree.setVisibility (View.VISIBLE);
                    txtDegree.setVisibility (View.VISIBLE);
                    spCert.setVisibility (View.GONE);
                    txtCert.setVisibility (View.GONE);
                } else {
                    spDegree.setVisibility (View.GONE);
                    txtDegree.setVisibility (View.GONE);
                    spCert.setVisibility (View.VISIBLE);
                    txtCert.setVisibility (View.VISIBLE);
                }
            }
        });

        btnNext.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (checkData ()){
                    String dob = "";
                    dob = spMonth.getSelectedItem ().toString() + "/" + txtDay.getText().toString () + "/" + txtYr.getText().toString ();

                    // old way
                    // startActivity(new Intent(MainClassList.this, ChooseClass.class));

                    // new way
                    Intent nextScreen = new Intent (MainClassList.this, ChooseClass.class);
                    nextScreen.putExtra ("FirstName", firstName.getText().toString ());
                    nextScreen.putExtra ("LastName", lastName.getText ().toString ());
                    nextScreen.putExtra ("Phone", phone.getText ().toString ());
                    nextScreen.putExtra ("BirthDate", dob);

                    if (spDegree.getVisibility () == View.VISIBLE){
                        nextScreen.putExtra ("isDegreeCert", "Degree");
                        nextScreen.putExtra ("degreeCert", spDegree.getSelectedItem().toString ());
                    }
                    else {
                        nextScreen.putExtra ("isDegreeCert", "Certificate");
                        nextScreen.putExtra ("degreeCert", spCert.getSelectedItem().toString ());
                    }

                    //Start Activity
                    startActivity (nextScreen);
                }

            }
        });

    }

    private boolean checkData(){
        final EditText firstName = findViewById (R.id.firstName);
        final EditText lastName = findViewById (R.id.lastName);
        final EditText phone = findViewById (R.id.phone);
        final EditText txtDay = findViewById (R.id.days);
        final EditText txtYr = findViewById (R.id.year);

        if(firstName.getText().toString ().isEmpty ()){
            //error
            firstName.setError ("Invalid First Name");
            firstName.requestFocus ();
            return false;
        }

        if(lastName.getText().toString ().isEmpty ()){
            lastName.setError ("Invalid Last Name");
            lastName.requestFocus ();
            return false;
        }

        if(phone.getText().toString ().isEmpty ()) {
            phone.setError ("Invalid Phone Number");
            phone.requestFocus ();
            return false;
        }

        if(txtDay.getText().toString ().isEmpty ()){
            txtDay.setError ("Invalid Day Selection");
            txtDay.requestFocus ();
            return false;
        }

        if(txtYr.getText().toString ().isEmpty ()) {
            txtYr.setError ("Invalid Year Selection");
            txtYr.requestFocus ();
            return false;
        }

        return true;
    }
}
