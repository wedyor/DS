package com.example.ds;


import android.os.Parcel;
import android.os.Parcelable;

//to pass data from activity to another we use
public class CourseRVModal implements Parcelable {
    private String CourseName;
    private String CoursePrice;
    private String SuitedFor;
    private String CourseLink;
    private String CourseImg;
    private String CourseDescription;
    private String CourseID;

    public CourseRVModal(){

    }

    protected CourseRVModal(Parcel in) {
        CourseName = in.readString();
        CoursePrice = in.readString();
        SuitedFor = in.readString();
        CourseLink = in.readString();
        CourseImg = in.readString();
        CourseDescription = in.readString();
        CourseID = in.readString();
    }

    public static final Creator<CourseRVModal> CREATOR = new Creator<CourseRVModal>() {
        @Override
        public CourseRVModal createFromParcel(Parcel in) {
            return new CourseRVModal(in);
        }

        @Override
        public CourseRVModal[] newArray(int size) {
            return new CourseRVModal[size];
        }
    };

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCoursePrice() {
        return CoursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        CoursePrice = coursePrice;
    }

    public String getSuitedFor() {
        return SuitedFor;
    }

    public void setSuitedFor(String suitedFor) {
        SuitedFor = suitedFor;
    }

    public String getCourseLink() {
        return CourseLink;
    }

    public void setCourseLink(String courseLink) {
        CourseLink = courseLink;
    }

    public String getCourseImg() {
        return CourseImg;
    }

    public void setCourseImg(String courseImg) {
        CourseImg = courseImg;
    }

    public String getCourseDescription() {
        return CourseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        CourseDescription = courseDescription;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public CourseRVModal(String courseName, String coursePrice, String suitedFor, String courseLink, String courseImg, String courseDescription, String courseID) {
        CourseName = courseName;
        CoursePrice = coursePrice;
        SuitedFor = suitedFor;
        CourseLink = courseLink;
        CourseImg = courseImg;
        CourseDescription = courseDescription;
        CourseID = courseID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(CourseName);
        parcel.writeString(CoursePrice);
        parcel.writeString(SuitedFor);
        parcel.writeString(CourseLink);
        parcel.writeString(CourseImg);
        parcel.writeString(CourseDescription);
        parcel.writeString(CourseID);
    }
}
