package com.app.mealan;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.registerr).setOnClickListener(view->register());
        findViewById(R.id.loggon).setOnClickListener(view->loggonuser());
    }

    public void register()
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    public void loggonuser()
    {

        EditText email = findViewById(R.id.email);
        EditText pass = findViewById(R.id.pass);

        db.collection("user")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    int flagg = 0;
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                        {
                            if (Objects.equals(document.getString("Email"), email.getText().toString()))
                            {
                                if (Objects.equals(document.getString("Password"), pass.getText().toString()))
                                {
                                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                                    flagg = 1;
                                    if (Objects.equals(document.getString("NGO"), "NIL"))
                                    {
                                        donor();
                                    }
                                    else
                                    {
                                        receiver();
                                    }
                                    break;
                                }
                            }

                        }
                        if (flagg == 0)
                        {
                            Toast.makeText(getApplicationContext(),"Incorrect Email ID or Password",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Technical Error, Try again Later",Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void donor()
    {
        Intent intent = new Intent(this, DonorActivity.class);
        startActivity(intent);
    }

    public void receiver()
    {
        Intent intent = new Intent(this, reciever.class);
        startActivity(intent);
    }
}

//sadasdasssssffdsf