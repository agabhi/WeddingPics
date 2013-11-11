package com.weddingpics;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weddingpics.model.HttpRequestObject;
import com.weddingpics.model.ServerResponseObject;
import com.weddingpics.service.AlbumService;
import com.weddingpics.util.UserTypeEnum;

public class WeddingSetupTwoActivity extends Activity {
	
	final Context context = this;
	private EditText weddingDateText;
	private Calendar weddingDate;
	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wedding_setup_two);
		addListenerOnDateText();
		
		Intent intentData = getIntent();
		
		final String email = (String) intentData.getSerializableExtra("email");
		final Boolean isNewUser = (Boolean) intentData.getSerializableExtra("isNewUser");
		final String fullName = (String) intentData.getSerializableExtra("fullName");
		final String password = (String) intentData.getSerializableExtra("password");
		
		
		
		RelativeLayout nextbtn = (RelativeLayout) findViewById(R.id.nextbtn);
		// if button is clicked, close the custom dialog
		nextbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView firstNameInput    =(TextView)findViewById(R.id.firstNameInput);
				TextView fianceNameInput    =(TextView)findViewById(R.id.fianceNameInput);
				TextView weddingDateInput  =(TextView)findViewById(R.id.weddingDateInput);
				TextView weddingIdInput  =(TextView)findViewById(R.id.weddingIdInput);
				String firstUser = firstNameInput.getText().toString();
				String secondUser = fianceNameInput.getText().toString();
				String weddingId = weddingIdInput.getText().toString();
				String weddingdate = weddingDateInput.getText().toString().replaceAll("Wedding Date: ", "");
				if (!firstUser.isEmpty() && !secondUser.isEmpty() && !weddingdate.isEmpty() && !weddingId.isEmpty()) {
					HttpRequestObject reponse;
					try {
						reponse = AlbumService.getInstance().createAlbum(email,fullName,password,firstUser,secondUser,weddingId,weddingdate,UserTypeEnum.GROOM.getValue(),UserTypeEnum.BRIDE.getValue(),isNewUser);
						Gson gson = new Gson();
						ServerResponseObject serverResponseObject = gson.fromJson(reponse.getResponse(), ServerResponseObject.class);
						if (serverResponseObject != null && serverResponseObject.getIsSuccess()) {
							if (serverResponseObject.getAlbum() != null) {
								Intent intent = new Intent(context, WeddingSetupThreeActivity.class);
								intent.putExtra("weddingId",serverResponseObject.getAlbum().getWeddingId());
								startActivity(intent);
							}
						} else {
							Toast.makeText(WeddingSetupTwoActivity.this,"Album cerate some problem Try again! : "+serverResponseObject.getErrorMessage(), Toast.LENGTH_LONG).show();	
						}
					} catch (Exception e) {
						Log.e("WeddingSetupTwoActivity", "Error occured Album creation.", e);
						Toast.makeText(WeddingSetupTwoActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
					}
				} else if (firstNameInput.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupTwoActivity.this,"Please entre first name field. " , Toast.LENGTH_LONG).show();
				}  else if (fianceNameInput.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupTwoActivity.this,"Please entre fiance name field." , Toast.LENGTH_LONG).show();
				} else if (weddingDateInput.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupTwoActivity.this,"Please entre wedding date field. " , Toast.LENGTH_LONG).show();
				}  else if (weddingIdInput.getText().toString().isEmpty()) {
					Toast.makeText(WeddingSetupTwoActivity.this,"Please entre wedding id field." , Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}

	public void addListenerOnDateText() {
		weddingDateText = (EditText) findViewById(R.id.weddingDateInput);
		weddingDateText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

				// when dialog box is closed, below method will be called.
				public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.YEAR, selectedYear);
					cal.set(Calendar.MONTH, selectedMonth);
					cal.set(Calendar.DAY_OF_MONTH, selectedDay);
					weddingDate = cal;
					SimpleDateFormat mmDDyyyy = new SimpleDateFormat("MM/dd/yyyy");
					weddingDateText.setText("Wedding Date: " + mmDDyyyy.format(cal.getTime()));

				}
			};
			Calendar cal = null;
			if (weddingDate == null) {
				cal = Calendar.getInstance();
			} else {
				cal = weddingDate;
			}
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		}
		return null;
	}

}
