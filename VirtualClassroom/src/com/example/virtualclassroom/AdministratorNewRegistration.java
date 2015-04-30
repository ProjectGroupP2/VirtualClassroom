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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdministratorNewRegistration extends Activity {
  
	private ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    EditText username;
    EditText password;
    EditText confirmPassword;
    EditText securityQuestion;
    EditText securityQuestionAnswer; 
    EditText securityKey;
     
    private static String url_create_administrator = "http://10.0.2.2/virtualClassroom/create_administrator.php";
    private static final String TAG_SUCCESS = "success";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_registration);
        
        username = (EditText) findViewById(R.id.admin_username);
        password = (EditText) findViewById(R.id.admin_password);
        confirmPassword = (EditText) findViewById(R.id.admin_confirmPassword);
        securityQuestion = (EditText) findViewById(R.id.adminSecurityQuestion);
        securityQuestionAnswer = (EditText) findViewById(R.id.adminSecutiyQuestionAnswer);
        securityKey = (EditText) findViewById(R.id.adminSecurityKey);
        String testKey= securityKey.getText().toString();
        if(testKey.equals("AA"))
        {
        	
        }
        Button btnCreateNewAdministrator = (Button) findViewById(R.id.btnAdminRegister);
         
        btnCreateNewAdministrator.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
            	            	 
            	new CreateNewAdministrator().execute();
            	 
            }
        });
    }
 
    /**
     * Background Async Task to Create new Administrator
     * */
    class CreateNewAdministrator extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AdministratorNewRegistration.this);
            pDialog.setMessage("Creating Administrator..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Creating Administrator
         * */
        protected String doInBackground(String... args) {
            String adminUsername = username.getText().toString();
            String adminPassword = password.getText().toString();
            String adminConfirmPassword = confirmPassword.getText().toString();
            String adminSecurityQuestion = securityQuestion.getText().toString();
            String adminSecurityQuestionAnswer = securityQuestionAnswer.getText().toString();
            String adminSecurityKey = securityKey.getText().toString();
               if(adminSecurityKey.equals("AA") && adminPassword.equals(adminConfirmPassword))
               {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", adminUsername));
            params.add(new BasicNameValuePair("password", adminPassword));
            params.add(new BasicNameValuePair("confirmPassword", adminConfirmPassword));
            params.add(new BasicNameValuePair("securityQuestion", adminSecurityQuestion));
            params.add(new BasicNameValuePair("securityQuestionAnswer", adminSecurityQuestionAnswer));
            params.add(new BasicNameValuePair("securityKey", adminSecurityKey));
            
            
            // getting JSON Object
            
            JSONObject json = jsonParser.makeHttpRequest(url_create_administrator, "POST", params);
 
            // check log cat fro response
            Log.d("Create Response", json.toString());
 
            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) {
                    // successfully created Administrator
                    Intent i = new Intent(getApplicationContext(), AdministratorLogin.class);
                    startActivity(i);
 
                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
               }
               
               else
               {
            	   runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(getApplicationContext(),
									"Wrong key", Toast.LENGTH_LONG).show();

						}
					});
               }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }
 
    }
 
}