package it.univaq.disim.bioinformatics.melanoq.controller;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

public class Response<R> {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    private static final TimeZone tz = TimeZone.getTimeZone("GMT");

    /*
        "timestamp": "2019-05-31T20:02:10.094+0000",
        "status": 401,
        "error": "Unauthorized",
        "message": "Unauthorized",
        "path": "/melanoq/api/questionnaire/test"
        "data": { ... }
    */

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private R data;

    public Response() {
    }

    public Response(HttpStatus status) {
        Date date = new Date(System.currentTimeMillis());
        sdf.setTimeZone(tz);

        this.status = status.value();
        this.timestamp = sdf.format(date);
        this.message = status.getReasonPhrase();
    }

    public Response(HttpStatus status, HttpServletRequest request) {
        Date date = new Date(System.currentTimeMillis());
        sdf.setTimeZone(tz);

        this.status = status.value();
        this.timestamp = sdf.format(date);
        this.path = request.getRequestURI();
        this.message = status.getReasonPhrase();
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        this.message = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public R getData() {
        return data;
    }

    public void setData(R data) {
        this.data = data;
    }

    public String toString(){
        String s =
                "{\n"+
                "   \"timestamp\": \""+this.getTimestamp()+"\",\n" +
                "   \"status\": "+this.getStatus()+",\n" +
                "   \"error\": \""+this.getError()+"\",\n" +
                "   \"message\": \""+this.getMessage()+"\",\n" +
                "   \"path\": \""+this.getPath()+"\"\n" +
                "}";
        return s;
    }
}
