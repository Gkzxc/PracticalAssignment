package my.edu.utar.practicalassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Combination_Breakdown extends AppCompatActivity {

    private EditText itemNameEditText;
    private EditText itemPriceEditText;
    private ArrayList<OrderItem> itemList;
    private TextView selectedItemsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combination_breakdown);

        Button calculatebutton1=findViewById(R.id.Calculate2);
    Button addItemButton1 = findViewById(R.id.Add_button);
        itemList = new ArrayList<>();
        selectedItemsTextView = findViewById(R.id.textView);
        itemNameEditText = findViewById(R.id.ItemName);
        itemPriceEditText = findViewById(R.id.ItemPrice);

    addItemButton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Call a method to handle adding items
            addItemFromUserInput();
        }
    });

    calculatebutton1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double totalBill = 0, averageBill = 0;
            int counter = 0;

            EditText numberOfPeopleEditText = findViewById(R.id.NumberOfPeople); // Replace with your EditText's ID
            counter = Integer.parseInt(numberOfPeopleEditText.getText().toString());
            double[] percentages = new double[counter];

            for (OrderItem item : itemList) {
                totalBill += item.getPrice();
            }
            EditText[] percentageEditTexts = new EditText[counter];
            for (int i = 0; i < counter; i++) {
                int editTextId = getResources().getIdentifier("percentage_edittext_" + (i + 1), "id", getPackageName());
                percentageEditTexts[i] = findViewById(editTextId);
            }

            for (int i = 0; i < counter; i++) {
                percentages[i] = Double.parseDouble(percentageEditTexts[i].getText().toString()) / 100.0;
            }

            double[] percentageShares = new double[counter];
            for (int i = 0; i < counter; i++) {
                percentageShares[i] = percentages[i] * totalBill;
            }

            LinearLayout amountContainer = findViewById(R.id.amount_container);
            amountContainer.removeAllViews();
            for (int i = 0; i < counter; i++) {
               TextView textView=new TextView(getApplicationContext());
                textView.setText("Amount to pay: RM" + percentageShares[i]);
                amountContainer.addView(textView);
            }
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
}