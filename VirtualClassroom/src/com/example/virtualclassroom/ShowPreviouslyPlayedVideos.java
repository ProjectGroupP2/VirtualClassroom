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
import android.content.SharedPreferences;
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

public class ShowPreviouslyPlayedVideos extends ListActivity {
	public static final String STUDENT_PREFERENCE="student";
	public static final String VIDEO_PREFERENCE="video";
	private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> videoList;
    private static String url_video_list = "http://10.0.2.2/VirtualClassroom/get_bookmarkedvideo_list.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_VIDEOLIST = "videoname";
    private static final String TAG_VIDEO = "videoname";
    //private static final String TAG_POSITION = "position";
   
    JSONArray videos = null;
//    JSONArray position = null;
    String username = null;
    String video= null;
    //int position;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        SharedPreferences setting = getSharedPreferences(STUDENT_PREFERENCE, 0);
        username= setting.getString("username", "Wrong");
        videoList = new ArrayList<HashMap<String, String>>();
        new VideoList().execute();
        
        ListView lv = getListView();
        
        lv.setOnItemClickListener(new OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
        	   video = ((TextView) view.findViewById(R.id.video_name)).getText().toString();
        	   
        	   Intent i = new Intent(getApplicationContext(),ResumeVideo.class);
        	  
        	   SharedPreferences settings= getSharedPreferences(VIDEO_PREFERENCE, 0);
	           SharedPreferences.Editor editor= settings.edit();
	           editor.putString("video", video);
               editor.commit();
			   startActivity(i);
			   
				 
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

class VideoList extends AsyncTask<String, String, String>
{

	   /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(ShowPreviouslyPlayedVideos.this);
        pDialog.setMessage("Loading video List. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    protected String doInBackground(String... args) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        JSONObject json = jParser.makeHttpRequest(url_video_list, "GET", params);
        Log.d("Subjects : ", json.toString());
         try {               
            int success = json.getInt(TAG_SUCCESS);
             if (success == 1) {
            	 videos = json.getJSONArray(TAG_VIDEOLIST);
  //               position=json.getJSONArray(TAG_POSITION);
                 
                for (int i = 0; i < videos.length(); i++) {
                    JSONObject c = videos.getJSONObject(i);
                    
                    String name = c.getString(TAG_VIDEO);
                     HashMap<String, String> map = new HashMap<String, String>();
                     
                     map.put(TAG_VIDEO, name);

                    videoList.add(map);
                }
            } 
             else {
             	runOnUiThread(new Runnable() {
						public void run() {

							Toast.makeText(getApplicationContext(),
									"No subject found",
									Toast.LENGTH_LONG).show();
						}

					});
            }
             
             videoList= new ArrayList<HashMap<String,String>>(videoList);
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
            			ShowPreviouslyPlayedVideos.this, videoList,
                         R.layout.list_of_videos, new String[] { 
            					TAG_VIDEO},
                         
                         new int[] { R.id.video_name });
               
                 setListAdapter(adapter);
            }
        });

    }

}

}
