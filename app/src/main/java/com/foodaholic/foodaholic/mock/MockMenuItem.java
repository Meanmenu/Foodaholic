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
                                   "http://img.grouponcdn.com/iam/s62qEmSG18daPk9mpumm/t460x279/qu-725x483.jpg",
                                    "http://img.grouponcdn.com/iam/wja5eT4ut4LxQQHzER14/t460x279/vQ-2048x1229.jpg",
                                    "http://img.grouponcdn.com/iam/wpk1NNTmZnhPADGLQRe4/t460x279/np-2048x1242.jpg",
                                    "http://img.grouponcdn.com/deal/6UJpyjN4yiATuJMkDfGP/t460x279/m1-700x420.jpg"

                        )
                , "Apple Onion Bruschettas", null, null, 4.2, "Indian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/test/59yo6QNjkJFSWBa9F8x8/t460x279/Js-440x267.jpg",
                        "http://img.grouponcdn.com/deal/9ziMQTx7aMfgMktgJmzT/t460x279/Av-1500x900.jpg",
                        "http://img.grouponcdn.com/iam/59sm1sqpG4o2R63RWeGj/t460x279/ZS-5760x3456.jpg",
                        "http://img.grouponcdn.com/iam/eZ2uqnui3pzbGuvKWoBo/t460x279/UZ-5677x3406.jpg",
                        "http://img.grouponcdn.com/deal/9ziMQTx7aMfgMktgJmzT/t460x279/Av-1500x900.jpg",
                        "http://img.grouponcdn.com/iam/i8H4WHBwgm3nxzCWkMcR/t460x279/qT-2048x1229.jpg",
                        "http://img.grouponcdn.com/deal/24KZCc5hcvJdX1ZR1K1P/t460x279/fM-960x582.jpg"),
                "Quesadillas", null, null, 3.5, "Asian"));
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
                Lists.newArrayList("http://img.grouponcdn.com/iam/cxCuX9fjuwK9UWpPv1RT/t460x279/NE-4500x2700.jpg"),
                "Spinach Lettuce", null, null, 4.2, "Indian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/iam/v1i844qxvt7cfJEH1A8Y/t460x279/vV-5000x3000.jpg")
                , "Greek Salad", null, null, 3.5, "Asian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/iam_raw/nHtdejmg2CMkJHrzsNTW/t500x300/o7-3504x2336.jpg")
                , "Italian Salad", null, null, 2.5, "American"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/iam_raw/pZux2WPt2XmuuAZNS6mf/t500x300/Pf-3504x2336.jpg")
                , "Gourmet Salad", null, null, 4.3, "Mexican"));
        return menuItemList;
    }

    public static List<MenuItemData> menuMock3(){
        List<MenuItemData> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/iam/7X5xNPC5Gb8tZ3xaS8x8/t460x279/UP-2048x1229.jpg")
                , "Grilled Veggies SW", null, null, 4.2, "Indian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/iam/auf24fR5zeXmFDp13CjR/t460x279/at-5000x3000.jpg")
                , "Deli sandwich", null, null, 3.5, "Asian"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/iam_raw/bBjxnTEnriBSiqTVRJ6c/t500x300/uY-4288x2848.jpg")
                , "Elvis", null, null, 2.5, "American"));
        menuItemList.add(new MenuItemData(
                Lists.newArrayList("http://img.grouponcdn.com/iam/2cfgwVfBEGx1opwTfLrH/t460x279/u6-2048x1229.jpg")
                , "Falafel", null, null, 4.3, "Mexican"));
        return menuItemList;
    }
}
