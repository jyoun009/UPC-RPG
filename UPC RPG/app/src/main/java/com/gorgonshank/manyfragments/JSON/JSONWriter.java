package com.gorgonshank.manyfragments.JSON;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gorgonshank.manyfragments.Data.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Modifier;

public class JSONWriter {

	public JSONWriter(Object myObject, File fileDirectory) throws IOException{
		
		// This file is saved into the source folder for this Java Project
		String myFileName = Constants.JSON;
		
		// Gson is used to create a json object that is spaced nicely
        // The Modifer.TRANSIENT constant is used to include Static Fields
		Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();

		// Change our object to a JSON String
        String jsonString = gson.toJson(myObject);

        Log.i("Stuff", jsonString);

        // We have to pass a context to our writer to get the directory
        Writer writer = new BufferedWriter(new FileWriter(new File(fileDirectory, myFileName)));
        writer.write(jsonString);
        writer.flush();
        writer.close();

	}
}
