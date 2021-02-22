package com.aadev.aecomics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aadev.aecomics.models.Comic;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageComicDetails;
    private TextView textTitle;
    private TextView textDescription;
    private TextView textPrice;
    private EditText editTextNumber;
    private Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageComicDetails = findViewById(R.id.imageComicDetails);
        textTitle = findViewById(R.id.textTitle);
        textDescription = findViewById(R.id.textDescription);
        textPrice = findViewById(R.id.textViewPrice);
        editTextNumber = findViewById(R.id.editTextNumber);
        btnBuy = findViewById(R.id.btnBuy);


        Bundle dados = getIntent().getExtras();

        String id = dados.getString("id");
        String title = dados.getString("title");
        String description = dados.getString("description");
        String price = dados.getString("prices");
        String images = dados.getString("images");
        String rare = dados.getString("rare");

        try {
            String url = images;
            Picasso.get().load(url).into(imageComicDetails);

            textTitle.setText(title);
            textDescription.setText(description);
        }catch (Exception e){
            System.out.println("Erro ao carregar dados");
        }

    }
}