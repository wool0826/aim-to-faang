import java.util.*;

class Solution {
    //다른 언어의 구조체처럼 각 중복되는 문자열 구간의 시작 인덱스(first), 끝나는 인덱스(last), 그리고 문자열 그 자체(name)를 저장하는 클래스를 만든다.
    class GemRange {
        public String name;
        public int first;
        public int last;
        
        public GemRange (String name, int first, int last){
            this.name = name;
            this.first = first;
            this.last = last;
        }
    }
    
    public int[] solution(String[] gems) {
        
        int[] answer = {0, gems.length-1};

        Map<String, Integer> passed = new HashMap<>();
        int maxSize;
        Queue<GemRange> queue = new LinkedList<>();
      
        //본격적인 풀이 전에 초기화 시작
        String currentGem = gems[0];
        int first = 0;
        int last;
        for(int i = 0; i < gems.length; i++){
            //중복없이 총 몇개의 원소가 있는지 알기 위해 해쉬맵을 세팅한다
            passed.put(gems[i], 1);
            //currentGem은 i보다 전에 있었던 문자열이다. 만약 i번째 문자열과 current가 같다면 중복되는 문자열이 계속 되고 있다는 뜻이고,
            //다르다면 중복되는 문자열이 i-1까지만 이어지고 i번째에선 끊겼다는 의미가 된다.
            //중복되는 문자열이 시작되는 인덱스는 first에 저장했으므로 currentGem과 같은 문자열은 first부터 i-1까지만 이어지고 i에서는 끊기게 된다.
            //이렇게 구간별로 큐에 저장하였다.
            if(!gems[i].equals(currentGem)){
                last = i-1;
                queue.add(new GemRange(currentGem, first, last));
              
                // 새로운 문자열의 시작점이 현재의 i이므로 first에 i를 저장해둔다. 그리고 currentGem도 새로운 문자열로 갱신한다.
                first = i;  
                currentGem = gems[i];
            }
        }
        //위의 반복문은 currentGem이라는 과거의 정보와 i를 비교하기 때문에 i가 gems의 마지막 인덱스일때를 저장하지 못한다.
        //그러니 한번더 돌려서 마지막 구간을 저장해준다.
        queue.add(new GemRange(gems[gems.length - 1], first, gems.length - 1));
        //중복없는 총 원소의 갯수가 총 몇개인지 알기위해 maxSize라는 정수형변수를 따로 준비하고 passed 해쉬맵은 뒤에서 써먹기 위해 내용물을 지워둔다.
        maxSize = passed.size();
        passed.clear();
        //초기화 끝

        //아래에서 first와 last를 구간별 last 인덱스로 초기화해두었는데,
        //문제에서 가장 빠른 인덱스가 답이라고 하니 전부 같은 문자열일때는 그냥 1,1을 리턴한다.
        if(maxSize == 1)
            return new int[] {1,1};

        //최소구간 찾기 시작
        currentGem = gems[0];
        first = queue.peek().last;
        last = first;
        for(int i = 0; i < gems.length; i++){
            //위의 초기화때와 마찬가지로 문자열을 구간별로 나눠서 처리하기위해 currentGem이라는 과거의 정보를 저장한다.
            if(!gems[i].equals(currentGem)){
                if(!passed.containsKey(currentGem)){
                    passed.put(currentGem, 1);
                }else{
                    //passed의 value가 1보다 크면 1번 이상 중복되어서 나왔다는 의미가 된다.
                    passed.put(currentGem, passed.get(currentGem) + 1);
                }

                //큐에서 나온 구간과 중복되는 구간이 앞에 있었다면 passed의 value가 1보다 크다.
                //현재 큐의 맨앞 구간과 중복되는 구간이 있다면 뒤에도 같은 문자열구간이 있다는 얘기고 그렇다면 앞의 중복되는 문자열 구간은 하나 없어져도 된다는 얘기가 된다.
                //예를들어 {1 2 3 4} 의 구간이 있었는데, 1이 하나 더 추가되어서 {1 2 3 4 1} 이 되었다면 {2 3 4 1} 이여도 1,2,3,4를 모두 포함하게 된다.
                //만약 문제에서 주어진 문자열중 중복없는 모든 원소가 1,2,3,4라면 {1 2 3 4}와 {2 3 4 1} 중 어떤것이 더 짧은지만 비교하면 정답이 나오게 되는것이다.
                GemRange nextGem = queue.peek();
                while(passed.get(nextGem.name) > 1){
                    passed.put(nextGem.name, passed.get(nextGem.name) - 1); //중복되는 문자열 구간을 하나 없앨것이므로 passed도 갱신해준다.
                    queue.remove(); //중복되는 큐를 하나 없앤다.
                    nextGem = queue.peek();
                    first = nextGem.last; //중복을 없앴으므로 다음 구간의 마지막 인덱스가 최소 구간의 앞부분이된다.
                }

                if(passed.size() == maxSize){ //위의 초기화구간에서 미리구한 최대 사이즈가 총 원소의 갯수이므로 이걸 만족할때만 답을 갱신한다.
                    int tmp = last - first + 1;
                    int minRange = answer[1] - answer[0] + 1;
                    if(minRange > tmp){ //더 낮은것만 갱신한다. 만약 값이 같으면 반복문이 0부터 시작하므로 시작 인덱스가 더 빠른것만 저장될 것이다.
                        answer[0] = first;
                        answer[1] = last;
                    }
                }
              
                //여기가 좀 어려운 부분인데, 정답을 구하려면 최소값을 구해야 하므로 currentGem의 시작 구간을 저장해야한다.
                //그러려면 우선 위에서 answer를 갱신하고 그 뒤에 currentGem과 새로운 문자열이 시작되는 인덱스인 i를 저장하여
                //answer에서 사용되는 last는 과거의 정보가 되게 하고 이 주석 밑에서 갱신되는 last는 뒤에 사용되도록 만든다.
                //예를들어 모든 원소가 AAA,BBB,CCC인 문제에서 {AAA, BBB, CCC, AAA, AAA, CCC} 까지 계산했을때, currentGem은 AAA이고 gems[i] 는 CCC이다.
                //passed에 갱신된 currentGem은 AAA이므로 AAA까지만 계산했다 볼 수 있는데, 위의 maxSize 조건문에서 사용된 last에는 AAA의 시작인덱스인 3이 저장되어있을것이다.
                //그러므로 maxSize 조건문에서 answer가 {1, 3}로 갱신되고, 이 주석 밑에서 last가 현재 i인 5로 갱신되어 나중에 사용될것이다.
                last = i;
                currentGem = gems[i];
            }
        }
        
        //위의 초기화 구간과 마찬가지로 currentGem이 마지막일때는 계산하지 못하므로 한번 더 돌려준다....
        if(!passed.containsKey(currentGem)){
            passed.put(currentGem, 1);
        }else{
            passed.put(currentGem, passed.get(currentGem) + 1);
        }

        GemRange nextGem = queue.peek();
        while(passed.get(nextGem.name) > 1){
            passed.put(nextGem.name, passed.get(nextGem.name) - 1);
            queue.remove();
            nextGem = queue.peek();
            first = nextGem.last;
        }

        if(passed.size() == maxSize){
            int tmp = last - first + 1;
            int minRange = answer[1] - answer[0] + 1;
            if(minRange > tmp){
                answer[0] = first;
                answer[1] = last;
            }
        }

        //문제에서 인덱스의 시작이 0이아니라 1이므로 1씩 더해준다.
        answer[0] ++;
        answer[1] ++;
        return answer;
    }
}
