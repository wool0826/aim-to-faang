class Solution:
    def fullJustify(self, words: List[str], maxWidth: int) -> List[str]:
        results = []
        pre_i = 0
        len_sen = 0
        
        for i in range(len(words)):
            if len_sen + len(words[i]) + i - pre_i <= maxWidth:
                len_sen += len(words[i])
                
            else:
                # one word
                if i - pre_i == 1:
                    s = words[i - 1] + " " * (maxWidth - len_sen)
                    
                else:
                    q, r = divmod(maxWidth - len_sen, i - pre_i - 1)
                    
                    s = (" " * (q)).join(
                        (
                            [
                                (" " * (q + 1)).join(words[pre_i : pre_i + r + 1]),
                                *words[pre_i + r + 1 : i]
                            ]
                        )
                    )
                    
                results.append(s)

                len_sen = len(words[i])
                pre_i = i
        
        last = " ".join(words[pre_i : len(words)])
        last = last + " " * (maxWidth - len(last))
        results.append(last)
        
        return results
