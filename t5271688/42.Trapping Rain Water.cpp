class Solution
{
public:
    int trap(vector<int> &height)
    {
        int result = 0;
        int length = height.size();
        int leftMax[100010] = {0,};
        int rightMax[100010] = {0,};
        leftMax[0] = height[0];
        rightMax[length - 1] = height[length - 1];
        for (int i = 1; i < length; ++i)
        {
            leftMax[i] = max(leftMax[i - 1], height[i]);
            rightMax[length - i - 1] = max(rightMax[length - i], height[length - i - 1]);
        }
        for (int i = 0; i < length; i++)
        {
            int val = min(leftMax[i], rightMax[i]);
            result += val > height[i] ? (val - height[i]) : 0;
        }
        return result;
    }
};