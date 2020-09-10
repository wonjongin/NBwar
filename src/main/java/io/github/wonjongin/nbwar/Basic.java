package io.github.wonjongin.nbwar;

public class Basic {
    public static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
