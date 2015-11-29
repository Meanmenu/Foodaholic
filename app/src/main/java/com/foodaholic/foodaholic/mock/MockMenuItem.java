package com.foodaholic.foodaholic.mock;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 11/25/2015.
 */
public class MockMenuItem {
    public static List<MenuItem> menuMock1(){
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem(R.drawable.apple_bru+"", "Apple Onion Bruchesttas", null, null, 87, "Indian"));
        menuItemList.add(new MenuItem(R.drawable.qd+"", "Quesadillas", null, null, 90, "Asian"));
        menuItemList.add(new MenuItem(R.drawable.qd2+"", "Tortilla Pinwheels", null, null, 85, "American"));
        menuItemList.add(new MenuItem(R.drawable.pearl_deluxe_burguer+"", "Pearl Deluxe Burguer", null, null, 43, "Mexican"));
        return menuItemList;
    }

    public static List<MenuItem> menuMock2(){
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem(R.drawable.indian+"", "Spicy potatoes", null, null, 87, "Indian"));
        menuItemList.add(new MenuItem(R.drawable.onion_ring+"", "Onion Rings", null, null, 90, "Asian"));
        menuItemList.add(new MenuItem(R.drawable.teriyaki_burguer+"", "Teriyaki Burguer", null, null, 85, "American"));
        menuItemList.add(new MenuItem(R.drawable.pearl_deluxe_burguer+"", "Pearl Deluxe Burguer", null, null, 43, "Mexican"));
        return menuItemList;
    }

    public static List<MenuItem> menuMock3(){
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem(R.drawable.indian+"", "Spicy potatoes", null, null, 87, "Indian"));
        menuItemList.add(new MenuItem(R.drawable.onion_ring+"", "Onion Rings", null, null, 90, "Asian"));
        menuItemList.add(new MenuItem(R.drawable.teriyaki_burguer+"", "Teriyaki Burguer", null, null, 85, "American"));
        menuItemList.add(new MenuItem(R.drawable.pearl_deluxe_burguer+"", "Pearl Deluxe Burguer", null, null, 43, "Mexican"));
        return menuItemList;
    }
}
