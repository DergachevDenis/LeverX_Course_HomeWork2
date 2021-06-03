package com.dergachev.homework2.dogfarm.place.canteen;

import com.dergachev.homework2.dogfarm.dog.Age;
import com.dergachev.homework2.dogfarm.place.canteen.Feed.Feed;
import com.dergachev.homework2.dogfarm.place.canteen.Feed.FeedForAdultDogs;
import com.dergachev.homework2.dogfarm.place.canteen.Feed.FeedForElderlyDogs;
import com.dergachev.homework2.dogfarm.place.canteen.Feed.FeedForPuppies;

public class Kitchen {

    private String namePuppyFeed = "Royal Canin Puppy";
    private String nameAdultFeed = "Acana Adult Dog";
    private String nameElderlyFeed = "Pro Plan Elderly Dog";

    public Kitchen() {
    }

    public Feed createFeed(Age typeFeed) {
        Feed feed = switch (typeFeed) {
            case PUPPY -> new FeedForPuppies(namePuppyFeed);
            case ADULTDOG -> new FeedForAdultDogs(nameAdultFeed);
            case ELDERLYDOG -> new FeedForElderlyDogs(nameElderlyFeed);
        };

        return feed;
    }

    public String getNamePuppyFeed() {
        return namePuppyFeed;
    }

    public void setNamePuppyFeed(String namePuppyFeed) {
        this.namePuppyFeed = namePuppyFeed;
    }

    public String getNameAdultFeed() {
        return nameAdultFeed;
    }

    public void setNameAdultFeed(String nameAdultFeed) {
        this.nameAdultFeed = nameAdultFeed;
    }

    public String getNameElderlyFeed() {
        return nameElderlyFeed;
    }

    public void setNameElderlyFeed(String nameElderlyFeed) {
        this.nameElderlyFeed = nameElderlyFeed;
    }
}
