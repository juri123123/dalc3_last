package com.example.demo.controller;

import org.apache.catalina.connector.InputBuffer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class APIController {

    @RequestMapping(value = "/test.do", method = RequestMethod.GET)
    public ModelAndView Test() {
        ModelAndView mav = new ModelAndView();

        String url = "http://";
        String sb = "";
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while((line = br.readLine()) != null) {
                sb = sb + line + "\n";
            }
            System.out.println("========br========" + sb.toString());
            if (sb.toString().contains("ok")){
                System.out.println("Test");
            }
            br.close();
            System.out.println("" + sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mav.addObject("test1", sb.toString()); // "test1"는 jsp파일에서 받을때 이름,
        //sb.toString은 value값(여기에선 test)
        mav.addObject("fail", false);
        mav.setViewName("test");   // jsp파일 이름
        return mav;
    }

}