package com.example.aceraspire.stattracker;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class profile2 extends FortniteSignInActivity {

    public String icon;
    public String name;
    public int level;
    public String levelIcon;
    public int prestige;
    public String prestigeIcon;
    public int rating;
    public String ratingIcon;
    public int gamesWon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }
}
