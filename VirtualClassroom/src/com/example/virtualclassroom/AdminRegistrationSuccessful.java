package com.example.virtualclassroom;

import com.example.virtualclassroomproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminRegistrationSuccessful extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_next_page);
		
		Button	uploadVideo=(Button)findViewById(R.id.btnAdminUploadVideo);
		Button	authorizeStudent=(Button)findViewById(R.id.btnAuthorizeStudent);
		Button	authorizeStaff=(Button)findViewById(R.id.btnAuthorizeStaff);
		Button unauthorizeStaff=(Button)findViewById(R.id.btnUnapprovedStaff);
		Button	unauthorizeStudent=(Button)findViewById(R.id.btnUnapprovedStudent);
		Button	adminChangePassword=(Button)findViewById(R.id.btnAdminChangePassword);
		Button	adminDeleteVideo=(Button)findViewById(R.id.btnAdminDeleteVideo);
		Button	adminDeleteStudentAccount=(Button)findViewById(R.id.btnAdminDeleteStudentAccount);
		Button	adminDeleteStaffAccount=(Button)findViewById(R.id.btnAdminDeleteStaffAccount);
		
		uploadVideo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),UploadVideo.class);
				startActivity(i);
				
			}
		});
		adminChangePassword.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),AdministratorChangePassword.class);
				startActivity(i);
				
			}
		});
		
		adminDeleteVideo.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),DeleteVideos.class);
		startActivity(i);
		
	}
		});
		
		
		authorizeStudent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),AuthorizeStudentList.class);
				startActivity(i);
				
			}
		});
		
		authorizeStaff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),AuthorizeStaffList.class);
				startActivity(i);
				
			}
		});
		
		unauthorizeStaff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),UnauthorizeStaffList.class);
				startActivity(i);
				
			}
		});

		unauthorizeStudent.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),UnauthorizeStudentList.class);
		startActivity(i);
		
	}
});
adminDeleteStudentAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),DeleteAccount.class);
				startActivity(i);
				
			}
				});

adminDeleteStaffAccount.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(),DeleteStaffAccount.class);
		startActivity(i);
		
	}
		});

	}

}
