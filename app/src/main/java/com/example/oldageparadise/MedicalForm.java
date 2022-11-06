package com.example.oldageparadise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_form);

        databaseReference = FirebaseDatabase.getInstance().getReference("medicalform");

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
        CheckBox bathing = findViewById(R.id.checkBox1);
        CheckBox feeding = findViewById(R.id.checkBox2);
        CheckBox Nursing = findViewById(R.id.checkBox3);
        CheckBox physiotherapy = findViewById(R.id.checkBox4);
        CheckBox assistance = findViewById(R.id.checkBox5);
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
        String name = Name.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String contact = contact_no.getText().toString().trim();
        String address = Adress.getText().toString().trim();
        String age = Age.getText().toString().trim();

        String weight = Weight.getText().toString().trim();
        String startDate = startDateMedical.getText().toString().trim();
        String endDate = endDateMedical.getText().toString().trim();

        String key = databaseReference.push().getKey();
        MedicalFormModelClass medicalForm = new MedicalFormModelClass(name,email,contact,address,age,
                weight,startDate,endDate);
        databaseReference.child(key).setValue(medicalForm);
        Toast.makeText(getApplicationContext(), "info added", Toast.LENGTH_LONG).show();
    }
}