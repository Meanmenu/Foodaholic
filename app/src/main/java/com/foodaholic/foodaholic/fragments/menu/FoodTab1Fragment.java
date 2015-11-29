package com.foodaholic.foodaholic.fragments.menu;

import com.foodaholic.foodaholic.mock.MockMenuItem;
import com.foodaholic.foodaholic.model.MenuItem;

import java.util.List;

/**
 * Created by maygupta on 11/28/15.
 */
public class FoodTab1Fragment extends FoodFragment {


    @Override
    protected List<MenuItem> getMenuItemList() {
        return MockMenuItem.menuMock1();
    }
}
