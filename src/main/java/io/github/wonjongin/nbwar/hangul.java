package io.github.wonjongin.nbwar;

import java.util.HashMap;

import static org.bukkit.Bukkit.getLogger;

public class hangul {
    /**
     * 문자열의 마지막 글자가 받침을 가지고 있는지 판별합니다.
     *
     * @param str 받침이 있는지 판별할 문자열을 받습니다.
     * @return 받침을 가지고 있으면 true, 아니면 false를 반환합니다.
     */
    public static boolean hasUnderCase(String str) {
        getLogger().info(String.valueOf(str.length()));
        char last = str.charAt(str.length() - 1);
        if (last < 0xAC00 || last > 0xD7A3) {
            return false;
        }
        return (last - 0xAC00) % 28 > 0;
    }

    /**
     * 문자열에 주격조사를 추가합니다.
     *
     * @param str 주격조사를 추가할 문자열을 받습니다.
     * @return 주격조사가 추가된 문자열이 반환됩니다.
     */
    public static String addS(String str) {
        String S = hasUnderCase(str) ? "이" : "가";
        return str + S;
    }

    /**
     * 숫자에 부사격조사를 추가합니다.
     *
     * @param num 부사격조사를 추가할 정수를 받습니다.
     * @return 부사격조사가 추가된 문자열이 반환됩니다.
     */
    public static String addNumAdv(int num) {
        String numStr = String.valueOf(num);
        String lastChar = numToHangul(num % 10);
        String Adv = hasUnderCase(lastChar) ? "으로" : "로";
        return numStr + Adv;
    }

    /**
     * 숫자에 목적격조사를 추가합니다.
     *
     * @param num 목적격조사를 추가할 정수를 받습니다.
     * @return 목적격조사가 추가된 문자열이 반환됩니다.
     */
    public static String addNumO(int num) {
        String numStr = String.valueOf(num);
        String lastChar = numToHangul(num % 10);
        String O = hasUnderCase(lastChar) ? "을" : "를";
        return numStr + O;
    }

    /**
     * 숫자를 한글 문자로 바꿔줍니다.
     *
     * @param num 바꿀 한 자리 숫자를 받습니다.
     * @return 숫자의 한글 표기를 반환합니다.
     */
    public static String numToHangul(int num) {
        HashMap<Integer, String> map = new HashMap<Integer, String>() {{
            put(1, "일");
            put(2, "이");
            put(3, "삼");
            put(4, "사");
            put(5, "오");
            put(6, "육");
            put(7, "칠");
            put(8, "팔");
            put(9, "구");
            put(0, "공");
        }};
        return map.get(num);
    }
}
