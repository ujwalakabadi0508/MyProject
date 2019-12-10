package com.futurewise.app.twitter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@Component
@RestController
@RequestMapping("api/v1/{deviceType}/money-control")
public class MoneyController {

    @Autowired
    TwitterAuth twitterAuth;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        String doc = null;
        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offse" +
                    "t=&lmid=&isp=0&gmt=my_post&uid=valine&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset" +
                    "=&lmid=&isp=0&gmt=my_post&uid=neha0103&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset" +
                    "=&lmid=&isp=0&gmt=my_post&uid=arvind151&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset" +
                    "=&lmid=&isp=0&gmt=my_post&uid=aswathiguna11&pgno=").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=announcer_2012&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=ANAND24243&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=anibillion&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=flashstock05&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=uuuthh&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=bablulab&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=amateurinvestor&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=amateurinvestor&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=rdd99&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=rajendra421&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=blogist&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offset=&lmid=&isp=0&gmt=my_post&uid=srisat19&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
            getDataFromMoneyControl(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void getDataFromMoneyControl(String document) {
        try {
            String s1 = null;

            ObjectMapper objectMapper = new ObjectMapper();
            JsonObjectMapper[] jsonObjectMapper = new JsonObjectMapper[0];

            try {
                jsonObjectMapper = objectMapper.readValue(document, JsonObjectMapper[].class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (JsonObjectMapper jsonObjectMapper1 : jsonObjectMapper) {
                s1 = jsonObjectMapper1.getFull_message().replaceAll("[^a-zA-Z0-9\\s+]", "");
//            passingString(s1);
                String nagetiveWords = TwitterAuth.getNagetive();
                String positiveWords = TwitterAuth.getPositive();

                String[] nagetiveArray = nagetiveWords.split(",");
                String[] postiveArray = positiveWords.split(",");


                s1 = TwitterAuth.removeTheStopWords(s1);
                String items1 = TwitterAuth.getListOfStocks();
                String[] items2 = items1.split(",");
                for (String str3 : items2) {
                    if (s1.contains(str3)) {
                        String nagetivePositiveWords = TwitterAuth.SentimentAnalysis(nagetiveArray, postiveArray, s1);
                        TwitterAuth.insertedTheMethod(jsonObjectMapper1.getNick_name(), s1, nagetivePositiveWords);
                    }
                }
                System.out.println(jsonObjectMapper1.getFull_message());
            }

        } catch (Exception e) {
            System.out.println("exception occur");
        }
    }

}