package com.example.ds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditCourseActivity extends AppCompatActivity {
    private TextInputEditText CourseNameEdt,CoursePriceEdt,CourseSuitedForEdt,CourseImgEdt,CourseLinkEdt,CourseDescEdt;
    private Button UpdateCourseBtnBtn , deleteCourseBtnBtn;
    private ProgressBar loadingPB;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String courseID;
    private CourseRVModal courseRVModal;
    private String CourseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        CourseNameEdt = findViewById(R.id.idEdtCourseName);
        CoursePriceEdt = findViewById(R.id.idEdtCoursePrice);
        CourseSuitedForEdt = findViewById(R.id.idEdtCourseSuitedFor);
        CourseImgEdt = findViewById(R.id.idEdtCourseImageLink);
        CourseLinkEdt = findViewById(R.id.idEdtCourseLink);
        CourseDescEdt = findViewById(R.id.idEdtCourseDescription);
        UpdateCourseBtnBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtnBtn = findViewById(R.id.idBtnDeleteCourse);
        loadingPB = findViewById(R.id.idPBloading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        courseRVModal = getIntent().getParcelableExtra("course");
        if (courseRVModal != null) {

            CourseNameEdt.setText(courseRVModal.getCourseName());
            CoursePriceEdt.setText(courseRVModal.getCoursePrice());
            CourseDescEdt.setText(courseRVModal.getCourseDescription());
            CourseImgEdt.setText(courseRVModal.getCourseImg());
            CourseLinkEdt.setText(courseRVModal.getCourseLink());
            CourseSuitedForEdt.setText(courseRVModal.getSuitedFor());
            CourseID = courseRVModal.getCourseID();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("courses").child(courseID);
        UpdateCourseBtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CourseName = CourseNameEdt.getText().toString();
                String CoursePrice = CoursePriceEdt.getText().toString();
                String CourseDesc = CourseDescEdt.getText().toString();
                String CourseImg = CourseImgEdt.getText().toString();
                String CourseLink = CourseLinkEdt.getText().toString();
                String SuitedFor = CourseSuitedForEdt.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("CourseName", CourseName);
                map.put("CoursePrice", CoursePrice);
                map.put("CourseDesc", CourseDesc);
                map.put("CourseImg", CourseImg);
                map.put("CourseLink", CourseLink);
                map.put("SuitedFor", SuitedFor);
                map.put("CourseID", CourseID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditCourseActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditCourseActivity.this, MainActivity.class));


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(EditCourseActivity.this, "Fail to Update this Course !!", Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });

        deleteCourseBtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse();

            }
        });
        }
        private void deleteCourse(){
            databaseReference.removeValue();
            Toast.makeText(EditCourseActivity.this, " Course Deleted.. :(", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditCourseActivity.this, MainActivity.class));



        }
    }
