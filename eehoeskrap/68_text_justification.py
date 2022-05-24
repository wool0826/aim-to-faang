class Solution(object):
    def fullJustify(self, words, maxWidth):

        results = []
        all_results = []

        # 1. 문자열 조합 나누기
        # e.g. ['This is an', 'example of text', 'justification.']
        for idx, word in enumerate(words):
            results += word + " "
            results = "".join(results)
            if idx == len(words) - 1:
                all_results.append(results[:-1])
                break
            if len(results) + len(words[idx+1]) > maxWidth:
                all_results.append(results[:-1])
                results = ""

        # 2. 나눈 문자열 조합에서 추가해야하는 공백 수(add_empty_num)를 구하고,
        #    공백을 추가해야하는 횟수(empty_pos_num)을 구하여
        #    문자열을 생성
        new_all_results = []
        for r_idx, results in enumerate(all_results):
            exist_empty_num = results.count(" ")
            add_empty_num = maxWidth - len(results) + exist_empty_num
            empty_pos = [i for i in range(len(results)) if results[i] == " "]
            empty_pos_num = len(empty_pos)

            if empty_pos_num == 0:
                per_add_num = add_empty_num
            else:
                if add_empty_num % empty_pos_num == 0:
                    per_add_num = int(add_empty_num / empty_pos_num)
                else:
                    per_add_num = int(add_empty_num / empty_pos_num) + 1

            results = results.split(" ")
            new_results = []
            per_add_num_check = add_empty_num

            # 마지막 문자열은 왼쪽 정렬
            if r_idx == len(all_results) - 1:
                for idx, result in enumerate(results):
                    new_results += result
                    new_results = "".join(new_results)
                    if per_add_num_check == 0:
                        break
                    if idx == 0:
                        new_results += " "
                for n in range(add_empty_num - 1):
                    new_results += " "
                new_all_results.append(new_results)
            else:
                # 마지막 문자열이 아닐 때는
                # 왼쪽의 빈 슬롯이 오른쪽의 빈 슬롯보다 더 많은 공백이 할당되게끔 함
                # 이 부분 아직 못함
                for idx, result in enumerate(results):
                    new_results += result
                    new_results = "".join(new_results)

                    if per_add_num_check == 0:
                        break
                    for n in range(per_add_num):
                        new_results += " "
                        per_add_num_check -= 1
                new_all_results.append(new_results)

        print(new_all_results)

if __name__ == "__main__":
    words = ["This", "is", "an", "example", "of", "text", "justification."]
    #words = ["What","must","be","acknowledgment","shall","be"]
    maxWidth = 16

    # words = ["Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art",
    #  "is", "everything", "else", "we", "do"]
    # maxWidth = 20

    # words = ["The","important","thing","is","not","to","stop","questioning.","Curiosity","has","its","own","reason","for","existing."]
    # maxWidth = 17

    Solution().fullJustify(words, maxWidth)
