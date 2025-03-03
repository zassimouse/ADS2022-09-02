package by.it.group151002.vakar.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    //Обязательные к реализации методы и конструкторы
    private E value;
    private TaskB<E> left;
    private TaskB<E> right;

    public E getValue() {
        return value;
    }

    public TaskB<E> getLeft() {
        return left;
    }

    public TaskB<E> getRight() {
        return right;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public void setLeft(TaskB<E> left) {
        this.left = left;
    }

    public void setRight(TaskB<E> right) {
        this.right = right;
    }

    public void setNode(TaskB<E> node) {
        this.value = node.getValue();
        this.setRight(node.getRight());
        this.setLeft(node.getLeft());
    }

    public TaskB() {
        this.value = null;
        this.left = null;
        this.right = null;
    }
    //Обязательные к реализации методы и конструкторы

    private void delete_right(TaskB<E> deleted) {
        if (this.getRight() != null && this.getRight().getValue() != null) {
            this.getRight().delete_right(deleted);
            return;
        }
        deleted.setValue(this.getValue());
        this.setNode(this.getLeft());
    }

    @Override
    public boolean add(E e) {
        if (!(e instanceof Comparable)) {
            return false;
        }
        if (this.getValue() == null) {
            this.setValue(e);
            this.setRight(new TaskB<>());
            this.setLeft(new TaskB<>());
            return true;
        }
        if (((Comparable) this.getValue()).compareTo(e) > 0) {
            return this.getLeft().add(e);
        }
        if (((Comparable) this.getValue()).compareTo(e) < 0) {
            return this.getRight().add(e);
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Comparable)) {
            return false;
        }
        if (this.getValue() == null) {
            return false;
        }
        if (((Comparable<E>) o).compareTo(this.getValue()) > 0) {
            return this.getRight().remove(o);
        }
        if (((Comparable<E>) o).compareTo(this.getValue()) < 0) {
            return this.getLeft().remove(o);
        }
        if (this.getRight().getValue() == null) {
            this.setNode(this.getLeft());
            return true;
        }
        if (this.getLeft().getValue() == null) {
            this.setNode(this.getRight());
            return true;
        }
        this.getLeft().delete_right(this);
        return true;
    }

    @Override
    public boolean contains(Object o) {

        if (!(o instanceof Comparable)) {
            return false;
        }
        if (this.getValue() == null) {
            return false;
        }
        if (((Comparable) o).compareTo(this.getValue()) > 0) {
            return this.getRight().contains(o);
        }
        if (((Comparable) o).compareTo(this.getValue()) < 0) {
            return this.getLeft().contains(o);
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        this.setValue(null);
        this.setRight(null);
        this.setLeft(null);
    }

    @Override
    public boolean isEmpty() {
        if(this.getValue() == null){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        if(this.getValue() == null){
            return 0;
        }
        return 1 + this.getLeft().size() + this.getRight().size();
    }

    @Override
    public E first() {
        if(this.getLeft().getValue() != null){
            return this.getLeft().first();
        }
        return this.getValue();
    }
    @Override
    public E last() {
        if(this.getRight().getValue() != null){
            return this.getRight().last();
        }
        return this.getValue();
    }

    private void subString(StringBuilder builder) {
        if (this.getValue() == null) {
            return;
        }
        this.getLeft().subString(builder);
        builder.append(this.getValue());
        builder.append(", ");
        this.getRight().subString(builder);
    }

    @Override
    public String toString() {
        if (this.getValue() == null) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        getLeft().subString(builder);
        builder.append(this.getValue());
        builder.append(", ");
        getRight().subString(builder);
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public E lower(E e) {
        return null;
    }

    @Override
    public E floor(E e) {
        return null;
    }

    @Override
    public E ceiling(E e) {
        return null;
    }

    @Override
    public E higher(E e) {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return null;
    }


}
