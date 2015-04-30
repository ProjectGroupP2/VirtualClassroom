package com.example.virtualclassroom;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualclassroomproject.R;

public class UploadVideo extends Activity implements OnClickListener{
	public static final String VIDEO_PREFERENCE="video";
	public static final String VIDEO_PREFERENCE1="video1";
	String fileName=null;
	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private TextView messageText;
	private Button uploadButton, btnselectvideo;
	private ImageView imageview;
	private int serverResponseCode = 0;
	private ProgressDialog dialog = null;
	//private Spinner spinner1, spinner2;
	private String upLoadServerUri = null;
	private String filepath = null;
	int FLAG = 0;
	//private ListView mainListView ;
	//private ArrayAdapter<String> listAdapter ;
	private static final String TAG_SUCCESS = "success";
	//uploading details
	String serverLocation="http://10.0.2.2/";
	String pathSeperator="/";
	String folder="VirtualClassroom";
	private static String url_insert_videodetails = "http://10.0.2.2/virtualClassroom/create_video_entry.php";
	EditText txtstandard;
	EditText txtsubject,txtVideoName;
	String script="upload_to_server.php";
	//String finalpath=null;
	String location="VirtualClassroom";
	String branch="CSE";
//String videoName;
	//String standard=null;
	//String subject=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.uploadvideo);
		
		txtstandard=(EditText)findViewById(R.id.videouploadStandard);
		txtsubject=(EditText)findViewById(R.id.videouploadSubject);
		txtVideoName=(EditText)findViewById(R.id.editVideoName);
		//standard=txtstandard.getText().toString();
		//subject=txtsubject.getText().toString();
		uploadButton = (Button) findViewById(R.id.uploadButton);
		messageText = (TextView) findViewById(R.id.messageText);
		
		btnselectvideo = (Button) findViewById(R.id.button_selectvideo);
		imageview = (ImageView) findViewById(R.id.imageView_pic);

		btnselectvideo.setOnClickListener(this);
		uploadButton.setOnClickListener(this);
		//upLoadServerUri = "http://10.0.2.2/VirtualClassroom/SE/CSE/AT/upload_to_server.php";
				
		
		 
	}

	@Override
	public void onClick(View arg0) {
		if (arg0 == btnselectvideo) {
			FLAG = 1;
			Intent intent = new Intent();
			intent.setType("video/*");
			
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(
					Intent.createChooser(intent, "Complete action using"), 1);

			if (btnselectvideo.isPressed()) {
				Drawable bitmap = getResources().getDrawable(R.drawable.video);
				imageview.setImageDrawable(bitmap);
			}

		} else if (arg0 == uploadButton) {

			if (FLAG == 0) {
				Toast.makeText(UploadVideo.this,"Please select video !!!",Toast.LENGTH_LONG).show();
			} else {
				
				if (filepath != null) {
					dialog = ProgressDialog.show(UploadVideo.this, "","Uploading file...", true);
					messageText.setText("uploading started.....");
					new Thread(new Runnable() {
						public void run() {
							
							uploadFile(filepath);
												
						}
					}).start();
				} else {
					Toast.makeText(UploadVideo.this, "Please try again !!!",
							Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1 && resultCode == RESULT_OK) {
			Uri selectedImageUri = data.getData();
			filepath = getPath(selectedImageUri);
			Bitmap bitmap = BitmapFactory.decodeFile(filepath);
			String videoName=txtVideoName.getText().toString();
			
			 SharedPreferences settings= getSharedPreferences(VIDEO_PREFERENCE1, 0);
             SharedPreferences.Editor editor= settings.edit();
             editor.putString("videoName", videoName);
             
             editor.commit();
			
			imageview.setImageBitmap(bitmap);

			messageText.setText("Uploading file Name:" + videoName);

		}
	}

	class uploadFile extends AsyncTask<String, String, String>
	{

		 @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            pDialog = new ProgressDialog(UploadVideo.this);
	            pDialog.setMessage("Creating ..");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }
	 
		 
		 protected String doInBackground(String... args) {
	            String videouploadstandard = txtstandard.getText().toString();
	            String videouploadsubject = txtsubject.getText().toString();
	            SharedPreferences setting2 = getSharedPreferences(VIDEO_PREFERENCE1, 0);
			    String video= setting2.getString("videoName", "Wrong");
			    String videoName=txtVideoName.getText().toString();  
	           
             // Building Parameters
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("standard", videouploadstandard));
	            params.add(new BasicNameValuePair("subject", videouploadsubject));
	            params.add(new BasicNameValuePair("videoName", videoName));
	            
	            // getting JSON Object
	            
	            JSONObject json = jsonParser.makeHttpRequest(url_insert_videodetails, "POST", params);
	 
	            // check log cat fro response
	            Log.d("Create Response", json.toString());
	 
	            // check for success tag
	            try {
	                int success = json.getInt(TAG_SUCCESS);
	 
	                if (success == 1) {
	                	runOnUiThread(new Runnable() {
	  						public void run() {
	  							Toast.makeText(getApplicationContext(),"video uploading is done", Toast.LENGTH_LONG).show();

	  						}
	  					});
	                    
	                } else {
	                	runOnUiThread(new Runnable() {
	  						public void run() {
	  							Toast.makeText(getApplicationContext(),	"Error to upload video", Toast.LENGTH_LONG).show();

	  						}
	  					});
	                    
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
	
	@SuppressWarnings("deprecation")
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public int uploadFile(final String sourceFileUri) {

		//String fileName = sourceFileUri;
		
        String videoName=txtVideoName.getText().toString();  
       	fileName=videoName;//this is added new
		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		
		int maxBufferSize = 1 * 1024;
		File sourceFile = new File(sourceFileUri);

		if (!sourceFile.isFile()) {

			dialog.dismiss();

			Log.e("uploadFile", "Source File not exist :" + filepath);

			runOnUiThread(new Runnable() {
				public void run() {
					messageText.setText("Source File not exist :" + filepath);
				}
			});

			return 0;

		} else {
			try {
				FileInputStream fileInputStream = new FileInputStream(
						sourceFile);
				
				String standard=txtstandard.getText().toString();
				String subject=txtsubject.getText().toString();
				
				
				 SharedPreferences settings= getSharedPreferences(VIDEO_PREFERENCE, 0);
                 SharedPreferences.Editor editor= settings.edit();
                 editor.putString("standard", standard);
                 editor.putString("subject", subject);
                 editor.commit();
				
				upLoadServerUri=serverLocation+location+pathSeperator+standard+pathSeperator+branch+pathSeperator+subject+pathSeperator+script;
				URL url = new URL(upLoadServerUri);
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoInput(true); // Allow Inputs
				conn.setDoOutput(true); // Allow Outputs
				conn.setUseCaches(false); // Don't use a Cached Copy
				conn.setChunkedStreamingMode(1024);//i have added extra
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Connection", "Keep-Alive");
				conn.setRequestProperty("ENCTYPE", "multipart/form-data");
				conn.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + boundary);
				conn.setRequestProperty("uploaded_file", fileName);

				dos = new DataOutputStream(conn.getOutputStream());

				dos.writeBytes(twoHyphens + boundary + lineEnd);
				dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
						+ fileName + "\"" + lineEnd);

				dos.writeBytes(lineEnd);
				bytesAvailable = fileInputStream.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);
				while (bytesRead > 0) {

					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				}

				dos.writeBytes(lineEnd);
				dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
				serverResponseCode = conn.getResponseCode();
				String serverResponseMessage = conn.getResponseMessage();

				Log.i("uploadFile", "HTTP Response is : "+ serverResponseMessage + ": " + serverResponseCode);

				if (serverResponseCode == 200) {

					runOnUiThread(new Runnable() {
						public void run() {
							
							new uploadFile().execute();
						//	String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
							//		+ " c:/wamp/www/echo/uploads";
							//messageText.setText(msg);
							Toast.makeText(UploadVideo.this,"File Upload Complete.", Toast.LENGTH_SHORT).show();
//							Intent i = new Intent(getApplicationContext(),FinalVideoUpload.class);
//							startActivity(i);
							finish();
						}
					});
				}

				fileInputStream.close();
				dos.flush();
				dos.close();

			} catch (MalformedURLException ex) {

				dialog.dismiss();
				ex.printStackTrace();

				runOnUiThread(new Runnable() {
					public void run() {
						messageText
								.setText("MalformedURLException Exception : check script url.");
						Toast.makeText(UploadVideo.this,
								"MalformedURLException", Toast.LENGTH_SHORT)
								.show();
					}
				});

				Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
			} catch (Exception e) {

				dialog.dismiss();
				e.printStackTrace();

				runOnUiThread(new Runnable() {
					public void run() {
						messageText.setText("Got Exception : see logcat ");
						Toast.makeText(UploadVideo.this,
								"Got Exception : see logcat ",
								Toast.LENGTH_SHORT).show();
					}
				});
				Log.e("Upload file to server Exception",
						"Exception : " + e.getMessage(), e);
			}
			dialog.dismiss();
			return serverResponseCode;
		}
	}

}