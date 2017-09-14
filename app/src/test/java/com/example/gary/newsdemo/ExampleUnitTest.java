package com.example.gary.newsdemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        boolean flag = false;
        flag = true;
        if (flag) {
            System.out.print(flag);
        }
        else {
            System.out.print(flag+"1");
        }

    }
}