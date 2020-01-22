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

            JSONObject nameObject = rootJSONObject.optJSONObject(SANDWICH_NAME);
            sandwich.setMainName(nameObject.optString(SANDWICH_MAIN_NAME));

            JSONArray alsoKnownAsJSONArray = nameObject.optJSONArray(SANDWICH_AKA);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJSONArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJSONArray.optString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownAs);
            sandwich.setPlaceOfOrigin(rootJSONObject.optString(SANDWICH_ORIGIN));
            sandwich.setDescription(rootJSONObject.optString(SANDWICH_DESCRIPTION));
            sandwich.setImage(rootJSONObject.optString(SANDWICH_IMAGE));

            JSONArray ingredientsJSONArray = rootJSONObject.optJSONArray(SANDWICH_INGREDIENTS);
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJSONArray.length(); i++) {
                ingredients.add(ingredientsJSONArray.optString(i));
            }
            sandwich.setIngredients(ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
