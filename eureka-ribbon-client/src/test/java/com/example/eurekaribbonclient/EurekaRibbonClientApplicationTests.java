package com.example.eurekaribbonclient;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.security.RunAs;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootTest
public class EurekaRibbonClientApplicationTests implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }

    public EurekaRibbonClientApplicationTests() {
    }

    @Test
    public synchronized void contextLoads() throws Exception{
        String a = "P1";
        String b = new String("P1");
        String c = "P" + "1";
        Date date = new Date();
        String date1 = "2020-10-27 17:26";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        date = sdf.parse(date1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Long time1 = calendar.getTimeInMillis();
        calendar.setTime(new Date());
        Long time2 = calendar.getTimeInMillis();
        System.out.println( time2 - time1);
        System.out.println(a==c);
        System.out.println(a==a.intern());
        B b1 = new B();
        A a2 = new A(1);
        System.out.println(reverse("12"));

    }
    public static String reverse(String str){
        if(str==null||str.length()<1)
            return str;
        return reverse(str.substring(1))+str.charAt(0);

    }
    static  class  B{
        Integer id;
    }

}
class A{
    Integer id;

    public A(Integer id) {
        EurekaRibbonClientApplicationTests.B b = new EurekaRibbonClientApplicationTests.B();
        b.id = 1;
        this.id = id;
    }
}
