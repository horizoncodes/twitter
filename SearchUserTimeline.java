/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twitterchallenge;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class SearchUserTimeline {

    public static void main(String[] args) {
        // gets Twitter instance with default credentials
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("RotRyyJTnMAQYQrNQJityJ4Ig")
                .setOAuthConsumerSecret("3zncb4ldVuGQm1ctZGz5IDqLMV7GT9O5JKthFkEFKpMtY9SOA9")
                .setOAuthAccessToken("4017909975-MqfxX5LNK73gFnjRmrLD8AWof7Y5OVzGvMbK57D")
                .setOAuthAccessTokenSecret("z9a5de8KL9nVMlr0C4iJjzx8mLaAqf0FVxvYwzUpFV2KX");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            String user = "salesforce";
            List<Status> statuses = twitter.getUserTimeline(user, new Paging(1, 10));
            System.out.println("Showing @" + user + "'s user timeline.");
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