package org.example;

import java.util.Arrays;
import java.util.List;

public class WordBreak {
    private int[] dp;

    public boolean wordBreak(String s, List<String> wordDict) {
        dp = new int[s.length()];

        // -1: 확인하지 않음. 0: 확인했으나 안 됨. 1: 확인했고 잘 됨.
        Arrays.fill(dp, -1);

        return process(s, wordDict, s.length() - 1);
    }

    private boolean process(String originWord, List<String> wordDict, int remainLength) {
        if (remainLength < 0) return true;

        if (dp[remainLength] == -1) {
            for (String word : wordDict) {
                if (remainLength >= word.length() - 1 // 해당 word 를 처리할 만큼 남아있는지
                        && process(originWord, wordDict, remainLength - word.length()) // 이번 word 를 처리하고 난 뒤에 결과가 어떤지?
                        && check(originWord, word, remainLength - word.length() + 1)) { // 이번 word 를 처리할 수는 있는지?
                    dp[remainLength] = 1; // 그럼 이 위치에서는 breakable 하다.
                    break;
                }
            }
        }

        if (dp[remainLength] == -1) { // -1 그대로라면, breakable 하지 않다.
            dp[remainLength] = 0;
        }

        return dp[remainLength] == 1;
    }

    private boolean check(String originWord, String wordToCompare, int startIndex) {
        for (var i = 0; i < wordToCompare.length(); i++) {
            if (originWord.charAt(i + startIndex) != wordToCompare.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
