package com.foodaholic.foodaholic.mock;

import com.foodaholic.foodaholic.R;
import com.foodaholic.foodaholic.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 11/25/2015.
 */
public class MockMenuItem {
    public static List<MenuItem> menuMock(){
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem(R.drawable.fries+"", "Fries", null, null));
        menuItemList.add(new MenuItem(R.drawable.onion_ring+"", "Onion Rings", null, null));
        menuItemList.add(new MenuItem(R.drawable.teriyaki_burguer+"", "Teriyaki Burguer", null, null));
        menuItemList.add(new MenuItem(R.drawable.pearl_deluxe_burguer+"", "Pearl Deluxe Burguer", null, null));
        return menuItemList;
    }
}
