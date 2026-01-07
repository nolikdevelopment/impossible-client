package me.nolikdevelopment.impossibleclient.util;

public class StringUtils {
    public static String generateRandomString(int len) {
        String chrs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";//Можно еще добавить
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int idx = (int) (Math.random() * chrs.length());
            sb.append(chrs.charAt(idx));
        }

        return sb.toString();
    }
}
