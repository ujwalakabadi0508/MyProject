package com.futurewise.app.twitter;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/{deviceType}/twitter")
@Component
public class TwitterAuth {

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public static void main(String[] args) throws TwitterException, TwitterException, ClassNotFoundException, SQLException, IOException {

        String s1 = null;

        String items1 = getListOfStocks();
        String[] items2 = items1.split(",");
        for(String str : items2) {

            List<Status> statuses = search(str);
            for (Status tweetpost : statuses) {

                s1 = tweetpost.getText();
                s1 = s1.replaceAll("[^a-zA-Z0-9\\s+]", "");

                System.out.println("s1 @@@@@@ " + s1);
                s1 = removeTheStopWords(s1);


                String nagetiveWords = getNagetive();
                String positiveWords = getPositive();

                String[] nagetiveArray = nagetiveWords.split(",");
                String[] postiveArray = positiveWords.split(",");
                int count = 0;
                for (String str3 : items2) {

                    if (s1.contains(str3)) {
                        count++;
                        String nagetivePositiveWords = SentimentAnalysis(nagetiveArray, postiveArray, s1);
                        System.out.println(" there is twitter for mutualFund@@@ " + tweetpost.getUser().getName() + " : " + tweetpost.getText() + "-------" + tweetpost.getUser().getTimeZone());
                        if(count == 1) {
                            insertedTheMethod(tweetpost.getUser().getName(), s1, nagetivePositiveWords);
                        }
                    }

                }
            }
        }
    }



    public static String SentimentAnalysis(String[] nagetiveArray, String[] positiveArrays, String tweetStr) {
        int countP = 0;
        int countN = 0;

        for (String str : positiveArrays){
            if(tweetStr.contains(str)){
                countP++;
            }
        }
        for(String str1 :nagetiveArray){
            if(tweetStr.contains(str1)){
                countN++;
            }
        }

        if(countP > countN){
            System.out.println("count @@@@@@ "+ countN + "  "+ countP);
            return "positive";
        }else if(countP < countN){
            return "nagetive";
        }else{
            return "nutral";
        }
    }


    public static String removeTheStopWords(String text) throws IOException {
        List<String> stopWords = null;
        String original = text;
        stopWords = Files.readAllLines(Paths.get("/home/ifanow/english_stopwords.txt"));

        String[] allWords = original.trim().split(" ");
        StringBuilder builder = new StringBuilder();

        for(String word : allWords) {
            if(!stopWords.contains(word)) {
                original = original.replaceAll("[^a-zA-Z0-9\\s+]" , "");
            }
        }

        String result = original.toString().trim();
        return  result;
    }

    public static String getListOfStocks() throws SQLException, ClassNotFoundException {
        String str = null;
        String str1 = "";
        Class.forName("com.mysql.jdbc.Driver");

        Connection con= DriverManager.getConnection("jdbc:mysql://proddbv1.cqudi11uwk5f.us-east-1.rds.amazonaws.com/money_manager_test?noAccessToProcedureBodies=true","devuser","devuser@03");

        PreparedStatement stmt=con.prepareStatement("SELECT name_of_amc FROM `amc_name_of_stocks`");
        ResultSet rs=stmt.executeQuery();

        while(rs.next()){
            str = rs.getString("name_of_amc").concat(",");
            str1 = str1.concat(str);
        }
        con.close();
        return  str1;
    }

    public static void insertedTheMethod(String name, String text, String positiveNagetiveStr ) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        Connection con= DriverManager.getConnection("jdbc:mysql://proddbv1.cqudi11uwk5f.us-east-1.rds.amazonaws.com/money_manager_test?noAccessToProcedureBodies=true","devuser","devuser@03");

        PreparedStatement stmt=con.prepareStatement("INSERT INTO `twitter_fetch_data`( `user_name`, `user_text` , positive_nagetive_sign) VALUES (?,?,?)");

        stmt.setString(1 , name);
        stmt.setString(2 , text);
        stmt.setString(3 , positiveNagetiveStr);
        int i=stmt.executeUpdate();

        con.close();

    }

    public static String getNagetive() throws  ClassNotFoundException, SQLException {
        String str = null;
        String str1 = "";
        Class.forName("com.mysql.jdbc.Driver");

        Connection con= DriverManager.getConnection("jdbc:mysql://proddbv1.cqudi11uwk5f.us-east-1.rds.amazonaws.com/money_manager_test?noAccessToProcedureBodies=true","devuser","devuser@03");

        PreparedStatement stmt=con.prepareStatement("SELECT  `negative_words` FROM `nagetive_words`");
        ResultSet rs=stmt.executeQuery();

        while(rs.next()){
            str = rs.getString("negative_words").concat(",");
            str1 = str1.concat(str);
//        st1.add(st);
        }
        con.close();
        return  str1;

    }

    public static String getPositive() throws  ClassNotFoundException, SQLException {
        String str = null;
        String str1 = "";
        Class.forName("com.mysql.jdbc.Driver");

        Connection con= DriverManager.getConnection("jdbc:mysql://proddbv1.cqudi11uwk5f.us-east-1.rds.amazonaws.com/money_manager_test?noAccessToProcedureBodies=true","devuser","devuser@03");

        PreparedStatement stmt=con.prepareStatement("SELECT positive_words FROM `positive_words` ");
        ResultSet rs=stmt.executeQuery();

        while(rs.next()){
            str = rs.getString("positive_words").concat(",");
            str1 = str1.concat(str);
        }
        con.close();
        return  str1;
    }

    public static List<Status> search(String keyword) {
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

}
