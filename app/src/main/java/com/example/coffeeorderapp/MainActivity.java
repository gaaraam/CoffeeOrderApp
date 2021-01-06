package com.example.coffeeorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



        public static final int QUANTITY_MIN = 0;
        public static final int QUANTITY_MAX = 10;
        public static final int COFFEE_PRICE = 3000;
        private Button mMinusButton;
        private Button mPlusButton;
        private TextView mQuantityTextView;
        private TextView mResultTextView;
        private Button mOrderButton;

        // 수량
        private int mQuantity;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mMinusButton = findViewById(R.id.minus_button);
        mPlusButton = findViewById(R.id.plus_button);
        mQuantityTextView = findViewById(R.id.quantity_textview);
        mResultTextView = findViewById(R.id.result);
        mOrderButton = findViewById(R.id.order);

        mMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantity--;
                if(mQuantity < QUANTITY_MIN) {
                    mQuantity = QUANTITY_MIN;
                }

                displayResult();
            }
        });

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantity++;
                if(mQuantity > QUANTITY_MAX){
                    mQuantity = QUANTITY_MAX;
                }

                displayResult();
            }
        });

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mResultTextView.getText().toString();
                Toast.makeText(MainActivity.this,
                        message,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

        private void displayResult() {
        mQuantityTextView.setText("" + mQuantity); // 문자열 자리에 int만 들어가면 터짐

        String result = "가격 : " + (COFFEE_PRICE * mQuantity) + " 원\n감사합니다";
        mResultTextView.setText(result);
    }



        public void init(){
        mQuantity = 0;
    }


    }