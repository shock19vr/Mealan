package com.app.mealan;

import android.os.Bundle;
import android.widget.TextView;

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

public class Profile extends AppCompatActivity {

    String ID;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = FirebaseFirestore.getInstance();
        ID = getIntent().getStringExtra("id");
        showlist();


    }

    private void showlist() {

        TextView numberpeople = findViewById(R.id.numberpeople);
        TextView address = findViewById(R.id.address);
        TextView food = findViewById(R.id.food);
        TextView name = findViewById(R.id.name);
        TextView number = findViewById(R.id.number);
        TextView email = findViewById(R.id.email);

        database.collection("listing").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                        {
                            if (document.getId().equals(ID))
                            {
                                address.setText(document.getString("address"));
                                numberpeople.setText(document.getString("people"));
                                food.setText(document.getString("food"));
                                String userid = document.getString("user");
                                database.collection("user").get()
                                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                for (QueryDocumentSnapshot document1 : queryDocumentSnapshots)
                                                {
                                                    if (document1.getId().equals(userid))
                                                    {
                                                        name.setText(document1.getString("Name"));
                                                        number.setText(document1.getString("Phone"));
                                                        email.setText(document1.getString("Email"));
                                                        break;
                                                    }
                                                }
                                            }
                                        });
                                break;
                            }
                        }
                    }
                });

    }


}