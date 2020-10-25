package com.example.eurekaribbonclient;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.security.RunAs;
import java.util.*;
import java.util.concurrent.Callable;

@SpringBootTest
public class EurekaRibbonClientApplicationTests implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }

    public EurekaRibbonClientApplicationTests() {
    }

    @Test
    public synchronized void contextLoads() {
        String a = "P1";
        String b = new String("P1");
        String c = "P" + "1";
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(a==a.intern());
        B b1 = new B();
        A a2 = new A(1);
        System.out.println(reverse("123"));

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
