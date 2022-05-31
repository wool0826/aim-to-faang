class Solution
{
public:
    int numBusesToDestination(vector<vector<int>> &routes, int source, int target)
    {
        if (source == target)
            return 0;
        unordered_map<int, vector<int>> m;
        int length = routes.size();
        for (int i = 0; i < length; i++)
        {
            for (auto curr : routes[i])
            {
                m[curr].push_back(i);
            }
        }
        vector<int> visited(length, 0);
        queue<int> Q;
        Q.push(source);
        int result = 0;
        while (!Q.empty())
        {
            int size = Q.size();
            result++;
            while (size--)
            {
                int curr = Q.front();
                Q.pop();
                for (auto i : m[curr])
                {
                    if (visited[i])
                        continue;
                    visited[i] = 1;
                    for (auto next : routes[i])
                    {
                        if (next == target)
                            return result;
                        Q.push(next);
                    }
                }
            }
        }
        return -1;
    }
};