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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        EditText name = findViewById(R.id.name);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone);
        EditText pass = findViewById(R.id.pass);
        EditText conpass = findViewById(R.id.conpass);
        EditText ngo = findViewById(R.id.ngobox);

        CheckBox cb = findViewById(R.id.checkBox);

        if (!email.getText().toString().contains("@gmail.com"))
        {
            Toast.makeText(getApplicationContext(),"Email is Invalid",Toast.LENGTH_LONG).show();
        }
        else if (!Objects.equals((pass.getText().toString()), conpass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),"Passwords doesn't Match",Toast.LENGTH_LONG).show();
        }
        else if (name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || pass.getText().toString().isEmpty() || conpass.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Fields cannot be Empty",Toast.LENGTH_LONG).show();
        }
        else if (cb.isChecked() && ngo.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"NGO ID or Welfare Society name is Mandatory",Toast.LENGTH_LONG).show();
        }
        else
        {
            firestore.collection("user")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            int flagg;
                            flagg = 0;
                            for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                            {

                                if (Objects.equals(document.getString("Email"), email.getText().toString()))
                                {
                                    Toast.makeText(getApplicationContext(),"Email already Registered",Toast.LENGTH_LONG).show();
                                    flagg = 1;
                                    break;
                                }
                                if (Objects.equals(document.getString("Phone"), phone.getText().toString()))
                                {
                                    Toast.makeText(getApplicationContext(),"Phone Number already Registered",Toast.LENGTH_LONG).show();
                                    flagg = 1;
                                    break;
                                }
                            }
                            if (flagg == 0)
                            {
                                Map<String,Object> user = new HashMap<>();
                                user.put("Name",name.getText().toString());
                                user.put("Email",email.getText().toString());
                                user.put("Phone",phone.getText().toString());
                                user.put("Password",pass.getText().toString());
                                user.put("NGO",ngo.getText().toString());

                                firestore.collection("user").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(getApplicationContext(), "Registered",Toast.LENGTH_LONG).show();
                                        login();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Failed to Register",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Technical Issue, Try again Later",Toast.LENGTH_LONG).show();
                        }
                    });
        }



    }


}
//jii