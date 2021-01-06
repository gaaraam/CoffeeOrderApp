# CoffeeOrderApp
간단한 커피주문 앱을 만들어보자

<img src = "https://user-images.githubusercontent.com/16756666/103751727-17aecb80-504c-11eb-8842-a0b275d31f61.gif" width = 30%></img>
<br>
## 요구사항
1. 커피의 수량을 1부터 10까지 버튼으로 지정할 수 있다.
2. 주문할 커피의 수량을 지정하면 얼마를 지불해야 하는지 결과값이 출력될 수 있다.
3. 주문버튼을 누르면 접수받을 수 있다.

## activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="vertical"
    android:layout_margin="10dp">

    <!-- 제목에 해당하는 TextView에 android:text를
    res/values/string.xml 로 추출함. -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/title"
        android:textSize="30sp"
        />

    <!-- 플러스 Button과 마이너스 Button, 그리고 중간에 수량이 증가하고 감소함을
     출력해주는 TextView를 LinearLayout으로 정렬한다. -->
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp">

        <Button
            android:id="@+id/minus_button"
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:text="@string/minus"/>
        <TextView
            android:textAlignment="center"
            android:id="@+id/quantity_textview"
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:text="@string/zero"/>
        <Button
            android:id="@+id/plus_button"
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:text="@string/plus"/>
    </LinearLayout>

    <TextView
        android:layout_margin="10dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/price"
        android:textSize="30sp"
        />

    <!-- 수량 * 커피값의 결과값이 출력되는 TextView. -->
    <TextView
        android:id="@+id/result"
        android:layout_margin="10dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/result"/>

    <!-- 주문을 하기 위한 버튼 -->
    <Button
        android:id="@+id/order"
        android:layout_margin="10dp"
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:text="@string/order"/>

</LinearLayout>
```

## MainActivity.java

```java
package com.example.coffeeorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        // 상수 선언
        public static final int QUANTITY_MIN = 0;
        public static final int QUANTITY_MAX = 10;
        public static final int COFFEE_PRICE = 3000;
        // 버튼과 텍스트뷰 
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
        
        // view 찾아오기
        mMinusButton = findViewById(R.id.minus_button);
        mPlusButton = findViewById(R.id.plus_button);
        mQuantityTextView = findViewById(R.id.quantity_textview);
        mResultTextView = findViewById(R.id.result);
        mOrderButton = findViewById(R.id.order);

        
        // +버튼을 클릭하면 mQuantity에 저장된 값이 1씩 증가
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
        
        // - 버튼을 클릭하면 mQuantity에 저장된 값이 1씩 감소 
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
        
        // 주문 버튼을 눌렀을 때, mResultTextView의 값이 Toast창으로도 출력되게 함.
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
        // 수량 * 커피값을 하여 mResultTextView에 출력하는 method
        private void displayResult() {
        mQuantityTextView.setText("" + mQuantity); // 문자열 자리에 int만 들어가면 터짐

        String result = "가격 : " + (COFFEE_PRICE * mQuantity) + " 원\n감사합니다";
        mResultTextView.setText(result);
    }


        // mQuantity를 초기화하는 method
        public void init(){
        mQuantity = 0;
    }


    }
```

