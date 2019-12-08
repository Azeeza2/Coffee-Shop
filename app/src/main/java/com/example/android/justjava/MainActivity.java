package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.jar.Attributes;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private View view;
    int quantity = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        CheckBox hasWhippedCream = findViewById(R.id.add_whipped_cream_checkBox);
        boolean addWhippedCream = hasWhippedCream.isChecked();

        CheckBox hasChocolateCream = findViewById(R.id.add_chocolate_cream_checkBox);
        boolean addChocolateCream = hasChocolateCream.isChecked();

        EditText editText = findViewById(R.id.editText);
        Editable Name = editText.getText();

        int price= calculatePrice(addWhippedCream, addChocolateCream);
        String priceMessage = createOrderSummary(price, addWhippedCream, addChocolateCream, Name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:balogunzzt@gmail.com" )); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.just_java_order_app_for , Name));
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /* This method displays the given price on the screen.
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolateCream) {

        int pricePerCup = 5;


        if (addWhippedCream){
            pricePerCup = (pricePerCup + 1) ;
        }
         if (addChocolateCream) {
            pricePerCup = (pricePerCup + 2);
        }
         return pricePerCup * quantity;
    }

    /**
     *
     * @param price calculates price of order
     * @param addWhippedCream adds whipped cream topping
     * @return returns the value of the method.
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolateCream, Editable Name) {
        String priceMessage = getString(R.string.order_summary_name, Name);
        priceMessage = priceMessage + "\nAdd Whipped cream? " + addWhippedCream;
        priceMessage = priceMessage + "\nAdd Chocolate cream? " + addChocolateCream;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage = priceMessage + "\nTotal: $" + price;
        priceMessage += priceMessage + "\n" + getString(R.string.thank_you);
        return priceMessage;

    }


    /**
     * This method displays the increment button on the screen.
     */
    public void increment(View view) {

        if (quantity == 100) {
            Toast toast = Toast.makeText(this, "You cannot order more than 100 cups", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the decrement button on the screen.
     */
    public void decrement(View view) {

        if (quantity == 1) {
            Toast toast = Toast.makeText(this, "You cannot order less than 1 cup", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    private void displayQuantity(int num) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + num);
    }

    public void hasWhippedCream(View view) {

    }
    public void hasChocolateCream (View view){
    }
    public  void editText (View view){

    }


}
