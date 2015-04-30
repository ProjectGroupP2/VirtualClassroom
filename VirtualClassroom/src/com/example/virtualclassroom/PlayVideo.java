package com.example.virtualclassroom;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.virtualclassroom.AdministratorNewRegistration.CreateNewAdministrator;
import com.example.virtualclassroomproject.R;

public class PlayVideo extends Activity{
	public static final String STUDENT_PREFERENCE="student";
	public static final String SUBJECT_PREFERENCE="subject";
	public static final String VIDEO_PREFERENCE="video";
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
		Integer time;
		//int position;
		//String studentname=null;
		String location="VirtualClassroom";
		//String uriPath="http://10.0.2.2/VirtualClassroom/SE/CSE/AT/k1.mp4";
		String uriPath=null;
		String folder="uploads";
		EditText comments;
		TextView displaycomment; 
		
		 private static String url_bookmark_video = "http://10.0.2.2/VirtualClassroom/bookmark_video.php";
		 //private static String url_add_comments = "http://10.0.2.2/virtualClassroom/add_comment.php";
		 
		 private static final String TAG_SUCCESS = "success";
		 
				
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.playvideo);
			videoview = (VideoView) findViewById(R.id.VideoView);
			
			SharedPreferences setting = getSharedPreferences(STUDENT_PREFERENCE, 0);
		    standard= setting.getString("standard", "Wrong");
		    branch= setting.getString("branch", "Wrong");
		   // studentname= setting.getString("studentname", "Wrong");
		    
		    username= setting.getString("username", "Wrong");
		    
			SharedPreferences setting1 = getSharedPreferences(SUBJECT_PREFERENCE, 0);
		    subject= setting1.getString("subject", "Wrong");
		     
		    SharedPreferences setting2 = getSharedPreferences(VIDEO_PREFERENCE, 0);
		    video= setting2.getString("video", "Wrong");
		    
			
		  uriPath=serverLocation+pathSeperator+location+pathSeperator+standard+pathSeperator+branch+pathSeperator+subject+pathSeperator+folder+pathSeperator+video;

//		  comments = (EditText) findViewById(R.id.Comments);
//	      displaycomment = (TextView) findViewById(R.id.displayComment);
//		  Button btnSubmitComment = (Button) findViewById(R.id.btnSubmitComments);
//		 
//		  btnSubmitComment.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					//int position = videoview.getDuration();
//					//videoview.pause();
//					new AddComment().execute();
//					
//				}
//			});
		  pause=(Button)findViewById(R.id.buttonPause);
		  pause.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//int position = videoview.getDuration();
					// time=videoview.getCurrentPosition().;
					videoview.pause();
					time=videoview.getCurrentPosition();
					new ShowPreviousVideo().execute();
					
				}
			});
		  
			pDialog = new ProgressDialog(PlayVideo.this);
			pDialog.setTitle("Video Is Streaming.....");
			pDialog.setMessage("Buffering...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

			try {
				
				MediaController mediacontroller = new MediaController(PlayVideo.this);
				mediacontroller.setAnchorView(videoview);
				videoview = (VideoView) findViewById(R.id.VideoView);
				dm = new DisplayMetrics();
				this.getWindowManager().getDefaultDisplay().getMetrics(dm);
				int height = dm.heightPixels;
				int width = dm.widthPixels;
				videoview.setMinimumWidth(width);
				videoview.setMinimumHeight(height);
				videoview.setMediaController(mediacontroller);
								
				Uri video = Uri.parse(uriPath);
				videoview.setMediaController(mediacontroller);
				videoview.setVideoURI(video);
			//int position=	videoview.getCurrentPosition();
			    //int duration=videoview.getDuration()
				//videoview.setZOrderOnTop(true);
				
				//videoview.start();
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
				
			}

			videoview.requestFocus();
			
			videoview.setOnPreparedListener(new OnPreparedListener() {
				public void onPrepared(MediaPlayer mp) {
					pDialog.dismiss();
					videoview.start();
										
				}
			});

		}
		
		 /**
	     * Background Async Task to Create new Administrator
	     * */
	    class ShowPreviousVideo extends AsyncTask<String, String, String> {
	 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(PlayVideo.this);
	            pDialog.setMessage("Bookmarking video..");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }
	 
	        /**
	         * Creating Administrator
	         * */
	        protected String doInBackground(String... args) {
	           
	        	//Integer  position1 =videoview.getCurrentPosition();
	        	//String position=position1.toString();
	            // Building Parameters
	        	//String time1= time.ToString();
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("username",username));
	            params.add(new BasicNameValuePair("videoname", video));
	            params.add(new BasicNameValuePair("position", time.toString()));
	           
	            
	            // getting JSON Object
	            
	            JSONObject json = jsonParser.makeHttpRequest(url_bookmark_video, "POST", params);
	 
	            // check log cat fro response
	            Log.d("Create Response", json.toString());
	 
	            // check for success tag
	            try {
	                int success = json.getInt(TAG_SUCCESS);
	 
	                if (success == 1) {
	                    // successfully created Administrator
	                	runOnUiThread(new Runnable() {
	 						public void run() {
	                	Toast.makeText(getApplicationContext(),
								"Bookmark is done Successfully", Toast.LENGTH_LONG).show();
	                    Intent i = new Intent(getApplicationContext(), StudentRegistrationSuccessful.class);
	                    startActivity(i);
	 
	                    // closing this screen
	                    finish();
	 						}
	 						});
	                } else {
	                	
	                    // failed to create product
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
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
	    

		 /**
	     * Background Async Task to Create new Administrator
	     * */
//	    class AddComment extends AsyncTask<String, String, String> {
//	 
//	        /**
//	         * Before starting background thread Show Progress Dialog
//	         * */
//	        @Override
//	        protected void onPreExecute() {
//	            super.onPreExecute();
//	            pDialog = new ProgressDialog(PlayVideo.this);
//	            pDialog.setMessage("adding comment..");
//	            pDialog.setIndeterminate(false);
//	            pDialog.setCancelable(true);
//	            pDialog.show();
//	        }
//	 
//	        /**
//	         * Creating Administrator
//	         * */
//	        protected String doInBackground(String... args) {
//	           
//	        	 String addComment = comments.getText().toString();
//	        	 
//	            // Building Parameters
//	            List<NameValuePair> params = new ArrayList<NameValuePair>();
//	            params.add(new BasicNameValuePair("comments",addComment));
//	            params.add(new BasicNameValuePair("videoName", video));
//	            params.add(new BasicNameValuePair("username", username));
//	           
//	            
//	            // getting JSON Object
//	            
//	            JSONObject json = jsonParser.makeHttpRequest(url_add_comments, "POST", params);
//	 
//	            // check log cat fro response
//	            Log.d("Create Response", json.toString());
//	 
//	            // check for success tag
//	            try {
//	                int success = json.getInt(TAG_SUCCESS);
//	 
//	                if (success == 1) {
//	                    // successfully created Administrator
//	                	runOnUiThread(new Runnable() {
//	                		public void run() {
//	                	Toast.makeText(getApplicationContext(),
//								"Add comment is done Successfully", Toast.LENGTH_LONG).show();
//	                    Intent i = new Intent(getApplicationContext(), PlayVideo.class);
//	                    startActivity(i);
//	                	
//	                    // closing this screen
//	                    finish();
//	                		}
//	                	});
//	                	
//	                } else {
//	                	runOnUiThread(new Runnable() {
//	 						public void run() {
//	 							Toast.makeText(getApplicationContext(),
//	 									"Error in adding comment...",
//	 									Toast.LENGTH_LONG).show();
//
//	 						}
//	 					});
//	                    // failed to create product
//	                }
//	            } catch (JSONException e) {
//	                e.printStackTrace();
//	            }
//	 
//	            return null;
//	        }
//	 
//	        /**
//	         * After completing background task Dismiss the progress dialog
//	         * **/
//	        protected void onPostExecute(String file_url) {
//	            // dismiss the dialog once done
//	            pDialog.dismiss();
//	        }
//	 
//	    }


	}
