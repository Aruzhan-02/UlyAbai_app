package com.example.ulyabai.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ulyabai.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SingleNewsPage extends AppCompatActivity {
    TextView tvTitle, tvText, tvDate;
    ImageView tvImage;
    FirebaseFirestore firestore;
    DocumentReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news_page);

        tvTitle = findViewById(R.id.tvTitle);
        tvText = findViewById(R.id.tvText);
        tvImage = findViewById(R.id.tvImage);
        tvDate = findViewById(R.id.tvDate);
        firestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        String nDate = intent.getStringExtra("news_date");
        tvDate.setText(nDate);

        getNewsFromFirebase();

    }

    private void getNewsFromFirebase() {
        String nDate = tvDate.getText().toString();
        reference = firestore.collection("news").document(nDate);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){
                            String nTitle = task.getResult().getString("title");
                            String nImage = task.getResult().getString("image");
                            String nText = task.getResult().getString("text");

                            tvTitle.setText(nTitle);
                            Glide.with(getApplicationContext()).load(nImage).into(tvImage);
                            tvText.setText(nText);

                        }else {
                            Toast.makeText(SingleNewsPage.this, "Can't get data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}