package com.example.ds;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> implements CourseClickInterface{
    private ArrayList<CourseRVModal> courseRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private CourseClickInterface courseClickInterface;
    private ViewHolder holder;
    private int position;


    public CourseRVAdapter(ArrayList<CourseRVModal> courseRVModalArrayList, Context context, CourseClickInterface courseClickInterface) {
        this.courseRVModalArrayList = courseRVModalArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @NonNull
    @Override
    public CourseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_rv_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        CourseRVModal courseRVModal = courseRVModalArrayList.get(position);
        holder.CourseNameTV.setText(courseRVModal.getCourseName());
        holder.CoursePriceTV.setText("Rs ." + courseRVModal.getCoursePrice());
        Picasso.get().load(courseRVModal.getCourseImg()).fit()
                .centerCrop().into(holder.CourseIV);
        SetAnimaion(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseClickInterface.onCourseClick(position);
            }
        });
    }
    @Override
    public void onCourseClick(int position) {

    }
    @Override
    public int getItemCount() {

        return courseRVModalArrayList.size();

    }

    private void SetAnimaion(View itemView, int position){
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos=position;
        }

    }


    // public interface CourseClickInterface{
     //   void onCourseClick(int position);

    //}

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView CourseNameTV , CoursePriceTV;
        private ImageView CourseIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CourseNameTV = itemView.findViewById(R.id.idIVCourseName);
            CoursePriceTV = itemView.findViewById(R.id.idIVCoursePrice);
            CourseIV = itemView.findViewById(R.id.idIVCourse);
        }
    }
}
