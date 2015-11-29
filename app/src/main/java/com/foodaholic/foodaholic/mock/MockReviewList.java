package com.foodaholic.foodaholic.mock;

import com.foodaholic.foodaholic.model.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maygupta on 11/29/15.
 */
public class MockReviewList {

    public static List<Review> getList() {
        List<Review> reviews = new ArrayList<>();

        reviews.add(new Review(3.5, "Mayank Gupta", "https://scontent.fsnc1-1.fna.fbcdn.net/hprofile-xaf1/v/t1.0-1/p320x320/10968305_1015878085092410_4103411558902970210_n.jpg?oh=3dde591786d08d0df13110ff536a47e1&oe=56F59D55", "16th Nov 2015", "It was cooked with Olive oil and tasted good but it was very spicy!!"));
        reviews.add(new Review(2.5, "Mayank Gupta", "https://scontent.fsnc1-1.fna.fbcdn.net/hprofile-xaf1/v/t1.0-1/p320x320/10968305_1015878085092410_4103411558902970210_n.jpg?oh=3dde591786d08d0df13110ff536a47e1&oe=56F59D55", "16th Nov 2015", "It was cooked with Olive oil and tasted good but it was very spicy!!"));
        reviews.add(new Review(4.5, "Mayank Gupta", "https://scontent.fsnc1-1.fna.fbcdn.net/hprofile-xaf1/v/t1.0-1/p320x320/10968305_1015878085092410_4103411558902970210_n.jpg?oh=3dde591786d08d0df13110ff536a47e1&oe=56F59D55", "16th Nov 2015", "It was cooked with Olive oil and tasted good but it was very spicy!!"));
        reviews.add(new Review(1.5, "Mayank Gupta", "https://scontent.fsnc1-1.fna.fbcdn.net/hprofile-xaf1/v/t1.0-1/p320x320/10968305_1015878085092410_4103411558902970210_n.jpg?oh=3dde591786d08d0df13110ff536a47e1&oe=56F59D55", "16th Nov 2015", "It was cooked with Olive oil and tasted good but it was very spicy!!"));
        reviews.add(new Review(4.9, "Mayank Gupta", "https://scontent.fsnc1-1.fna.fbcdn.net/hprofile-xaf1/v/t1.0-1/p320x320/10968305_1015878085092410_4103411558902970210_n.jpg?oh=3dde591786d08d0df13110ff536a47e1&oe=56F59D55", "16th Nov 2015", "It was cooked with Olive oil and tasted good but it was very spicy!!"));

        return reviews;
    }
}
