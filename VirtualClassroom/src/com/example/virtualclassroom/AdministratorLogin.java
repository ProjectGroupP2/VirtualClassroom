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

public class AdministratorLogin extends Activity {
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
    EditText username;
    EditText password;
     
    private static String url_checkValidAdmin = "http://10.0.2.2/VirtualClassroom/check_valid_admin.php";
    private static final String TAG_SUCCESS = "success";
      
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
 
        username = (EditText) findViewById(R.id.loginUsername);
        password = (EditText) findViewById(R.id.loginPassword);
        
        Button btnNewAdministrator = (Button)findViewById(R.id.btnNewAdminRegistration);
      
        Button btnAdminForgetPassword=(Button)findViewById(R.id.btnAdminForgetPassword);
        btnAdminForgetPassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),AdministratorForgetPassword.class);
				startActivity(i);
				
			}
		});
        btnNewAdministrator.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),AdministratorNewRegistration.class);
				startActivity(i);
 				
			}
		});
                      		
        Button btnCheckAdministrator = (Button) findViewById(R.id.btnLogin);
        btnCheckAdministrator.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View view) {
              new CheckValidAdministrator().execute();
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
 
   class CheckValidAdministrator extends AsyncTask<String, String, String> {
    
    	@Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AdministratorLogin.this);
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
             
			JSONObject json = jsonParser.makeHttpRequest(url_checkValidAdmin, "POST", params);
               
             Log.d("Create Response", json.toString());
             try {
                 int success = json.getInt(TAG_SUCCESS);
  
                 if (success == 1) {
                    Intent i = new Intent(getApplicationContext(), AdminRegistrationSuccessful.class);
                    startActivity(i);
                      finish();
                 } 
                 else {
                	 runOnUiThread(new Runnable() {
  						public void run() {
  							Toast.makeText(getApplicationContext(),
  									"Invalid Administrator. Please check details",
  									Toast.LENGTH_LONG).show();

  						}
  					});
                      
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