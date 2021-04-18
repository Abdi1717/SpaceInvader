import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class FileLineIterator implements Iterator<String> {

    // TODO: add the fields needed to implement your FileLineIterator
	private BufferedReader brTextReader;
	private Reader textReader;
	private String lineBeingRead = "";
	


    public FileLineIterator(String filePath) {
      File fileReader = new File(filePath);
    
       if (filePath == null || !fileReader.exists()) { 
    	   throw new IllegalArgumentException("filePath is null or filePath doesn't exist"); 
    	   }
    	
    	try {
			textReader = new FileReader(filePath);
			brTextReader = new BufferedReader(textReader);
			lineBeingRead = brTextReader.readLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.print("File not found");
		} catch (IOException e) {
			lineBeingRead = null;
			System.out.println(e);
		}
    	
    
    	
   

    }

 
    @Override
    public boolean hasNext() {
        
    
        try {
        	if (lineBeingRead != null) {
        		return true;
        	}
			brTextReader.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        return false;
    }

    @Override
    public String next() {
    if (hasNext()) {
      try{
    	  
    	  String lineOn = lineBeingRead;  
    	  
    	  lineBeingRead = brTextReader.readLine();
    	  
    	  return (lineOn);
    		
      } catch (IOException e) {
    	  throw new NoSuchElementException();
      }
    }
  
    return (lineBeingRead);
   }
}