package com.foodaholic.foodaholic.fragments.menu;

import com.foodaholic.foodaholic.mock.MockMenuItem;
import com.foodaholic.foodaholic.model.MenuItemData;

import java.util.List;

/**
 * Created by maygupta on 11/28/15.
 */
public class FoodTab3Fragment extends FoodFragment {

    @Override
    protected List<MenuItemData> getMenuItemList() {
        return MockMenuItem.menuMock3();
    }
}

