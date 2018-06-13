package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */
public class Word {

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /** Default translation for the word */
    private String mDefaultTranslation;

    /**
     * Constructs a new Word object with a Miwork translation and a default
     * translation.
     *
     * @param defaultTranslation the word in the language the user is familiar with.
     * @param miwokTranslation the word in the Miowk language.
     */
    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }


    /**
     * Gets the default translation of the word.
     *
     * @return Miwok text to display in the TextView
     */
    public String getMiwokTranslation () {
        return mMiwokTranslation;
    }

    /**
     * Gets the Miwok translation of the word.
     *
     * @return default text to display in the TextView
     */
    public String getDefaultTranslation () {
        return mDefaultTranslation;
    }
}
