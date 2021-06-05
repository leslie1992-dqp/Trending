package com.example.trending;

public class ListBean {
    private String mName;
    private String mUnknown;
    private String mIntroduction;
    private String mLanguage;
    private String languageColor;
    private String mUrl;
    private int mStars;
    private int mLines;

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getLines() {
        return mLines;
    }

    public int getStars() {
        return mStars;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getUnknown() {
        return mUnknown;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setIntroduction(String introduction) {
        mIntroduction = introduction;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public void setLines(int lines) {
        mLines = lines;
    }

    public void setStars(int stars) {
        mStars = stars;
    }

    public void setUnknown(String unknown) {
        mUnknown = unknown;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getLanguageColor() {
        return languageColor;
    }

    public void setLanguageColor(String languageColor) {
        this.languageColor = languageColor;
    }
}
