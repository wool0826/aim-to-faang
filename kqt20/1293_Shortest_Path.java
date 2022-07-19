import java.util.*;

class Solution {

    //BFS 사용을 위해 Queue에 노드를 저장해야하는데, 그것을 위한 내부클래스
    //grid[][]의 x,y 좌표만 저장한다
    //다만 2차원 배열에서 가로는 [][O] 이고, 세로는 [O][] 인데 xy축 좌표를 표현할땐 x가 가로 y가 세로이므로
    //괜히 헷갈리지 않게 y x 순서를 바꿔서 사용한다.
    static class QueueInstance {
        int y;  //grid[y][]
        int x;  //gird[][x]

        QueueInstance(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public int shortestPath(int[][] grid, int k) {

        //grid의 길이를 각각 저장해놓은 변수
        int m = grid.length;
        int n = grid[0].length;

        //각 위치까지 가는데 필요한 이동 거리를 저장해 놓은 배열.
        //거기에 BFS를 위해 방문한 적이 있는 노드인지 체크하는 용도도 겸비한다. 값이 0이면 방문한적이 없는 위치.
        int[][] lengthMap = new int[m][n];

        //각 위치까지 가고 남은 k값(막힌 부분을 뚫을 수 있는 회수)을 저장한 배열.
        int[][] kMap = new int[m][n];

        //grid[0][0]부터 시작이므로 [0][0]의 값들을 초기화 해준다.
        lengthMap[0][0] = 1;
        kMap[0][0] = k;

        //BFS를 위해 초기값을 큐에 넣는다.
        Queue<QueueInstance> queue = new LinkedList<>();
        queue.add(new QueueInstance(0, 0));

        while(!queue.isEmpty()){
            QueueInstance currentPosition = queue.poll();
            int y = currentPosition.y;
            int x = currentPosition.x;

            //목적지에 도착하면 해당 위치까지 가는데 필요했던 이동거리를 리턴하면서 끝낸다.
            if(y == m-1 && x == n-1)
                return lengthMap[m-1][n-1] - 1;

            //현재 위치에서 상하좌우로 이동할 수 있고, 현재 위치가 벽에 붙어있다면 인덱스를 초과할 수 있으므로
            //아래와 같이 조건문을 만든다. 조건에만 맞는다면 상하좌우를 모두 방문해야 하므로 else if가 아니라 각각의 if문으로 만든다.
            if(y - 1 >= 0){
                //코드가 반복되서 메소드를 따로 빼놓았다. 해당 메소드는 아래에있다.
                queueSet(queue, y-1, x, y, x, grid, lengthMap, kMap);
            }
            if(x - 1 >= 0){
                queueSet(queue, y, x-1, y, x, grid, lengthMap, kMap);
            }
            if(y + 1 < m){
                queueSet(queue, y+1, x, y, x, grid, lengthMap, kMap);
            }
            if(x + 1 < n){
                queueSet(queue, y, x+1, y, x, grid, lengthMap, kMap);
            }
        }

        //목적지에 도착하지 않았는데 while문이 끝났다면 목적지에 도착할 수 없는 것이므로 -1을 리턴한다.
        //lengthMap의 초기값은 0이므로 한번도 방문한 적이 없다면 [m-1][n-1]은 무조건 0이고 거기에 -1을하면 -1이된다.
        //따라서 그냥 -1을 리턴해도 되지만 그냥 이렇게 작성했다.
        return lengthMap[m-1][n-1] - 1;
    }

    //BFS 핵심 로직
    //current X Y는 현재 위치이고, next X Y는 위에서 넘겨줬던 다음 위치다.
    //next Y가 y-1이라면 위로 한칸인 [y-1][x] 가 다음위치, next X가 x+1이라면 오른쪽으로 한칸인 [y][x+1] 이런식으로.
    private void queueSet (Queue<QueueInstance> queue, int nextY, int nextX, int currentY, int currentX, 
                           int[][] grid, int[][] lengthMap, int[][] kMap) {
        //다음 위치가 0(즉 한번도 방문한적 없다) 이면서,
        //현재 위치에서 k값이 0이 아니거나 다음 위치의 grid가 1(벽으로 막혀있다) 일때,
        //한마디로 벽에 막혀있어도 뚫을 수 있거나 벽에 안막혀 있을때
        if(lengthMap[nextY][nextX] == 0 && (kMap[currentY][currentX] != 0 || grid[nextY][nextX] != 1)){

            //다음 위치까지 가는데 필요한 이동거리는 현재까지 가는데 필요했던 이동거리에 +1이므로 해당값을 저장해주고
            lengthMap[nextY][nextX] = lengthMap[currentY][currentX] + 1;

            //벽에 막혀있다면
            if(grid[nextY][nextX] == 1) {
                //뚫고나서 다음위치의 k값을 -1 해준다.
                kMap[nextY][nextX] = kMap[currentY][currentX] - 1;
                //다음 위치를 큐에 집어넣어서 BFS를 지속한다.
                queue.add(new QueueInstance(nextY, nextX));
            }
            //벽에 안 막혀 있다면
            else {
                //그냥 갈 수 있으므로 k값은 그대로 넘겨준다.
                kMap[nextY][nextX] = kMap[currentY][currentX];
                queue.add(new QueueInstance(nextY, nextX));
            }
        }
        //이 부분이 중요한데, 위의 조건문만 사용한다면 두가지 문제가 있기 때문이다.
        //하나는 벽을 뚫고가면 최단거리가 나와도 먼저 큐에 들어갔던 안뚫고가는 루트가 먼저 목적지에 도착하여 진짜 최단거리가 안나온다.
        //두번째는 처음 방문한 노드만 가게되면 왔던곳을 역행하지 못하기 때문에 위로 올라가야 하는 경우 목적지까지 도착하지를 못한다.
        //그래서 전에 방문했던 노드일 경우의 조건문을 추가한다.

        //else if 이므로 위의 조건이 안맞을 때, 즉 lengthMap[next]는 0이 아니므로 방문한 적이 있을때만 실행되는 조건문이다.
        //중복계산을 막기위해 다음위치가 현재위치보다 작거나 같을때만 실행한다.
        else if(lengthMap[nextY][nextX] <= lengthMap[currentY][currentX] + 1){

            //만약 해당 위치에서 k를 다 쓰기 전까지 목적지에 도착하는 루트가 있다면 BFS와 큐의 특성상, 큐에서 가장 먼저 끝나서 return 될 것이기 때문에 여기서 k값을 바꿔도 상관이 없다.
            //하지만 k를 다써도 목적지에 도착할 수 없다면, 해당 위치까지 더 높은 k를 가지고 가는 최단루트를 통해 가면 도달 할 수도 있기 때문에 계속 갱신해준다.
            //그리고 벽에 막힌곳의 k를 갱신하면 k의 값에 상관없는 최단거리만 나오는 대참사가 일어나므로 벽에 안막힌 곳만 k를 갱신한다.
            if(grid[nextY][nextX] == 0 && kMap[nextY][nextX] < kMap[currentY][currentX]){

                //최단거리와 k값을 갱신해준다.
                lengthMap[nextY][nextX] = lengthMap[currentY][currentX] + 1;
                kMap[nextY][nextX] = kMap[currentY][currentX];
                queue.add(new QueueInstance(nextY, nextX));

            }
        }
    }

}