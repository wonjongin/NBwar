package io.github.wonjongin.nbwar;

import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.Map;

public class Basic {

    /**
     * 문자열이 정수인지 판별합니다.
     *
     * @param s 정수인지 판별할 문자열을 받습니다.
     * @return boolean 정수인지 여부를 반환합니다.
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 퍼센트에 대한 확률의 적용여부를 계산합니다.
     *
     * @param percent 퍼센트에 해당하는 정수값을 입력받습니다.
     * @return boolean 으로 적용여부를 반환합니다.
     */
    public static boolean randomOfPercent(int percent) {
        int random = (int) Math.floor(Math.random() * 100);
        return random <= percent;
    }

}
