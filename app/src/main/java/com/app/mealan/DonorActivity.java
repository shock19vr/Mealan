package com.app.mealan;

import static com.google.android.gms.common.util.CollectionUtils.listOf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonorActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    String ID;

    String[] list = {"Biryani",
            "Pulao",
            "Khichdi",
            "Lemon Rice",
            "Tomato Rice",
            "Curd Rice",
            "Tamarind Rice",
            "Coconut Rice",
            "Jeera Rice",
            "Ghee Rice",
            "Pongal",
            "Bisi Bele Bath",
            "Chitranna",
            "Vangi Bath",
            "Ambil Biriyani",
            "Malabar Biriyani",
            "Zarda Pulao",
            "Tehri",
            "Tehchambal",
            "Bagara Rice",
            "Butter Chicken",
            "Chicken Tikka Masala",
            "Rogan Josh",
            "Kadhai Paneer",
            "Palak Paneer",
            "Chana Masala",
            "Dal Makhani",
            "Sambar",
            "Rasam",
            "Aloo Gobi",
            "Baingan Bharta",
            "Chettinad Chicken Curry",
            "Malvani Curry",
            "Korma",
            "Saag Curry",
            "Navratan Korma",
            "Fish Curry",
            "Prawn Curry",
            "Laal Maas",
            "Vindaloo",
            "Madras Curry",
            "Malabar Curry",
            "Dhansak",
            "Paneer Butter Masala",
            "Kadhi Pakora",
            "Toor Dal",
            "Masoor Dal",
            "Moong Dal",
            "Chana Dal",
            "Urad Dal",
            "Rajma",
            "Lobiya",
            "Sambar Dal",
            "Moth Dal",
            "Kulith Dal",
            "Chhola",
            "Val Dal",
            "Lobia",
            "Rawan Dal",
            "Maah Dal",
            "Masoor Sabut",
            "Tuvar Dal",
            "Kala Chana",
            "Kabuli Chana",
            "Sem",
            "Idli",
            "Dosa",
            "Uttapam",
            "Vada",
            "Upma",
            "Poha",
            "Paratha",
            "Chole Bhature",
            "Aloo Paratha",
            "Samosa",
            "Medu Vada",
            "Masala Dosa",
            "Puri Bhaji",
            "Kachori",
            "Jalebi",
            "Halwa",
            "Chilla",
            "Cheela",
            "Dhokla",
            "Sev Usal",
            "Misal Pav",
            "Sabudana Khichdi",
            "Poori Sabzi",
            "Shahi Tukda",
            "Bread Pakora",
            "Bun Maska",
            "Gulab Jamun",
            "Jalebi",
            "Rasmalai",
            "Rasgulla",
            "Ladoo",
            "Barfi",
            "Halwa",
            "Sandesh",
            "Kalakand",
            "Kheer",
            "Kulfi",
            "Rabdi",
            "Phirni",
            "Shrikhand",
            "Peda",
            "Mysore Pak",
            "Badam Halwa",
            "Jangri",
            "Gujiya",
            "Imarti",
            "Sohan Halwa",
            "Dharwad Pedha",
            "Basundi",
            "Malai Sandesh",
            "Chandrakala"
            };

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

        MultiAutoCompleteTextView foods = findViewById(R.id.fooddetails);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        foods.setAdapter(adapter);
        foods.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

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


        if (food.getText().toString().isEmpty() || address.getText().toString().isEmpty() || people.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Incomplete Details",Toast.LENGTH_LONG).show();
        }
        else
        {
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
            Intent intent = new Intent(this, DonorActivity.class);
            startActivity(intent.putExtra("id",ID));
        }
    }
    public void homepage()
    {
        Intent intent = new Intent(this, Donor_home.class);
        startActivity(intent.putExtra("id",ID));
    }

    public void postpage()
    {
        Intent intent = new Intent(this, DonorActivity.class);
        startActivity(intent.putExtra("id",ID));
    }

    public void profilepage()
    {
        Intent intent = new Intent(this, UserProfilePage.class);
        intent.putExtra("id",ID);
        startActivity(intent.putExtra("id",ID));
    }

}