package cn.lee.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：lix492
 * 2021/6/25
 */
@Getter
@AllArgsConstructor
public enum PowEnum {

    BASIC(1),MOBILE(2),IDENTITY(4),XXX(32);

    private Integer code;

    public static void main(String[] args) {
        System.out.println(orOperation(BASIC,BASIC,BASIC));
        System.out.println(orOperation(MOBILE));
        System.out.println(orOperation(BASIC,IDENTITY));
        System.out.println(orOperation(BASIC,MOBILE,IDENTITY,IDENTITY));

        System.out.println(code2List(111));
        System.out.println(11&5);
    }

    public static Integer orOperation(final PowEnum ... powEnums){
        //方法一
        Integer result=0;
        for (int i = 0; i < powEnums.length; i++) {
            result=result|powEnums[i].getCode();
        }
        //方法二
        int a=Arrays.stream(powEnums).mapToInt(value->value.getCode()).distinct().sum();
        System.out.println(a);
        return result;
    }

    public static List<PowEnum> code2List(Integer totalCode) {
        return Arrays.stream(PowEnum.class.getEnumConstants()).filter(pow -> (totalCode & pow.getCode()) == pow.getCode()).collect(Collectors.toList());
    }

    public static int list2Code(List<PowEnum> powList) {
        return powList.stream().mapToInt(value -> value.getCode()).distinct().sum();
    }

}
