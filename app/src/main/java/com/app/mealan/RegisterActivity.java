package com.app.mealan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.loginn).setOnClickListener(view->login());
        findViewById(R.id.submit).setOnClickListener(view->submit());
        findViewById(R.id.checkBox).setOnClickListener(view->box());

        firestore = FirebaseFirestore.getInstance();



    }

    public void login()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void box()
    {
        CheckBox cb = findViewById(R.id.checkBox);
        if (cb.isChecked())
        {
            findViewById(R.id.ngobox).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.ngobox).setVisibility(View.GONE);
        }
    }

    public void submit()
    {
        Map<String,Object> user = new HashMap<>();
        EditText name = findViewById(R.id.name);
        user.put("Name",name.getText().toString());
        EditText email = findViewById(R.id.email);
        user.put("Email ID",email.getText().toString());
        EditText phone = findViewById(R.id.phone);
        user.put("Phone No.",phone.getText().toString());
        EditText pass = findViewById(R.id.pass);
        user.put("Password",pass.getText().toString());
        EditText ngo = findViewById(R.id.ngobox);
        user.put("NGO/Welfare Society Name",ngo.getText().toString());
        firestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Registered",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Register",Toast.LENGTH_LONG).show();
            }
        });

    }


}

//done