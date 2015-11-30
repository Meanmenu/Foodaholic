package com.foodaholic.foodaholic.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.foodaholic.foodaholic.R;

import java.util.List;

public class MenuItem implements Parcelable {
    private String pictureUrlList; //TODO this should be a list
    private String itemName;
    private List<Review> reviews; //TODO this should be a complex object
    private String description;
    private double score;
    private String cuisine;

    public MenuItem(String pictureUrlList,
                    String itemName,
                    List<Review> reviews,
                    String description,
                    double score,
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
                ", reviews_fragment='" + reviews + '\'' +
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
        dest.writeString(this.description);
        dest.writeDouble(this.score);
    }

    public MenuItem() {
    }

    private MenuItem(Parcel in) {
        this.pictureUrlList = in.readString();
        this.itemName = in.readString();
        this.description = in.readString();
        this.score = in.readDouble();
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
        return String.valueOf(score);
    }

    public String getCuisine() {
        return cuisine;
    }

    public int getColorForScore() {
        if( score >= 4.0 ) {
            return R.color.dark_green;
        } else if( score >= 3.0 ) {
            return R.color.green;
        }

        return R.color.orange;
    }
}
