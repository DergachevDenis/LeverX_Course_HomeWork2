package com.dergachev.homework2.dogfarm.place.canteen.feed;

import java.util.Objects;

public abstract class Feed {
    private String feedName;

    public Feed() {
    }

    public Feed(String feedName) {
        this.feedName = feedName;
    }

    public String eatFood() {
        return "I eat delicious "+feedName+".";
    }

    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feed feed = (Feed) o;
        return Objects.equals(feedName, feed.feedName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedName);
    }

    @Override
    public String toString() {
        return "Feed{" +
                "feedName='" + feedName + '\'' +
                '}';
    }
}
