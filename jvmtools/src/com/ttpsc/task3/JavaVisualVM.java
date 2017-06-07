package com.ttpsc.task3;

import java.util.ArrayList;
import java.util.List;

public class JavaVisualVM {

    
    public static void main(String[] args) throws InterruptedException {
        
        List<MyLargeObject> myLargeObjects = new ArrayList<MyLargeObject>();
        
        while (true) {
            System.out.println("hello");
            Thread.sleep(1000);
            myLargeObjects.add(new MyLargeObject());
        }
        
    }
    
}

class MyLargeObject {

    List<Integer> internalList = new ArrayList<Integer>();
    
    public MyLargeObject() {
        for (int i = 0; i < 10e5; i++) {
            internalList.add(i);
        }
    }
    
    public MyLargeObject(double size) {
        for (int i = 0; i < size; i++) {
            internalList.add(i);
        }
    }
    
}
