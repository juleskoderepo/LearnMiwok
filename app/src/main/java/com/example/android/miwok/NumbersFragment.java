package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.STREAM_MUSIC;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    /**
     * Handles playback of all sound files
     */
    private MediaPlayer mediaPlayer;
    /**
     * Handles audio focus when playing sound files
     */
    private AudioManager am;

    /**
     * This listener is triggered when the (@link MediaPlayer)
     * has completed playing the audio file.
     */
    private MediaPlayer.OnCompletionListener CompletionListener
            = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    /**
     * This listener is triggered when audio focus changes
     */
    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == am.AUDIOFOCUS_LOSS) {
                        mediaPlayer.stop(); // stop playback because audio focus is lost.
                        // release resources
                        releaseMediaPlayer();
                    } else if (focusChange == am.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == am.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
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


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // Create a list of translations
        final ArrayList<Word> translations = new ArrayList<Word>();

        // Populate list
        translations.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        translations.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        translations.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        translations.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        translations.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        translations.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        translations.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        translations.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        translations.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        translations.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        //ListView and ArrayAdapter Word example - Section 4, Lesson 3.27
        WordAdapter adapter = new WordAdapter(getActivity(), translations, R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        // play the audio file associated with the list item to hear the correct pronunciation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord = translations.get(position);

                /* Release media player if it currently exists so we can play a
                 * different sound. */
                releaseMediaPlayer();

                int afResult = am.requestAudioFocus(afChangeListener, STREAM_MUSIC, AUDIOFOCUS_GAIN_TRANSIENT);

                if (afResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    /* Create and set up the {@link MediaPlayer} for the audio resource
                     * associated with the current word. */
                    mediaPlayer = MediaPlayer.create(getActivity(), currentWord.getAudioResourceID());

                    // Start the audio file
                    mediaPlayer.start();

                    /* Set up a listener on the media player, so that we can stop and
                     * release the media player once the audio has finished playing.
                     */
                    mediaPlayer.setOnCompletionListener(CompletionListener);
                }

            }
        });


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        /** When the fragment stops, release the media player resources because
         *  we won't be playing any more audio.
         */
        releaseMediaPlayer();
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

}
