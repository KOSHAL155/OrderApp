package com.example.orderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EnumMap;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    int price=0;
    int n=0;
    int topping1=10;    
    int topping2=20;
    public void increment(View view) {

        n=n+1;
        display(n);
        price=n*5;
        CheckBox checkBox12 = (CheckBox)findViewById(R.id.check12);
        boolean check_1=checkBox12.isChecked();
        CheckBox checkBox21 = (CheckBox)findViewById(R.id.check21);
        boolean check_2=checkBox21.isChecked();
        if(check_1==true){
            int top_price=topping1*n;
            price=top_price+price;
        }if(check_2==true){
            int top_price=topping2*n;
            price=top_price+price;
        }
        displayPrice(price);

    }
    public void decrement(View view) {
        if(n<=0){
            Toast.makeText(this,"order can not be less then 0",Toast.LENGTH_LONG).show();

        }

        if(n>0){
        n=n-1;
        }
        price=n*5;
        CheckBox checkBox12 = (CheckBox)findViewById(R.id.check12);
        boolean check_1=checkBox12.isChecked();
        CheckBox checkBox21 = (CheckBox)findViewById(R.id.check21);
        boolean check_2=checkBox21.isChecked();
        if(check_1==true){
            int top_price=topping1*n;
            price=top_price+price;
        }if(check_2==true){
            int top_price=topping2*n;
            price=top_price+price;
        }
        display(n);
        displayPrice(price);
    }
    public void check1(View view){
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            int top_price=n*topping1;
            price=top_price+price;
        }else if(!checkBox.isChecked()){
            int top_price=n*topping1;
            price=price-top_price;
            if(price<0){
                price=0;
            }
        }


        displayPrice(price);
    }
    public void check2(View view){
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked()){
            int top_price=n*topping2;
            price=top_price+price;
        }else if(!checkBox.isChecked()){
            int top_price=n*topping2;
            price=price-top_price;
            if(price<0){
                price=0;
            }
        }


        displayPrice(price);
    }



    public void submitOrder(View view) {
        CheckBox checkBox1 = (CheckBox)findViewById(R.id.check12);
        boolean check1=checkBox1.isChecked();
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.check21);
        boolean check2=checkBox2.isChecked();

        EditText c_name=findViewById(R.id.nameC);
        EditText p_num=findViewById(R.id.p_number);
        String customer=c_name.getText().toString();
        String p=p_num.getText().toString();
        String email=createSummary(customer,p,check1,check2);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMsg();
        displayorder();

    }
    public String createSummary(String c,String p,boolean c1,boolean c2){
        String res="Customer Name : "+c+"\nPhone number :"+p+"\n want first topping "+ c1+"\nwant 2nd topping "+ c2+"\nPrice : "+ price+"\nQuantity :"+n;
        return res;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);

        quantityTextView.setText("" + number);
    }
    public void displayPrice(int number) {
        TextView price = (TextView) findViewById(R.id.Price);

        price.setText("$" + number);
    }
    private void displayMsg() {
        TextView price = (TextView) findViewById(R.id.thanku);

        price.setText("ThankYou For Placing Order");
    }
    private void displayorder() {
        TextView orderSummary = (TextView) findViewById(R.id.orderSummary);
        EditText c_name=findViewById(R.id.nameC);
        EditText p_num=findViewById(R.id.p_number);
        String customer=    c_name.getText().toString();
        String p=p_num.getText().toString();
        orderSummary.setText("\n"+customer+"\n"+p+""+"\nPrice :$"+price+"\nQuantity :"+n);
    }

}