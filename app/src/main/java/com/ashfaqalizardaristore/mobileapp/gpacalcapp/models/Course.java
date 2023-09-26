package com.ashfaqalizardaristore.mobileapp.gpacalcapp.models;

import java.util.ArrayList;

public class Course {
    private int mId;
    private String mName;
    private float mGradePoints;
    private float mCreditHours;

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmGradePoints(float mGradePoints) {
        this.mGradePoints = mGradePoints;
    }

    public void setmCreditHours(float mCreditHours) {
        this.mCreditHours = mCreditHours;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public float getmGradePoints() {
        return mGradePoints;
    }

    public float getmCreditHours() {
        return mCreditHours;
    }

    public static int getLastCourseId() {
        return lastCourseId;
    }

    public Course(int mId, String mName, float mGradePoints, float mCreditHours) {
        this.mId = mId;
        this.mName = mName;
        this.mGradePoints = mGradePoints;
        this.mCreditHours = mCreditHours;
    }

    public static int lastCourseId = 0;

    public static ArrayList<Course> createCoursesList(int numCourses) {
        ArrayList<Course> courses = new ArrayList<>();
        int id = 0;
        for (int i = 1; i <= numCourses; i++) {
            courses.add(new Course(++id, "", 0, 0));
        }
        lastCourseId = id;
        return courses;
    }

}
