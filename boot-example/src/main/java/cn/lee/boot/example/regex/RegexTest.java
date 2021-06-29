package cn.lee.boot.example.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：lix492
 * 2021/5/31
 */

public class RegexTest {

    /**
     * 提取数字
     */
    @Test
    public void extractNum() {
        String str = "1Hello,Lee,12342";
        Pattern pattern = Pattern.compile("[\\D]");//[^0-9]
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.replaceAll(""));

    }

    @Test
    public void removeNum() {
        String str = "Hello,Lee,1234";
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.replaceAll(""));

    }
}
