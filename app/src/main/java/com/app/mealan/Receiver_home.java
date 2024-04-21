package com.app.mealan;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Receiver_home extends AppCompatActivity {

    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiver_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        findViewById(R.id.profile).setOnClickListener(view->profile());
        findViewById(R.id.listing).setOnClickListener(view->lists());
        findViewById(R.id.help).setOnClickListener(view->help());
        ID = getIntent().getStringExtra("id");
    }

    private void help() {
        Intent intent = new Intent(this, help_section.class);
        startActivity(intent.putExtra("id",ID));
    }

    private void lists() {
        Intent intent = new Intent(this, reciever.class);
        startActivity(intent.putExtra("id",ID));
    }

    private void profile() {
        Intent intent = new Intent(this, UserProfilePage.class);
        startActivity(intent.putExtra("id",ID));
    }
}