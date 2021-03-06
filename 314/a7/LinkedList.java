import java.util.*;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{
    Node<T> head = null;
    Node<T> current;
    
    public LinkedList(){
        head = null;
        current = head;
    }
    
    public LinkedList(Iterable<T> iterable){
        for(T e : iterable) add(e);
    }
    
    public Iterator<T> iterator(){
        return new LinkedListIterator<T>(head);
    }
    
    public Node<T> get_head(){
        return head;
    }
    
    public void insert_first(T data){
        head = new Node<T>(data, head);
    }
    
    public void add(T data){
        Node<T> temp = new Node<T>(data);
        if(data != null){ 
            if(head != null){
                current = head;
                while(current.get_next() != null){ 
                    current = current.get_next(); 
                }
                current.set_next(temp);
            }else{
                head = temp;
            }
        }
    }
    
    public LinkedList<T> reverse(){
        LinkedList<T> list = new LinkedList<T>();
        Node<T> temp = head;
        while(temp != null){
            list.insert_first(temp.data);
            temp = temp.next;
        }
        return list;
    }
    
    @Override
    public String toString(){
        String temp = "[ ";
        for(T e : this){
            temp = temp + e + " ";
        }
        temp = temp + "]";
        return temp;
    }
    
    class LinkedListIterator<T> implements Iterator<T>{
        Node<T> next_node;

        public LinkedListIterator(Node<T> n){
           next_node = n; 
        }

        public boolean hasNext(){
            return next_node != null;
        }

        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            T data = next_node.data;
            next_node = next_node.next;
            return data;
        }
    }
}