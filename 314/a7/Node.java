import java.util.Iterator;

public final class Node<T> implements Iterable<T>{
    public final T data;
    Node<T> next;
        
    public Node(T d){
        data = d;
        this.next = null;
    }
    
    public Node(T d, Node<T> next){
        data = d;
        this.next = next;
    }
    
    public void set_next(Node<T> next){
        this.next = next;
    }
    
    public Node<T> get_next(){
        return next;
    }
    
    public Iterator<T> iterator(){
        return new NodeIterator<T>(this);
    }
}