
class Solution
{
public:
    vector<string> fullJustify(vector<string> &words, int maxWidth)
    {
        vector<string> ans;
        queue<string> Queue;
        int length = words.size();
        int idx = 0;
        int currLength = 0;
        while (idx < length)
        {
            if (currLength + words[idx].size() + Queue.size() <= maxWidth)
            {
                currLength += words[idx].size();
                Queue.push(words[idx]);
                idx++;
            }
            else
            {
                string result = "";
                int padding = maxWidth - currLength;
                while (!Queue.empty())
                {
                    int currPadding = 0;
                    result += Queue.front();
                    Queue.pop();
                    if (Queue.empty())
                        currPadding = padding;
                    else{
                        currPadding = (padding % Queue.size() == 0) ? (padding / Queue.size()) : (padding / Queue.size() + 1);
                        padding-=currPadding;
                    }
                    
                    for (int i = 0; i < currPadding; i++)
                        result += " ";
                cout<<result<<endl;
                }
                ans.push_back(result);
                currLength = 0;
            }
        }
        string result = "";
                int padding = maxWidth - currLength;
                while (!Queue.empty())
                {
                    int currPadding = 0;
                    result += Queue.front();
                    Queue.pop();
                    if (Queue.empty())
                        currPadding = padding;
                    else{
                        currPadding = 1;
                        padding-=currPadding;
                    }
                    
                    for (int i = 0; i < currPadding; i++)
                        result += " ";
                }
                ans.push_back(result);
        return ans;
    }
};