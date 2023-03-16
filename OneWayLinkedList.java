
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object=e;
        }
        E object;
        Element next=null;
    }

    Element sentinel;

    private class InnerIterator implements Iterator<E>{
        // TODO
        Element current;
        public InnerIterator() {
            // TODO //DONE
            current = sentinel;
        }
        @Override
        public boolean hasNext() {
            // TODO //DONE
            return current.next != null;
        }

        @Override
        public E next() {
            // TODO //DONE
            if (hasNext()){
                current = current.next;
            }
            return null;
        }
    }

    public OneWayLinkedList() {
        sentinel = new Element(null);
        // TODO //DONE
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        // TODO Auto-generated method stub //DONE
        Element current = sentinel;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Element(e);
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        // TODO Auto-generated method stub //DONE
        Element current = sentinel;
        Element nextCurrent = sentinel;
        int iter = 0;
        while (iter < index){
            iter++;
            current = current.next;
            nextCurrent = nextCurrent.next;
        }
        current.next = new Element(element);
        current = current.next;
        current.next = nextCurrent;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub //DONE
        sentinel.next = null;

    }

    @Override
    public boolean contains(E element) {
        // TODO Auto-generated method stub //DONE
        Element current = sentinel;
        boolean ifContains = false;
        while (current.next != null){
            if (current == element){
                ifContains = true;
                break;
            }
            current = current.next;
        }
        return ifContains;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        // TODO Auto-generated method stub //DONE
        Element current = sentinel;
        int iter = 0;
        while(iter < index && current.next != null){
            current = current.next;
            iter++;
        }
        return current.object;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        // TODO Auto-generated method stub //DONE
        Element current = sentinel;
        Element setCurrent = sentinel;
        int iter = 0;
        while (iter < index){
            iter++;
            current = current.next;
            setCurrent = setCurrent.next;
        }
        if (setCurrent.next != null){
            setCurrent = setCurrent.next;
        }
        current.next = new Element(element);
        current = current.next;
        current.next = setCurrent;
        return null;
    }

    @Override
    public int indexOf(E element) {
        // TODO Auto-generated method stub //DONE
        int index = 0;
        Element current = sentinel;
        while (current.next != element){
            index++;
            current = current.next;
        }
        return index;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub //DONE
        Element current = sentinel;
        return current.next == null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        // TODO Auto-generated method stub
        Element current = sentinel;
        Element nextCurrent = sentinel;
        int iter = 0;
        while (iter < index){
            iter++;
            current = current.next;
            nextCurrent = nextCurrent.next;
        }
        nextCurrent = nextCurrent.next;
        current.next = nextCurrent.next;

        return null;
    }

    @Override
    public boolean remove(E e) {
        // TODO Auto-generated method stub
        Element current = sentinel;
        Element nextCurrent = sentinel;
        while (current.next != null){
            if (current.next == e){
                nextCurrent = nextCurrent.next;
                current.next = nextCurrent.next;
                break;
            }
            current = current.next;
            nextCurrent = nextCurrent.next;
        }

        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub //DONE
        Element current = sentinel;
        int sizeIterator = 0;
        while (current.next != null){
            sizeIterator++;
            current = current.next;
        }

        return sizeIterator;
    }

}

