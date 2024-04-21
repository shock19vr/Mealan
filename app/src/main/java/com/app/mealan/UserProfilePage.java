package com.app.mealan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
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

public class UserProfilePage extends AppCompatActivity {
    FirebaseFirestore db;

    public static final String shared_prefs = "NIL";

    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_profile_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ID = getIntent().getStringExtra("id");


        db = FirebaseFirestore.getInstance();
        profile();


        findViewById(R.id.logout).setOnClickListener(view->logout());
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences(shared_prefs,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name","NIL");
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void profile() {

        TextView name = findViewById(R.id.name);
        TextView ngo = findViewById(R.id.ngoid);
        TextView number = findViewById(R.id.num);
        TextView email = findViewById(R.id.email);


        db.collection("user").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                        {
                            if (document.getId().equals(ID))
                            {
                                name.setText(document.getString("Name"));
                                ngo.setText(document.getString("NGO"));
                                number.setText(document.getString("Phone"));
                                email.setText(document.getString("Email"));

                                break;
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Technical Error, Try again Later",Toast.LENGTH_LONG).show();
                    }
                });
    }
}