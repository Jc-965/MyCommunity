package com.example.mycommunity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MyTroopActivity extends AppCompatActivity {
    ImageButton closeButton;
    AlertDialog.Builder builder;

    EditText name, email, address, description;

    ImageButton image;

    public static String sAddress, sName, sDescription, sEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_troop);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.tvTitle)).setText("My Troop");

        ImageButton volunteerBtn = findViewById(R.id.volunteer_btn);
        ImageButton homeBtn = findViewById(R.id.home_btn);
        ImageButton myTroopBtn = findViewById(R.id.my_troop_btn);
        ImageButton newsArticleBtn = findViewById(R.id.news_article_btn);

        TextView tName = findViewById(R.id.tName);
        TextView tEmail = findViewById(R.id.tEmail);
        TextView tAddress = findViewById(R.id.tAddress);
        TextView tDescription = findViewById(R.id.tDescription);

        volunteerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyTroopActivity.this, VolunteerActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyTroopActivity.this, MainActivity.class));
            }
        });

        myTroopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyTroopActivity.this, MyTroopActivity.class));
            }
        });

        newsArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyTroopActivity.this, NewsActivity.class));
            }
        });

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Troop Details");
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MyTroopActivity.this);
        db = db.child(acct.getEmail().replaceAll("[.#$]" , ","));
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap o = (HashMap) dataSnapshot.getValue();

                if(o != null) {
                    sAddress = (String) o.get("Troop Address (Latitude, Longitude)");
                    sEmail = (String) o.get("Troop Email");
                    sName = (String) o.get("Troop Name");
                    sDescription = (String) o.get("Troop Description");

                    tName.setText("Name: " + sName);
                    tAddress.setText("Address: " + sAddress);
                    tEmail.setText("Email: " + sEmail);
                    tDescription.setText("Description: " + sDescription);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        db.addValueEventListener(postListener);


//        db = db.child(acct.getEmail().replaceAll("[.#$]" , ",")).child("Troop Address");
//
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Object o = dataSnapshot.getValue();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        };
//        db.addValueEventListener(postListener);

        View view = getLayoutInflater().inflate(R.layout.edit, null);

//        image = view.findViewById(R.id.tImage);

//        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
//                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
//                    // Callback is invoked after the user selects a media item or closes the
//                    // photo picker.
//                    if (uri != null) {
//                        Log.d("PhotoPicker", "Selected URI: " + uri);
//                    } else {
//                        Log.d("PhotoPicker", "No media selected");
//                    }
//                });
//
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pickMedia.launch(new PickVisualMediaRequest.Builder()
//                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
//                        .build());
//            }
//        });

        closeButton = (ImageButton) findViewById(R.id.edit);
        builder = new AlertDialog.Builder(this);

        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                builder.setTitle("Update your troop!");

                View view = getLayoutInflater().inflate(R.layout.edit, null);
                builder.setView(view);

                name = view.findViewById(R.id.tName);
                email = view.findViewById(R.id.tEmail);
                address = view.findViewById(R.id.tAddress);
                description = view.findViewById(R.id.tDescription);

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Map<String, Object> map = new HashMap<>();

                        map.put("Troop Name", name.getText().toString());
                        map.put("Troop Email", email.getText().toString());
                        map.put("Troop Address (Latitude, Longitude)", address.getText().toString());
                        map.put("Troop Description", description.getText().toString());

                        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Troop Details");
                        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MyTroopActivity.this);

                        if(acct!=null){
                            String email = acct.getEmail();
                            assert email != null;
                            db.child(email.replaceAll("[.#$]" , ",")).setValue(map);
                        }
                        else{
                            Toast.makeText(MyTroopActivity.this, "Your email is not valid", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel(); // closes dialog
                    }
                });

                builder.show();
            }
        });


    }
}