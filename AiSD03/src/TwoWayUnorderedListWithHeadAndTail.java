import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E> {

  private class Element {
    public Element(E e) {
      // TODO
      this.object = e;
    }

    public Element(E e, Element next, Element prev) {
      // TODO
      this.object = e;
      this.next = next;
      this.prev = prev;
    }

    E object;
    Element next = null;
    Element prev = null;
  }

  Element head;
  Element tail;
  // can be realization with the field size or without
  int size = 0;

  private class InnerIterator implements Iterator<E> {
    Element current;
    // TODO maybe more fields....

    public InnerIterator() {
      // TODO
      current = head;
    }

    @Override
    public boolean hasNext() {
      // TODO
      return current != null;
    }

    @Override
    public E next() {
      // TODO
      if (hasNext()) {
        Element temp = current;
        current = current.next;
        return temp.object;
      }
      return null;
    }
  }

  private class InnerListIterator implements ListIterator<E> {
    Element next;
    Element previous;
    // TODO maybe more fields....
    int index;

    public InnerListIterator(){
      previous = null;
      next = head;
      this.index = 0;
    }

    @Override
    public void add(E e) {
      throw new UnsupportedOperationException();

    }

    @Override
    public boolean hasNext() {
      // TODO Auto-generated method stub
      return next != null;
    }

    @Override
    public boolean hasPrevious() {
      // TODO Auto-generated method stub
      return previous != null;
    }

    @Override
    public E next() {
      // TODO Auto-generated method stub
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      E nextComp = next.object;
      previous = next;
      next = next.next;
      index++;

      return nextComp;
    }

    @Override
    public int nextIndex() {
      throw new UnsupportedOperationException();
    }

    @Override
    public E previous() {
      // TODO Auto-generated method stub
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }

      E previousComp = previous.object;
      next = previous;
      previous = previous.prev;
      index--;

      return previousComp;
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
    public void set(E e) {
      // TODO Auto-generated method stub
      TwoWayUnorderedListWithHeadAndTail.this.set(index, e);
    }
  }

  public TwoWayUnorderedListWithHeadAndTail() {
    // make a head and a tail
    head = null;
    tail = null;
  }

  @Override
  public boolean add(E e) {
    Element current = head;
    if (current == null) {
      head = new Element(e);
      tail = head;
      size++;
    } else {
      while (current.next != null) {
        current = current.next;
      }
      current.next = new Element(e);
      current.next.prev = tail;
      tail = current.next;
      size++;
    }
    return true;

  }

  @Override
  public void add(int index, E element) {
    // TODO
    Element current = head;
    if  (current == null){
      if (index == 0){
        head = new Element(element);
        tail = head;
        size++;
      }
      else{
        throw new NoSuchElementException();
      }
    }
    else{
      if (index == 0){
        Element temp = new Element(element);
        head = temp;
        temp.next = current;
        size++;
      }
      else{
        if (index == size){
          for (int i = 0; i < size - 1; i++){
            current = current.next;
          }
          Element temp = new Element(element);
          tail = temp;
          temp.prev = current;
          current.next = tail;
          size++;

        }
        else{
          if (index > 0 && index < size){
            for (int i = 0; i < index - 1; i++){
              current = current.next;
            }
            Element toAdd = new Element(element);
            Element temp = current.next;
            current.next = toAdd;
            toAdd.next = temp;
            size++;
          }
          else{
            throw new NoSuchElementException();
          }
        }
      }
    }
  }

  @Override
  public void clear() {
    // TODO
    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public boolean contains(E element) {
    // TODO
    Element current = head;
    for (int i = 0; i < size; i++){
      if (current.object.equals(element)){
        return true;
      }
      current = current.next;
    }
    return false;
  }

  @Override
  public E get(int index) {
    // TODO
    Element current = head;
    if (index < size && index >= 0) {
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
      return current.object;
    }
    throw new NoSuchElementException();
  }

  @Override
  public E set(int index, E element) {
    // TODO
    Element current = head;
    if (index < size && index >= 0) {
      for (int i = 0; i < index; i++) {
        current = current.next;
      }
      E toSet = current.object;
      current.object = element;
      return toSet;
    }
    throw new NoSuchElementException();
  }

  @Override
  public int indexOf(E element) {
    Element current = head;
    int index = 0;
    while (current != null) {
      if (current.object.equals(element)) {
        return index;
      }
      index++;
      current = current.next;
    }
    return -1;
  }

  @Override
  public boolean isEmpty() {
    // TODO

    return head == null;
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
  public E remove(int index) {
    Element current = head;
    if (index < size && index >= 0) {
      Element toRemove = current;
      if (index == 0) {
        if (head.next != null) {
          head = head.next;
          head.prev = null;
          size--;
          return toRemove.object;
        }
        clear();
        return toRemove.object;
      }

      for (int i = 0; i < index; i++) {
        current = current.next;
      }
      toRemove = current;
      if (index > 0 && index < size - 1) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return toRemove.object;
      }
      tail = current.prev;
      tail.next = null;
      size--;
      return toRemove.object;
    }

    throw new NoSuchElementException();
  }

  @Override
  public boolean remove(E e) {
    // TODO
    Element current = head;
    if (current == null){
      return false;
    }
    if (current.object.equals(e)){
      head = head.next;
      size--;
      return true;
    }
    while (current != null && !current.object.equals(e)){
      current = current.next;
    }
    if (current == null){
      return false;
    }
    if (current == tail){
      tail = tail.prev;
      tail.next = null;
      size--;
      return true;
    }

    current.prev.next = current.next;
    current.next.prev = current.next;
    size--;
    return true;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    Iterator<E> iterator = new InnerIterator();

    while (iterator.hasNext()) {
      builder.append("\n");
      builder.append(iterator.next());
    }
    return builder.toString();
  }

  public String toStringReverse() {
    ListIterator<E> iter = new InnerListIterator();
    while (iter.hasNext()) {
      iter.next();
    }

    StringBuilder builder = new StringBuilder();
    while (iter.hasPrevious()) {
      builder.append("\n").append(iter.previous());
    }
    return builder.toString();
  }

  public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
    // TODO
    if (other == null){
      return;
    }

    if (other.head == null){
      return;
    }

    if (other.head == head && other.tail == tail){
      return;
    }

    if (head == null) {
      head = other.head;
      tail = other.tail;
      size = other.size;
      other.head = null;
      other.tail = null;
      return;
    }

    tail.next = other.head;
    other.head.prev = tail;
    tail = other.tail;
    size += other.size;
    other.head = null;
    other.tail = null;
    
  }
}
