package my.edu.utar.practicalassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class Student_Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

    TextView displayedNames = findViewById(R.id.displayed_name);

    // Retrieve and display student names from SharedPreferences
    SharedPreferences sharedPreferences = getSharedPreferences("your_shared_prefs_name", MODE_PRIVATE);
    Set<String> namesSet = (HashSet<String>) sharedPreferences.getStringSet("student_names", new HashSet<>());
        for (String name : namesSet) {
        displayedNames.append(name + "\n");
    }

        final Button button3 = (Button) findViewById(R.id.Main);
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Student_Information.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}