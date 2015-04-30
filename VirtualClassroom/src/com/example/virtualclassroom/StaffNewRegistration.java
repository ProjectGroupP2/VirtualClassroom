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
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualclassroomproject.R;

public class StaffNewRegistration  extends Activity{
	private ProgressDialog pDialog;
	 
    JSONParser jsonParser = new JSONParser();
    EditText firstName;
    EditText middleName;
    EditText lastName;
    EditText staffNewUsername;
    EditText password;
    EditText confirmPassword;
    EditText securityQuestion;
    EditText secutiyQuestionAnswer; 
   TextView errorMessage;
    private static String url_create_staff = "http://10.0.2.2/virtualClassroom/create_staff.php";
    private static final String TAG_SUCCESS = "success";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_registration);
        
        firstName = (EditText) findViewById(R.id.staffRegistrationFirstName);
        middleName = (EditText) findViewById(R.id.staffRegistrationMiddleName);
        lastName = (EditText) findViewById(R.id.staffRegistrationLastName);
        staffNewUsername=(EditText)findViewById(R.id.staffNewUserName);
        password = (EditText) findViewById(R.id.staffNewpassword);
        //String testpass= password.getText().toString();
        confirmPassword = (EditText) findViewById(R.id.confirmStaffPassword);
        //String testconfpass= confirmPassword.getText().toString();
        securityQuestion = (EditText) findViewById(R.id.staffRegistrationSecurityQuestion);
        secutiyQuestionAnswer = (EditText) findViewById(R.id.staffRegistrationAnswer);
//        if(testpass.equals(testconfpass))
//        {
//        	
//        }
        
      //  errorMessage=(TextView)findViewById(R.id.StaffRegistrationErrorMessage);
        Button btnCreateNewStaff = (Button) findViewById(R.id.btnStaffRegister);
         
        btnCreateNewStaff.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View view) {
              new CreateNewStaff().execute();
            }
        });
    }
     
    class CreateNewStaff extends AsyncTask<String, String, String> {
       
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StaffNewRegistration.this);
            pDialog.setMessage("Creating Staff..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        protected String doInBackground(String... args) {
        	
            String staffFirstName = firstName.getText().toString();
            String staffMiddleName = middleName.getText().toString();
            String staffLastName = lastName.getText().toString();
            String staffUsername= staffNewUsername.getText().toString();
            String staffPassword = password.getText().toString();
            String staffConfirmPassword = confirmPassword.getText().toString();
            String staffSecurityQuestion = securityQuestion.getText().toString();
            String staffSecurityQuestionAnswer = secutiyQuestionAnswer.getText().toString();
//            if(staffPassword.equals(staffConfirmPassword))
//            {
            	List<NameValuePair> params = new ArrayList<NameValuePair>();
            
            params.add(new BasicNameValuePair("firstName", staffFirstName));
            params.add(new BasicNameValuePair("middleName", staffMiddleName));
            params.add(new BasicNameValuePair("lastName", staffLastName));
            params.add(new BasicNameValuePair("username", staffUsername));
            params.add(new BasicNameValuePair("password", staffPassword));
            params.add(new BasicNameValuePair("confirmPassword", staffConfirmPassword));
            params.add(new BasicNameValuePair("securityQuestion", staffSecurityQuestion));
            params.add(new BasicNameValuePair("securityQuestionAnswer", staffSecurityQuestionAnswer));
            params.add(new BasicNameValuePair("status", "Disabled"));
                                  
            JSONObject json = jsonParser.makeHttpRequest(url_create_staff, "POST", params);
            Log.d("Create Response", json.toString());
 
            try {
                int success = json.getInt(TAG_SUCCESS);
                 if (success == 1) {
                   
                	 runOnUiThread(new Runnable() {
 						public void run() {
 							Toast.makeText(getApplicationContext(),
 									"Staff Registration is done Successfully", Toast.LENGTH_LONG).show();
 							 
 							Intent i = new Intent(getApplicationContext(),StaffLogin.class);
 							startActivity(i);
 							finish();

 						}
 					});
//                    Intent i = new Intent(getApplicationContext(), StaffLoginSuccessful.class);
//                    startActivity(i);
// 
//                    finish();
                }
                 else {
                   
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            }
            
//            else
//            {
//         	   runOnUiThread(new Runnable() {
//						public void run() {
//							Toast.makeText(getApplicationContext(),
//									"password and conform pssword are not same", Toast.LENGTH_LONG).show();
//
//						}
//					});
           // }
            
            return null;
        }
 
        protected void onPostExecute(String file_url) {
           pDialog.dismiss();
        }
 
    }
}