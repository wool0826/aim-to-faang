import java.util.Arrays;

class Solution1 {

    //디버깅용
    public static void main(String[] args) {
        //int[][] a = {{14,17,18,16,14,16}, {17,3,10,2,3,8},{11,10,4,7,1,7},{13,7,2,9,8,10},{13,1,3,4,8,6},{20,3,3,9,10,8}};
        int[][] a = {{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}};

        Solution1 sol = new Solution1();
        System.out.println("result = " + sol.trapRainWater(a));
    }

    public int trapRainWater(int[][] heightMap) {

        int[][] waterMap = new int[heightMap.length][heightMap[0].length];
        int result = 0;

        for (int[] ints : waterMap) {
            Arrays.fill(ints, -1);
        }

        for (int i=1; i<heightMap.length-1; i++) {
            for (int j=1; j<heightMap[i].length-1; j++) {
                if(waterMap[i][j] == -1){
                    recur(heightMap, waterMap, i, j, 2 * 10000, heightMap[i][j], 0);
                }
            }
        }

        for (int i=1; i<heightMap.length-1; i++) {
            for (int j=1; j<heightMap[i].length-1; j++) {
                if(waterMap[i][j] > heightMap[i][j]){
                    result += waterMap[i][j] - heightMap[i][j];
                }
            }
        }

        return result;

    }

    private int recur(int[][] heightMap, int[][] waterMap, int i, int j, int min, int first, int count){

        if(heightMap[i+1][j] <= first && i+1 >= heightMap.length-1){
            return 0;
        }
        if(heightMap[i][j+1] <= first && j+1 >= heightMap[i].length-1){
            return 0;
        }
        if(heightMap[i-1][j] <= first && i-1 <= 0){
            return 0;
        }
        if(heightMap[i][j-1] <= first && j-1 <= 0){
            return 0;
        }


        if(heightMap[i+1][j] > first){
            min = Math.min(min, heightMap[i+1][j]);
        }
        if(heightMap[i][j+1] > first){
            min = Math.min(min, heightMap[i][j+1]);
        }
        if(heightMap[i-1][j] > first){
            min = Math.min(min, heightMap[i-1][j]);
        }
        if(heightMap[i][j-1] > first){
            min = Math.min(min, heightMap[i][j-1]);
        }

        waterMap[i][j] = 0;
        int ifCount = 0;

        //방문한적이 없는 인덱스만 방문해야 하므로 waterMap  = -1 일때만 다음으로 넘어가야 한다.
        if(heightMap[i+1][j] < min && i+1 < heightMap.length-1 && waterMap[i+1][j] == -1){
            min = recur(heightMap, waterMap, i+1, j, min, first, count+1);
            ifCount++;
        }
        if(heightMap[i][j+1] < min && j+1 < heightMap[i].length-1 && waterMap[i][j+1] == -1){
            min = recur(heightMap, waterMap, i, j+1, min, first, count+1);
            ifCount++;
        }
        if(heightMap[i-1][j] < min && i-1 > 0 && waterMap[i-1][j] == -1){
            min = recur(heightMap, waterMap, i-1, j, min, first, count+1);
            ifCount++;
        }
        if(heightMap[i][j-1] < min && j-1 > 0 && waterMap[i][j-1] == -1){
            min = recur(heightMap, waterMap, i, j-1, min, first, count+1);
            ifCount++;
        }

        if(count == 0 && ifCount == 0
                && ((heightMap[i+1][j] <= heightMap[i][j]) || (heightMap[i][j+1] <= heightMap[i][j])
        || (heightMap[i-1][j] <= heightMap[i][j]) || (heightMap[i][j-1] <= heightMap[i][j]))) {
            min = 0;
        }

        if(heightMap[i][j] < first && min == 0){
            waterMap[i][j] = -1;
        } else {
            waterMap[i][j] = min;
            mapSync(waterMap, i, j);
        }

        return min;
    }

    private void mapSync(int[][] waterMap, int i, int j){

        if(waterMap[i+1][j] > 0 && waterMap[i+1][j] < waterMap[i][j]){
            waterMap[i+1][j] = waterMap[i][j];
            mapSync(waterMap, i+1, j);
        }
        if(waterMap[i][j+1] > 0 && waterMap[i][j+1] < waterMap[i][j]){
            waterMap[i][j+1] = waterMap[i][j];
            mapSync(waterMap, i, j+1);
        }
        if(waterMap[i-1][j] > 0 && waterMap[i-1][j] < waterMap[i][j]){
            waterMap[i-1][j] = waterMap[i][j];
            mapSync(waterMap, i-1, j);
        }
        if(waterMap[i][j-1] > 0 && waterMap[i][j-1] < waterMap[i][j]){
            waterMap[i][j-1] = waterMap[i][j];
            mapSync(waterMap, i, j-1);
        }
    }
}