package com.example.virtualclassroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualclassroomproject.R;

public class UnauthorizeStaffList extends ListActivity {
	
	private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> subjectList;
    private static String url_unapprovedStaff_list = "http://10.0.2.2/VirtualClassroom/get_Unapproved_Staff.php";
    private static String url_update_Staff_Status = "http://10.0.2.2/VirtualClassroom/enable_staff.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS= "product";
    private static final String TAG_USERNAME = "username";

    JSONArray subjects = null;
    String staffName=null;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unauthorize_staff);
 
        subjectList = new ArrayList<HashMap<String, String>>();
         new UnauthorizeStaff().execute();
         ListView lv = getListView();
         lv.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	staffName = ((TextView) view.findViewById(R.id.name)).getText()
						.toString();

            	
				new UpdateStatusOfStaff().execute();
       
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
    
    class UpdateStatusOfStaff extends AsyncTask<String, String, String>
    {

    	 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(UnauthorizeStaffList.this);
             pDialog.setMessage("updating status. Please wait...");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(false);
             pDialog.show();
         }
    	 
    	 protected String doInBackground(String... args) {
             List<NameValuePair> params = new ArrayList<NameValuePair>();
             params.add(new BasicNameValuePair("username", staffName));
             JSONObject json = jParser.makeHttpRequest(url_update_Staff_Status, "POST", params);
             Log.d("Subjects : ", json.toString());
              try {               
                 int success = json.getInt(TAG_SUCCESS);
                  if (success == 1) {
                	  
                	  runOnUiThread(new Runnable() {
  						public void run() {
  							Toast.makeText(getApplicationContext(),
  									"Status Updated", Toast.LENGTH_LONG).show();

  						}
  					});
                  }
                 	  
                     
                  
                  else {
                	  runOnUiThread(new Runnable() {
    						public void run() {
    							Toast.makeText(getApplicationContext(),
    									"Status Not Updated", Toast.LENGTH_LONG).show();

    						}
    					});
 
                 }
                  
               
             } catch (JSONException e) {
                 e.printStackTrace();
             }
  
             return null;
         }
    	 
    	 protected void onPostExecute(String file_url) {
             
             pDialog.dismiss(); }
    	
    }
    
    class UnauthorizeStaff extends AsyncTask<String, String, String> {
    	 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(UnauthorizeStaffList.this);
            pDialog.setMessage("Loading Subject List. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
 
        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
           
            JSONObject json = jParser.makeHttpRequest(url_unapprovedStaff_list, "GET", params);
            Log.d("Subjects : ", json.toString());
             try {               
                int success = json.getInt(TAG_SUCCESS);
                 if (success == 1) {
                   subjects = json.getJSONArray(TAG_PRODUCTS);
           
                    for (int i = 0; i < subjects.length(); i++) {
                        JSONObject c = subjects.getJSONObject(i);
                      
                        String username = c.getString(TAG_USERNAME);
                         HashMap<String, String> map = new HashMap<String, String>();
                       
                         map.put(TAG_USERNAME, username);
 
                        subjectList.add(map);
                    }
                } 
                 else {
                	
                }
                 
                 subjectList= new ArrayList<HashMap<String,String>>(subjectList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
           
            pDialog.dismiss();
           
            runOnUiThread(new Runnable() {
                public void run() {
                  
                	 ListAdapter adapter = new SimpleAdapter(
                             UnauthorizeStaffList.this, subjectList,
                             R.layout.list_item, new String[] { 
                                     TAG_USERNAME},
                             
                             new int[] {R.id.name });
                    
                     setListAdapter(adapter);
                }
            });
 
        }
 
    }
}