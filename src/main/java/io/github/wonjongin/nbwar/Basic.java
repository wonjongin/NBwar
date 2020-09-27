package io.github.wonjongin.nbwar;

import org.yaml.snakeyaml.Yaml;

import java.util.Map;

public class Basic {
    public static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean existKeyinYaml(String str){
        Yaml yaml = new Yaml();
        Map<String, Object> obj = yaml.load(str);
        return true;
    }
}
