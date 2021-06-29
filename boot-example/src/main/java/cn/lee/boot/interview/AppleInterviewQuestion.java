package cn.lee.boot.interview;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：lix492
 * 2021/5/12
 */


public class AppleInterviewQuestion {

    /**
     * 1.菲波那契数列，每个数一定等于前两个数之和：0，1，1，2，3，5，8，13......
     *     要求写一个函数，根据传入的数字位置（数在数列中的位置从0开始），算出对应的数。f(indexNum)
     *     请给出两种不同的实现方法
     *     示例：传入5，返回5；传入10，返回55.
     */
    @Test
    public void test33() {
        int num=9;
        System.out.println(fibonacciByArray(num));
        System.out.println(fibonacciByRecursion(num));


    }


    //递归算法
    private long fibonacciByRecursion(long num){
        if(num==0||num==1){
            return num;
        }
        return fibonacciByRecursion(num-1)+fibonacciByRecursion(num-2);
    }

    //非递归算法,利用数组
    private int fibonacciByArray(int num){
        if(num==0||num==1){
            return num;
        }
        int[] arr=new int[num+1];
         arr[0]=0;
         arr[1]=1;
        for(int i=2;i<=num;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        System.out.println(Arrays.toString(arr));
        return arr[num];
    }
    /**
     * 2.给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *     说明：本题中，我们将空字符串定义为有效的回文串。
     *     示例 1:
     *     输入: "A man, a plan, a canal: Panama"
     *     输出: true
     *     示例 2:
     *     输入: "race a car"
     *     输出: false
     * @return
     */
    @Test
    public void checkPalindromeString(){
        String str="A man, a plan, a canal: Panama";

        Pattern pattern=Pattern.compile("[^0-9a-zA-Z]");
        Matcher matcher=pattern.matcher(str);
        String filterStr= matcher.replaceAll("").toLowerCase();
        System.out.println(filterStr);

        /**
         * 方法1
         */
        String reverseStr=new StringBuffer(filterStr).reverse().toString();
        System.out.println(reverseStr);
        System.out.println(StringUtils.endsWithIgnoreCase(filterStr,reverseStr));

        /**
         * 方法2
         */
        int len=filterStr.length();
        boolean flag=true;
        for(int i=0;i<=Math.floorDiv(len,2);i++){
            int k=len-1-i;
            System.out.println("i="+i+",char="+filterStr.charAt(i)+","+filterStr.charAt(k));
            if(filterStr.charAt(i)!=filterStr.charAt(k)){
                flag=false;
                break;
            }
        }
        System.out.println(flag);
    }


/*
1.	菲波那契数列，每个数一定等于前两个数之和：0，1，1，2，3，5，8，13......
    要求写一个函数，根据传入的数字位置（数在数列中的位置从0开始），算出对应的数。f(indexNum)
    请给出两种不同的实现方法
    示例：传入5，返回5；传入10，返回55.

2.	给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    说明：本题中，我们将空字符串定义为有效的回文串。
    示例 1:
    输入: "A man, a plan, a canal: Panama"
    输出: true
    示例 2:
    输入: "race a car"
    输出: false

3.	输入一个字符串，打印出该字符串中字符的所有排列。
    你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
    示例:
    输入：s = "abc"
    输出：["abc","acb","bac","bca","cab","cba"]*/

    public static void main(String[] args) {
        String str="ddabc";
        str=removeRepeat(str);
        permutation(str.toCharArray(),0);
    }
    public static void permutation(char[] str, int i) {
        if (i >= str.length)
            return;
        if (i == str.length - 1) {
            System.out.println(String.valueOf(str));
        } else {
            for (int j = i; j < str.length; j++) {
                char temp = str[j];
                str[j] = str[i];
                str[i] = temp;

                permutation(str, i + 1);

                temp = str[j];
                str[j] = str[i];
                str[i] = temp;
            }
        }
    }

    /**
     * 把字符串去重，并升序排序
     * @param str
     * @return
     */
    private static String removeRepeat(String str) {

        //把String变成单一字符数组
        char[] chars = str.toCharArray();

        //把字符串数组放入TreeSet中，根据set元素不重复的特性去掉重复元素。根据treeSet的有序性排序
        TreeSet<Character> treeSet = new TreeSet();
        for (char s : chars) {
            treeSet.add(s);
        }

        System.out.println(treeSet.toString());
        //把treeSet拼接成字符串
        str = "";
        for (char s : treeSet) {
            str += s;
        }
        return str;
    }
    /*

4.	给你一个有序数组 nums ，请你删除重复出现的元素，使每个元素只出现一次 ，返回删除后数组的新长度。
    不要使用额外的数组空间，你必须在原地修改输入数组。

    示例 1：
    输入：nums = [1,1,2]
    输出：2, nums = [1,2]
    解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
    示例 2：
    输入：nums = [0,0,1,1,1,2,2,3,3,4]
    输出：5, nums = [0,1,2,3,4]
    解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。

*/

    @Test
    public void testArr(){
        int[] nums={0,0,1,1,1,2,2,3,3,4};
        System.out.println(nums.length);
        System.out.println(Arrays.toString(nums));
        Set<Integer> set= new HashSet();
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
            if(!list.contains(nums[i])){
                list.add(nums[i]);
            }

        }
        nums=new int[set.size()];
        int j=0;
        for (Integer value:set){
            System.out.println(value);
            nums[j]=value;
            j++;
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(set.size());
    }


    @Test
    public void tt(){
        int[] nums={0,0,1,1,1,2,2,3,3,4,8,9};
        removeDuplicates(nums,2);
    }

    public int removeDuplicates(int[] nums, int k) {
        System.out.println(Arrays.toString(nums));
        int left = 0, right = 1;
        while(right < nums.length) {
            if (nums[left] == nums[right]) {
                ++right;
            } else {
                nums[++left] = nums[right++];
            }
        }
        System.out.println(Arrays.toString(nums));
        return left + 1;
    }
}
