package com.example.virtualclassroom;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualclassroomproject.R;

public class DeleteVideos extends Activity {
	private ProgressDialog pDialog;
	EditText standard;
	EditText subject;
	 EditText videoName;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
	private static String url_delete_video = "http://10.0.2.2/VirtualClassroom/delete_video.php";
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.delete_video);
	        
	       standard = (EditText) findViewById(R.id.deleteVideostandard);
	        subject = (EditText) findViewById(R.id.deleteVideoSubject);
	         videoName = (EditText) findViewById(R.id.deleteVideoName);
	   
	   Button     deleteVideo = (Button) findViewById(R.id.btnDeleteVideo);
	   
	   deleteVideo.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			new DeletVideo().execute();
			
		}
	});
	   
	   
	 }
	 
	 class DeletVideo extends AsyncTask<String, String, String> 
	 {
		  @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(DeleteVideos.this);
	            pDialog.setMessage("Deleting Video..");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }
	 

		@Override
		protected String doInBackground(String... arg0) {
			String Standard = standard.getText().toString();
			String Subject=subject.getText().toString();
			String VideoName=videoName.getText().toString();
			
			 List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("standard", Standard));
	            params.add(new BasicNameValuePair("subject", Subject));
	            params.add(new BasicNameValuePair("videoName", VideoName));
	            JSONObject json = jsonParser.makeHttpRequest(url_delete_video, "POST", params);
	            
	            // check log cat fro response
	            Log.d("Create Response", json.toString());
	            
	            try {
	                int success = json.getInt(TAG_SUCCESS);
	 
	                if (success == 1) {
	                	runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(getApplicationContext(),
										"Video has deleted successfully", Toast.LENGTH_LONG).show();
								
								
								//finish();

							}
						});
	                    
	                   // finish();
	                } else {
	                	runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(getApplicationContext(),
										"Error in deleting video", Toast.LENGTH_LONG).show();
								 
								 
								finish();

							}
						});
	                    // failed to create product
	                }
	            }
	            
	            catch (JSONException e) {
	                e.printStackTrace();
	            }
			return null;
		}
		protected void onPostExecute(String file_url) {
	          pDialog.dismiss();
	         
	        }
		 
	 }
	

}
