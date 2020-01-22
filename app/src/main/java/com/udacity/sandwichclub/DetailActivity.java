package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.get()
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView alsoKnownAsTextView = (TextView) findViewById(R.id.also_known_tv);
        TextView descriptionTextView = (TextView) findViewById(R.id.description_tv);
        TextView originTextView = (TextView) findViewById(R.id.origin_tv);
        TextView ingredientsTextView = (TextView) findViewById(R.id.ingredients_tv);

        String alsoKnownAsString = "";
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
        if (alsoKnownAsList.size() > 1) {
            alsoKnownAsString = TextUtils.join("\n", alsoKnownAsList);
            alsoKnownAsTextView.setText(alsoKnownAsString);
        } else {
            alsoKnownAsTextView.setText(R.string.no_other_names_label);
        }

        String ingredientsString = "";
        List<String> ingredientsList = sandwich.getIngredients();
        if (ingredientsList.size() > 1) {
            ingredientsString = TextUtils.join("\n", ingredientsList);
        }

        ingredientsTextView.setText(ingredientsString);
        descriptionTextView.setText(sandwich.getDescription());

        if (sandwich.getPlaceOfOrigin().equals("")) {
            originTextView.setText(R.string.unknown_label);
        } else {
            originTextView.setText(sandwich.getPlaceOfOrigin());
        }
    }
}
