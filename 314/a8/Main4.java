// Assignment 8, CSCE-314
// Section: 502
// Student Name: Jessica Fang
// UIN: 224003796
// Kyra (Katherine) Drake, Colin Banigan, Alexandria Stacy

import java.util.*;
import java.util.Arrays;
import java.lang.Class;
import java.lang.reflect.*;

public class Main4{    
    static void testing(Object obj){
		Class cl = obj.getClass();	
		Method methods[] = cl.getDeclaredMethods();
        
        
        for(Method method : methods){
            String mname = method.getName();
            ArrayList<Type> param = new ArrayList<Type>(Arrays.asList(method.getGenericParameterTypes()));
            if(Modifier.isStatic(method.getModifiers()) && Modifier.isPublic(method.getModifiers()) && param.size() == 0  && method.getGenericReturnType().toString().equals("boolean") && mname.length() >= 4 && mname.substring(0,4).equals("test")){
                try{
                    boolean temp = (Boolean) method.invoke((Object)obj, (Object[])null);
                    if(temp) System.out.println("OK: " + mname + " succeeded");
                    else System.out.println("FAILED: " + mname + " failed");
                }catch(Exception e){}
            }
        }
	}
    
    public static void main(String args[]){
        if(args.length != 1) System.out.println("enter one class");
        else{
            try{
                Object obj = Class.forName(args[0]).newInstance();
                testing(obj);
            }catch(Exception e){}
        }
	}
    
}