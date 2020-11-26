package io.github.wonjongin.nbwar;

import java.util.HashMap;

import static org.bukkit.Bukkit.getLogger;

public class hangul {
    public static boolean hasUnderCase(String str) {
        getLogger().info(String.valueOf(str.length()));
        char last = str.charAt(str.length() - 1);
        if (last < 0xAC00 || last > 0xD7A3) {
            return false;
        }
        return (last - 0xAC00) % 28 > 0;
    }

    public static String addS(String str) {
        String S = hasUnderCase(str) ? "이" : "가";
        return str + S;
    }

    public static String addNumAdv(int num) {
        String numStr = String.valueOf(num);
        String lastChar = numToHangul(num % 10);
        String Adv = hasUnderCase(lastChar) ? "으로" : "로";
        return numStr + Adv;
    }
    public static String addNumO(int num) {
        String numStr = String.valueOf(num);
        String lastChar = numToHangul(num % 10);
        String O = hasUnderCase(lastChar) ? "을" : "를";
        return numStr + O;
    }

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
