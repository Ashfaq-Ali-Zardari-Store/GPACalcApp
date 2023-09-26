package com.ashfaqalizardaristore.mobileapp.gpacalcapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashfaqalizardaristore.mobileapp.gpacalcapp.adapters.CoursesAdapter;
import com.ashfaqalizardaristore.mobileapp.gpacalcapp.databinding.ActivityMainBinding;
import com.ashfaqalizardaristore.mobileapp.gpacalcapp.models.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainActivity;
    private Dialog dialog;
    private ArrayList<Object> universities, branches;
    private ArrayList<Course> courses;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainActivity.getRoot());

        // initialize array list
        universities = new ArrayList<>();
        branches = new ArrayList<>();

        // set value in array list
        universities.add("SZABIST University");
        branches.add("Sachal Colony Larkana Pakistan");
        branches.add("Clifton Karachi (Main Campus) Pakistan");
        branches.add("Islamabad Pakistan");
        branches.add("Bangali Colony Hyderabad Pakistan");
        branches.add("Gharo Thatta Gharo Pakistan");
        branches.add("Dubai International Academic City Dubai UAE");

        mainActivity.universityView.setOnClickListener(v -> {
            // Initialize dialog
            dialog = new Dialog(MainActivity.this);

            // set custom dialog
            dialog.setContentView(R.layout.dialog_searchable_spinner);

            // set custom height and width
            dialog.getWindow().setLayout(650, 800);

            // set transparent background
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // show dialog
            dialog.show();

            // Initialize and assign variable
            EditText editText = dialog.findViewById(R.id.edit_text);
            ListView listView = dialog.findViewById(R.id.list_view);

            // Initialize array adapter
            ArrayAdapter<Object> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, universities);

            // set adapter
            listView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            listView.setOnItemClickListener((parent, view, position, id) -> {
                // when item selected from list
                // set selected item on textView
                String selectedText = adapter.getItem(position).toString();
                mainActivity.universityView.setText(selectedText);

                Toast.makeText(MainActivity.this, "selection " + selectedText, Toast.LENGTH_LONG).show();
                loadBranches();
                // Dismiss dialog
                dialog.dismiss();
            });
        });
        // Lookup the recyclerview in activity layout
        // Initialize contacts
        courses = Course.createCoursesList(1);
        // Create adapter passing in the sample user data
        CoursesAdapter adapter = new CoursesAdapter(courses, getApplicationContext());
        // Attach the adapter to the recyclerview to populate items
        mainActivity.rvCourses.setAdapter(adapter);
        // Set layout manager to position the items
        mainActivity.rvCourses.setLayoutManager(new LinearLayoutManager(this));
        mainActivity.btnAdd.setOnClickListener(view -> {
            courses.add(new Course(Course.getLastCourseId(), "", 0, 0));
            adapter.notifyDataSetChanged();
        });
        mainActivity.btnCalc.setOnClickListener(view -> {
            adapter.calculate();
//            double TotalGradesPoints = 0, TotalCoursesCreditHours = 0, gpa = 0;
//            int coursesCount = 0;
//
//            for (Course course : courses) {
//                TotalGradesPoints += (course.getmGradePoints() * course.getmCreditHours());
//                TotalCoursesCreditHours += (course.getmCreditHours());
//                coursesCount++;
//            }
//            gpa = (TotalGradesPoints / TotalCoursesCreditHours) / coursesCount;
//            Toast.makeText(this, "GPA is: " + gpa, Toast.LENGTH_SHORT).show();
        });
    }
//    private double TotalGradesPoints = 0, TotalCoursesCreditHours = 0, gpa = 0;
//    private int coursesCount = 0;
    private void loadBranches() {
        mainActivity.branchView.setOnClickListener(v -> {
            // Initialize dialog
            dialog = new Dialog(MainActivity.this);

            // set custom dialog
            dialog.setContentView(R.layout.dialog_searchable_spinner);

            // set custom height and width
            dialog.getWindow().setLayout(650, 800);

            // set transparent background
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            // show dialog
            dialog.show();

            // Initialize and assign variable
            EditText editText = dialog.findViewById(R.id.edit_text);
            listView = dialog.findViewById(R.id.list_view);
            TextView dropdown_menu_title = dialog.findViewById(R.id.dropdown_menu_title);
            dropdown_menu_title.setText(getString(R.string.select_education_body_branch));
            // Initialize array adapter
            ArrayAdapter<Object> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, branches);

            // set adapter
            listView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            listView.setOnItemClickListener((parent, view, position, id) -> {
                // when item selected from list
                // set selected item on textView
                String selectedText = adapter.getItem(position).toString();
                mainActivity.branchView.setText(selectedText);

                Toast.makeText(MainActivity.this, "selection " + selectedText, Toast.LENGTH_LONG).show();
                // Dismiss dialog
                dialog.dismiss();
            });
        });

    }
}