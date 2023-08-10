package my.edu.utar.practicalassignment;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calculate_Bill extends AppCompatActivity {

    private ArrayList<OrderItem> itemList;
    private TextView selectedItemsTextView;
    private EditText itemNameEditText;
    private EditText itemPriceEditText;
    private CheckBox saveCheckbox;
    private EditText nameInput, ageInput;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bill);
        saveCheckbox = findViewById(R.id.Save_Party);
        nameInput = findViewById(R.id.StudName);
        sharedPreferences = getSharedPreferences("student_data", MODE_PRIVATE);
        itemList = new ArrayList<>();
        selectedItemsTextView = findViewById(R.id.selected_items_textview);
        itemNameEditText = findViewById(R.id.Order);
        itemPriceEditText = findViewById(R.id.Price);
        Button saveButton=findViewById(R.id.save_button);
        Button resultButton=findViewById(R.id.result);



        saveCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    nameInput.setVisibility(View.VISIBLE);
                    saveButton.setVisibility(View.VISIBLE);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveCheckbox.isChecked()) {
                    String name = nameInput.getText().toString();
                    Set<String> namesSet = sharedPreferences.getStringSet("student_names", new HashSet<>());
                    namesSet.add(name);

                    // Save the updated set of names to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putStringSet("student_names", namesSet);
                    editor.apply();
                }
            }
        });

        final Button button1 = (Button) findViewById(R.id.CustomBreakdown);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Calculate_Bill.this, Custome_Breakdown.class);
                startActivity(i);
            }
        });

        final Button button2 = (Button) findViewById(R.id.CombinationBreakdown);
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Calculate_Bill.this, Combination_Breakdown.class);
                startActivity(i);
            }
        });

        final Button button3 = (Button) findViewById(R.id.Main);
        button3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Calculate_Bill.this, MainActivity.class);
                startActivity(i);
            }
        });

        Button addItemButton = findViewById(R.id.addList);
        Button calculateButton = findViewById(R.id.Calculate);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to handle adding items
                addItemFromUserInput();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to handle calculating the bill
                calculateBill();
            }
        });
    }
        private void addItemFromUserInput() {
            String itemName = itemNameEditText.getText().toString();
            double itemPrice = Double.parseDouble(itemPriceEditText.getText().toString());

            addItemAndDisplayList(itemName, itemPrice);

            // Clear the input fields
            itemNameEditText.getText().clear();
            itemPriceEditText.getText().clear();
        }


    private void addItemAndDisplayList(String itemName, double itemPrice) {
        itemList.add(new OrderItem(itemName, itemPrice));
        displaySelectedItems();
    }

    private void displaySelectedItems() {
        StringBuilder sb = new StringBuilder("Selected Items:\n");

        for (OrderItem item : itemList) {
            sb.append(item.getName()).append(" - $").append(item.getPrice()).append("\n");
        }

        selectedItemsTextView.setText(sb.toString());
    }

    private void calculateBill(){
        double totalBill=0, averageBill=0;
        int counter=0;
        EditText numberOfPeopleEditText = findViewById(R.id.NumberOfPeople); // Replace with your EditText's ID
        String numberOfPeople=numberOfPeopleEditText.getText().toString();
        TextView BillPerPerson=findViewById(R.id.paidprice);
        for (OrderItem item : itemList){
            totalBill+=item.getPrice();
        }

        counter = Integer.parseInt(numberOfPeople);

        averageBill = totalBill/counter;

        BillPerPerson.setText("Bill per person: RM" + averageBill);
    }


}