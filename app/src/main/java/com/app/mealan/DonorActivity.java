package com.app.mealan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DonorActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donorr);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        findViewById(R.id.homebutton).setOnClickListener(view->homepage());
        findViewById(R.id.postbutton).setOnClickListener(view->postpage());
        findViewById(R.id.profilebutton).setOnClickListener(view->profilepage());
        findViewById(R.id.postlisting).setOnClickListener(view->postlist());

        firestore = FirebaseFirestore.getInstance();
        ID = getIntent().getStringExtra("id");
    }




    public  void  postlist()
    {
        EditText food = (EditText) findViewById(R.id.fooddetails);
        EditText address = (EditText) findViewById(R.id.address);
        EditText people = (EditText) findViewById(R.id.people);

        Map<String,Object> listing = new HashMap<>();
        listing.put("food",food.getText().toString());
        listing.put("address",address.getText().toString());
        listing.put("people",people.getText().toString());
        listing.put("user",ID);

        firestore.collection("listing").add(listing).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Post Created",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to Post",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void homepage()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void postpage()
    {
        Intent intent = new Intent(this, DonorActivity.class);
        startActivity(intent);
    }

    public void profilepage()
    {
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("id",ID);
        startActivity(intent);
    }

}