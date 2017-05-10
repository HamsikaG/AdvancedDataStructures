import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class encoder {

	public static void main(String[] args)
	{
		String inputfile=args[0];
		File f=new File(inputfile);
		Read_Input input = new Read_Input();
		HashMap<String,Long> freq_table = input.Read_InputMethod(f);
		//File f=new File("C:\\Users\\hamsi\\Desktop\\ADS\\sample_input_large.txt");

//		// Binary Heap
//		BinaryHeap binaryHeap = new BinaryHeap();
//		long startTime = java.lang.System.nanoTime();
//		for (int runCount = 0;runCount<10;runCount++)
//		{
//			binaryHeap.BinHuffmanTree(freq_table);
//		}
//
//		long endTime = java.lang.System.nanoTime();
//		System.out.println("The elapsed time for Binary Heap is "+  (float)(endTime-startTime)/1000000000 + "s");
//
//		//PairingHeap
//				PairingHeap ph = new PairingHeap();
//				long startTimePH = java.lang.System.nanoTime();
//				for (int runCount = 0;runCount<10;runCount++)
//				{
//					ph.PairTree(freq_table);
//
//				}
//				long endTimePH = java.lang.System.nanoTime();
//				System.out.println("The elapsed time for PairingHeap is "+  (float)(endTimePH-startTimePH)/1000000000 + "s");









//		//FourWayHeap
		Datum newValue = null;
//		long startTimeFH = java.lang.System.nanoTime();
//
//		FourWayHeap Fh = new FourWayHeap();
//		for (int runCount = 0;runCount<10;runCount++)
//		{
//			newValue=Fh.buildTree(freq_table);
//
//		}
//		long endTimeFH = java.lang.System.nanoTime();
//     	System.out.println("The elapsed time for FourWayHeap is "+  (float)(endTimeFH-startTimeFH)/1000000000 + "s");


		long startTimeEncoder = java.lang.System.currentTimeMillis();
		FourWayHeap Fh1 = new FourWayHeap();

			newValue=Fh1.buildTree(freq_table);



		GenCodeTable table=new GenCodeTable();
		HashMap<Integer,StringBuilder> hMap=table.CodeTable(newValue);
 		try {
 			 File file = new File("encoded.bin");
 			FileOutputStream outputStream       = new FileOutputStream(file);
 	        StringBuilder sBuilder=new StringBuilder();

 			 if (!file.exists())
 					file.createNewFile();

 			  try{
 				  BufferedReader reader = new BufferedReader(new FileReader(f));
 			      String line;


 				    while ((line =reader.readLine())!= null){
 				    	if(!line.equals(""))
 				    		sBuilder.append(hMap.get(Integer.parseInt(line)).toString());
 				    }
 				  reader.close();
 	for(int i=0;i<sBuilder.length();i=i+8)
 		outputStream.write(Integer.parseInt(sBuilder.substring(i, i+8),2));

 	outputStream.close();
 			  }
 			  catch (Exception e){

 			  }

 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
		long endTimeEncoder = java.lang.System.currentTimeMillis();
		System.out.println("The elapsed time for Encoder is "+
		(float)(endTimeEncoder-startTimeEncoder)/1000+" s");


	}

}
 class GenCodeTable {

	public HashMap<Integer,StringBuilder> CodeTable(Datum newValue) {
		// TODO Auto-generated method stub
		HashMap<Integer,StringBuilder> hMap=new HashMap<>();

		ArrayList<String> aList=new ArrayList<>();
		CodeTableGenerator code=new CodeTableGenerator();
	    ((CodeTableGenerator) code).CodeTable(hMap,newValue,aList);
	    File file = new File("code_table.txt");
	    if (!file.exists()) {
	        try {
				if (file.createNewFile()) {
				    PrintWriter writer = new PrintWriter("code_table.txt", "UTF-8");
				    for (Map.Entry<Integer,StringBuilder> entry :hMap.entrySet()) {
					    writer.println(entry.getKey() + " " + entry.getValue());
					}

				    writer.close();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return hMap;


	}

}