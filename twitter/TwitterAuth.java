package com.futurewise.app.twitter;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/{deviceType}/twitter")
@Component
public class TwitterAuth {

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public static void main(String[] args) throws TwitterException, TwitterException, ClassNotFoundException, SQLException, IOException {
        String findTheWord = "RT @bsindia: BreakingNews | RBI springs surprise, maintains repo rate at 5.15%"     ;
        String stopWords = "";
        String s1 = null;
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("swdEh0nsxEb8uyk7a50gNx7Kd")
                .setOAuthConsumerSecret("FZFHbCMA6dDNoBrAZc1TlaMJZnSvKwouWjBWgK44TU23pOSQMS")
                .setOAuthAccessToken("1385161057-OOJNVWEfi8l7fnPG4wLkJPZr9tJfp4xx1BQ4hjH")
                .setOAuthAccessTokenSecret("TrAecQptsgIgF2rhOE51newtsSNDGGYuSwlDC0rnJRR10");

        TwitterFactory TF = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = TF.getInstance();

        List<Status> status = twitter.getHomeTimeline();

//        String items1 = getListOfStocks();
//        String[] items2 = items1.toLowerCase().split(",");
//        String[] allWords = items1.toLowerCase().split(",");
//        String str[] = new String[items1.size()];
//
//        // ArrayList to Array Conversion
//        for (int j = 0; j < items1.size(); j++) {
//            // Assign each value to String array
//            str[j] = String.valueOf(items1.get(j));
//        }
//
////            String [] items = (String[]) items1;
//        System.out.println("items ##### "+ str);
        boolean isArray = true;
        for (Status tweetpost : status) {
            s1 = tweetpost.getText();
            s1 = s1.replaceAll("[^a-zA-Z0-9\\s+]" , "");
            System.out.println("s1 @@@@@@ "+ s1);
            s1 = removeTheStopWords(s1);
            String items1 = getListOfStocks();
            String[] items2 = items1.split(",");
            for(String str3 : items2) {
//                   System.out.println("s1@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+ s1);
                if(s1.contains(str3)){
                    System.out.println(" there is twitter for mutualFund@@@ "+tweetpost.getUser().getName() + " : " + tweetpost.getText() + "-------"+ tweetpost.getUser().getTimeZone());
                    SentimentAnalysis(s1);
                    insertedTheMethod(tweetpost.getUser().getName() , s1);
                }
            }
        }
//        SentimentAnalysis(s1);
    }


    public static String removeTheStopWords(String text) throws IOException {
        List<String> stopWords = null;
        stopWords = Files.readAllLines(Paths.get("/home/ifanow/english_stopwords.txt"));
//        System.out.println("stopWords @@@ "+ stopWords);
        String original = text;

//        String target = "quick brown fox jumps lazy dog";
        String[] allWords = original.trim().split(" ");
//        System.out.println("allWords @@@@ "+ allWords.toString());
        StringBuilder builder = new StringBuilder();
        for(String word : allWords) {
//            word = word.replaceFirst("#" , " ").trim();
//            System.out.println("wordssss @@@@ "+ word);
            if(!stopWords.contains(word)) {
                original = original.replaceAll("[^a-zA-Z0-9\\s+]" , "");
//                builder.append(word);
//                builder.append(' ');
            }
        }

        String result = original.toString().trim();
//        System.out.println("reuslt ##### "+ result);
        return  result;

    }

    public static String getListOfStocks() throws SQLException, ClassNotFoundException {
//        ArrayList<StockList> st1 = new ArrayList<>();
        String str = null;
        String str1 = "";
//        StockList st = new StockList();
        Class.forName("com.mysql.jdbc.Driver");

        Connection con= DriverManager.getConnection("jdbc:mysql://proddbv1.cqudi11uwk5f.us-east-1.rds.amazonaws.com/money_manager_test?noAccessToProcedureBodies=true","devuser","devuser@03");

        PreparedStatement stmt=con.prepareStatement("SELECT name_of_amc FROM `amc_name_of_stocks`");
        ResultSet rs=stmt.executeQuery();
        while(rs.next()){
            str = rs.getString("name_of_amc").concat(",");
            str1 = str1.concat(str);
//        st1.add(st);
        }
        con.close();
        return  str1;
    }

    public static void insertedTheMethod(String name, String text) throws ClassNotFoundException, SQLException {
//            System.out.println("name@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+ name );

        Class.forName("com.mysql.jdbc.Driver");

        Connection con= DriverManager.getConnection("jdbc:mysql://proddbv1.cqudi11uwk5f.us-east-1.rds.amazonaws.com/money_manager_test?noAccessToProcedureBodies=true","devuser","devuser@03");

        PreparedStatement stmt=con.prepareStatement("INSERT INTO `twite_fetch_data`( `user_name`, `user_text`) VALUES (?,?)");

        stmt.setString(1 , name);
        stmt.setString(2 , text);
        int i=stmt.executeUpdate();
//            System.out.println(i+" records inserted");

        con.close();

    }
    public static void SentimentAnalysis(String semtimnetAnalysisSentetence){
//    String text = "Those who find ugly meanings in beautiful things are corrupt without being charming.";
        System.out.println("semtimnetAnalysisSentetence @@@@@@ "+semtimnetAnalysisSentetence);
        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
        sentimentAnalyzer.initialize();
        SentimentResult sentimentResult = sentimentAnalyzer.getSentimentResult(semtimnetAnalysisSentetence);

        System.out.println("Sentiment Score: " + sentimentResult.getSentimentScore());
        System.out.println("Sentiment Type: " + sentimentResult.getSentimentType());
        System.out.println("Very positive: " + sentimentResult.getSentimentClass().getVeryPositive()+"%");
        System.out.println("Positive: " + sentimentResult.getSentimentClass().getPositive()+"%");
        System.out.println("Neutral: " + sentimentResult.getSentimentClass().getNeutral()+"%");
        System.out.println("Negative: " + sentimentResult.getSentimentClass().getNegative()+"%");
        System.out.println("Very negative: " + sentimentResult.getSentimentClass().getVeryNegative()+"%");
    }

}
