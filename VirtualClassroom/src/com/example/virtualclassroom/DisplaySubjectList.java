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

public class DisplaySubjectList  extends ListActivity{
	public static final String SUBJECT_PREFERENCE="subject";
	public static final String STUDENT_PREFERENCE="student";
	private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> subjectList;
    private static String url_subject_list = "http://10.0.2.2/VirtualClassroom/get_subject_list.php";
   
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_SUBJECTLIST = "subject";
    private static final String TAG_SUBJECT = "subject";
    
    JSONArray subjects = null;
    String subject = null;
    String standard= null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        subjectList = new ArrayList<HashMap<String, String>>();
        SharedPreferences setting = getSharedPreferences(STUDENT_PREFERENCE, 0);
        standard= setting.getString("standard", "Wrong");
        new SubjectList().execute();
        ListView lv = getListView();
        
        lv.setOnItemClickListener(new OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view,
                   int position, long id) {
        	   subject = ((TextView) view.findViewById(R.id.subjectname)).getText()
						.toString();

        	   Intent i = new Intent(getApplicationContext(),DisplayVideoList.class);
				
				SharedPreferences settings= getSharedPreferences(SUBJECT_PREFERENCE, 0);
	            SharedPreferences.Editor editor= settings.edit();
	            editor.putString("subject", subject);
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
   
   class SubjectList extends AsyncTask<String, String, String> {
   	 
       /**
        * Before starting background thread Show Progress Dialog
        * */
       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           pDialog = new ProgressDialog(DisplaySubjectList.this);
           pDialog.setMessage("Loading Subject List. Please wait...");
           pDialog.setIndeterminate(false);
           pDialog.setCancelable(false);
           pDialog.show();
       }

       protected String doInBackground(String... args) {
           List<NameValuePair> params = new ArrayList<NameValuePair>();
           params.add(new BasicNameValuePair("standard", standard));
           JSONObject json = jParser.makeHttpRequest(url_subject_list, "GET", params);
           Log.d("Subjects : ", json.toString());
            try {               
               int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                  subjects = json.getJSONArray(TAG_SUBJECTLIST);
          
                   for (int i = 0; i < subjects.length(); i++) {
                       JSONObject c = subjects.getJSONObject(i);
                       
                       String name = c.getString(TAG_SUBJECT);
                        HashMap<String, String> map = new HashMap<String, String>();
                        
                        map.put(TAG_SUBJECT, name);

                       subjectList.add(map);
                   }
               } 
                else {
                	runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(getApplicationContext(),	"No subject found",Toast.LENGTH_LONG).show();
						}

					});
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
               			DisplaySubjectList.this, subjectList,
                            R.layout.list_of_subjects, new String[] { 
               					TAG_SUBJECT},
                            
                            new int[] { R.id.subjectname });
                            setListAdapter(adapter);
               }
           });

       }

   }

}