package com.udacity.sandwichclub.utils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject entireJsonString = new JSONObject(json);// store entire string that was passed....entireJsonstring should be used to get keys

            JSONObject nameKey = entireJsonString.getJSONObject("name"); // store key: "name"
            String mainNameHolder = nameKey.optString("mainName"); // get value mapped to key and store in mainName
            JSONArray otherNamesHolder = nameKey.optJSONArray("alsoKnownAs");
            ArrayList<String> otherNames= new ArrayList<>();
            if(otherNamesHolder.length()>0) {
                for (int i = 0; i < otherNamesHolder.length(); i++) {

                    otherNames.add(otherNamesHolder.getString(i));
                }
            }


            String placeOfOriginHolder = entireJsonString.optString("placeOfOrigin");
            String descriptionHolder = entireJsonString.optString("description");
            String imageHolder = entireJsonString.optString("image");
            JSONArray ingredientHolder = entireJsonString.optJSONArray("ingredients");

            ArrayList<String> ingredients = new ArrayList<>();
            if(ingredientHolder.length()>0) {
                for (int i = 0; i < ingredientHolder.length(); i++)
                    ingredients.add(ingredientHolder.getString(i));
            }


            return new Sandwich(mainNameHolder,otherNames,placeOfOriginHolder,descriptionHolder,imageHolder,ingredients);


        } catch (JSONException e) {

            e.printStackTrace();
        }


        return null;
    }
}
