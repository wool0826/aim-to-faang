class Solution {
public:
    int uniqueLetterString(string s) {
        vector<int> V[26];
        int ans=0;
        int n=s.length();
        for(int i=0; i<26; ++i) V[i].push_back(-1);
        for(int i=0; i<n; ++i) {
            V[s[i]-'A'].push_back(i);
        }
        for(int i=0; i<26; ++i) {
            V[i].push_back(n);
            for(int j=1; j<V[i].size()-1; j++) {
                ans+=(V[i][j]-V[i][j-1])*(V[i][j+1]-V[i][j]);
            }
        }
        return ans;
    }
};