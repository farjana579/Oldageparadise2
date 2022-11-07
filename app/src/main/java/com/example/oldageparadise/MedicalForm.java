package com.example.oldageparadise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.assist.AssistStructure;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Batch;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale;

public class MedicalForm extends AppCompatActivity {

    EditText startDateMedical;
    EditText endDateMedical;
    Calendar calendar;
    EditText Name;
    EditText Email;
    EditText Adress;
    EditText contact_no;
    EditText Age;
    EditText Weight;
    Button subMitButton;
    String Bathing, Feeding, nursing, Assistance,Physiotherahy;
    CheckBox bathing,feeding, Nursing,assistance,physiotherapy;
    DatabaseReference databaseReference;
    DatabaseReference reference;
    String get_email_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_form);

        databaseReference = FirebaseDatabase.getInstance().getReference("medicalform");
        reference = FirebaseDatabase.getInstance().getReference().child("Profile");

        startDateMedical = findViewById(R.id.editTextStartDateMedicalForm);
        endDateMedical = findViewById(R.id.editTextEndDateMedicalForm);
        calendar = Calendar.getInstance();
        Name = findViewById(R.id.editTextTextPersonNameMedicalForm);
        Email = findViewById(R.id.editTextTextEmailAddressMedicalForm);
        contact_no = findViewById(R.id.editTextPhoneMedicalForm);
        Adress = findViewById(R.id.editTextTextPostalAddress);
        Weight = findViewById(R.id.editTextNumber2);
        Age = findViewById(R.id.editTextNumberMedicalForm);
        subMitButton = findViewById(R.id.button);
         bathing = findViewById(R.id.checkBox1);
         feeding =  findViewById(R.id.checkBox2);
        Nursing = findViewById(R.id.checkBox3);
         physiotherapy = findViewById(R.id.checkBox4);
         assistance = findViewById(R.id.checkBox5);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, dayOfMonth);
                updateCalender();
            }

            public void updateCalender() {
                String format = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                startDateMedical.setText(sdf.format(calendar.getTime()));
            }
        };
        startDateMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MedicalForm.this, date, calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH)).show();
            }
        });

        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, dayOfMonth);
                updateCalender();
            }

            public void updateCalender() {
                String format = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                endDateMedical.setText(sdf.format(calendar.getTime()));
            }
        };

        endDateMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MedicalForm.this, date1, calendar.get(calendar.YEAR),
                        calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH)).show();
            }
        });

        subMitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    public void saveData() {
        //user_email fetch from profile code

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                for(DataSnapshot snapshot : datasnapshot.getChildren()){
                    String ma = snapshot.child("email").getValue().toString();
                    get_email_profile = ma;



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //end_user_email code from profile code
        String name = Name.getText().toString().trim();
        Email.setText(get_email_profile);
        String email = Email.getText().toString().trim();
        String contact = contact_no.getText().toString().trim();
        String address = Adress.getText().toString().trim();
        String age = Age.getText().toString().trim();
        String weight = Weight.getText().toString().trim();
        String startDate = startDateMedical.getText().toString().trim();
        String endDate = endDateMedical.getText().toString().trim();
        if(bathing.isChecked()){
            Bathing = bathing.getText().toString();
        }
        if(feeding.isChecked()){
            Feeding= feeding.getText().toString();
        }
        if(Nursing.isChecked()){
            nursing= Nursing.getText().toString();
        }
        if(assistance.isChecked()){
            Assistance= assistance.getText().toString();
        }
        if(physiotherapy.isChecked()){
            Physiotherahy = physiotherapy.getText().toString();
        }

        String key = databaseReference.push().getKey();
        MedicalFormModelClass medicalForm = new MedicalFormModelClass(name, email, contact, address, age,
                weight, startDate, endDate,Bathing,Feeding,nursing,Assistance,Physiotherahy);
        databaseReference.child(key).setValue(medicalForm);
        Toast.makeText(getApplicationContext(), "info added", Toast.LENGTH_SHORT).show();
    }
}