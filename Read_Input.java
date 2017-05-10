import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Read_Input {
	public HashMap<String,Long>  Read_InputMethod(File f)
	{
        HashMap<String, Long> freq = new HashMap<String, Long>();
        List<String> l = new ArrayList<String>();
	  try{
		  BufferedReader reader = new BufferedReader(new FileReader(f));
	      String line;
		    while ((line =reader.readLine())!= null){
		    	if(!line.equals(""))
		      l.add(line);
		    }
		  reader.close();
	  }
	  catch (Exception e){
	    e.printStackTrace();
	    return null;
	  }
		for(int i=0;i<l.size();i++){
			String x=l.get(i);
			if (freq.containsKey(x) && x!="")
                freq.put(x, freq.get(x)+1 );
			else
                freq.put(x, (long)1 );
		}

//		for (Map.Entry<String, Integer> entry : freq.entrySet())
//		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		return freq;
	}


}
