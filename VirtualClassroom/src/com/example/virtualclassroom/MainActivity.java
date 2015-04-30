package com.example.virtualclassroom;

import com.example.virtualclassroomproject.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnStudentLogin, btnStaffLogin, btnAdminLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnStudentLogin = (Button) findViewById(R.id.btnStudentLogin);
		btnStaffLogin = (Button) findViewById(R.id.btnStaffLogin);
		btnAdminLogin = (Button) findViewById(R.id.btnAdminLogin);
		
/****************************************************************************************************************/
		btnStudentLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),StudentLogin.class);
				startActivity(i);

			}
		});

/****************************************************************************************************************/
		btnStaffLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), StaffLogin.class);
				startActivity(i);

			}
		});

/****************************************************************************************************************/
		btnAdminLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), AdministratorLogin.class);
				startActivity(i);

			}
		});
	}


}
