package com.foodaholic.foodaholic.mock;

import com.foodaholic.foodaholic.model.MenuItemData;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 11/25/2015.
 */
public class MockMenuItem {
    public static List<MenuItemData> menuMock1(){
        List<MenuItemData> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/9RMfnHpj4RgKkzd9Fc34/t460x279/Tf-2048x1242.jpg",
                                   "https://www.raleys.com/images/imagesrecipes/3565092.jpg" )
                , "Apple Onion Bruschettas", null, null, 4.2, "Indian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/59yo6QNjkJFSWBa9F8x8/t460x279/Js-440x267.jpg",
                        "http://www.quesadillasmari.com/wp-content/uploads/2011/02/quesadillas-Asadas-mari.jpg",
                        "https://pastaprincessandmore.files.wordpress.com/2013/03/pulled-pork-and-peach-salsa-quesadilla-blog-h.jpg"), "Quesadillas", null, null, 3.5, "Asian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/86ZTybjEnFMBXtkUVvth/t460x279/9i-2048x1242.jpg"),
                "Tortilla Pinwheels", null, null, 2.5, "American"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/eDa8kg6NSao4koQgrLjm/t460x279/tV-2048x1242.jpg"), "Samosa", null, null, 4.3, "Mexican"));
        return menuItemList;
    }

    public static List<MenuItemData> menuMock2(){
        List<MenuItemData> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/9RMfnHpj4RgKkzd9Fc34/t460x279/Tf-2048x1242.jpg"),
                "Apple Onion Bruschettas", null, null, 4.2, "Indian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/59yo6QNjkJFSWBa9F8x8/t460x279/Js-440x267.jpg")
                , "Quesadillas", null, null, 3.5, "Asian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/86ZTybjEnFMBXtkUVvth/t460x279/9i-2048x1242.jpg")
                , "Tortilla Pinwheels", null, null, 2.5, "American"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/eDa8kg6NSao4koQgrLjm/t460x279/tV-2048x1242.jpg")
                , "Samosa", null, null, 4.3, "Mexican"));
        return menuItemList;
    }

    public static List<MenuItemData> menuMock3(){
        List<MenuItemData> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/9RMfnHpj4RgKkzd9Fc34/t460x279/Tf-2048x1242.jpg")
                , "Apple Onion Bruschettas", null, null, 4.2, "Indian"));
        menuItemList.add(new MenuItemData(Lists.newArrayList("http://img.grouponcdn.com/test/59yo6QNjkJFSWBa9F8x8/t460x279/Js-440x267.jpg")
                , "Quesadillas", null, null, 3.5, "Asian"));
        menuItemList.add(new MenuItemData(Lists.newArrayList("http://img.grouponcdn.com/test/86ZTybjEnFMBXtkUVvth/t460x279/9i-2048x1242.jpg")
                , "Tortilla Pinwheels", null, null, 2.5, "American"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/eDa8kg6NSao4koQgrLjm/t460x279/tV-2048x1242.jpg")
                , "Samosa", null, null, 4.3, "Mexican"));
        return menuItemList;
    }
}
