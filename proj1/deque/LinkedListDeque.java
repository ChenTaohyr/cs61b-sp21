package deque;


public class LinkedListDeque<type> {



    static class Node<T>{
        Node<T> last;
        Node<T> next;
        T item;

        public Node(Node<T> last , Node<T> next, T item) {
            this.last = last;
            this.next = next;
            this.item = item;
        }

        public Node() {
        }
    }

    private int size = 0;
    private Node<type> sentinel = new Node<>();

    public LinkedListDeque() {
        sentinel.last = sentinel;
        sentinel.next = sentinel;
        sentinel.item = null;
    }

    public boolean isEmpty(){
        return sentinel.last == sentinel;
    }


    public void addFirst(type item){
        sentinel.next = new Node<>(sentinel,sentinel.next,item);
        sentinel.next.next.last = sentinel.next;
        size += 1;
    }
    public void addLast(type item){
        sentinel.last = new Node<>(sentinel.last,sentinel,item);
        sentinel.last.last.next = sentinel.last;
        size += 1;
    }
    public type removeLast(){
        if(size == 0)return null;
        type returnItem = sentinel.last.item;
        sentinel.last = sentinel.last.last;
        sentinel.last.last.next = sentinel;
        size -= 1;
        return returnItem;

    }
    public type removeFirst(){
        if(size == 0)return null;
        type returnItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.last = sentinel;
        size -= 1;
        return returnItem;
    }
    public int size(){
        return size;
    }

    /**
     * index start at 1
     * @param index min 1
     * @return index th item of yhe quene
     */
    public type get(int index){
        if(index > size || size == 0 || index < 1)return null;
        Node<type> searchNode = sentinel;
        for(int i =0 ; i < index ; i++){
            searchNode = searchNode.next;
        }
        return searchNode.item;
    }
    public void printDeque(){
        Node<type> currNode = sentinel;
        for(int i = 0; i < size ; i++){
            currNode = currNode.next;
            System.out.print(currNode.item);
        }
        System.out.println();

    }
    /*
    ??????????
     */
    public type getRecursive(int index){

        return get(index);

    }



}
