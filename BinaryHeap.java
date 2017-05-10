
import java.util.*;
import java.util.Map.Entry;
public class BinaryHeap{


    public ArrayList<Datum> myHeap;
    public int lChild(int current)
    {
        return 2*current+1;
    }

    public int rChild(int current)
    {
        return 2*current+2;
    }

    public int parent(int current){
        return (current-1)/2;
    }

    public int getHeapSize(){
        return myHeap.size();
    }



    public void heapify(int current)
    {
        int i=current;
        int left=lChild(i);
        int right=rChild(i);


        if(left<getHeapSize() && myHeap.get(left).freq<=myHeap.get(i).freq){
            i=left;
        }

        if(right<getHeapSize() && myHeap.get(right).freq<=myHeap.get(i).freq){
            i=right;
        }
        if(i!=current)
        {
            Collections.swap(myHeap,i,current);
            heapify(i);
        }
    }
public void insert(Datum node){
	myHeap.add(node);
     int curr=getHeapSize()-1;
     int currparent=parent(curr);
   //  System.out.println("\n node to be inserted is.. "+newDatum.getKey()+" "+newDatum.getFreq());
     while(curr>0 && myHeap.get(curr).freq<myHeap.get(currparent).freq)
     {
         Collections.swap(myHeap,curr,currparent);
         curr=currparent;
         currparent=parent(curr);

     }
}


    public Datum binaryRemove()
    {
        Datum d=null;
        if(getHeapSize()>0)
        {
            d=myHeap.get(0);
            if(getHeapSize()>1)
            {
            	int temp=getHeapSize()-1;
                myHeap.set(0,myHeap.remove(temp));
                heapify(0);
            }
            else{
            	myHeap.remove(0);
            }

        }
        return d;

    }

    public void BinHuffmanTree(HashMap<String,Long> hm){
        Datum left;
        Datum right;
        Datum newDatum;
        myHeap=new ArrayList<Datum>();
        for(Entry<String,Long>entry: hm.entrySet()){
            int x=Integer.parseInt(entry.getKey());
            Long b=entry.getValue();
            Datum d=new Datum(x,b);
                myHeap.add(d);

        }
        for(int i=(getHeapSize()/2)-1;i>=0;i--)
            heapify(i);
        while(myHeap.size()!=1){
            left=binaryRemove();
            right=binaryRemove();
            newDatum=new Datum(left.freq+right.freq);
            newDatum.left=left;
            newDatum.right=right;
            insert(newDatum);
    }
    }
}



