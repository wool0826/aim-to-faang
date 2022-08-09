class Solution {

    //디버깅용
    /*public static void main(String[] args) {
        //int[][] a = {{14,17,18,16,14,16}, {17,3,10,2,3,8},{11,10,4,7,1,7},{13,7,2,9,8,10},{13,1,3,4,8,6},{20,3,3,9,10,8}};
        //int[][] a = {{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}};
        int[][] a = {{9,9,9,9,9,9,8,9,9,9,9},{9,0,0,0,0,0,1,0,0,0,9},{9,0,0,0,0,0,0,0,0,0,9},{9,0,0,0,0,0,0,0,0,0,9},{9,9,9,9,9,9,9,9,9,9,9}};

        Solution1 sol = new Solution1();
        System.out.println("result = " + sol.trapRainWater(a));
    }*/

    public int trapRainWater(int[][] heightMap) {

        //빗물이 고이는 높이를 저장하는 배열
        int[][] waterMap = new int[heightMap.length][heightMap[0].length];
        int result = 0;

        //기본개념은 각 블럭의 높이만큼 빗물이 고일수 있는지를 확인하는 것이다.
        //그러므로 모든 인덱스를 순회한다.
        for (int i=0; i<heightMap.length; i++) {
            for (int j=0; j<heightMap[i].length; j++) {
                //[i][j]의 높이만큼 물웅덩이가 생기려면 [i][j]의 높이보다 낮은 블럭이어야 한다.
                //그러므로 동서남북 현 위치의 높이보다 낮은 인덱스를 전부 방문한다.
                if(i+1 < heightMap.length) {
                    //해당 위치에 이미 물웅덩이가 생겨서 waterMap이 갱신되어 있을 때,
                    //이미 있는 물웅덩이보다 낮은 높이는 탐색할 필요가 없이 이미 포함되어있는 것이나 마찬가지이므로 패스하기 위해 조건을 넣는다.
                    if (heightMap[i + 1][j] < heightMap[i][j] && waterMap[i + 1][j] < heightMap[i][j]) {
                        //탐색한 인덱스를 저장하기 위해 2차원 배열을 하나 만들어서 넘겨준다
                        boolean[][] tmpWaterMap = new boolean[heightMap.length][heightMap[0].length];

                        //만약 사방이 막혀있어서 다음으로 넘어가지 못하면 ( 한마디로 물 웅덩이가 생긴다면 ) true를 반환하는 재귀함수를 돌려서
                        if(recur(heightMap, waterMap, tmpWaterMap, i + 1, j, heightMap[i][j])){
                            //해당 함수가 탐색했던 모든 길에 물웅덩이가 생긴것이므로 waterMap에 탐색한 모든 길의 물웅덩이 위치를 갱신한다.
                            checkWaterMap(waterMap, tmpWaterMap, heightMap[i][j]);
                        }
                    }
                }
                if(j+1 < heightMap[i].length) {
                    if (heightMap[i][j + 1] < heightMap[i][j] && waterMap[i][j + 1] < heightMap[i][j]) {
                        boolean[][] tmpWaterMap = new boolean[heightMap.length][heightMap[0].length];
                        if(recur(heightMap, waterMap, tmpWaterMap, i, j + 1, heightMap[i][j])){
                            checkWaterMap(waterMap, tmpWaterMap, heightMap[i][j]);
                        }
                    }
                }
                if(i-1 >= 0) {
                    if (heightMap[i - 1][j] < heightMap[i][j] && waterMap[i - 1][j] < heightMap[i][j]) {
                        boolean[][] tmpWaterMap = new boolean[heightMap.length][heightMap[0].length];
                        if(recur(heightMap, waterMap, tmpWaterMap, i - 1, j, heightMap[i][j])){
                            checkWaterMap(waterMap, tmpWaterMap, heightMap[i][j]);
                        }
                    }
                }
                if(j-1 >= 0) {
                    if (heightMap[i][j - 1] < heightMap[i][j] && waterMap[i][j - 1] < heightMap[i][j]) {
                        boolean[][] tmpWaterMap = new boolean[heightMap.length][heightMap[0].length];
                        if(recur(heightMap, waterMap, tmpWaterMap, i, j - 1, heightMap[i][j])){
                            checkWaterMap(waterMap, tmpWaterMap, heightMap[i][j]);
                        }
                    }
                }
            }
        }

        //모든 계산이 끝나고 물웅덩이의 높이와 heightMap의 높이를 빼서 물 웅덩이의 크기를 구한다.
        //가장자리는 물웅덩이가 절대로 생길수가 없으므로 빼고 계산한다.
        for (int i=1; i<heightMap.length-1; i++) {
            for (int j=1; j<heightMap[i].length-1; j++) {
                if(waterMap[i][j] > heightMap[i][j]){
                    result += waterMap[i][j] - heightMap[i][j];
                }
            }
        }

        return result;

    }

    //핵심 재귀 함수
    private boolean recur(int[][] heightMap, int[][] waterMap, boolean[][] tmpWaterMap, int i, int j, int first) {

        //방문체크
        tmpWaterMap[i][j] = true;

        //더이상 갈데없이 막혀있는지 여부를 체크하는 변수.
        boolean isBlocked = true;

        //우선 이 함수로 넘어올땐 [i][j]의 높이보다 낮은 블럭들만 방문하게 되어있으므로 현재 위치가 가장자리라는 것은 가장자리가 첫 시작 위치보다 높이가 낮다는 것을 의미한다.
        //그렇다면 밖으로 물이 줄줄 새버려서 절대로 해당 높이의 물웅덩이가 생길 수 없으므로 false를 반환하여 재귀를 끝낸다.
        if (i >= heightMap.length - 1 || j >= heightMap[i].length - 1 || i <= 0 || j <= 0) {
            return false;
        }

        //동서남북 중에서 조건에 맞는 블럭을 전부 방문한다.
        if (i + 1 < heightMap.length) {
            //다음 블럭이 첫 시작 높이보다 낮고, 방문한 적이 없는 블럭만 방문한다.
            //그리고 위에서 말한 것 처럼 물웅덩이가 이미 있을 때 계산 낭비를 막기 위해 이미 있는 웅덩이가 지금 재귀에서 첫 시작 높이보다 낮을때만 방문한다.
            if (heightMap[i + 1][j] < first && waterMap[i + 1][j] < first && !tmpWaterMap[i + 1][j]) {
                isBlocked = recur(heightMap, waterMap, tmpWaterMap, i + 1, j, first);
            }
        }
        //만약 동서남북중 한군데라도 가장자리가 뚫려있다면 나머지는 볼 필요도 없이 성립하지 않으므로 isBlocked가 true일때만 방문하도록 한다.
        if (j + 1 < heightMap[i].length && isBlocked) {
            if (heightMap[i][j + 1] < first && waterMap[i][j + 1] < first && !tmpWaterMap[i][j + 1]) {
                isBlocked = recur(heightMap, waterMap, tmpWaterMap, i, j + 1, first);
            }
        }
        if (i - 1 >= 0 && isBlocked) {
            if (heightMap[i - 1][j] < first && waterMap[i - 1][j] < first && !tmpWaterMap[i - 1][j]) {
                isBlocked = recur(heightMap, waterMap, tmpWaterMap, i - 1, j, first);
            }
        }
        if (j - 1 >= 0 && isBlocked) {
            if (heightMap[i][j - 1] < first && waterMap[i][j - 1] < first && !tmpWaterMap[i][j - 1]) {
                isBlocked = recur(heightMap, waterMap, tmpWaterMap, i, j - 1, first);
            }
        }

        //isBlocked가 false일 때, 즉 한군데라도 뚫려있다면 false를 반환한다.
        if (!isBlocked) {
            return false;
        }

        //그게 아니라면 true를 반환하여 이 재귀에 해당하는 블럭들은 물웅덩이가 생길 조건을 만족했다고 알린다.
        return true;
    }

    //위의 재귀함수에서 지나온 길 (boolean 배열에서 true로 갱신된 인덱스)을 따라서 waterMap을 갱신하는 함수.
    private void checkWaterMap(int[][] waterMap, boolean[][] tmpWaterMap, int height){
        for (int i = 1; i < waterMap.length-1; i++){
            for (int j = 1; j < waterMap[i].length-1; j++){
                if(tmpWaterMap[i][j]){
                    waterMap[i][j] = height;
                }
            }
        }
    }
}