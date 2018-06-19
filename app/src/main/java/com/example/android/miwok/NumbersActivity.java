package com.example.android.miwok;

import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // declare an array of English number words
        String[] words = new String[10];

        // initialize elements
        words[0] = "one";
        words[1] = "two";
        words[2] = "three";
        words[3] = "four";
        words[4] = "five";
        words[5] = "six";
        words[6] = "seven";
        words[7] = "eight";
        words[8] = "nine";
        words[9] = "ten";

        String className = getLocalClassName();
        for (int i = 0; i < words.length; i++) {
            Log.v(className, "Word at index " + i + ": " + words[i]);
        }

        //Create a list of words
        ArrayList<String> wordsAl = new ArrayList<String>();

        // store items in ArrayList
        for (String item : words) {
            wordsAl.add(item);
        }

        // Create a list of translations
        ArrayList<Word> translations = new ArrayList<Word>();

        // Populate list
/*        Word w = new Word("one","lutti");
        translations.add(w);
*/
        // A more concise way to write the above two lines of code
        translations.add(new Word("one", "lutti", R.drawable.number_one));
        translations.add(new Word("two", "otiiko", R.drawable.number_two));
        translations.add(new Word("three", "tolookosu", R.drawable.number_three));
        translations.add(new Word("four", "oyyisa", R.drawable.number_four));
        translations.add(new Word("five", "massokka", R.drawable.number_five));
        translations.add(new Word("six", "temmokka", R.drawable.number_six));
        translations.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        translations.add(new Word("eight", "kawinta", R.drawable.number_eight));
        translations.add(new Word("nine", "wo'e", R.drawable.number_nine));
        translations.add(new Word("ten", "na'aacha", R.drawable.number_ten));


        // print contents of ArrayList to log
/*
        for (int i=0; i<wordsAl.size(); i++){
            Log.v(getLocalClassName(),"Word at index " + i + " of ArrayList: " + wordsAl.get(i));
        }
*/

        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootViewNumbers);


        //int i = 0;

        // example while loop - Section 4, Lesson 3.11
/*
        while (i < wordsAl.size()) {
            // Create a new TextView
            TextView wordView = new TextView(this);

            // Set the text to be word at the current index
            wordView.setText(wordsAl.get(i));

            // Add this TextView as another child to the root view of this layout
            rootView.addView(wordView);

            // Update counter variable
            i++;
        }
*/

/*        // example for loop - Section 4, Lesson 3.12
        for (int i=0; i < wordsAl.size(); i++) {
            TextView wordView = new TextView(this);
            wordView.setText(wordsAl.get(i));

            rootView.addView(wordView);
        }
*/

        //ListView and ArrayAdapter example - Section 4, Lesson 3.19
/*        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.list_item,wordsAl);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
*/

/*
        //GridView experiment - Section 4, Lesson 3.20
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,wordsAl);

        GridView gridView = (GridView) findViewById(R.id.grid);

        gridView.setAdapter(itemsAdapter);
*/

        //ListView and ArrayAdapter Word example - Section 4, Lesson 3.27
        WordAdapter adapter = new WordAdapter(this, translations, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        // enable up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
