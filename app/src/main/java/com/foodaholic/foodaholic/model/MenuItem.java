package com.foodaholic.foodaholic.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItem implements Parcelable {
    private String pictureUrlList; //TODO this should be a list
    private String itemName;
    private String reviews; //TODO this should be a complex object
    private String description;
    private float score;
    private String cuisine;

    public MenuItem(String pictureUrlList,
                    String itemName,
                    String reviews,
                    String description,
                    float score,
                    String cuisine) {
        this.pictureUrlList = pictureUrlList;
        this.itemName = itemName;
        this.reviews = reviews;
        this.description = description;
        this.score = score;
        this.cuisine = cuisine;
    }


    public String getPictureUrlList() {
        return pictureUrlList;
    }

    public void setPictureUrlList(String pictureUrlList) {
        this.pictureUrlList = pictureUrlList;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "pictureUrlList='" + pictureUrlList + '\'' +
                ", itemName='" + itemName + '\'' +
                ", reviews='" + reviews + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pictureUrlList);
        dest.writeString(this.itemName);
        dest.writeString(this.reviews);
        dest.writeString(this.description);
    }

    public MenuItem() {
    }

    private MenuItem(Parcel in) {
        this.pictureUrlList = in.readString();
        this.itemName = in.readString();
        this.reviews = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<MenuItem> CREATOR = new Parcelable.Creator<MenuItem>() {
        public MenuItem createFromParcel(Parcel source) {
            return new MenuItem(source);
        }

        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

    public String getScore() {
        return String.valueOf(score) + "%";
    }

    public String getCuisine() {
        return cuisine;
    }
}
