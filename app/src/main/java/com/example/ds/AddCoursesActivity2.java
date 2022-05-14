package com.example.ds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCoursesActivity2 extends AppCompatActivity {

    private TextInputEditText CourseNameEdt,CoursePriceEdt,CourseSuitedForEdt,CourseImgEdt,CourseLinkEdt,CourseDescEdt;
    private Button addCourseBtnBtn;
    private ProgressBar loadingPB;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses2);
        CourseNameEdt = findViewById(R.id.idEdtCourseName);
        CoursePriceEdt = findViewById(R.id.idEdtCoursePrice);
        CourseSuitedForEdt = findViewById(R.id.idEdtCourseSuitedFor);
        CourseImgEdt = findViewById(R.id.idEdtCourseImageLink);
        CourseLinkEdt = findViewById(R.id.idEdtCourseLink);
        CourseDescEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtnBtn = findViewById(R.id.idBtnAddCourse);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("courses");
        addCourseBtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = CourseNameEdt.getText().toString();
                String coursePrice = CoursePriceEdt.getText().toString();
                String SuitedFor = CourseSuitedForEdt.getText().toString();
                String courseImg = CourseImgEdt.getText().toString();
                String courseLink = CourseLinkEdt.getText().toString();
                String courseDesc = CourseDescEdt.getText().toString();
                String courseID = courseName;
                CourseRVModal courseRVModal = new CourseRVModal(courseName,coursePrice,SuitedFor,courseImg,courseLink,courseDesc,courseID);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(courseID).setValue(courseRVModal);
                        Toast.makeText(AddCoursesActivity2.this, "course Added succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddCoursesActivity2.this, MainActivity.class));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddCoursesActivity2.this, "error is " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });





    }
}