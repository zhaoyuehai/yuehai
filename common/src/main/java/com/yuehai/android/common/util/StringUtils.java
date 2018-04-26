package com.yuehai.android.common.util;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public final static String UTF_8 = "utf-8";

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit;
        strURL = strURL.trim();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                for (int i = 1; i < arrSplit.length; i++) {
                    strAllParam = arrSplit[i];
                }
            }
        }
        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> getParamsByUrl(String URL) {
        Map<String, String> mapRequest = new HashMap<>();
        String[] arrSplit;
        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual;
            arrSplitEqual = strSplit.split("[=]");
            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (!arrSplitEqual[0].equals("")) {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    // 邮箱验证
    public static boolean checkEmail(String email) {
        Pattern p = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 判断字符串是否全部为中文字符组成
     *
     * @param str 检测的文字
     * @return true:含有中文字符
     */
    public static boolean isChineseStr(String str) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        char c[] = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            Matcher matcher = pattern.matcher(String.valueOf(c[i]));
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPassWrod(String password) {
        Pattern p = Pattern
                .compile("[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,16}");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static boolean isEmpty(String value) {
        return !(value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim()));
    }

    public static boolean isNotEmpty(String value) {
        return !StringUtils.isEmpty(value);
    }

    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    public static String tryGetString(JSONObject json, String key,
                                      String defaultStr) {
        try {
            String s = json.getString(key);
            if (s.equalsIgnoreCase("null") || s.equals("")) {
                return defaultStr;
            }
            return s;
        } catch (Exception e) {
            return defaultStr;
        }
    }

    public static CharSequence getHighLightText(String content, int color,
                                                int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public static String formatFileSize(long len) {
        return formatFileSize(len, false);
    }

    public static String formatFileSize(long len, boolean keepZero) {
        String size;
        DecimalFormat formatKeepTwoZero = new DecimalFormat("#.00");
        DecimalFormat formatKeepOneZero = new DecimalFormat("#.0");
        if (len < 1024) {
            size = String.valueOf(len + "B");
        } else if (len < 10 * 1024) {
            // [0, 10KB)
            size = String.valueOf(len * 100 / 1024 / (float) 100) + "KB";
        } else if (len < 100 * 1024) {
            // [10KB, 100KB)
            size = String.valueOf(len * 10 / 1024 / (float) 10) + "KB";
        } else if (len < 1024 * 1024) {
            // [100KB, 1MB)
            size = String.valueOf(len / 1024) + "KB";
        } else if (len < 10 * 1024 * 1024) {
            // [1MB, 10MB)
            if (keepZero) {
                size = String.valueOf(formatKeepTwoZero.format(len * 100 / 1024
                        / 1024 / (float) 100))
                        + "MB";
            } else {
                size = String.valueOf(len * 100 / 1024 / 1024 / (float) 100)
                        + "MB";
            }
        } else if (len < 100 * 1024 * 1024) {
            // [10MB, 100MB)
            if (keepZero) {
                size = String.valueOf(formatKeepOneZero.format(len * 10 / 1024
                        / 1024 / (float) 10))
                        + "MB";
            } else {
                size = String.valueOf(len * 10 / 1024 / 1024 / (float) 10)
                        + "MB";
            }
        } else if (len < 1024 * 1024 * 1024) {
            // [100MB, 1GB)
            size = String.valueOf(len / 1024 / 1024) + "MB";
        } else {
            // [1GB, ...)
            size = String.valueOf(len * 100 / 1024 / 1024 / 1024 / (float) 100)
                    + "GB";
        }
        return size;
    }

    /**
     * 获取字符，限制自符数
     *
     * @return 限制后字符, isCha是否字
     */
    static public String getSubstring(int num, String str, boolean isCha) {
        String subStr = str;
        try {
            if (subStr == null) {
                return "";
            }
            int rNum = getSubNum(subStr);
            while (rNum > num) {
                subStr = subStr.substring(0, subStr.length() - 1);
                rNum = getSubNum(subStr);
            }
        } catch (Exception e) {
            return "";
        }
        return subStr;
    }

    /**
     * 获取字符，限制自符数,辅助方法
     *
     * @return 限制后字符, isCha是否字
     */
    private static int getAscii(char cn) {
        if (((int) cn) <= 160) { // 单字节字符
            return 1;
        } else
            return 2; // 错误
    }

    /**
     * 获取字符，限制自符数,辅助方法
     *
     * @return 字符长度
     */
    public static int getSubNum(String subStr) {
        int rNum = 0;
        for (int i = 0; i < subStr.length(); i++) {
            int iii = getAscii(subStr.charAt(i));
            rNum += iii;
        }
        return rNum;
    }

    /**
     * 判断开头不能听出现特殊字符
     */
    public static boolean isHashTwoSpecial(String text) {
        String regEx = "^((?=[\\x21-\\x7e]+)[^A-Za-z0-9]){2,}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    /**
     * 手机号码隐藏
     *
     * @param phone
     */
    public static String hiddenPhone(String phone) {
        String temp = "";
        if (isNotEmpty(phone)) {
            temp += phone.substring(0, 3);
            temp += "****";
            temp += phone.substring(phone.length() - 4, phone.length());
        }

        return temp;
    }

    /**
     * 身份证号隐藏
     *
     * @param idCard
     */
    public static String hiddenIdCard(String idCard) {
        String temp = "";
        if (isNotEmpty(idCard)) {
            temp += idCard.substring(0, 3);
            temp += "***********";
            temp += idCard.substring(idCard.length() - 4, idCard.length());
        }

        return temp;
    }

    public static String hiddenMail(String mail) {
        String temp = "";
        if (isNotEmpty(mail)) {
            if (mail.length() > 4) {
                temp = "****";
                temp += mail.substring(4, mail.length());
            }
        }
        return temp;
    }

    // 转化全角
    public static String ToDBC(String input) {

        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 传一个秒数返回指定模板的时间字符串,异常或者传空都会返回空串
     *
     * @param time    时间毫秒数
     * @param templet 时间模板
     * @return
     */
    private static String getDate(String time, String templet) {
        if (time == null || "".equals(time) || templet == null
                || "".equals(templet)) {
            return "";
        }
        String timeStr;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(templet, Locale.getDefault());
            Date d = new Date(Long.parseLong(time));
            timeStr = sdf.format(d);
        } catch (Exception e) {
            return "";
        }
        return timeStr;
    }

    public static String getDate(long time) {
        String timeStr;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
            timeStr = sdf.format(time);
        } catch (Exception e) {
            return "";
        }
        return timeStr;
    }

    /**
     * 传一个秒数返回 yyyy-MM-dd hh:mm 格式的时间,异常或者传空都会返回空串
     *
     * @param time 时间毫秒数
     */
    public static String getDate(String time) {
        if (time == null || time.contains("-") || time.contains(":")) {
            return time;
        }
        return getDate(time, "yyyy-MM-dd hh:mm");
    }

    public static String formatTime(Long ms, boolean needMinute, boolean needSecond) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
//        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuilder sb = new StringBuilder();
        sb.append(day).append("天");
        sb.append(hour).append("小时");
        if (needMinute) {
            sb.append(minute).append("分");
        }
        if (needSecond) {
            sb.append(second).append("秒");
        }
//            if (milliSecond > 0) {
//                sb.append(milliSecond).append("毫秒");
//            }
        return sb.toString();
    }

    public static String getDateWithSecond(String time) {
//        SimpleDateFormat f=new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        if (time == null || time.contains("-") || time.contains(":")) {
            return time;
        }
        return getDate(time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param arg0 原有的字符
     * @param arg1 添加的字符
     */
    public static SpannableStringBuilder setRed(Context context, int arg0,
                                                String arg1) {
        String string = context.getResources().getString(arg0);
        if (StringUtils.isNotEmpty(string)) {
            if (string.contains("%1$s")) {
                String text = String.format(context.getResources().getString(arg0),
                        arg1);
                int index[] = new int[1];
                index[0] = text.indexOf(arg1);
                SpannableStringBuilder style = new SpannableStringBuilder(text);
                style.setSpan(new ForegroundColorSpan(Color.RED), index[0], index[0]
                        + arg1.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                return style;
            } else {
                return new SpannableStringBuilder(string);
            }
        } else {
            return new SpannableStringBuilder("");
        }
    }

    /**
     * @param arg0 原有的字符串
     * @param arg1 添加的字符
     * @param arg2 添加的字符2
     */

    public static SpannableStringBuilder setRed2(Context context, int arg0,
                                                 String arg1, String arg2) {
        String string = context.getResources().getString(arg0);
        if (StringUtils.isNotEmpty(string)) {
            if (string.contains("%1$s") && string.contains("%2$s")) {
                String text = String.format(string, arg1, arg2);
                int index[] = new int[2];
                index[0] = text.indexOf(arg1);
                index[1] = text.indexOf(arg2);
                SpannableStringBuilder style = new SpannableStringBuilder(text);
                style.setSpan(new ForegroundColorSpan(Color.RED), index[0], index[0]
                        + arg1.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.RED), index[1], index[1]
                        + arg2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                return style;
            } else {
                return new SpannableStringBuilder(string);
            }
        } else {
            return new SpannableStringBuilder("");
        }
    }

    public static boolean isContainNumber(String str) {
        for (int i = 0; i < str.length(); i++) { //循环遍历字符串
            if (Character.isDigit(str.charAt(i))) { //用char包装类中的判断数字的方法判断每一个字符
                return true;
            }
        }
        return false;
    }

}
