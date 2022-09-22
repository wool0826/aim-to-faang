package org.example;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // memo[i] = i 까지 봤을 때 가장 긴 subsequence 의 길이를 저장함.
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);

        // 10, 9, 2, 5, 3, 7, 101, 18 을 예시로 한 경우
        for (var i = 0; i < nums.length; i++) {

            // 현재 3이라고 가정할 때, 10, 9, 2, 5 중 나보다 값이 작은 값의 memo 를 참조해서 내 memo 를 갱신함.
            for (var j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }
            }
        }

        return Arrays.stream(memo).max().orElse(1);
    }

    public static void main(String[] args) {
        var a = new LongestIncreasingSubsequence();
        System.out.println(a.lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
