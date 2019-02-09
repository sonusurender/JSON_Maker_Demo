package com.jsonmaker_demo;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private static EditText name, location, age, number;
	private static TextView showJsonData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	// Initialize all the views
	private void init() {
		name = (EditText) findViewById(R.id.name);
		location = (EditText) findViewById(R.id.location);
		age = (EditText) findViewById(R.id.age);
		number = (EditText) findViewById(R.id.contact_number);
		showJsonData = (TextView) findViewById(R.id.show_jsondata);

		// Click listener for submit button
		findViewById(R.id.submit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				// Call the json data convertor method
				ConvertToJsonData();
			}
		});
	}

	// Method to convert simple data into jsondata
	private void ConvertToJsonData() {
		// get texts from all edittexts
		String getName = name.getText().toString();
		String getLocation = location.getText().toString();
		String getNumber = number.getText().toString();
		String getAge = age.getText().toString();

		// Check if all fields are filled or not
		if (getName.length() != 0 && !getName.equals("")
				&& getLocation.length() != 0 && !getLocation.equals("")
				&& getNumber.length() != 0 && !getNumber.equals("")
				&& getAge.length() != 0 && !getAge.equals("")) {
			try {

				JSONObject dataObject = new JSONObject();// JsonObject for
															// storing edittext
															// fields
				dataObject.put("name", getName);// now put the edittext values
												// w.r.t. keyvalue like "name"
				dataObject.put("location", getLocation);
				dataObject.put("number", getNumber);
				dataObject.put("age", getAge);

				JSONArray mainArray = new JSONArray();// JsonArray to put the
														// dataObject into
														// jsonArray, since
														// there can be more
														// data if data is saved
														// in database so the
														// jsonObject data will
														// be put into jsonArray
				mainArray.put(dataObject);

				// Finally put the jsonArray into another jsonObject with "data"
				// keyvlaue or any other keyvalue
				JSONObject mainObject = new JSONObject(); // This object is
															// optional you can
															// convert json data
															// according to your
															// need
				mainObject.put("data", mainArray);

				// Now, set the converted json data to textview to see the
				// actual format
				showJsonData.setText("Converted JSON data is \n\n"
						+ mainObject.toString());
			} catch (Exception e) {
				e.printStackTrace();

				// If any exception occurs then display error
				showJsonData.setText("Error in converting data.");
			}

		}
		// If all fileds or one of the filed is empty show toast
		else {
			Toast.makeText(MainActivity.this, "All fields are required.",
					Toast.LENGTH_SHORT).show();
		}

	}
}
