package com.myProject.player_bloc;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

    }

    public boolean isSubsequence(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        int si = 0;
        int ti = 0;
        boolean res = false;

        while (ti < tArr.length && si < sArr.length) {
            res = tArr[ti] == sArr[si];
        }

    }
}
