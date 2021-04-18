/* Tests for FileLineIterator */

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class GameTest {

    /* Here's a test to help you out,
     * but you still need to write your own.
     *
     * You don't need to create any files for your tests though.
     * (Our submission infrastructure actually won't accept any files you make for testing)
     */

    @Test
    public void testHasNextAndNext() {
        FileLineIterator li = new FileLineIterator("File/HighScore.csv");
        assertTrue(li.hasNext());
        assertEquals("make,6", li.next());
        assertEquals("JohnnyTest,6", li.next());
        assertFalse(li.hasNext());
    }

    @Test
    public void testFileNotThere() {
    	boolean val = false;
    	
    	try {
    		FileLineIterator li = new FileLineIterator("files/lol.csv");
    	}
    	catch (IllegalArgumentException e) {
    		val = true;
    	}
    	
    	assertEquals(val, true);
    }
    
    @Test 
    public void testHealthBarGameOver() {
    	
    	HealthBar health = new HealthBar();
    	health.humanShipDeath();
    	health.humanShipDeath();
    	health.humanShipDeath();
    	System.out.print(health.getLives());
    	
    	assertEquals(health.getLives(), 0);
    	
    }
    
    @Test
    public void removeIntersection() {
    	 TreeMap <Integer,Barrier> barrierMap = new TreeMap <Integer, Barrier>();
    	 LinkedList <Bullet> bulletList = new LinkedList <Bullet>();
         ArrayList <Bullet> bulletRemove = new ArrayList <Bullet>();
         
         if(!bulletList.isEmpty() ) {
         	for(Bullet b: bulletList) {
      
             if((!barrierMap.isEmpty())) {
          	   for (Map.Entry<Integer,Barrier>  entry : barrierMap.entrySet()) {
          		   Integer Key = entry.getKey();
          		   Barrier value = entry.getValue();
          		  if(b.intersects(value)) {
          			
               		value.setHitCount();
               		
               		
               		bulletRemove.add(b);	
               	}
          		   
          	   }
          	   
          	
             }
             

         	
         
         	
         	}
         	
         	   if(!bulletRemove.isEmpty() ) {
                	for(Bullet b: bulletRemove) {
                		
               		bulletList.remove(b);
                	  
                	}
         	   
         	   
         	   }
         	
         }
         
         assertEquals(bulletRemove.size(),0);
         
    	 
    }
    
    @Test
    public void addAliens() {
    	TreeMap <String, AlienShip> alienMap = new TreeMap <String, AlienShip>();
    	int alienNumber = 0;
    	for(int row = 60; row < 180; row+= 40 ) {
    		for(int col = 200; col <420 ;col+=40) {
    			alienNumber++;
    			alienMap.put( ("Alien"+alienNumber),new AlienShip(col, row, -2));
    		}
    	}
    	
    	 assertEquals(alienMap.size(), 18);
    	
    	
    }
    

    
    @Test
    public void testNullFile() {
    	
    	boolean val = false;
    	
    	try {
    		FileLineIterator li = new FileLineIterator(null);
    	}
    	catch (NullPointerException e) {
    		 val = true;
    	}
    	
    	assertEquals(val, true);
    }
    
 
    


}

