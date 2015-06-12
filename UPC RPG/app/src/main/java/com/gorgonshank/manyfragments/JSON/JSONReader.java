package com.gorgonshank.manyfragments.JSON;

import android.content.Context;

import com.gorgonshank.manyfragments.Data.Constants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

    private JSONObject myObject = null;

    public JSONReader(File fileDirectory) throws FileNotFoundException{

        JSONParser parser = new JSONParser();

        String myFileName = Constants.JSON;

        try {
            Object obj = parser.parse(new FileReader(new File(fileDirectory, myFileName)));
            JSONObject jsonObject = (JSONObject) obj;
            this.myObject = jsonObject;
        } catch (IOException e) {
            System.out.println("I/O exception found.");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Parse exception found.");
            e.printStackTrace();
        }

    }

    public JSONObject getMyObject() {
        return myObject;
    }
}
