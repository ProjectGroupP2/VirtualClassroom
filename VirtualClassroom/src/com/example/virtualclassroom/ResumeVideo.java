package com.example.virtualclassroom;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.virtualclassroomproject.R;

public class ResumeVideo extends Activity{
	public static final String STUDENT_PREFERENCE="student";
	public static final String SUBJECT_PREFERENCE="subject";
	public static final String VIDEO_PREFERENCE="video";
	public static final String VIDEO_PREFERENCE1="sharedValue";
	JSONParser jsonParser = new JSONParser();
		ProgressDialog pDialog;
		VideoView videoview;
		DisplayMetrics dm;
		Button pause,play;
		String serverLocation="http://10.0.2.2";
		String pathSeperator="/";
		String standard=null;
		String username=null;
		String branch=null;
		String subject= null;
		String video=null;
		String position1=null;
		Integer time;
		int position;
		//String studentname=null;
		String location="VirtualClassroom";
		//String uriPath="http://10.0.2.2/VirtualClassroom/SE/CSE/AT/k1.mp4";
		String uriPath=null;
		String folder="uploads";
		EditText comments;
		TextView displaycomment; 
		Integer finalPosition;
		
		 //private static String url_bookmark_video = "http://192.168.1.20/VirtualClassroom/bookmark_video.php";
		 private static String url_resume_video = "http://10.0.2.2/VirtualClassroom/resume_video.php";

		 //private static String url_add_comments = "http://10.0.2.2/virtualClassroom/add_comment.php";
		 
		 private static final String TAG_SUCCESS = "success";
		 private static final String TAG_POSITION = "position";
		 private static final String TAG_POSITION1 = "position";
		 private static final String TAG_POSITIONlIST = "position";
				
		 JSONArray positions = null;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.resume_video);
			videoview = (VideoView) findViewById(R.id.VideoView1);
			
			SharedPreferences setting = getSharedPreferences(STUDENT_PREFERENCE, 0);
		    standard= setting.getString("standard", "Wrong");
		    branch= setting.getString("branch", "Wrong");
		   // studentname= setting.getString("studentname", "Wrong");
		    
		    username= setting.getString("username", "Wrong");
		    
			SharedPreferences setting1 = getSharedPreferences(SUBJECT_PREFERENCE, 0);
		    subject= setting1.getString("subject", "Wrong");
		     
		    SharedPreferences setting2 = getSharedPreferences(VIDEO_PREFERENCE, 0);
		    video= setting2.getString("video", "Wrong");
		    
		   // new  ResumeVideoLengh().execute();
			
		  uriPath=serverLocation+pathSeperator+location+pathSeperator+standard+pathSeperator+branch+pathSeperator+subject+pathSeperator+folder+pathSeperator+video;
		  
		  
		  new  GetResumeVideo().execute();
		}
		
		 /**
	     * Background Async Task to Create new Administrator
	     * */
	   

		class GetResumeVideo extends AsyncTask<String, String, String> {
		    
	    	@Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(ResumeVideo.this);
	            pDialog.setMessage("Resuming video. Please wait...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(false);
	            pDialog.show();
	        }
	          
	        protected String doInBackground(String... args) {
	        	
	             List<NameValuePair> params = new ArrayList<NameValuePair>();
	             params.add(new BasicNameValuePair("username", username));
	             params.add(new BasicNameValuePair("videoname", video));
	             
				JSONObject json = jsonParser.makeHttpRequest(url_resume_video, "GET", params);
	               
	             Log.d("Create Response", json.toString());
	             try {
	                 int success = json.getInt(TAG_SUCCESS);
	  
	                 if (success == 1) {
	                	 positions = json.getJSONArray(TAG_POSITIONlIST);
	                	 
	                	 position=json.getInt(TAG_POSITION1);
	                	 
//	                	 runOnUiThread(new Runnable() {
//	 	 						public void run() {
//	                	finalPosition= Integer.getInteger(position).intValue();
//	 	 						}
//	                	 });		
//	                	 
	                	 SharedPreferences setting1 = getSharedPreferences(VIDEO_PREFERENCE1, 0);
	                	 SharedPreferences.Editor editor= setting1.edit();
	                	  editor.putInt("position", position);
	                	  editor.commit();

//	                	 runOnUiThread(new Runnable() {
//	                	 	 						public void run() {
	                	 	 					   	   Intent i = new Intent(getApplicationContext(),ResumeVideo1.class);     	 
	                	 	 					   	startActivity(i);
				//}
	                	 	 					//});
	                	 	                      

	                 } 
	                 else {
	                	 runOnUiThread(new Runnable() {
	 						public void run() {
	 							Toast.makeText(getApplicationContext(),
	 									"Error in resuming video",
	 									Toast.LENGTH_LONG).show();

	 						}
	 					});
	                      
	                 }
	             } 
	             
	             catch (JSONException e) {
	                 e.printStackTrace();
	             }
	             
	             //}
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
