

package com.kuke.common.utils;


import com.kuke.common.language.Language;
import com.kuke.common.value.ArgumentKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * @date: 2018-12-20
 * @version: 1.0
 * @author: wangchenxing
 */
public class IDUtils {
    public static final String[] VAL_CODE_ARR = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
    public static final String[] WI = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
    /**
     * 身份证的最小出生日期,1900年1月1日
     */
    private final static Date MINIMAL_BIRTH_DATE = new Date(-2209017600000L);
    private static final String BIRTH_DATE_FORMAT = "yyyyMMdd";
    private final static int NEW_CARD_NUMBER_LENGTH = 18;
    private final static int OLD_CARD_NUMBER_LENGTH = 15;
    public final static String LENGTH_ERROR = "Language.getIDNumberLengthError()";
    public final static String NUMBER_ERROR = "Language.getIDNumberNumberError()";
    public final static String DATE_ERROR = "Language.getIDNumberDateError()";
    public final static String AREA_ERROR = "Language.getIDNumberAreaError()";
    public final static String CHECKCODE_ERROR = "Language.getIDNumberCheckCoedError()";
    public final static String DATE_BIRTH_ERROR = "Language.getIDNumberDateOfBirthError()";
    /**
     * 是否需要返回自动补全成的身份证
     */
    private static boolean isNeedReturn_AutoCard = false;

    /**
     * @param idcardNumber     需要验证的身份证
     * @param isReturnAutoCard 验证无误后，是否需要返回自动补全身份证
     * @return 身份证无误返回传入的身份证号
     */
    public static String validateEffective(String idcardNumber, boolean isReturnAutoCard) {
        isNeedReturn_AutoCard = isReturnAutoCard;
        return validateEffective(idcardNumber);
    }

    /**
     * 身份证校验
     *
     * @param idcardNumber 需要验证的身份证
     * @return 身份证无误返回传入的身份证号
     */
    public static String validateEffective(String idcardNumber) {
        String aI = idcardNumber.trim();
        System.out.println(aI.length() != 15);
        if (aI.length() == ArgumentKey.NUMBER_FIFTEEN | aI.length() == ArgumentKey.NUMBER_EIGHTEEN) {
            // 如果为15位则自动补全到18位
            if (aI.length() == OLD_CARD_NUMBER_LENGTH) {
                aI = contertToNewCardNumber(aI);
            }
        } else {
            return LENGTH_ERROR;
        }
        // 身份证号的前15,17位必须是阿拉伯数字
        for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
            char ch = aI.charAt(i);
            if (ch < '0' || ch > '9') {
                return NUMBER_ERROR;
            }
        }
        // 校验身份证日期信息是否有效 ，出生日期不能晚于当前时间，并且不能早于1900年
        try {
            Date birthDate = getBirthDate(aI);
            if (null == birthDate) {
                return DATE_ERROR;
            }
            if (!birthDate.before(new Date())) {
                return DATE_ERROR;
            }
            if (!birthDate.after(MINIMAL_BIRTH_DATE)) {
                return DATE_ERROR;
            }
            /**
             * 出生日期中的年、月、日必须正确,比如月份范围是[1,12],日期范围是[1,31]，还需要校验闰年、大月、小月的情况时，
             * 月份和日期相符合
             */
            String birthdayPart = getBirthDayPart(aI);
            String realBirthdayPart = createBirthDateParser().format(birthDate);
            if (!birthdayPart.equals(realBirthdayPart)) {
                return DATE_ERROR;
            }
        } catch (Exception e) {
            return DATE_ERROR;
        }
        // 校验地区码是否正确
        Hashtable<String, String> h = getAreaCode();
        if (h.get(aI.substring(0, ArgumentKey.NUMBER_TWO)) == null) {
            return AREA_ERROR;
        }
        // 校验身份证最后一位 身份证校验码
        if (!calculateVerifyCode(aI).equals(String.valueOf(aI.charAt(NEW_CARD_NUMBER_LENGTH - 1)))) {
            return CHECKCODE_ERROR;
        }
        return isNeedReturn_AutoCard == false ? idcardNumber : aI;
    }

    /**
     * 把15位身份证号码转换到18位身份证号码<br>
     * 15位身份证号码与18位身份证号码的区别为：<br>
     * 1、15位身份证号码中，"出生年份"字段是2位，转换时需要补入"19"，表示20世纪<br>
     * 2、15位身份证无最后一位校验码。18位身份证中，校验码根据根据前17位生成
     *
     * @param oldCardNumber
     * @return
     */
    private static String contertToNewCardNumber(String oldCardNumber) {
        StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
        buf.append(oldCardNumber.substring(0, 6));
        buf.append("19");
        buf.append(oldCardNumber.substring(6));
        buf.append(calculateVerifyCode(buf));
        return buf.toString();
    }

    /**
     * 计算最后一位校验码  加权值%11
     * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
     * （2）计算模 Y = mod(S, 11)
     * （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
     *
     * @param cardNumber
     * @return
     */
    private static String calculateVerifyCode(CharSequence cardNumber) {
        int sum = 0;
        for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
            char ch = cardNumber.charAt(i);
            sum += ((int) (ch - '0')) * Integer.parseInt(WI[i]);
        }
        return VAL_CODE_ARR[sum % 11];
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    private static Hashtable<String, String> getAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    private static Date getBirthDate(String idcard) {
        Date cacheBirthDate = null;
        try {
            cacheBirthDate = createBirthDateParser().parse(getBirthDayPart(idcard));
        } catch (Exception e) {
            throw new RuntimeException(DATE_BIRTH_ERROR);
        }
        return new Date(cacheBirthDate.getTime());
    }

    public static SimpleDateFormat createBirthDateParser() {
        return new SimpleDateFormat(BIRTH_DATE_FORMAT);
    }

    private static String getBirthDayPart(String idcardnumber) {
        return idcardnumber.substring(6, 14);
    }

}
