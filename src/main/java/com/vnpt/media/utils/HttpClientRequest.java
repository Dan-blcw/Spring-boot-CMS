/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.utils;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
//import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 *
 * @author tung
 */
public class HttpClientRequest {

    public static String sendHttpPostRequestFormData(String url, String lParam, Logger logger, String token) {
        CloseableHttpClient httpClient = null;
        HttpEntity respEntity;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Authorization", token);
            //httpPost
            logger.info("url: " + url + ", request: " + lParam + ", authen: " + token);
            StringEntity params = new StringEntity(lParam, "UTF-8");
            httpPost.setEntity(params);
            response = httpClient.execute(httpPost);
            respEntity = response.getEntity();
            if (respEntity != null) {
                String content = EntityUtils.toString(respEntity, "UTF-8");
                logger.info("Response url:" + url + ", request: " + lParam + ", result: " + content);
                return content;
            }
        } catch (UnsupportedCharsetException | IOException | ParseException e) {
            logger.error("Có lỗi xảy ra: ", e.fillInStackTrace());
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ex) {

                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {

                }
            }
        }

        logger.info("Response url:" + url + ", request: " + lParam + ", result NULL ");
        return null;
    }

    public static String sendHttpPostRequest(String url, String lParam, Logger logger, String token) {
        CloseableHttpClient httpClient = null;
        HttpEntity respEntity;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", token);
            //httpPost
            logger.info("url: " + url + ", request: " + lParam + ", authen: " + token);
            StringEntity params = new StringEntity(lParam, "UTF-8");
            httpPost.setEntity(params);
            response = httpClient.execute(httpPost);
            respEntity = response.getEntity();
            if (respEntity != null) {
                String content = EntityUtils.toString(respEntity, "UTF-8");
                logger.info("Response url:" + url + ", request: " + lParam + ", result: " + content);
                return content;
            }
        } catch (UnsupportedCharsetException | IOException | ParseException e) {
            logger.error("Có lỗi xảy ra: ", e.fillInStackTrace());
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException ex) {

                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {

                }
            }
        }

        logger.info("Response url:" + url + ", request: " + lParam + ", result NULL ");
        return null;
    }

}
