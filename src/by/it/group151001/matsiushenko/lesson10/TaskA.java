package by.it.group151001.matsiushenko.lesson10;

import java.util.*;

public class TaskA<E> implements NavigableSet<E> {
    private E value;
    private TaskA<E> left;
    private TaskA<E> right;
    private void SetKey(TaskA<E> elem){
        this.left = elem.left;
        this.right = elem.right;
        this.value = elem.value;
    }
    public TaskA() {
        this.value = null;
        this.left = null;
        this.right = null;

    }

    public static void main(String[] args) {
        TaskA<Integer> tree = new TaskA<>();
        tree.add(8);
        tree.add(7);
        tree.SetKey(null);
    }

    @Override
    public boolean add(E e) {
        if (this.value == null){
            this.value = e;
            this.left = new TaskA<>();
            this.right = new TaskA<>();
            return true;
        }else{
            if (this.value.hashCode() < e.hashCode()){
                return this.right.add(e);
            }
            if (this.value.hashCode() > e.hashCode()){
                return this.left.add(e);
            }
        }
        return false;
    }

    public TaskA<E> getMinimumKey(TaskA<E> curr)
    {
        while (curr.left.value != null) {
            curr = curr.left;
        }
        return curr;
    }
    public TaskA<E> deleteNode(TaskA<E> root, E key)
    {
        // указатель для хранения родителя текущего узла
        TaskA<E> parent = null;

        // начинаем с корневого узла
        TaskA<E> curr = root;
        System.out.println(curr.value);
        // поиск ключа в BST и установка его родительского указателя
        while (curr.value != null && !curr.value.equals(key))
        {
            // обновить родителя до текущего узла
            parent = curr;

            // если заданный ключ меньше текущего узла, переходим в левое поддерево;
            // иначе идем в правое поддерево
            if (curr.value.hashCode() > key.hashCode()) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }

        // возвращаем, если ключ не найден в дереве
        if (curr.value == null) {
            return root;
        }

        // Случай 1: удаляемый узел не имеет дочерних элементов, т. е. является листовым узлом
        if (curr.left.value == null && curr.right.value == null)
        {
            // если удаляемый узел не является корневым узлом, то устанавливаем его
            // родительский левый/правый дочерний элемент в null
            if (curr.value != root.value)
            {
                if (parent.left.value == curr.value) {
                    parent.left.value = null;
                }
                else {
                    parent.right.value = null;
                }
            }
            // если дерево имеет только корневой узел, устанавливаем его в null
            else {
                root.value = null;
            }
        }

        // Случай 2: удаляемый узел имеет двух потомков
        else if (curr.left.value != null && curr.right.value != null)
        {
            // найти его неупорядоченный узел-преемник
            TaskA<E> successor = getMinimumKey(curr.right);

            // сохраняем последующее значение
            E val = successor.value;

            // рекурсивно удаляем преемника. Обратите внимание, что преемник
            // будет иметь не более одного потомка (правого потомка)
            deleteNode(root, successor.value);

            // копируем значение преемника в текущий узел
            curr.value = val;
        }

        // Случай 3: удаляемый узел имеет только одного потомка
        else {
            // выбираем дочерний узел
            TaskA<E> child = (curr.left.value != null)? curr.left: curr.right;

            // если удаляемый узел не является корневым узлом, устанавливаем его родителя
            // своему потомку
            if (curr.value != root.value)
            {
                if (curr.value == parent.left.value) {
                    parent.left = child;
                }
                else {
                    parent.right = child;
                }
            }

            // если удаляемый узел является корневым узлом, то установить корень дочернему
            else {
                root = child;

            }
        }

        return root;
    }
    @Override

    public boolean remove(Object o) {
        TaskA<E> root = deleteNode(this,(E)o);
        this.SetKey( root);
        return root.value == this.value;
    }
    private void subString(StringBuilder sb) {
        if(this.value == null){
            return;
        }
        this.left.subString(sb);
        sb.append(this.value);
        sb.append(", ");
        this.right.subString(sb);
        return;
    }
    @Override
    public String toString() {
        if (this.value == null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        left.subString(sb);
        sb.append(this.value);
        sb.append(", ");
        right.subString(sb);
        sb.delete(sb.length() - 2,sb.length());
        sb.append("]");
        return sb.toString();

    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

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

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }
}
