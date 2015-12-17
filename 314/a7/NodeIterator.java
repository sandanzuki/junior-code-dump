import java.util.*;
import java.util.Iterator;

public class NodeIterator<T> implements Iterator<T>{
    Node<T> next_node;
    
    public NodeIterator(Node<T> n){
       next_node = n; 
    }
    
    public boolean hasNext(){
        return next_node != null;
    }
    
    public T next(){
        if(!hasNext()) throw new NoSuchElementException();
        T data = next_node.data;
        next_node = next_node.get_next();
        return data;
    }
}