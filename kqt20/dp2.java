//LCS 해결 못함
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] table = new int[n][m];

        int right = 0;
        int result = 0;

        for(int left = 0; left < n; left++){
            for(int j = right; j < m; j++){
                if(text1.charAt(left) == text2.charAt(j)) {
                    result++;

                    right = j+1;
                    j = m;
                }
            }
        }

        return result;
    }
}