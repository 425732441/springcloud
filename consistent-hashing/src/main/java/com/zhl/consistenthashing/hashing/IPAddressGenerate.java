package com.zhl.consistenthashing.hashing;

import java.util.Random;

/**
 * @author Zhanghualei
 * @Classname IPAddressGenerate
 * @Date 2021/1/20 14:25
 */
public class IPAddressGenerate {

    public String[] getIPAddress(int num) {
        String[] res = new String[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            res[i] = String.valueOf(random.nextInt(256)) + "." + String.valueOf(random.nextInt(256)) + "."
                    + String.valueOf(random.nextInt(256)) + "." + String.valueOf(random.nextInt(256)) + ":"
                    + String.valueOf(random.nextInt(9999));
        }
        return res;
    }
}