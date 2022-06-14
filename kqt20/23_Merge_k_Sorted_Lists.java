class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        //파라미터로 들어온 리스트를 모조리 읽어서 오름차순으로 정렬하기위해
        //리스트의 val을 KEY로 가지고, 해당 val이 얼마나 있는지를 표현하기 위해 ListNode를 VALUE로 가지는 맵을 만든다.
        Map<Integer, ListNode> counting = new TreeMap<>();

        //파라미터로 들어온 ListNode로 이루어진 배열에서 ListNode를 하나씩 사용하기 위한 for each 문.
        for (ListNode list : lists) {

            //ListNode를 순차적으로 방문하며 끝까지 방문하여 ListNode가 null이 되면 끝나는 반복문.
            while(list != null){

                //맵이 현재 인자로 들어온 list의 val을 KEY로 가지고 있다면 그 KEY에 저장된 ListNode를 가져와서
                //그 ListNode를 순차적으로 탐색한 뒤, 가장 마지막 노드에 현재 val을 가지는 ListNode를 새로 추가해준다.
                //안 가지고 있으면 새로 만든다.
                if (counting.containsKey(list.val)) {
                    findLastNode(counting.get(list.val)).next = new ListNode(list.val);
                } else {
                    counting.put(list.val, new ListNode(list.val));
                }

                //다음 노드를 방문한다.
                 list = list.next;
             }
        }

        //리턴하기위한 빈껍데기 생성.
        ListNode result = null;

        //맵에 흩어진 노드들을 잇기위해서 현재 result에 묶인 노드중 가장 마지막 노드를 저장하는 노드.
        ListNode lastNode = null;

        //맵을 순차적으로 탐색하는 for each문. TreeMap으로 저장해서 오름차순으로 정렬이 되어있다.
        for (Map.Entry<Integer, ListNode> entry : counting.entrySet()) {

            //첫 KEY, VALUE로 result 초기화
            if(result == null){
                result = entry.getValue();

                //result.next가 null이면 탐색이 불가능하므로 따로 result를 그대로 넣고
                //null이 아니면 다음노드로 넘어간다.
                if(result.next != null)
                    lastNode = result.next;
                else
                    lastNode = result;

            //첫 노드를 잇고 난 후 마지막 노드에 다음 노드를 잇는다.
            } else {
                lastNode.next = entry.getValue();
            }

            //마지막 노드 경신
            lastNode = findLastNode(lastNode);
        }
        return result;
    }

    //ListNode를 탐색하여 가장 마지막 노드를 반환하는 메소드
    private ListNode findLastNode(ListNode list){
        while (list.next != null){
            list = list.next;
        }
        return list;
    }
}