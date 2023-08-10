package my.edu.utar.practicalassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    final Button button = (Button) findViewById(R.id.CalculateButton);
    button.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), Calculate_Bill.class);
            startActivity(i);
        }
    });

        final Button button1 = (Button) findViewById(R.id.PartyButton);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Student_Information.class);
                startActivity(i);
            }
        });
    }
}