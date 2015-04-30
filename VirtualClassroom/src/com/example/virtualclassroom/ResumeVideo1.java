package com.example.virtualclassroom;

import org.json.JSONArray;

import com.example.virtualclassroomproject.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class ResumeVideo1 extends Activity{
	public static final String STUDENT_PREFERENCE="student";
	public static final String SUBJECT_PREFERENCE="subject";
	public static final String VIDEO_PREFERENCE="video";
	public static final String VIDEO_PREFERENCE1="sharedValue";
	JSONParser jsonParser = new JSONParser();
		ProgressDialog pDialog;
		VideoView videoview;
		DisplayMetrics dm;
		Button pause,play;
		String serverLocation="http:/10.0.2.2";
		String pathSeperator="/";
		String standard=null;
		String username=null;
		String branch=null;
		String subject= null;
		String video=null;
		String position1=null;
		Integer time;
		int position;
		Integer finalPosition;
		//String studentname=null;
		String location="VirtualClassroom";
		//String uriPath="http://10.0.2.2/VirtualClassroom/SE/CSE/AT/k1.mp4";
		String uriPath=null;
		String folder="uploads";
		EditText comments;
		TextView displaycomment; 
		
		 //private static String url_bookmark_video = "http://192.168.1.20/VirtualClassroom/bookmark_video.php";
		 //private static String url_resume_video = "http://192.168.1.20/VirtualClassroom/resume_video.php";

		 //private static String url_add_comments = "http://10.0.2.2/virtualClassroom/add_comment.php";
		 
//		 private static final String TAG_SUCCESS = "success";
//		 private static final String TAG_POSITION = "position";
//		 private static final String TAG_POSITIONlIST = "position";
	
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
		    
		    SharedPreferences setting3 = getSharedPreferences(VIDEO_PREFERENCE1, 0);
		    position= setting3.getInt("position",1);
		   // finalPosition= Integer.getInteger(position).intValue();
		   // position=Integer.parseInt(position1);
		   // new  ResumeVideoLengh().execute();
			
		  uriPath=serverLocation+pathSeperator+location+pathSeperator+standard+pathSeperator+branch+pathSeperator+subject+pathSeperator+folder+pathSeperator+video;
		  
		  		  
			pDialog = new ProgressDialog(ResumeVideo1.this);
			pDialog.setTitle("Video Is Streaming.....");
			pDialog.setMessage("Buffering...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

			try {
				
				MediaController mediacontroller = new MediaController(ResumeVideo1.this);
				mediacontroller.setAnchorView(videoview);
				videoview = (VideoView) findViewById(R.id.VideoView1);
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
					
					videoview.seekTo(position);
										
				}
			});


		}	   
	}
