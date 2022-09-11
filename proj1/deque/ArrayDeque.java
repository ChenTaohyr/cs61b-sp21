package deque;

public class ArrayDeque<T> {
    private Object[] array = new Object[8];
    private int resizeFactor = 2;
    private double usageFactor = 0.25;
    private int size = 0;
    private int capacity = 8;
    private int headIndex = -1;
    private int tailIndex = -1;


    public ArrayDeque() {
    }


    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(T item){
        if(size == capacity)resize(true);
        if(headIndex == -1) {
            headIndex = 0;
            tailIndex = 0;
        }
        if(headIndex == 0)headIndex += capacity;
        headIndex -= 1;
        array[headIndex] = item;
        size += 1;
    }


    public void addLast(T item){
        if(size == capacity)resize(true);
        if(tailIndex == -1) {
            headIndex = 0;
            tailIndex = 0;
        }
        if(tailIndex == capacity-1)tailIndex = tailIndex - capacity;
        tailIndex += 1;
        array[tailIndex] = item;
        size += 1;

    }
    public T removeLast(){
        if(size == 0)return null;
        if(tailIndex == 0)tailIndex += capacity;
        tailIndex -= 1;
        T returnItem = (T)array[tailIndex];
        array[tailIndex] = null;
        size -= 1;
        if(tailIndex == headIndex){
            tailIndex = -1;
            headIndex = -1;
        }
        double usage = (double)size/capacity;
        if(usage < usageFactor)resize(false);
        return returnItem;

    }
    public T removeFirst(){
        if(size == 0)return null;
        if(headIndex == capacity - 1)headIndex -= capacity;
        headIndex += 1;
        T returnItem = (T)array[tailIndex];
        array[headIndex] = null;
        size -= 1;
        if(tailIndex == headIndex){
            tailIndex = -1;
            headIndex = -1;
        }
        double usage = (double)size/capacity;
        if(usage < usageFactor)resize(false);
        return returnItem;

    }
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
            Object[] tempArray = new Object[newCapacity];
            if(headIndex < tailIndex){
                System.arraycopy(array,0,tempArray,0,capacity);
            }else {
                System.arraycopy(array,headIndex,tempArray,newCapacity-capacity+headIndex,capacity-headIndex);
                headIndex = newCapacity-capacity+headIndex;
            }
            array = tempArray;
            capacity = newCapacity;
        }else if(capacity > 8){
            int newCapacity = capacity / resizeFactor;
            Object[] tempArray = new Object[newCapacity];
            if(headIndex < tailIndex){
                System.arraycopy(array,headIndex,tempArray,0,size);
            }else {
                System.arraycopy(array,headIndex,tempArray,newCapacity-size+headIndex-1,size-headIndex+1);
                headIndex = newCapacity-size+headIndex-1;
            }
            array = tempArray;
            capacity = newCapacity;
        }

    }
    public void printDeque(){
        for(int i = 0; i<size ;i++){
            if(i+headIndex<capacity) {
                System.out.println(array[i + headIndex]);
            }else{
                System.out.println(array[i +headIndex -capacity]);
            }
        }


    }
    public T get(int index){
        return index+headIndex <capacity ? (T)array[index+headIndex] :(T)array[index+headIndex-capacity];
    }

}
