package com.app.mealan;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class reciever extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore database;
    MyAdaptor myAdaptor;
    ArrayList<User> list;

    String ID;

    String field;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reciever);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.listings);
        database = FirebaseFirestore.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ID = getIntent().getStringExtra("id");
        findViewById(R.id.homebutton).setOnClickListener(view->homepage());
        findViewById(R.id.postbutton).setOnClickListener(view->postpage());
        findViewById(R.id.profilebutton).setOnClickListener(view->profilepage());

        list = new ArrayList<>();
        database.collection("listing").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots)
                        {
                            String num = document.getString("people");
                            String add = document.getString("address");
                            String foo = document.getString("food");
                            User temp = new User(num,add,foo, document.getId());
                            list.add(temp);
                        }
                        myAdaptor.notifyDataSetChanged();
                    }
                });
        myAdaptor = new MyAdaptor(this,list);
        recyclerView.setAdapter(myAdaptor);

    }

    public void homepage()
    {
        Intent intent = new Intent(this, Receiver_home.class);
        startActivity(intent.putExtra("id",ID));
    }

    public void postpage()
    {
        Intent intent = new Intent(this, reciever.class);
        startActivity(intent.putExtra("id",ID));
    }

    public void profilepage()
    {
        Intent intent = new Intent(this, UserProfilePage.class);
        intent.putExtra("id",ID);
        startActivity(intent.putExtra("id",ID));
    }
}