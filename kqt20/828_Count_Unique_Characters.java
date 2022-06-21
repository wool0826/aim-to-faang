class Solution {
    public int uniqueLetterString(String s) {
        // count[0] = A = 65 ~~~~ count[25] = Z = 90
        int[] countFront = new int[26];
        int[] countBack = new int[26];
        int result = 0;

        for(int i=0; i < s.length(); i++){
            //알파벳 순서에 대응하여 countFront와 countBack의 인덱스를 0부터 25까지 설정하기위해 A를 빼준다
            int presentChar = s.charAt(i) - 'A';

            if(countBack[presentChar] != 0) {
                //한 원소를 포함하는 서브스트링의 개수는, 앞에서 그 원소까지 잘라낸 스트링의 길이 * 그 원소부터 스트링 끝까지의 길이이다.
                //예시로 ABCDEF 가 있을때 B를 포함하는 서브스트링의 개수는 B가 2번째로 있으니 AB의 길이 2 * BCDEF의 길이 5 => 2*5 = 10 이다.
                //이것을 식으로 나타내면 (B의 위치) * (스트링의 길이 - B의 위치 + 1)가 된다.

                //여기서는 중복이 없으면서 해당 알파벳을 포함하는 서브스트링을 구해야하므로
                //현재 알파벳의 위치보다 앞에있는 중복알파벳의 위치인 countFront를 서브스트링을 구하고자하는 알파벳인 countBack과 현재 위치인 i+1에 빼준다.

                //식은 (countBack[presentChar] - countFront[presentChar]) * ((i+1 - countFront[presentChar] - 1) - (countBack[presentChar] - countFront[presentChar]) + 1)
                //이렇게 나오는데, 식을 정리해서 아래와 같이 만들었다.
                result += (countBack[presentChar] - countFront[presentChar])
                        * (i - countBack[presentChar] + 1);

                //결과값을 result 에 더했으니 다음으로 넘어가기위해 back에 있던 위치를 front에 저장해준다.
                countFront[presentChar] = countBack[presentChar];
            }
            //원래 back에 있던것은 front에 저장했으니 back에는 현재 위치를 경신한다.
            //해당 알파벳이 전혀 안나왔을 경우엔 0만 남도록 하기 위해 +1해서 저장
            countBack[presentChar] = i+1;
        }

        int length = s.length();
        for(int i=0; i < 26; i++){
            if(countFront[i] == 0 && countBack[i] != 0){
                //앞은 0인데 뒤는 0이 아니라면 전체 스트링에 해당 알파벳이 단 한개 있었던 경우이다.
                //그러므로 그냥 전체 스트링에서 해당 알파벳을 포함하는 서브스트링의 개수를 구해서 result 에 더해준다.
                //식은 위에서 말한것 처럼 (B의 위치) * (스트링의 길이 - B의 위치 + 1)이다.
                result += countBack[i] * (length - countBack[i] + 1);
            } else if(countFront[i] != 0 && countBack[i] != 0){
                //앞도 뒤도 0이 아니라면 마지막에 저장된 인덱스 뒤로는 중복이 없거나 스트링의 끝인 경우이다.
                //그러므로 중복없는 스트링의 길이는 마지막으로 저장된 front인덱스 뒤로 전부가 되고, 다르게 말하면
                //총 길이에서 front 에 저장된 위치만큼 빼면 스트링의 길이가 나온다는 것이다.

                //식은 위에 있던것과 대부분 같고 단 하나 다른 부분은 i+1 부분이 length+1로 바뀐다는 점이다.
                result += (countBack[i] - countFront[i])
                        * (length - countBack[i] + 1);
            }
        }

        return result;
    }
}