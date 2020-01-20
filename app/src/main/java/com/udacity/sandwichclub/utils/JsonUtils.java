package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String SANDWICH_NAME = "name";
    private static final String SANDWICH_MAIN_NAME = "mainName";
    private static final String SANDWICH_AKA = "alsoKnownAs";
    private static final String SANDWICH_ORIGIN = "placeOfOrigin";
    private static final String SANDWICH_DESCRIPTION = "description";
    private static final String SANDWICH_IMAGE = "image";
    private static final String SANDWICH_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();
        try {
            JSONObject rootJSONObject = new JSONObject(json);

            JSONObject name = rootJSONObject.getJSONObject(SANDWICH_NAME);
            sandwich.setMainName(name.getString(SANDWICH_MAIN_NAME));

            JSONArray alsoKnownAsJSONArray = rootJSONObject.getJSONArray(SANDWICH_AKA);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJSONArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJSONArray.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setPlaceOfOrigin(rootJSONObject.getString(SANDWICH_ORIGIN));
            sandwich.setDescription(rootJSONObject.getString(SANDWICH_DESCRIPTION));
            sandwich.setImage(rootJSONObject.getString(SANDWICH_IMAGE));

            JSONArray ingredientsJSONArray = rootJSONObject.getJSONArray(SANDWICH_INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJSONArray.length(); i++) {
                ingredients.add(ingredientsJSONArray.getString(i));
            }
            sandwich.setIngredients(ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
