package com.example.virtualclassroom;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.virtualclassroomproject.R;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class StudentForgetPassword extends Activity {
	EditText studentUsername;
	EditText key;
	TextView password;
	EditText txtName;
	EditText studentPassword,studentSecurityQuestion,studentAnswer;
	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	private static String url_studentRetrivePassword = "http://10.0.2.2/VirtualClassroom/get_Student_Password.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_PRODUCT = "product";
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.student_forgetpassword);
		
		studentUsername= (EditText)findViewById(R.id.studentRecoveryUserName);
		studentSecurityQuestion= (EditText)findViewById(R.id.studentRecoverySecurityQuestion);
		studentAnswer= (EditText)findViewById(R.id.studentRecoveryAnswer);
		
		Button btnStudentRetrivePassword= (Button)findViewById(R.id.btnStudentRetrivePassword);
		btnStudentRetrivePassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new StudentRetrivePassword().execute();
				
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
	class StudentRetrivePassword extends AsyncTask<String, String, String>
	{
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StudentForgetPassword.this);
            pDialog.setMessage("Checking credentials. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

		@Override
		protected String doInBackground(String... params) {
			 runOnUiThread(new Runnable() {
				 public void run() {
					 int success;
			
            try {
            	
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
    			String username= studentUsername.getText().toString();
    			String securityQuestion= studentSecurityQuestion.getText().toString();
    			String securityQuestionAnswer= studentAnswer.getText().toString();
    			
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("securityQuestion", securityQuestion));
                params.add(new BasicNameValuePair("securityQuestionAnswer", securityQuestionAnswer));
                
                JSONObject json = jsonParser.makeHttpRequest(url_studentRetrivePassword, "GET", params);
                
                Log.d("Create Response", json.toString());
                 success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                	JSONArray productObj = json.getJSONArray(TAG_PRODUCT);	
                	JSONObject product = productObj.getJSONObject(0);
                	studentPassword=(EditText)findViewById(R.id.studentRecoveredPassword);
                	studentPassword.setText(product.getString(TAG_PASSWORD));
                	
                } 
                
                else {
               	
                     
                }
            } 
            
            catch (JSONException e) {
                e.printStackTrace();
            }
				 }
			 });
			 
				 
			 
            
            return null;
			
		}
		  protected void onPostExecute(String file_url) {
			  
			
	          pDialog.dismiss();
	         
	        }
		
	}

}
