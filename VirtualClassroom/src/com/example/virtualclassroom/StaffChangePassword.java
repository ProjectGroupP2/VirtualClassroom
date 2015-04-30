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
import android.widget.TextView;

import com.example.virtualclassroomproject.R;

public class StaffChangePassword  extends Activity{
	public static final String USERNAME_PREFERENCE="username";
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
    EditText oldPassword;
    EditText newPasswrod;
    EditText confirmNewPassword;
    TextView errorMessage;
    String username;
    private static String url_updateStaffPassword = "http://10.0.2.2/VirtualClassroom/update_staff_password.php";
    private static final String TAG_SUCCESS = "success";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.staff_changepassword);
		
		Button staffChangePassword= (Button)findViewById(R.id.btnStaffChangePassword);
		//oldPassword= (EditText)findViewById(R.id.staffOldPassword);
		newPasswrod= (EditText)findViewById(R.id.staffNewPassword);
		confirmNewPassword=(EditText)findViewById(R.id.staffCofirmNewPassword);
		SharedPreferences setting = getSharedPreferences(USERNAME_PREFERENCE, 0);
		username= setting.getString("username", "Wrong");
		staffChangePassword.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new ChangePassword().execute();
					
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

	
class ChangePassword extends AsyncTask<String, String, String> {
	    
    	@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StaffChangePassword.this);
            pDialog.setMessage("Checking credentials. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
          
        protected String doInBackground(String... args) {
        	// String staffOldPassword =  oldPassword.getText().toString();
             String staffNewPassword =  newPasswrod.getText().toString();
            // String staffConfirmPassword= confirmNewPassword.getText().toString();
              
             
             List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", staffNewPassword));
           // params.add(new BasicNameValuePair("password", staffConfirmPassword));
			JSONObject json = jsonParser.makeHttpRequest(url_updateStaffPassword, "POST", params);
               
             Log.d("Create Response", json.toString());
             try {
                 int success = json.getInt(TAG_SUCCESS);
  
                 if (success == 1) {
                    Intent i = new Intent(getApplicationContext(), StaffLoginSuccessful.class);
                    startActivity(i);
                      finish();
                 } 
                 else {
                	
                      
                 }
             } 
             
             catch (JSONException e) {
                 e.printStackTrace();
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
