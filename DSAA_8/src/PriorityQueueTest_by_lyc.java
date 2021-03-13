class PriorityQueueTest {

    public static void main(String[] args) {

    }

}
class PriorityQueue{
    int[] s;
    int size = 0;
    public PriorityQueue(int n){
        s = new int[n];
    }
    //加入元素
    //根最小
    //k:队列（最后一个）要插入的位置 node: 要插入的节点
    public void siftUp_min(int k, int node){
        while(k>0){
            int parent = s[(k-1)/2];
            if(parent<=node){
                break;
            }
            s[k] = parent;
            k = (k-1)/2;
        }
        s[k] = node;
    }
    public void insert_min(int node){
        s[size] = node;
        size++;
        siftUp_min(size-1,node);
    }
    //删除元素
    //k: 删除元素的位置 node: 队列的最后一个节点
    public void siftDown_min(int k, int node){
        if(size==0){
            return;
        }
        int parent = (size - 2) / 2;
        while (k <= parent) {
            int left = 2 * k + 1;
            if(left>=size){
                break;
            }
            int right = left + 1;
            //选择左右子节点中最小的
            int best = left;
            if (right < size && s[left] > s[right]) {
                best = right;
            }
            if (node <= s[best]) {
                break;
            }
            s[k] = s[best];
            k = best;
        }
        s[k] = node;
    }
    public int delete_min(){
        if(size==0){
            return -1;
        }
        int a = s[0];
        int node = s[size-1];
        s[size-1] = 0;
        size--;
        siftDown_min(0, node);
        return a;
    }


    //加入元素
    //根最大
    //k:队列（最后一个）要插入的位置 node: 要插入的节点
    public void siftUp_max(int k, int node){
        while(k>0){
            int parent = s[(k-1)/2];
            if(parent>=node){
                break;
            }
            s[k] = parent;
            k = (k-1)/2;
        }
        s[k] = node;
    }
    public void insert_max(int node){
        s[size] = node;
        size++;
        siftUp_max(size-1,node);
    }
    //删除元素
    //k: 删除元素的位置 node: 队列的最后一个节点
    public void siftDown_max(int k, int node){
        if(size==0){
            return;
        }
        int parent = (size - 2) / 2;
        while (k <= parent) {
            int left = 2 * k + 1;
            if(left>=size){
                break;
            }
            int right = left + 1;
            //选择左右子节点中最大的
            int best = left;
            if (right < size && s[left] < s[right]) {
                best = right;
            }
            if (node >= s[best]) {
                break;
            }
            s[k] = s[best];
            k = best;
        }
        s[k] = node;
    }
    public int delete_max(){
        if(size==0){
            return -1;
        }
        int a = s[0];
        int node = s[size-1];
        s[size-1] = 0;
        size--;
        siftDown_max(0, node);
        return a;
    }
}