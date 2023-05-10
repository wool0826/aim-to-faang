import java.util.*;
import java.lang.Math;

class Solution {
    
    private static List<Integer[]> firstLines = new ArrayList<>();
    
    public int solution(int[][] clockHands) {
        
        int min = Integer.MAX_VALUE;
        
        permutation(new Integer[clockHands.length], 0, clockHands.length);
        
        for(Integer[] firstLine : firstLines){
            
            int tmpMin = 0;
            
            int[][] change = new int[clockHands[0].length][clockHands.length];
            for(int i=0; i<clockHands[0].length; i++){
                System.arraycopy(clockHands[i],0,change[i],0,clockHands[i].length);
            }
            
            for(int i=0; i<firstLine.length; i++){            
                for(int j=1; j<=firstLine[i]; j++){
                    turn(change, i-1, 0);
                    turn(change, i, 0);
                    turn(change, i+1, 0);
                    turn(change, i, 1);
                    tmpMin++;
                }
            }
            for(int j=1; j<change.length; j++){
                for(int i=0; i<change[j].length; i++){
                    while((change[j-1][i]) !=0){
                        turn(change, i-1, j);
                        turn(change, i, j);
                        turn(change, i+1, j);
                        turn(change, i, j-1);
                        turn(change, i, j+1);
                        tmpMin++;
                    }
                }
            }
            boolean isZero = true;
            for(int i=0; i<change[0].length; i++){
                if(change[change.length-1][i] != 0)
                    isZero = false;
            }
            if(isZero)
                min = Math.min(min,tmpMin);
        }
        
        return min;
    }
    private void turn(int[][] change, int x, int y){
        if(x>=0 && x<change[0].length && y>=0 && y<change.length)
            change[y][x] = (change[y][x] + 1) % 4;
    }
    private void permutation(Integer[] tmp, int count, int length){
        if(count == length){
            Integer[] save = new Integer[length];
            System.arraycopy(tmp,0,save,0,length);
            firstLines.add(save);
            return;
        }
        for(int i=0; i<4; i++){
            tmp[count] = i;
            permutation(tmp, count+1, length);
        }
    }
}
