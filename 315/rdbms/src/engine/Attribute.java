/** 
 * Class Attribute defines the Types(VARCHAR or INTEGER) that can exist in each
 * column in a Relation, as well as the name of that column and whether it is a
 * primary key
 * 
 * @author StaticLightningDragons
 */
package engine;

public class Attribute {
    /**
     * The domain of an attribute.
     * An attribute is only allow to be of
     * type VARCHAR or type INTEGER
     */
    public enum Type {
        VARCHAR,
        INTEGER
    }
    
    /**
     * {@value name} String name of the Attribute/Column
     * {@value type} Type VARCHAR or INTEGER of the Attribute/Column
     * {@value primary_key} Boolean if Attribute/Column can be a primary key value
     */
    private String name;
    private Type type;
    private boolean primary_key; // is it a primary key or not
    
    /**
     * Constructor
     * 
     * @param n The name of the Attribute
     * @param t The type of the Attribute, VARCHAR or INTEGER
     * @param primary If the Attribute is a primary key
     */
    public Attribute(String n, Type t, boolean primary) {
        name = n;
        type = t;
        primary_key = primary;
    }
    
    /**
     * Constructor
     * 
     * @param n The name of the Attribute
     */
    public Attribute(String n) {
        name = n;
    }
    
    /**
     * Copy Constructor
     * 
     * @param a Attribute to make a copy of
     */
    public Attribute(Attribute a) {
        this.name = a.name;
        this.type = a.type;
        this.primary_key = a.primary_key;
    }
    
    /**
     * Get the name of the attribute.
     *
     * @return Returns the name of the attribute
     */
    public String get_name() {
        return name;
    }
    
    /**
     * Get the type of the attribute
     * 
     * @return Returns the type of the Attribute, either VARCHAR or INTEGER
     */
    public Type get_type() {
        return type;
    }
    
    /**
     * Get if the attribute is a primary key
     * 
     * @return boolean True if Attribute is a primary_key, false otherwise
     */
    public boolean get_primary() {
        return primary_key;
    }
    
    /**
     * Set the name of the attribute
     * 
     * @param n The name of the Attribute
     */
    public void set_name(String n) {
        name = n;
    }
    
    /**
     * Set the type of the attribute
     * 
     * @param t The type of the Attribute
     */
    public void set_type(Type t) {
        type = t;
    }
    
    /**
     * Set whether the attribute is a primary key or not
     * 
     * @param b Boolean value of true if Attribute is primary key or false otherwise
     */
    public void set_primary(boolean b) {
        primary_key = b;
    }
    
    /**
     * Override hashCode function, produce a hash code to uniquely identify each attribute
     *
     * @return Returns the hash value of the attribute
     */
    @Override
    public int hashCode() {
        int hash = 5;
        String hash_type = (type == Type.VARCHAR ? "VARCHAR" : "INTEGER");
        hash = 89*hash + (this.name != null ? this.name.hashCode() : 0);
        hash = (primary_key ? 89*hash + 666 : 89*hash + 999);
        hash =  89*hash + (hash_type != null ? hash_type.hashCode() : 0);
        return hash;
    }
    
    /**
     * Override of equals function, check if two attributes are equal
     *
     * @param obj Attribute object being compared to THIS
     * @return Returns true if the attributes are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Attribute)) return false;
        if (obj == this) return true;
        return this.name.equals(((Attribute) obj).get_name()) && this.type == ((Attribute) obj).get_type() && this.primary_key == ((Attribute) obj).get_primary();
    }
}