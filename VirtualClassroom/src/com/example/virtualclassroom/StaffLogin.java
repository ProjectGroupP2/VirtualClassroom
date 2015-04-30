package com.example.virtualclassroom;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.virtualclassroomproject.R;

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
import android.widget.TextView;
import android.widget.Toast;

public class StaffLogin  extends Activity{
	public static final String USERNAME_PREFERENCE="username";
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
    EditText username;
    EditText password;
    TextView errorMessage;
    private static String url_checkValidStaff = "http://10.0.2.2/VirtualClassroom/check_valid_staff.php";
    private static final String TAG_SUCCESS = "success";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.staff_login);
		 username = (EditText) findViewById(R.id.staffUsername);
	     password = (EditText) findViewById(R.id.staffPassword);
	     errorMessage=(TextView)findViewById(R.id.staffLoginErrorText);
	     Button btnStaffForgetPassword= (Button)findViewById(R.id.btnLinkToStaffForgetPasswordScreen);
	     btnStaffForgetPassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i= new Intent(getApplicationContext(), StaffForgetPassword.class);
				startActivity(i);
				
			}
		});
	        Button btnNewStaffRegistration = (Button)findViewById(R.id.btnLinkToStaffRegistrationScreen);
	        btnNewStaffRegistration.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),StaffNewRegistration.class);
					startActivity(i);
					
				}
			});
	        
	        Button btnCheckStaff = (Button) findViewById(R.id.btnStaffLogin);
	        btnCheckStaff.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new CheckValidStaff().execute();
					
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
	
	class CheckValidStaff extends AsyncTask<String, String, String> {
	    
    	@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StaffLogin.this);
            pDialog.setMessage("Checking credentials. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
          
        protected String doInBackground(String... args) {
        	 String staffUsername = username.getText().toString();
             String staffPassword = password.getText().toString();
              
             if(staffUsername==null || staffPassword==null)
             {
            	 Toast.makeText(getApplicationContext(),
							"All Fields are Mandatory",
							Toast.LENGTH_LONG).show();
             }
             else
             {
             List<NameValuePair> params = new ArrayList<NameValuePair>();
             params.add(new BasicNameValuePair("username", staffUsername));
             params.add(new BasicNameValuePair("password", staffPassword));
             
			JSONObject json = jsonParser.makeHttpRequest(url_checkValidStaff, "POST", params);
               
             Log.d("Create Response", json.toString());
             try {
                 int success = json.getInt(TAG_SUCCESS);
  
                 if (success == 1) {
                    Intent i = new Intent(getApplicationContext(), StaffLoginSuccessful.class);
                    SharedPreferences settings= getSharedPreferences(USERNAME_PREFERENCE, 0);
                    SharedPreferences.Editor editor= settings.edit();
                    editor.putString("username", username.getText().toString());
                    editor.commit();
                    startActivity(i);
                    finish();
                 } 
                 else {
                	 runOnUiThread(new Runnable() {
 						public void run() {
 							Toast.makeText(getApplicationContext(),
 									"Invalid Staff. Please check details",
 									Toast.LENGTH_LONG).show();

 						}
 					});
                      
                 }
             } 
             
             catch (JSONException e) {
                 e.printStackTrace();
             }
             
             }
             return null;
         }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
          pDialog.dismiss();
         
        }
     }	

}
