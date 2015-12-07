package com.foodaholic.foodaholic.util;

import com.foodaholic.foodaholic.model.PlaceData;
import com.google.common.base.Optional;

/**
 * Created by carlos on 12/6/2015.
 */
public class BeaconLookUp {

    public static Optional<PlaceData> getPlaceInformationFromBeacon(String id){
        PlaceData place = new PlaceData();
        switch (id){
            case "f7826da6bc5b71e0893e":
                place.setName("Pearl's Deluxe");
                place.setImageUrl("http://s3-media4.fl.yelpcdn.com/bphoto/mLfBCMDKXahEsT9hfw7nug/l.jpg");
                return Optional.of(place);
            default:
                return Optional.absent();
        }
    }
}
