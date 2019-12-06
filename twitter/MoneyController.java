package com.futurewise.app.twitter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

@Component
public class MoneyController {

    @Autowired
    TwitterAuth twitterAuth;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        String s1 = null;
        String doc = null;
        try {
            doc = Jsoup.connect("https://mmb.moneycontrol.com/index.php?q=boarder/ajax_call&section=get_messages&is_boarder_page=1&offse" +
                    "t=&lmid=&isp=0&gmt=my_post&uid=valine&pgno=1").ignoreContentType(true).execute().body(); // URL shortened!
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonObjectMapper[] jsonObjectMapper = new JsonObjectMapper[0];
        try {
            jsonObjectMapper = objectMapper.readValue(doc, JsonObjectMapper[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (JsonObjectMapper jsonObjectMapper1 : jsonObjectMapper) {
            s1 = jsonObjectMapper1.getFull_message().replaceAll("[^a-zA-Z0-9\\s+]" , "");
//            passingString(s1);

            s1 = TwitterAuth.removeTheStopWords(s1);
            String items1 = TwitterAuth.getListOfStocks();
            String[] items2 = items1.split(",");
            for(String str3 : items2) {
//                   System.out.println("s1@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+ s1);
                if(s1.contains(str3)){

                    TwitterAuth.SentimentAnalysis(s1);
                    TwitterAuth.insertedTheMethod(jsonObjectMapper1.getNick_name() , s1);
                }
            }
            System.out.println(jsonObjectMapper1.getFull_message());
//            System.out.println("s1 @@@@@@ "+ s1);
        }
//        System.out.println("doc @@@ " + doc);
    }
}