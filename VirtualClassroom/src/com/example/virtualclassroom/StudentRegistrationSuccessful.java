package com.example.virtualclassroom;

import com.example.virtualclassroomproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentRegistrationSuccessful extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_next_student_screen);
		
		Button subjectList= (Button)findViewById(R.id.btnAllSubjectsList);
		Button videoList= (Button)findViewById(R.id.btnPreviouslyPlayedVideos);
		
		subjectList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),DisplaySubjectList.class);
				startActivity(i);
				
			}
		});
		
		videoList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),DisplayBookmarkSubjectList.class);
				startActivity(i);
				
			}
		});
		
	}

}
