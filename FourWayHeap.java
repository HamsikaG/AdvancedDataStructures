
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;


public class FourWayHeap {


	public static ArrayList<Datum> heap;

	public static void heapify(int pos) {


		int i=pos;
		int c1=Child1(i);
		int c2=Child2(i);
		int c3=Child3(i);
		int c4=Child4(i);

		if(c1<heap.size() && heap.get(i).freq>heap.get(c1).freq){
			i=c1;
		}
		if(c2<heap.size() && heap.get(i).freq>heap.get(c2).freq){
			i=c2;
		}
		if(c3<heap.size() && heap.get(i).freq>heap.get(c3).freq){
			i=c3;
		}
		if(c4<heap.size() && heap.get(i).freq>heap.get(c4).freq){
			i=c4;
		}
        if(i!=pos)
        {
            Collections.swap(heap,i,pos);
            heapify(i);
        }
	}

	public static void insert(Datum n){

		heap.add(n);
		int i=heap.size()-1;
		int p=parent(i);
		while(i>0 && heap.get(i).freq<heap.get(p).freq){
			Collections.swap(heap, i, p);
			i=p;
			p=parent(i);
		}

	}
	public static Datum removeMin(){

		Datum min=null;
		if(heap.size()>3){
		   min=heap.get(3);
		    if(heap.size()>4){
	        	heap.set(3,heap.remove(heap.size()-1));
	        	heapify(3);
		    }
		    else
		    	heap.remove(3);
	}
		return min;
}


	public static int Child1(int i) {
		return 4*(i-2);
	}
	public static int Child2(int i) {
		return 4*(i-2)+1;
	}
	public static int Child3(int i) {
		return 4*(i-2)+2;
	}
	public static int Child4(int i) {

		return 4*(i-2)+3;
	}

	 public static int parent(int i){
	        return (i/4)+2;
	    }

	 public Datum buildTree(HashMap<String,Long> hm){

		 Datum left=null;
		 Datum right=null;
		 Datum newDatum=null;

		 heap=new ArrayList<Datum>();
         heap.add(new Datum(0,(long)0));
         heap.add(new Datum(0,(long)0));
         heap.add(new Datum(0,(long)0));
		 Set<Map.Entry<String,Long>> entryset = hm.entrySet();
	     for(Entry<String,Long> entry :entryset)	  {
		      int x=Integer.parseInt(entry.getKey());
		      Long y=entry.getValue();
		      Datum n =new Datum(x,y);
	    	  heap.add(n);
	     }

	        for(int i=((heap.size()-1)/4)+2;i>=3;i--)
	        	heapify(i);
	   while(heap.size()>4){
	            left=removeMin();
	            right=removeMin();
	            newDatum=new Datum(left.freq+right.freq);
	            newDatum.left=left;
                newDatum.right=right;
	            insert(newDatum);

       }
	return newDatum;
	 }
 }













