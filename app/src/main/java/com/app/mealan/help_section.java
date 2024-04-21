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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class help_section extends AppCompatActivity {

    FirebaseFirestore firestore;
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_help_section);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        findViewById(R.id.send).setOnClickListener(view->send());
        ID = getIntent().getStringExtra("id");
        firestore = FirebaseFirestore.getInstance();
    }

    public void send() {

        EditText subject = findViewById(R.id.subject);
        EditText query = findViewById(R.id.query);
        Map<String,Object> ques = new HashMap<>();


        if (subject.getText().toString().isEmpty() || query.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Incomplete Details",Toast.LENGTH_LONG).show();
        }

        else
        {
            firestore.collection("user").get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                            {
                                if (document.getId().equals(ID))
                                {
                                    String email = document.getString("Email");
                                    ques.put("Email",email);
                                    ques.put("Subject",subject.getText().toString());
                                    ques.put("Query",query.getText().toString());

                                    firestore.collection("queries").add(ques)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(getApplicationContext(), "Query Submitted",Toast.LENGTH_LONG).show();
                                                    subject.setText("");
                                                    query.setText("");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getApplicationContext(), "Technical Issue",Toast.LENGTH_LONG).show();
                                                }
                                            });
                                    break;
                                }
                            }
                        }
                    });
        }



    }


}