package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */
public class Word {

    /**
     * Miwok translation for the word
     */
    private String mMiwokTranslation;

    /**
     * Default translation for the word
     */
    private String mDefaultTranslation;

    /**
     * Resource image ID associated with word. Initiated as having no image resource id
     */
    private int ImageResourceId = NO_IMAGE_PROVIDED;

    /**
     * Constant value representing that there is no image i.e. no resource id
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Audio resource ID associated with word.
     */
    private int AudioResourceId;


    /**
     * Constructs a new Word object with a Miwork translation, a default translation
     * and audio resource.
     *
     * @param defaultTranslation the word in the language the user is familiar with.
     * @param miwokTranslation   the word in the Miowk language.
     * @param audioResourceId    the resource ID of the audio (pronunciation) associated with the word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        AudioResourceId = audioResourceId;
    }


    /**
     * Constructs a new Word object with a Miwork translation, a default
     * translation, an image resource, and an audio resource.
     *
     * @param defaultTranslation the word in the language the user is familiar with.
     * @param miwokTranslation   the word in the Miowk language.
     * @param imageResourceID    the resource ID of the image associated with the word.
     * @param audioResourceId    the resource ID of the audio (pronunciation) associated with the word.
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceID, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        ImageResourceId = imageResourceID;
        AudioResourceId = audioResourceId;
    }


    /**
     * Gets the default translation of the word.
     *
     * @return Miwok text to display in the TextView
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Gets the Miwok translation of the word.
     *
     * @return default text to display in the TextView
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Gets the image associated with the Miwok word
     *
     * @return the resource ID of the image to display with the Miwok word
     */
    public int getImageResourceID() {
        return ImageResourceId;
    }

    /**
     * Returns whether or not an image is available for this word.
     */
    public boolean hasImage() {
        return ImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Gets the audio pronunciation associated with the Miwok word
     *
     * @return the resource ID of the audio to play with the Miwok word
     */
    public int getAudioResourceID() {
        return AudioResourceId;
    }

}
