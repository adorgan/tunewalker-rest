package com.tunewalker.rest.util;

public class EmailSubscription {

    private final String hash;
    private final String email;

    public EmailSubscription(String hash, String email){
        this.hash = hash;
        this.email = email;
    }

    public String getHTML(){
        return "<p>Thank you for subscribing! Everytime I publish a new post, you'll get an email notification. " +
                "Feel free to unsubscribe at any time by clicking the link below.&nbsp;</p>\n" +
                "<p>Thank!</p>\n" +
                "<p>-Tim</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p style=\"text-align: center;\"><a clicktracking=\"off\" href=\"" +
                "http://localhost:8081/api/unsubscribe?email=" +
                email +
                "&hash=" +
                hash +
                "\">Unsubscribe</a></p>";
    }
}
