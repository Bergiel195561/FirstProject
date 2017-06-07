package com.ttpsc.task2;

public class ClassLoaderLoadClass {
    public static void main(String[] a) {
       java.io.PrintStream out = System.out;
       Object o = null;
       Class c = null;
       ClassLoader l = null;

       out.println("");
       out.println("Loading with 'new' operator...");
       o = new Hello();
       c = o.getClass();

       out.println("");
       out.println("Loading with Class.forName() method...");
       try {
          c = Class.forName("com.ttpsc.task2.ClassLoaderTest");
       } catch (Exception e) {
          e.printStackTrace();
       }

       //---------------------------------------------------------------------
       out.println("");
       out.println("Loading with ClassLoader.loadClass() method...");
       l = ClassLoader.getSystemClassLoader();
       try {
          c = l.loadClass("com.ttpsc.task2.SystemInOut");
       } catch (Exception e) {
          e.printStackTrace();
       }

       //-------------------------------------------------------------------
       out.println("");
       out.println("Loading the same class again...");
       l = ClassLoader.getSystemClassLoader();
       try {
          c = l.loadClass("com.ttpsc.task2.SystemInOut");
          Object newInstance = c.newInstance();
          
       } catch (Exception e) {
          e.printStackTrace();
       }
    }
 }

class Hello {}
class ClassLoaderTest{
}
class SystemInOut{}
