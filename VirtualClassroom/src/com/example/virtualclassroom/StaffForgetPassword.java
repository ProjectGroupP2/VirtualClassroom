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
public class StaffForgetPassword extends Activity {
	EditText staffUsername,staffSecurityQuestion,staffAnswer;
	EditText key;
	TextView password;
	EditText txtName;
	EditText staffPassword;
	JSONParser jsonParser = new JSONParser();
	private ProgressDialog pDialog;
	private static String url_staffRetrivePassword = "http://10.0.2.2/VirtualClassroom/get_Staff_Password.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_PRODUCT = "product";
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.staff_forgetpassword);
		
		staffUsername= (EditText)findViewById(R.id.staffRetrievePassword);
		staffSecurityQuestion=(EditText)findViewById(R.id.staffRecoverySecurityQuestion);
		staffAnswer=(EditText)findViewById(R.id.staffRecoveryAnswer);
		
		Button btnStaffRetrivePassword= (Button)findViewById(R.id.btnStaffRetrivePassword);
		btnStaffRetrivePassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new StaffRetrivePassword().execute();
				
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
	class StaffRetrivePassword extends AsyncTask<String, String, String>
	{
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StaffForgetPassword.this);
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
    			String username= staffUsername.getText().toString();
    			String securityQuestion= staffSecurityQuestion.getText().toString();
    			String securityQuestionAnswer= staffAnswer.getText().toString();
    			
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("securityQuestion", securityQuestion));
                params.add(new BasicNameValuePair("securityQuestionAnswer", securityQuestionAnswer));
                
                JSONObject json = jsonParser.makeHttpRequest(url_staffRetrivePassword, "GET", params);
                
                Log.d("Create Response", json.toString());
                 success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                	JSONArray productObj = json.getJSONArray(TAG_PRODUCT);	
                	JSONObject product = productObj.getJSONObject(0);
                	staffPassword=(EditText)findViewById(R.id.staffRecoveredPassword);
                	staffPassword.setText(product.getString(TAG_PASSWORD));
                	
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
