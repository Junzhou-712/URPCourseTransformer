package httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import util.Encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginHttpClient {
    /**
    * @ClassName: LoginHttpClient
    * @Description: simulate to login urp sys
    * @author: joe_su
    * @date: 1:34 PM 2022/4/29
    */
    public String loginSystem(String userId, String password, String captcha) {
        //create a HttpClient Object
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //create a POST request
        HttpPost httpPost = new HttpPost("http://zhjw.scu.edu.cn/login");
        String encryptedPasswd = Encrypt.digestString(password);
        List<NameValuePair> nvpr = new ArrayList<NameValuePair>();
        nvpr.add(new BasicNameValuePair("j_username",userId));
        nvpr.add(new BasicNameValuePair("j_password",encryptedPasswd));
        nvpr.add(new BasicNameValuePair("j_captcha",captcha));
        nvpr.add(new BasicNameValuePair("_spring_security_remember_me","on"));

        try {
            //set the request body
            httpPost.setEntity(new UrlEncodedFormEntity(nvpr,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //response
        CloseableHttpResponse response = null;
        //reponse data
        String data = null;
        try {
            //send the request, receive the response
            response = httpClient.execute((httpPost));
            if(response.getStatusLine().getStatusCode() == 200) {
                //login successfully
                HttpEntity entity = response.getEntity();
                data = EntityUtils.toString(entity,"utf-8");
            } else {
                data = "failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
