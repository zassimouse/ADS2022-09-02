package by.it.group151003.romanko.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    public TaskC() {
    }

    public int size = 0;
    private Node<E> rootNode;

    private int compareTo(E o, E o1) {
        return Integer.compare((int) o, (int) o1);
    }

    public class Node<E> {
        public E value;
        public Node<E> leftChild;
        public Node<E> parent;
        public Node<E> rightChild;

        public Node(E e) {
            this.value = e;
        }
    }

    @Override
    public boolean add(E e) {
        boolean isDone = false;
        if (!contains(e)) {
            Node<E> newNode = new Node(e);
            Node<E> currentNode = this.rootNode;
            Node<E> prevNode;
            if (this.rootNode == null) {
                this.rootNode = newNode;
                this.rootNode.parent = null;
                this.size++;
                return true;
            } else {
                while (!isDone) {
                    prevNode = currentNode;
                    if (compareTo(currentNode.value, e) > 0) {
                        if (currentNode.leftChild == null) {
                            currentNode.leftChild = newNode;
                            currentNode.leftChild.parent = currentNode;
                            this.size++;
                            isDone = true;
                        }
                        currentNode = currentNode.leftChild;
                    } else {
                        if (currentNode.rightChild == null) {
                            currentNode.rightChild = newNode;
                            currentNode.rightChild.parent = currentNode;
                            this.size++;
                            isDone = true;
                        }
                        currentNode = currentNode.rightChild;
                    }
                }
            }
        }
        return isDone;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> currentNode = this.rootNode;
        boolean isDone = false;
        while (!isDone && size > 0) {
            if (currentNode == null) return false;
            if (currentNode != null && compareTo(currentNode.value, (E) o) > 0) {
                currentNode = currentNode.leftChild;
            } else if (currentNode != null && compareTo(currentNode.value, (E) o) < 0) {
                currentNode = currentNode.rightChild;
            }
            if (currentNode != null && compareTo(currentNode.value, (E) o) == 0) {
                isDone = true;
            }
        }
        if (currentNode.leftChild == null && currentNode.rightChild == null) {
            if (currentNode.parent == null) {
                rootNode = null;
            } else if (currentNode.parent.rightChild == currentNode) {
                currentNode.parent.rightChild = null;
            } else {
                currentNode.parent.leftChild = null;
            }
            size--;
            return true;
        }
        if (currentNode.leftChild == null || currentNode.rightChild == null) {
            if (currentNode.leftChild == null) {
                if (currentNode.parent == null) {
                    rootNode = currentNode.rightChild;
                } else if (currentNode.parent.leftChild == currentNode) {
                    currentNode.rightChild.parent = currentNode.parent;
                    currentNode.parent.leftChild = currentNode.rightChild;
                } else if (currentNode.parent.rightChild == currentNode) {
                    currentNode.rightChild.parent = currentNode.parent;
                    currentNode.parent.rightChild = currentNode.rightChild;
                }
            }
            if (currentNode.rightChild == null) {
                if (currentNode.parent == null) {
                    rootNode = currentNode.leftChild;
                } else if (currentNode.parent != null && currentNode.parent.rightChild == currentNode) {
                    currentNode.leftChild.parent = currentNode.parent;
                    currentNode.parent.rightChild = currentNode.leftChild;
                } else if (currentNode.parent != null && currentNode.parent.leftChild == currentNode) {
                    currentNode.leftChild.parent = currentNode.parent;
                    currentNode.parent.leftChild = currentNode.leftChild;
                }
            }
            size--;
            return true;
        }
        if (currentNode.leftChild != null && currentNode.rightChild != null) {
            Node<E> tmp;
            tmp = currentNode.rightChild;
            boolean isDone2 = false;
            while (tmp.leftChild != null) {
                tmp = tmp.leftChild;
                isDone2 = true;
            }
            if (isDone2) {
                if (tmp.rightChild != null) {
                    tmp.rightChild.parent = tmp.parent;
                    tmp.parent.leftChild = tmp.rightChild;
                    currentNode.value = tmp.value;
                }
                if (tmp.rightChild == null) {
                    tmp.parent.leftChild = null;
                    currentNode.value = tmp.value;
                }
                size--;
                return true;
            } else if (!isDone2) {
                if (tmp.rightChild != null) {
                    tmp.rightChild.parent = tmp.parent;
                    tmp.parent.rightChild = tmp.rightChild;
                    currentNode.value = tmp.value;
                }
                if (tmp.rightChild == null) {
                    tmp.parent.rightChild = null;
                    currentNode.value = tmp.value;
                }
                size--;
                return true;
            }
            size--;
            return true;
        }
        return false;
    }

    private void inOrderSB(Node<E> current, StringBuilder s) {
        if (current != null) {
            inOrderSB(current.leftChild, s);
            s.append(current.value);
            s.append(", ");
            inOrderSB(current.rightChild, s);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append('[');
        inOrderSB(this.rootNode, s);
        if (s.length() < 2) {
            s.append("]");
        } else {
            s.delete(s.length() - 2, s.length());
            s.append("]");
        }
        return s.toString();
    }

    @Override
    public boolean contains(Object o) {
        Node<E> currentNode = this.rootNode;
        boolean isDone = false;
        while (!isDone && this.size > 0) {
            if (currentNode == null) return false;
            if (currentNode != null && compareTo(currentNode.value, (E) o) > 0) {
                currentNode = currentNode.leftChild;
            }
            if (currentNode != null && compareTo(currentNode.value, (E) o) < 0) {
                currentNode = currentNode.rightChild;
            }
            if (currentNode != null && compareTo(currentNode.value, (E) o) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.rootNode = null;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E first() {
        Node<E> node = rootNode;
        while (node.leftChild != null){
            node = node.leftChild;
        }
        return node.value;
    }

    @Override
    public E last() {
        Node<E> node = rootNode;
        while (node.rightChild != null){
            node = node.rightChild;
        }
        return node.value;
    }

    @Override
    public E lower(E e) {
        E value = null;
        StringBuilder s = new StringBuilder();
        inOrderSB(this.rootNode, s);
        String[] tmp = s.toString().replaceAll("[\\s]{1,}", "").split(",");
        Integer[] tmp1 = new Integer[tmp.length];
        for (int i = 0; i < tmp.length; i++){
            tmp1[i] = Integer.valueOf(tmp[i]);
        }
        E[] arr = (E[]) tmp1;
        for (E elem: arr){
            if (compareTo(elem, e) >= 0){
                return value;
            }
            value = elem;
        }
        return null;
    }

    @Override
    public E floor(E e) {
        E value = null;
        StringBuilder s = new StringBuilder();
        inOrderSB(this.rootNode, s);
        String[] tmp = s.toString().replaceAll("[\\s]{1,}", "").split(",");
        Integer[] tmp1 = new Integer[tmp.length];
        for (int i = 0; i < tmp.length; i++){
            tmp1[i] = Integer.valueOf(tmp[i]);
        }
        E[] arr = (E[]) tmp1;
        for (E elem: arr){
            if (compareTo(elem, e) > 0){
                return value;
            }
            value = elem;
        }
        if (value != null && compareTo(value, e) == 0)
            return value;
        return null;
    }

    @Override
    public E ceiling(E e) {
        E value = null;
        StringBuilder s = new StringBuilder();
        inOrderSB(this.rootNode, s);
        String[] tmp = s.toString().replaceAll("[\\s]{1,}", "").split(",");
        Integer[] tmp1 = new Integer[tmp.length];
        for (int i = 0; i < tmp.length; i++){
            tmp1[i] = Integer.valueOf(tmp[i]);
        }
        E[] arr = (E[]) tmp1;
        for (E elem: arr){
            if (compareTo(elem, e) >= 0){
                return elem;
            }
        }
        return null;
    }

    @Override
    public E higher(E e) {
        E value = null;
        StringBuilder s = new StringBuilder();
        inOrderSB(this.rootNode, s);
        String[] tmp = s.toString().replaceAll("[\\s]{1,}", "").split(",");
        Integer[] tmp1 = new Integer[tmp.length];
        for (int i = 0; i < tmp.length; i++){
            tmp1[i] = Integer.valueOf(tmp[i]);
        }
        E[] arr = (E[]) tmp1;
        for (E elem: arr){
            if (compareTo(elem, e) > 0){
                return elem;
            }
        }
        return null;
    }

    @Override
    public E pollFirst() {
        E firstValue = this.first();
        remove(firstValue);
        return firstValue;
    }

    @Override
    public E pollLast() {
        E lastValue = this.last();
        remove(lastValue);
        return lastValue;
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
