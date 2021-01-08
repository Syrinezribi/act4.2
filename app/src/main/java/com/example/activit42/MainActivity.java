package com.example.activit42;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText editmail,editpass,editconfirm;
    Button boutonRegist,boutonlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        editmail = (EditText) findViewById(R.id.idMail);
        editpass = findViewById(R.id.idPass);
        editconfirm = (EditText) findViewById(R.id.idCpass);
        boutonRegist = (Button) findViewById(R.id.buttonReg);
        boutonlogin = (Button) findViewById(R.id.buttonLog);
        boutonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginAct.class);
                startActivity(intent);
            }
        });
        boutonRegist.setOnClickListener(v -> {
            String editmaile = editmail.getText().toString();
            String editpasse = editpass.getText().toString();
            String editconfirme = editconfirm.getText().toString();
            if (editmaile.equals("") || editpasse.equals("") || editconfirme.equals("")) {
                Toast.makeText(getApplicationContext(), "les champs sont vides", Toast.LENGTH_LONG).show();
            } else {
                if (editpasse.equals(editconfirme)) {
                    Boolean checkmail = db.checkmail(editmaile);
                    if (checkmail == true) {
                        Boolean insert = db.insert(editmaile, editpasse);
                        if (insert == true) {
                            Toast.makeText(getApplicationContext(), "Inscription réussite!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Email existe!", Toast.LENGTH_LONG).show();
                        }
                    }

                }
                Toast.makeText(getApplicationContext(), "Mot passe incorrecte!", Toast.LENGTH_LONG).show();
            }

        });

    }
}