class Solution
{
public:
    int maxArea(vector<int> &height)
    {

        int ans = 0;
        int l = 0;
        int r = height.size() - 1;

        while (l < r)
        {
            ans = max(ans, min(height[l], height[r]) * (r - l));
            height[l] < height[r] ? l++ : r--;
        }
        return ans;
    }
};