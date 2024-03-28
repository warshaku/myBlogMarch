package com.myBlogMarch.myBlogMarch.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String message;
   private Date date;

   private String url;
//    public ErrorDetails(String message, Date date, String url) {
//        this.message = message;
//        this.date = date;
//        this.url = url;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
