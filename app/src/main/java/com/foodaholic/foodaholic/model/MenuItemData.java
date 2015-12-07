package com.foodaholic.foodaholic.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MenuItemData implements Parcelable {
    private List<String> pictureUrlList; //TODO this should be a list
    private String itemName;
    private List<Review> reviews; //TODO this should be a complex object
    private String description;
    private double score;
    private String cuisine;
    private String type;

    public MenuItemData(List<String> pictureUrlList,
                        String itemName,
                        List<Review> reviews,
                        String description,
                        double score,
                        String cuisine,
                        String type) {
        this.pictureUrlList = pictureUrlList;
        this.itemName = itemName;
        this.reviews = reviews;
        this.description = description;
        this.score = score;
        this.cuisine = cuisine;
        this.type = type;
    }


    public List<String> getPictureUrlList() {
        return pictureUrlList;
    }

    public void setPictureUrlList(List<String> pictureUrlList) {
        this.pictureUrlList = pictureUrlList;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MenuItemData{" +
                "pictureUrlList='" + pictureUrlList + '\'' +
                ", itemName='" + itemName + '\'' +
                ", reviews=" + reviews +
                ", description='" + description + '\'' +
                ", score=" + score +
                ", cuisine='" + cuisine + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.pictureUrlList);
        dest.writeString(this.itemName);
        dest.writeList(this.reviews);
        dest.writeString(this.description);
        dest.writeDouble(this.score);
        dest.writeString(this.cuisine);
        dest.writeString(this.type);
    }

    protected MenuItemData(Parcel in) {
        this.pictureUrlList = in.createStringArrayList();
        this.itemName = in.readString();
        this.reviews = new ArrayList<Review>();
        in.readList(this.reviews, List.class.getClassLoader());
        this.description = in.readString();
        this.score = in.readDouble();
        this.cuisine = in.readString();
        this.type = in.readString();
    }

    public static final Creator<MenuItemData> CREATOR = new Creator<MenuItemData>() {
        public MenuItemData createFromParcel(Parcel source) {
            return new MenuItemData(source);
        }

        public MenuItemData[] newArray(int size) {
            return new MenuItemData[size];
        }
    };
}
