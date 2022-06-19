class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {

        List<Integer> traveledList = new ArrayList<>();

        int travel = -1;
        int tempTravel;

        if(source == target)
            return 0;

        for(int i=0; i<routes.length; i++){
            tempTravel = findRoute(routes, i, source, target, routes.length, 1, traveledList);
            traveledList.clear();
            if(travel == -1 && tempTravel != -1)
                travel = tempTravel;
            else if(travel != -1 && tempTravel != -1 && travel > tempTravel)
                travel = tempTravel;
        }

        return travel;
    }

    public int findBus(int[] route, int low, int high, int target){

        if(low > high)
            return -1;

        int mid = (low + high)/2;
        if(route[mid] == target){
            return mid;
        }
        else if(route[mid] < target){
            return findBus(route, mid+1, high, target);
        }
        else{
            return findBus(route, low, mid-1, target);
        }
    }

    public int findRoute(int[][] routes, int routeIndex, int source, int target, int travel, int tempTravel, List<Integer> traveledList) {

        if(routeIndex<routes.length){
            int sourceIndex = findBus(routes[routeIndex], 0, routes[routeIndex].length-1, source);
            if(sourceIndex != -1){
                if(findBus(routes[routeIndex], 0, routes[routeIndex].length-1, target) != -1){
                    if(travel > tempTravel)
                        travel = tempTravel;
                    return travel;
                }
                traveledList.add(routeIndex);
                int result = -1;
                for(int i=0; i<routes[routeIndex].length; i++){
                    if(i != sourceIndex){
                        int nextIndex = 0;
                        while (traveledList.contains(nextIndex)){
                            nextIndex++;
                        }
                        int tmp = findRoute(routes, nextIndex, routes[routeIndex][i], target, travel, tempTravel+1, traveledList);
                        if(tmp != -1 && travel >= tmp) {
                            travel = tmp;
                            result = tmp;
                        }
                        for(int j=traveledList.size()-1; j>tempTravel-1; j--)
                            traveledList.remove(j);
                    }
                }
                return result;
            }
            int nextIndex = routeIndex+1;
            while (traveledList.contains(nextIndex)){
                nextIndex ++;
            }
            return findRoute(routes, nextIndex, source, target, travel, tempTravel, traveledList);
        }
        return -1;
    }
}