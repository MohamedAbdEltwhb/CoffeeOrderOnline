package com.example.mm.coffeeorderonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private int mQuantity = 0;
    private TextView mQuantityTextView;
    EditText nameField;
    CheckBox whippedCreamCheckbox, chocolateCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuantityTextView = (TextView)findViewById(R.id.quantity_text_view);
        nameField = (EditText)findViewById(R.id.name_field);
        whippedCreamCheckbox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckbox = (CheckBox)findViewById(R.id.chocolate_checkbox);
    }

    public void inCrement(View view) {
        if (mQuantity == 70){
            showToast("You Cannot have more than 70 Coffees");
            return;
        }
        mQuantity ++;
        mQuantityTextView.setText(String.valueOf(mQuantity));
    }

    public void deCrement(View view) {
        if (mQuantity == 0){
            showToast("You Cannot have Less than 1 Coffee");
            return;
        }
        mQuantity --;
        mQuantityTextView.setText(String.valueOf(mQuantity));

    }

    public void submitOrder(View view) {
        String name = nameField.getText().toString().trim();
        boolean haswhippedCream = whippedCreamCheckbox.isChecked();
        boolean hasChocolat = chocolateCheckbox.isChecked();
        int price = calculatePrice(haswhippedCream,hasChocolat);

        String priceMessage = createOrderSummary(name  , haswhippedCream , hasChocolat, price);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: "));
        intent.putExtra(Intent.EXTRA_SUBJECT , "Coffee Order Online for: " + name);
        intent.putExtra(Intent.EXTRA_TEXT , priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }

    @SuppressLint("StringFormatInvalid")
    private String createOrderSummary(String name , boolean addwhippedCream , boolean addChocolat, int price) {

        // int price = calculatePrice();
        String priceMassge = getString(R.string.order_summary_name , name);
        priceMassge += "\n"+ getString(R.string.order_summary_whipped_cream , addwhippedCream);
        priceMassge += "\n"+ getString(R.string.order_summary_chocolate , addChocolat);
        priceMassge += "\n"+ getString(R.string.order_summary_quantitye , mQuantity);
        priceMassge += "\n"+ getString(R.string.order_summary_price , NumberFormat.getCurrencyInstance().format(price));
        priceMassge += "\n" + getString(R.string.thank_you);
        return priceMassge;
    }

    private int calculatePrice(boolean addwhippedCream , boolean addChocolat) {
        int basePrice = 5;

        if (addwhippedCream)
            basePrice += +1;

        if (addChocolat)
            basePrice += +2;

        return mQuantity * basePrice;
    }

    private void showToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
