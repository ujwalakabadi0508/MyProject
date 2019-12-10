package com.futurewise.app.twitter;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Collections;
import java.util.List;
public class TwitterSearchAPI {


    public List<Status> search(String keyword) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("swdEh0nsxEb8uyk7a50gNx7Kd")
                .setOAuthConsumerSecret("FZFHbCMA6dDNoBrAZc1TlaMJZnSvKwouWjBWgK44TU23pOSQMS")
                .setOAuthAccessToken("1385161057-OOJNVWEfi8l7fnPG4wLkJPZr9tJfp4xx1BQ4hjH")
                .setOAuthAccessTokenSecret("TrAecQptsgIgF2rhOE51newtsSNDGGYuSwlDC0rnJRR10");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query(keyword + " -filter:retweets -filter:links -filter:replies -filter:images");
        query.setCount(50);
        query.setLocale("en");
        query.setLang("en");;
        try {
            QueryResult queryResult = twitter.search(query);
            return queryResult.getTweets();
        } catch (TwitterException e) {
            // ignore
            e.printStackTrace();
        }
        return Collections.emptyList();

    }

    public static void main(String[] args) {
        TwitterSearchAPI twitterSearch = new TwitterSearchAPI();
        List<Status> statuses = twitterSearch.search("ICICI");
        int count = 0;
        for (Status status : statuses) {
            count++;
            System.out.println(" @@@@@@@ "+status.getText()+ " count " + count);
        }
    }
//    public static void SentimentAnalysis(String semtimnetAnalysisSentetence){
////    String text = "Those who find ugly meanings in beautiful things are corrupt without being charming.";
//        System.out.println("semtimnetAnalysisSentetence @@@@@@ "+semtimnetAnalysisSentetence);
//        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
//        sentimentAnalyzer.initialize();
//        SentimentResult sentimentResult = sentimentAnalyzer.getSentimentResult(semtimnetAnalysisSentetence);
//
//        System.out.println("Sentiment Score: " + sentimentResult.getSentimentScore());
//        System.out.println("Sentiment Type: " + sentimentResult.getSentimentType());
//        System.out.println("Very positive: " + sentimentResult.getSentimentClass().getVeryPositive()+"%");
//        System.out.println("Positive: " + sentimentResult.getSentimentClass().getPositive()+"%");
//        System.out.println("Neutral: " + sentimentResult.getSentimentClass().getNeutral()+"%");
//        System.out.println("Negative: " + sentimentResult.getSentimentClass().getNegative()+"%");
//        System.out.println("Very negative: " + sentimentResult.getSentimentClass().getVeryNegative()+"%");
//    }



//    ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setDebugEnabled(true)
//                .setOAuthConsumerKey("swdEh0nsxEb8uyk7a50gNx7Kd")
//                .setOAuthConsumerSecret("FZFHbCMA6dDNoBrAZc1TlaMJZnSvKwouWjBWgK44TU23pOSQMS")
//                .setOAuthAccessToken("1385161057-OOJNVWEfi8l7fnPG4wLkJPZr9tJfp4xx1BQ4hjH")
//                .setOAuthAccessTokenSecret("TrAecQptsgIgF2rhOE51newtsSNDGGYuSwlDC0rnJRR10");
//
//        TwitterFactory TF = new TwitterFactory(cb.build());
//        twitter4j.Twitter twitter = TF.getInstance();
//
//        List<Status> status = twitter.getHomeTimeline();
//
//        boolean isArray = true;
//        TwitterSearchAPI twitterSearch = new TwitterSearchAPI();
}