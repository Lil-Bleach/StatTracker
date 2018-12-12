package com.example.aceraspire.stattracker;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ow_profile extends OverwatchActivity {
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

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelIcon() {
        return levelIcon;
    }

    public void setLevelIcon(String levelIcon) {
        this.levelIcon = levelIcon;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public String getPrestigeIcon() {
        return prestigeIcon;
    }

    public void setPrestigeIcon(String prestigeIcon) {
        this.prestigeIcon = prestigeIcon;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRatingIcon() {
        return ratingIcon;
    }

    public void setRatingIcon(String ratingIcon) {
        this.ratingIcon = ratingIcon;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void parse(String json) {
        JsonParser parser = new JsonParser();
        JsonObject profile = parser.parse(json).getAsJsonObject();
        setIcon(profile.get("icon").getAsString());
        setName(profile.get("name").getAsString());
        setLevel(profile.get("level").getAsInt());
    }



}
