package com.twitterchallenge;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Date;
import java.util.List;

public class StatusFetchTask implements Runnable{
    private Twitter twitter;

    StatusFetchTask()
    {
        // gets Twitter instance with default credentials
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("RotRyyJTnMAQYQrNQJityJ4Ig")
                .setOAuthConsumerSecret("3zncb4ldVuGQm1ctZGz5IDqLMV7GT9O5JKthFkEFKpMtY9SOA9")
                .setOAuthAccessToken("4017909975-MqfxX5LNK73gFnjRmrLD8AWof7Y5OVzGvMbK57D")
                .setOAuthAccessTokenSecret("z9a5de8KL9nVMlr0C4iJjzx8mLaAqf0FVxvYwzUpFV2KX");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    @Override
    public void run()
    {
        try {
            String user = "salesforce";
            List<Status> statuses = twitter.getUserTimeline(user, new Paging(1, 10));
            System.out.println("Showing @" + user + "'s user timeline.");
            System.out.println("The time is : " + new Date());
            for (Status status : statuses) {
                StringBuilder line = new StringBuilder("Name: " + status.getUser().getName() + "Image:" + status.getUser().getMiniProfileImageURL() +
                        "@" + status.getUser().getScreenName() + " - " + status.getText() + " On: "+ status.getCreatedAt());
                if (status.isRetweeted()) {
                    line.append(" Retweeted "+status.getRetweetCount()+ " times.");
                }
                System.out.println(line);
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}
