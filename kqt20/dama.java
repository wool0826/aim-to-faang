import java.lang.Math;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        
        int[] answer = new int[balls.length];
        
        //두 점 = (balls[i][0], balls[i][1]) & (startX, startY)
        for(int i = 0; i < balls.length; i++){
            double min = Double.MAX_VALUE;
            //left
            if( !(startX > balls[i][0] && startY == balls[i][1]) ){
                double b1 = startX;
                double b2 = balls[i][0];
                double N = (startY > balls[i][1]) ? startY - balls[i][1] : balls[i][1] - startY;
                
                double x = (b2*N)/(b1+b2);
                
                double result = (x*x+b2*b2+(N-x)*(N-x)+b1*b1 + 2*Math.sqrt((x*x+b2*b2)*((N-x)*(N-x)+b1*b1)));
                
                //double result = Math.pow(Math.sqrt(x*x + b2*b2) + Math.sqrt((N-x)*(N-x) + b1*b1) , 2);
            
                //int result = 2*((b2*N)/(b1+b2))*((b2*N)/(b1+b2)) - 2*((b2*N*N)/(b1+b2)) + N*N + b1*b1 + b2*b2;
                min = (result < min)? result : min;
            }
            //right
            if( !(startX < balls[i][0] && startY == balls[i][1]) ){
                double b1 = m - startX;
                double b2 = m - balls[i][0];
                double N = (startY > balls[i][1]) ? startY - balls[i][1] : balls[i][1] - startY;
                
                double x = (b2*N)/(b1+b2);
                
                double result = (x*x+b2*b2+(N-x)*(N-x)+b1*b1 + 2*Math.sqrt((x*x+b2*b2)*((N-x)*(N-x)+b1*b1)));
                
                //double result = Math.pow(Math.sqrt(x*x + b2*b2) + Math.sqrt((N-x)*(N-x) + b1*b1) , 2);
            
                //int result = 2*((b2*N)/(b1+b2))*((b2*N)/(b1+b2)) - 2*((b2*N*N)/(b1+b2)) + N*N + b1*b1 + b2*b2;
                min = (result < min)? result : min;
            }
            //down
            if( !(startY > balls[i][1] && startX == balls[i][0]) ){
                double b1 = startY;
                double b2 = balls[i][1];
                double N = (startX > balls[i][0]) ? startX - balls[i][0] : balls[i][0] - startX;
                
                double x = (b2*N)/(b1+b2);
                
                double result = (x*x+b2*b2+(N-x)*(N-x)+b1*b1 + 2*Math.sqrt((x*x+b2*b2)*((N-x)*(N-x)+b1*b1)));
                
                //double result = Math.pow(Math.sqrt(x*x + b2*b2) + Math.sqrt((N-x)*(N-x) + b1*b1) , 2);
            
                //int result = 2*((b2*N)/(b1+b2))*((b2*N)/(b1+b2)) - 2*((b2*N*N)/(b1+b2)) + N*N + b1*b1 + b2*b2;
                min = (result < min)? result : min;
            }  
            //up
            if( !(startY < balls[i][1] && startX == balls[i][0]) ){
                double b1 = n - startY;
                double b2 = n - balls[i][1];
                double N = (startX > balls[i][0]) ? startX - balls[i][0] : balls[i][0] - startX;
                
                double x = (b2*N)/(b1+b2);
                
                double result = (x*x+b2*b2+(N-x)*(N-x)+b1*b1 + 2*Math.sqrt((x*x+b2*b2)*((N-x)*(N-x)+b1*b1)));
                
                //double result = Math.pow(Math.sqrt(x*x + b2*b2) + Math.sqrt((N-x)*(N-x) + b1*b1) , 2);
            
                //int result = 2*((b2*N)/(b1+b2))*((b2*N)/(b1+b2)) - 2*((b2*N*N)/(b1+b2)) + N*N + b1*b1 + b2*b2;
                min = (result < min)? result : min;
            }
            //leftdown
            if((double)(startY/startX) == (double)(balls[i][1]/balls[i][0]) && startX < balls[i][0]){
                double x1 = startX;
                double x2 = balls[i][0];
                double y1 = startY;
                double y2 = balls[i][1];
                
                double result = (x1*x1 + y1*y1 + x2*x2 + y2*y2 + 2*Math.sqrt((x1*x1 + y1*y1)*(x2*x2 + y2*y2)));
                //double result = Math.pow(Math.sqrt(x1*x1+y1*y1) + Math.sqrt(x2*x2+y2*y2), 2);
                min = (result < min)? result : min;
            }
            //leftup
            if((double)((n-startY)/startX) == (double)((n-balls[i][1])/balls[i][0]) && startX < balls[i][0]){
                double x1 = startX;
                double x2 = balls[i][0];
                double y1 = (n-startY);
                double y2 = (n-balls[i][1]);
                
                double result = (x1*x1 + y1*y1 + x2*x2 + y2*y2 + 2*Math.sqrt((x1*x1 + y1*y1)*(x2*x2 + y2*y2)));
                //double result = Math.pow(Math.sqrt(x1*x1+y1*y1) + Math.sqrt(x2*x2+y2*y2), 2);
                min = (result < min)? result : min;
            }
            //righdown
            if((double)(startY/(m-startX)) == (double)(balls[i][1]/(m-balls[i][0])) && (m-startX) < (m-balls[i][0])){
                double x1 = (m-startX);
                double x2 = (m-balls[i][0]);
                double y1 = startY;
                double y2 = balls[i][1];
                
                double result = (x1*x1 + y1*y1 +x2*x2 + y2*y2 + 2*Math.sqrt((x1*x1 + y1*y1)*(x2*x2 + y2*y2)));
                //double result = Math.pow(Math.sqrt(x1*x1+y1*y1) + Math.sqrt(x2*x2+y2*y2), 2);
                min = (result < min)? result : min;
            }
            //righup
            if((double)((n-startY)/(m-startX)) == (double)((n-balls[i][1])/(m-balls[i][0])) && (m-startX) < (m-balls[i][0])){
                double x1 = (m-startX);
                double x2 = (m-balls[i][0]);
                double y1 = (n-startY);
                double y2 = (n-balls[i][1]);
                
                double result = (x1*x1 + y1*y1 + x2*x2 + y2*y2 + 2*Math.sqrt((x1*x1 + y1*y1)*(x2*x2 + y2*y2)));
                //double result = Math.pow(Math.sqrt(x1*x1+y1*y1) + Math.sqrt(x2*x2+y2*y2), 2);
                min = (result < min)? result : min;
            }
        
            answer[i] = (int)min;
            //System.out.println(min);
            //System.out.println((int)min);
        }
        
        return answer;
    }
}
