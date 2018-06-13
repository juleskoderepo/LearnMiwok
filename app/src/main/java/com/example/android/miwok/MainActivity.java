/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView numbers;
    TextView family;
    TextView colors;
    TextView phrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category and capture the click event
        numbers = (TextView) findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                Intent openNumbers = new Intent (MainActivity.this,NumbersActivity.class);
                startActivity(openNumbers);
            }
        });

        //Find the View that shows the family members category and capture the click event
        family = (TextView) findViewById(R.id.family);

        family.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent openFamily = new Intent (MainActivity.this,FamilyMembersActivity.class);
                startActivity(openFamily);
            }
        });

        //Find the view that shows the colors category and capture the click event
        colors = (TextView) findViewById(R.id.colors);

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openColors = new Intent (MainActivity.this,ColorsActivity.class);
                startActivity(openColors);
            }
        });

        //Find the View that shows the phrases category and capture the click event
        phrases = (TextView) findViewById(R.id.phrases);

        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openPhrases = new Intent (MainActivity.this,PhrasesActivity.class);
                startActivity(openPhrases);
            }
        });
    }



}
