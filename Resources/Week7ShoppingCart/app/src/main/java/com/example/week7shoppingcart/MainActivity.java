package com.example.week7shoppingcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Items> myBasket;
    Button btnAddToCart;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ArrayList of class ItemsViewModel
        ArrayList<Items> data = new ArrayList<>();

        int[] images = {R.drawable.playstation_5, R.drawable.xbox_series_x, R.drawable.nintendo_switch, R.drawable.lap1};
        String[] consoles = {"Playstation 5", "X-Box Series X", "Nintendo Switch", "Alienware Laptop"};
        int[] prices = {480, 450, 250, 1550};

        for (int i = 0; i < images.length; i++) {
            data.add(new Items(images[i], consoles[i], prices[i]));
        }

        // This will pass the ArrayList to our Adapter
        CustomAdapter adapter = new CustomAdapter(data);

        // Setting the Adapter with the recyclerview
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> Toast.makeText(MainActivity.this, "You clicked on "
                + consoles[position], Toast.LENGTH_SHORT
        ).show());

        btnAddToCart.setOnClickListener(view -> {
            myBasket = adapter.getMyBasket();

            String everythingInTheBasket = returnEverythingInBasketAsString(myBasket);

            Intent intent = new Intent(MainActivity.this, BasketActivity.class);
            intent.putExtra("everythingInTheBasket",everythingInTheBasket);
            startActivity(intent);
        });
    }

    public String returnEverythingInBasketAsString(ArrayList<Items> myBasket) {
        StringBuilder eachItemText= new StringBuilder();
        String productName;
        int productQuantityInBasket;
        int productPrice;
        int totalPayForEachItem;
        int totalPay=0;
        String totalPayString;
        String everythingInTheBasket;


        for (int i=0; i<myBasket.size(); i++){

            productName = myBasket.get(i).getText();
            productPrice = myBasket.get(i).getPrice();
            productQuantityInBasket = myBasket.get(i).getQuantity();


            totalPayForEachItem = productQuantityInBasket * productPrice;
            eachItemText
                    .append("\n")
                    .append(productQuantityInBasket)
                    .append(" X ")
                    .append(productName)
                    .append("(£")
                    .append(productPrice)
                    .append(") = £")
                    .append(totalPayForEachItem);

            totalPay +=totalPayForEachItem;
        }

        totalPayString = "\n Total you need to pay = £"+totalPay;

        everythingInTheBasket = (eachItemText + totalPayString);

        return everythingInTheBasket;
    }
}