package com.ashfaqalizardaristore.mobileapp.gpacalcapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashfaqalizardaristore.mobileapp.gpacalcapp.R;
import com.ashfaqalizardaristore.mobileapp.gpacalcapp.models.Course;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private List<Course> mCourses;
    private Context mContext;
    public CoursesAdapter(List<Course> mCourses, Context mContext) {
        this.mCourses = mCourses;
        this.mContext = mContext;
    }

    public void calculate() {
        double TotalGradesPoints = 0, TotalCoursesCreditHours = 0, gpa = 0;
        int coursesCount = 0;

        for (int i = 0; i < mCourses.size(); i++) {
            Course course = mCourses.get(i);
            TotalGradesPoints += (course.getmGradePoints() * course.getmCreditHours());
            TotalCoursesCreditHours += (course.getmCreditHours());
            coursesCount++;
        }
        gpa = (TotalGradesPoints / TotalCoursesCreditHours) / coursesCount;
        Toast.makeText(mContext, "GPA is: " + TotalGradesPoints, Toast.LENGTH_SHORT).show();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_course, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Course course = mCourses.get(position);

        // Set item views based on your views and data model
        EditText name = holder.name;
        EditText gradePoints = holder.gradePoints;
        EditText creditHours = holder.creditHours;
        Button btnRemove = holder.btnRemove;
//        course.setmName(name.getText().toString());
//        course.setmGradePoints(Float.parseFloat(gradePoints.getText().toString()));
//        course.setmCreditHours(Float.parseFloat(creditHours.getText().toString()));

        btnRemove.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), mCourses.size()+ " removed successfully.", Toast.LENGTH_SHORT).show();
            mCourses.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public EditText name;
        public EditText gradePoints;
        public EditText creditHours;
        public Button btnRemove;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            name = itemView.findViewById(R.id.name);
            gradePoints = itemView.findViewById(R.id.gradePoints);
            creditHours = itemView.findViewById(R.id.creditHours);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }

}