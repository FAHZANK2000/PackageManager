package com.smartpack.packagemanager.utils.SerializableItems;

import android.content.Context;

import java.io.Serializable;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on October 25, 2025
 */
public class MenuItems implements Serializable {

    private final int titleRes, descriptionRes;
    private final int id;

    public MenuItems(int titleRes, int descriptionRes, int id) {
        this.titleRes = titleRes;
        this.descriptionRes = descriptionRes;
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getDescription(Context context) {
        return context.getString(descriptionRes);
    }

    public String getTile(Context context) {
        return context.getString(titleRes);
    }

}