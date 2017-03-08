package com.mycompany.app;

import org.assertj.core.util.diff.myers.MyersDiff;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello Siema!" );
        printMaster();
    	myFunction();
    }
    
    private static void printMaster(){
    	System.out.println("Szabat jest debeúciak");
        myFunction();

    }
    public static void myFunction() {
    	System.out.print("Jaro jest debe≈õciak !!!");
    }
    public int podajCosTam()
    {
    	return 6;
    }
}
