package com.smartpack.packagemanager.utils.SerializableItems;

import java.io.Serializable;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on October 25, 2025
 */
public class MenuItems implements Serializable {

    private final String titleText, descriptionText;
    private final int id;

    public MenuItems(String titleText, String descriptionText, int id) {
        this.titleText = titleText;
        this.descriptionText = descriptionText;
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getDescription() {
        return descriptionText;
    }

    public String getTile() {
        return titleText;
    }

}