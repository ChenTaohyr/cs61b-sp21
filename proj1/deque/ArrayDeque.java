package deque;


public class ArrayDeque<T> implements Deque<T>{

    //private Object[] array = new Object[8];
    private T[] array = (T[]) new Object[8];
    private int resizeFactor = 2;
    private double usageFactor = 0.25;
    private int size = 0;
    private int capacity = 8;
    private int headIndex = -1;
    private int tailIndex = -1;


    public ArrayDeque() {
    }

//    @Override
//    public boolean isEmpty(){
//        return size == 0;
//    }
    @Override
    public void addFirst(T item){
        if(size == capacity)resize(true);
        if(headIndex == -1) {
            headIndex = 0;
            tailIndex = 0;
            array[headIndex] = item;
            size += 1;
            return;
        }
        if(headIndex == 0)headIndex += capacity;
        headIndex -= 1;
        array[headIndex] = item;
        size += 1;
    }

    @Override
    public void addLast(T item){
        if(size == capacity)resize(true);
        if(tailIndex == -1) {
            headIndex = 0;
            tailIndex = 0;
            array[tailIndex] = item;
            size += 1;
            return;
        }
        if(tailIndex == capacity-1)tailIndex = tailIndex - capacity;
        tailIndex += 1;
        array[tailIndex] = item;
        size += 1;

    }
    @Override
    public T removeLast(){
        if(size == 0)return null;
        T returnItem = (T)array[tailIndex];
        array[tailIndex] = null;
        if(tailIndex == headIndex){
            tailIndex = -1;
            headIndex = -1;
        }else {
            if (tailIndex == 0) tailIndex += capacity;
            tailIndex -= 1;
        }
        size -= 1;

        double usage = (double)size/capacity;
        if(usage < usageFactor)resize(false);
        return returnItem;

    }
    @Override
    public T removeFirst(){
        if(size == 0)return null;
        T returnItem = (T)array[headIndex];
        array[headIndex] = null;
        if(tailIndex == headIndex){
            tailIndex = -1;
            headIndex = -1;
        }else {
            if (headIndex == capacity - 1) headIndex -= capacity;
            headIndex += 1;
        }
        size -= 1;


        double usage = (double)size/capacity;
        if(usage < usageFactor)resize(false);
        return returnItem;

    }
    @Override
    public int size(){
        return size;
    }

    /**
     *
     * @param b true represents extend the ayyay ,false represents to reduce
     */

    private void resize(boolean b) {
        if(b){
            int newCapacity = capacity * resizeFactor;
            T[] tempArray = (T[]) new Object[newCapacity];
            if(headIndex < tailIndex){
                System.arraycopy(array,0,tempArray,0,capacity);

            }else {
                System.arraycopy(array,headIndex,tempArray,newCapacity-capacity+headIndex,capacity-headIndex);
                System.arraycopy(array,0,tempArray,0,tailIndex+1);
                headIndex = newCapacity-capacity+headIndex;
            }
            array = tempArray;
            capacity = newCapacity;
        }else if(capacity > 8){
            int newCapacity = capacity / resizeFactor;
            T[] tempArray = (T[])new Object[newCapacity];
            if(headIndex < tailIndex){
                System.arraycopy(array,headIndex,tempArray,0,size);
                headIndex = 0;
                tailIndex = size-1;
            }else {
                System.arraycopy(array,headIndex,tempArray,newCapacity-capacity+headIndex,capacity-headIndex);
                System.arraycopy(array,0,tempArray,0,tailIndex+1);
                headIndex = newCapacity-size+headIndex-1;
            }
            array = tempArray;
            capacity = newCapacity;
        }

    }
    @Override
    public void printDeque(){
        for(int i = 0; i<size ;i++){
            if(i+headIndex<capacity) {
                System.out.println(array[i + headIndex]);
            }else{
                System.out.println(array[i +headIndex -capacity]);
            }
        }

    }
    @Override
    public T get(int index){
        return index+headIndex <capacity ? (T)array[index+headIndex] :(T)array[index+headIndex-capacity];
    }

}
