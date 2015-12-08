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
    private double price;
    private String cuisine;
    private String type;
    private String calories;
    private String ingredients;

    public String getPrice() {
        return "$" + String.valueOf(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public MenuItemData(List<String> pictureUrlList,
                        String itemName,
                        List<Review> reviews,
                        String description,
                        double score,
                        double price,
                        String cuisine,
                        String type,
                        String calories,
                        String ingredients) {
        this.pictureUrlList = pictureUrlList;
        this.itemName = itemName;
        this.reviews = reviews;
        this.description = description;
        this.score = score;
        this.price = price;
        this.calories = calories;
        this.ingredients = ingredients;
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
        dest.writeDouble(this.price);
        dest.writeString(this.calories);
        dest.writeString(this.ingredients);
    }

    public String getCalories() {
        return calories + " Kcal";
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
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
        this.price = in.readDouble();
        this.calories = in.readString();
        this.ingredients = in.readString();

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
