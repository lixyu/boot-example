package cn.lee.boot.example;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author ：lix492
 * 2021/6/21
 */

public class StringExample {


    @Test
    public void reverseStr() {
        String str = "Hello,lee";

        String reverse = new StringBuffer(str).reverse().toString();

        System.out.println("reverse str:" + reverse);

        StringUtils.isBlank(str);
    }

    @Test
    public void getDigit() {
        String a = "11123bbbaaeee199!#@";
        System.out.println(StringUtils.getDigits(a));
        ;
    }

    @Test
    public void getLetter() {
        String a = "11123bbbaaeee199!#@";
        //获取字母
        System.out.println(RegExUtils.removeAll(a, "[^a-zA-Z]"));
        System.out.println(RegExUtils.replaceAll(a, "[^a-zA-Z]", ""));
        System.out.println(RegExUtils.removeAll(a, "a-zA-Z"));
        //获取字母加数字
        System.out.println(RegExUtils.removeAll(a, "[^0-9a-zA-Z]"));
        System.out.println(RegExUtils.replaceAll(a, "[^0-9a-zA-Z]", ""));
    }

    @Test
    public void isLetterOrDigit() {
        String str = "Hello,World";
        System.out.println();
    }

}
