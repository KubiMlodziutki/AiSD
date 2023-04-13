import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            //TODO
            this.object = e;
        }
        public Element(E e, Element next, Element prev) {
            //TODO
            this.object = e;
            this.next = next;
            this.prev = prev;
        }
        // add element e after this
        public void addAfter(Element elem) {
            //TODO
            this.next.prev = elem;
            elem.next = this.next;
            this.next = elem;
            elem.prev = this;
        }
        // assert it is NOT a sentinel
        public void remove() {
            //TODO
            assert this != sentinel;
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
        E object;
        Element next=null;
        Element prev=null;
        public int compareTo(Element element) {
            return ((Comparable<E>)this.object).compareTo(element.object);
        }
    }


    Element sentinel;
    int size = 0;

    private class InnerIterator implements Iterator<E>{
        //TODO
        Element current;
        public InnerIterator() {
            //TODO
            current = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            //TODO
            return current != sentinel;
        }

        @Override
        public E next() {
            //TODO
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            E obj = current.object;
            current = current.next;
            return obj;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        //TODO
        Element next;
        Element prev;
        int index;
        public InnerListIterator() {
            //TODO
            next = sentinel.next;
            prev = sentinel;
            index = 0;
        }
        @Override
        public boolean hasNext() {
            //TODO
            return next != sentinel;
        }

        @Override
        public E next() {
            //TODO
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            E obj = next.object;
            prev = next;
            next = next.next;
            index++;
            return obj;
        }
        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasPrevious() {
            //TODO
            return prev != sentinel;
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public E previous() {
            //TODO
            if (!hasPrevious()){
                throw new NoSuchElementException();
            }
            E obj = prev.object;
            next = prev;
            prev = prev.prev;
            index--;
            return obj;
        }
        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }
    public TwoWayCycledOrderedListWithSentinel() {
        //TODO
        sentinel = new Element(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        //TODO
        Element current = sentinel.next;
        while (current != sentinel){
            if (current.compareTo(new Element(e)) > 0){
                break;
            }
            current = current.next;
        }
        Element toAddBetween = new Element(e, current, current.prev);
        current.prev.next = toAddBetween;
        current.prev = toAddBetween;
        size++;
        return true;
    }

    private Element getElement(int index) {
        //TODO
        Element current = sentinel.next;
        if (index < 0 || index > size - 1){
            throw new NoSuchElementException();
        }
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current;
    }

    private Element getElement(E obj) {
        //TODO
        Element current = sentinel.next;
        while (!current.object.equals(obj)){
            if (current == sentinel){
                throw new NoSuchElementException();
            }
            if (current.object.equals(obj)){
                return current;
            }
            current = current.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        //TODO
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        //TODO
        boolean ifContains = false;
        Element current = sentinel.next;
        for (int i = 0; i < size; i++){
            if (current.object.equals(element)){
                ifContains = true;
                break;
            }
            current = current.next;
        }
        return ifContains;
    }

    @Override
    public E get(int index) {
        //TODO
        return getElement(index).object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        //TODO
        Element current = sentinel.next;
        for (int i = 0; i < size; i++){
            if (current.object.equals(element)){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return sentinel.next == sentinel;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        //TODO
        if (index < 0 || index > size - 1){
            throw new NoSuchElementException();
        }
        /*if (index == 0){
            Element toRemove = sentinel.next;
            sentinel.next = toRemove.next;
            toRemove.next.prev = sentinel;
            size--;
            return toRemove.object;
        }*/

        Element current = sentinel.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return current.object;
    }

    @Override
    public boolean remove(E e) {
        //TODO
        if (sentinel.next == sentinel){
            return false;
        }
        Element current = sentinel.next;
        /*if (current.object.equals(e)){
            current.next.prev = sentinel;
            sentinel.next = current.next;
            size--;
            return true;
        }*/
        while (current != sentinel && !current.object.equals(e)){
            current = current.next;
        }
        if (current == sentinel){
            return false;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return true;
    }

    @Override
    public int size() {
        //TODO
        return size;
    }

    //@SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        //TODO
        if (this == other){
            return;
        }
        if (other == null){
            return;
        }
        if (other.isEmpty()){
            return;
        }
        Element current = sentinel.next;
        Element otherCurrent = other.sentinel.next;

        while (otherCurrent != other.sentinel && current != sentinel) {
            Element otherCurrentNext = otherCurrent.next;
            if (otherCurrent.compareTo(current) < 0) {
                otherCurrent.prev.next = otherCurrent.next;
                otherCurrent.next.prev = otherCurrent.prev;
                current.prev.next = otherCurrent;
                otherCurrent.prev = current.prev;
                otherCurrent.next = current;
                current.prev = otherCurrent;
                size++;
                other.size--;
                otherCurrent = otherCurrentNext;
                continue;
            }
            current = current.next;
        }
        if (current == sentinel) {
            current = current.prev;
            Element secondOtherCurrent = other.sentinel.next;
            current.next = secondOtherCurrent;
            secondOtherCurrent.prev = current;
            other.sentinel.prev.next = sentinel;
            sentinel.prev = other.sentinel.prev;
            size += other.size();
            other.clear();
        }
    }

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        //TODO
        Element current = sentinel.next;
        if (e == null){
            return;
        }
        while (current != sentinel){
            if (current.object.equals(e)){
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
            }
            current = current.next;
        }
    }




}
