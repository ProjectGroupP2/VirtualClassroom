package com.example.virtualclassroom;

import com.example.virtualclassroomproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StaffLoginSuccessful extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.staff_login_successful);
		
		Button btnStaffChangePassword = (Button)findViewById(R.id.btnStaffChangePassword);
		Button btnStaffUploadVideo = (Button)findViewById(R.id.btnStaffUploadVideo);
		Button btnstaffRecordVideo=(Button)findViewById(R.id.btnStaffRecordVideo);
		//Button btnUploadFile = (Button)findViewById(R.id.uploadFileButton);
		btnStaffChangePassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),StaffChangePassword.class);
			    startActivity(i);
				
			}
		});
		
		btnStaffUploadVideo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),UploadVideo.class);
				startActivity(i);
				
			}
		});
		
		btnstaffRecordVideo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),RecordVideo.class);
				startActivity(i);
				
			}
		});
		
		

	}

}
