package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.STREAM_MUSIC;

public class PhrasesActivity extends AppCompatActivity {

    /**
     * Handles playback of all sound files
     */
    private MediaPlayer mediaPlayer;
    private AudioManager am;

    private MediaPlayer.OnCompletionListener CompletionListener
            = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener(){
                @Override
                public void onAudioFocusChange (int focusChange){
                    if (focusChange == am.AUDIOFOCUS_LOSS){
                        mediaPlayer.stop(); // stop playback because audio focus is lost.
                        // release resources
                        releaseMediaPlayer();
                    } else if (focusChange == am.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == am.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        // pause playback bc audio focus has been temporarily lost.
                        mediaPlayer.pause();
                        //reset player to start so user can here full pronunciation.
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == am.AUDIOFOCUS_GAIN) {
                        // start playback bc audio focus has been regained
                        mediaPlayer.start();
                    }
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();

        // Populate list
/*        Word w = new Word("one","lutti");
        words.add(w);
*/
        // A more concise way to write the above two lines of code
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I'm coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let's go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        //ListView and ArrayAdapter Word example - Section 4, Lesson 3.27
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        // play the audio file associated with the list item to hear the correct pronunciation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord = words.get(position);

                /* Release media player if it currently exists so we can play a
                 * different sound. */
                releaseMediaPlayer();

                int afResult = am.requestAudioFocus(afChangeListener, STREAM_MUSIC, AUDIOFOCUS_GAIN_TRANSIENT);

                if (afResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    /* Create and set up the {@link MediaPlayer} for the audio resource
                     * associated with the current word. */
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, currentWord.getAudioResourceID());

                    // Start the audio file
                    mediaPlayer.start();

                    /* Set up a listener on the media player, so that we can stop and
                     * release the media player once the audio has finished playing.
                     */
                    mediaPlayer.setOnCompletionListener(CompletionListener);
                }
            }
        });

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

    /**
     * Helper methods to clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            // release audio focus
            am.abandonAudioFocus(afChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();

    }

}

