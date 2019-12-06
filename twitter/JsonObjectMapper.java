package com.futurewise.app.twitter;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonObjectMapper {

        private String full_message;
        private String nick_name;
        private int TOTAL_MSG_CNT;

        public int getTOTAL_MSG_CNT() {
            return TOTAL_MSG_CNT;
        }

        public void setTOTAL_MSG_CNT(int TOTAL_MSG_CNT) {
            this.TOTAL_MSG_CNT = TOTAL_MSG_CNT;
        }

        public String getFull_message() {
            return full_message;
        }

        public void setFull_message(String full_message) {
            this.full_message = full_message;
        }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}

