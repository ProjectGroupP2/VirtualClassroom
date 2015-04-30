package com.example.virtualclassroom;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualclassroomproject.R;

public class StudentLogin extends Activity {
	public static final String STUDENT_PREFERENCE="student";
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	EditText username;
	EditText password;
	private static String url_checkValidStudent = "http://10.0.2.2/VirtualClassroom/check_valid_student.php";
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_login);
		username = (EditText) findViewById(R.id.studentLoginUsername);
		password = (EditText) findViewById(R.id.studentLoginPassword);
		/****************************************************************************************************************/
		Button btnNewStudentRegistration = (Button) findViewById(R.id.btnLinkToNewStudentRegistration);
		btnNewStudentRegistration.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent i = new Intent(getApplicationContext(),StudentNewRegistration.class);
						startActivity(i);

					}
				});
		/****************************************************************************************************************/
		Button btnCheckStudent = (Button) findViewById(R.id.btnStudentLogin);
		btnCheckStudent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new CheckValidStudent().execute();
			}
		});
		
		Button btnStudentForgetPassword=(Button)findViewById(R.id.btnLinkToStudentForgetPassword);
		btnStudentForgetPassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),StudentForgetPassword.class);
				startActivity(i);
				
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 100) {
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}
	}
	/****************************************************************************************************************/
	class CheckValidStudent extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(StudentLogin.this);
			pDialog.setMessage("Checking credentials. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		protected String doInBackground(String... args) {
			String adminUsername = username.getText().toString();
			String adminPassword = password.getText().toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("username", adminUsername));
			params.add(new BasicNameValuePair("password", adminPassword));
			JSONObject json = jsonParser.makeHttpRequest(url_checkValidStudent,	"POST", params);
			Log.d("Create Response", json.toString());
			
			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					
			Intent i = new Intent(getApplicationContext(),StudentRegistrationSuccessful.class);
			
			SharedPreferences settings= getSharedPreferences(STUDENT_PREFERENCE, 0);
            SharedPreferences.Editor editor= settings.edit();
            
            String editedStandard= username.getText().toString();
            String standard= editedStandard.substring(3, 5);
            
            String editedBranch= username.getText().toString();
            String branch= editedBranch.substring(5, 8);
            
            editor.putString("standard", standard);
            editor.putString("branch", branch);
            editor.putString("username", username.getText().toString());
            editor.commit();
			startActivity(i);
			finish();
				
			} 
				
				else {
					runOnUiThread(new Runnable() {
 						public void run() {
 							Toast.makeText(getApplicationContext(),
 									"Invalid Student. Please check details",Toast.LENGTH_LONG).show();
 						}
 					});

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();

		}
	}
}