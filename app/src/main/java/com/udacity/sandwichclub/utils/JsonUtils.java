package com.udacity.sandwichclub.utils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {
    public static final String KEY_NAME="name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public static  final String KEY_PLACE_OF_ORIGIN="placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public  static final String KEY_IMAGE = "image";
    public  static final String KEY_INGREDIENTS ="ingredients";
    public  static final String KEY_NA = "N/A"; // set text to N/A if no value is specified for place of origin and/or alsoknownas


    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject entireJsonString = new JSONObject(json);// store entire string that was passed....entireJsonstring should be used to get keys

            JSONObject nameKey = entireJsonString.getJSONObject(KEY_NAME); // store key: "name"
            String mainNameHolder = nameKey.optString(KEY_MAIN_NAME); // get value mapped to key and store in mainName
            JSONArray otherNamesHolder = nameKey.optJSONArray(KEY_ALSO_KNOWN_AS);
            ArrayList<String> otherNames= new ArrayList<>();
            if(otherNamesHolder.length()>0) {
                for (int i = 0; i < otherNamesHolder.length(); i++) {

                    otherNames.add(otherNamesHolder.getString(i));
                }
            }
           else
            {
                otherNames.add(KEY_NA);
            }

            String placeOfOriginHolder = entireJsonString.optString(KEY_PLACE_OF_ORIGIN);
            String descriptionHolder = entireJsonString.optString(KEY_DESCRIPTION);
            String imageHolder = entireJsonString.optString(KEY_IMAGE);
            JSONArray ingredientHolder = entireJsonString.optJSONArray(KEY_INGREDIENTS);

            ArrayList<String> ingredients = new ArrayList<>();
            if(ingredientHolder.length()>0) {
                for (int i = 0; i < ingredientHolder.length(); i++)
                    ingredients.add(ingredientHolder.getString(i));
            }
            else
            {
                ingredients.add(KEY_NA);
            }
            // set place of origin to "not applicable" if none is specified
            if(placeOfOriginHolder.isEmpty())
            {
                placeOfOriginHolder = KEY_NA;
            }

            return new Sandwich(mainNameHolder,otherNames,placeOfOriginHolder,descriptionHolder,imageHolder,ingredients);


        } catch (JSONException e) {

            e.printStackTrace();
        }


        return null;
    }
}
