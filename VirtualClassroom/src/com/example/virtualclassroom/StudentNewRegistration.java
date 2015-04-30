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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualclassroomproject.R;

public class StudentNewRegistration extends Activity {

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	EditText firstName;
	EditText middleName;
	EditText lastName;
	EditText standard;
	EditText branch;
	EditText rollNumber;
	EditText password;
	EditText confirmPassword;
	EditText securityQuestion;
	EditText securityQuestionAnswer;
	 String testpass=null;
	 String testconfpass=null;
	private static String url_create_student = "http://10.0.2.2/virtualClassroom/create_student.php";
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_registration);

		firstName = (EditText) findViewById(R.id.studentFirstName);
		middleName = (EditText) findViewById(R.id.studentMiddleName);
		lastName = (EditText) findViewById(R.id.studentLastName);
		standard = (EditText) findViewById(R.id.studentStandard);
		branch = (EditText) findViewById(R.id.studentBranch);
		rollNumber = (EditText) findViewById(R.id.studentRollNumber);
		password = (EditText) findViewById(R.id.studentPassword);
		confirmPassword = (EditText) findViewById(R.id.StudentConfirmPassword);
		securityQuestion = (EditText) findViewById(R.id.studentSecurityQuestion);
		securityQuestionAnswer = (EditText) findViewById(R.id.studentAnswer);
		
		 testpass= password.getText().toString();	
		 testconfpass= confirmPassword.getText().toString();
		
		Button btnCreateNewStudent = (Button) findViewById(R.id.btnStudentRegister);
		btnCreateNewStudent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(testpass.equals(testconfpass))
		        {
				new CreateNewStudent().execute();
		        }
				else
				{
					Toast.makeText(getApplicationContext(),
							"password should not match with confirm passwrod", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	class CreateNewStudent extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(StudentNewRegistration.this);
			pDialog.setMessage("Registering Student..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			String studentFirstName = firstName.getText().toString();
			String studentMiddleName = middleName.getText().toString();
			String studentLastName = lastName.getText().toString();
			String StudentStandard = standard.getText().toString();
			String studentBranch = branch.getText().toString();
			String studentRollNumber = rollNumber.getText().toString();
			String studentPassword = password.getText().toString();
			String studentConfirmPassword = confirmPassword.getText().toString();
			String studentSecurityQuestion = securityQuestion.getText().toString();
			String studentSecurityQuestionAnswer = securityQuestionAnswer.getText().toString();
			
			final String username = (studentFirstName.substring(0, 1)+ studentMiddleName.substring(0, 1)+ studentLastName.substring(0, 1) + StudentStandard
			+ studentBranch + studentRollNumber).toUpperCase();
			
						List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("firstName", studentFirstName));
			params.add(new BasicNameValuePair("middleName", studentMiddleName));
			params.add(new BasicNameValuePair("lastName", studentLastName));
			params.add(new BasicNameValuePair("standard", StudentStandard));
			params.add(new BasicNameValuePair("branch", studentBranch));
			params.add(new BasicNameValuePair("rollNumber", studentRollNumber));
			params.add(new BasicNameValuePair("password", studentPassword));
			params.add(new BasicNameValuePair("confirmPassword",studentConfirmPassword));
			params.add(new BasicNameValuePair("securityQuestion",studentSecurityQuestion));
			params.add(new BasicNameValuePair("securityQuestionAnswer",	studentSecurityQuestionAnswer));
			params.add(new BasicNameValuePair("username", username));
			params.add(new BasicNameValuePair("status", "Disabled"));

			JSONObject json = jsonParser.makeHttpRequest(url_create_student,"POST", params);
			Log.d("Create Response", json.toString());
			try {
				int success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(getApplicationContext(),
									"Student Registration is done Successfully", Toast.LENGTH_LONG).show();
							Toast.makeText(getApplicationContext(),
									"Your Username is:"+username.toString(), Toast.LENGTH_LONG).show();
							Intent i = new Intent(getApplicationContext(),StudentLogin.class);
							startActivity(i);
							finish();

						}
					});
					
					
				}
			
			else {
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(getApplicationContext(),
									"Erro to Register Student", Toast.LENGTH_LONG).show();
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