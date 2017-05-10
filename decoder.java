import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class decoderDatum{
	int data;
	String code;
	decoderDatum left;
	decoderDatum right;
	public decoderDatum() {
		data=-1;
		code="";
		left=null;
		right=null;
	}
}

class huffManTree{
	decoderDatum root;

	public huffManTree() {
		// TODO Auto-generated constructor stub
		root=new decoderDatum();
	}

	void decodeTree(ArrayList<decoderDatum> decoderDatum){
		for(int i=0;i<decoderDatum.size();i++){
			decoderDatum node=this.root;
			String code=decoderDatum.get(i).code;
			for(int j=0;j<code.length();j++){
				if(code.charAt(j)=='0'){
					if(node.left == null)  node.left = new decoderDatum();
					node=node.left;
				}
				else {
					if(node.right == null) node.right=new decoderDatum();
					node=node.right;
				}
			}
			node.data=decoderDatum.get(i).data;
		}
	}

	void decoder(String binary) throws IOException{
		FileInputStream fis=new FileInputStream(binary);
		String decoderWriter="decoded.txt";
		FileWriter fw = new FileWriter(decoderWriter);
		BufferedWriter bw = new BufferedWriter(fw);
		byte[] buffer=null;
		buffer=new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<buffer.length;i++){
			byte b1 = buffer[i];
			String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
			sb.append(s1);
		}
		decoderDatum temp=this.root;
		long l=sb.length();
		for(int i = 0; i <= l; i++){
			if(temp.data != -1){
				StringBuilder st = new StringBuilder();
				st.append(temp.data);
				st.append("\n");
				bw.write(st.toString());
				temp=this.root;
				i--;
			}
			else if (i!=l){
				if(sb.charAt(i)=='0') temp=temp.left;
				else temp=temp.right;
			}
		}
	bw.close();
	}
}



public class decoder {

	  public static void main(String args[]){
			long startTime = System.currentTimeMillis();

		   String BinaryFile=args[0];
		   FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(args[1]);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   DataInputStream in = new DataInputStream(fstream);
           BufferedReader br = new BufferedReader(new InputStreamReader(in));
           ArrayList<decoderDatum> decoderDatums=new ArrayList<>();
		   String str;
	          try {
				while ((str= br.readLine()) != null)   {
				         String[] tokens = str.split(" ");
						 decoderDatum root=new decoderDatum();
						 root.code=tokens[1];
						 root.data=Integer.parseInt(tokens[0]);
						 decoderDatums.add(root);

   }
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				in.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	  		huffManTree hft=new huffManTree();
			hft.decodeTree(decoderDatums);
			try {
				hft.decoder(BinaryFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long stopTime = System.currentTimeMillis();
			System.out.println("time for decode:"+(stopTime-startTime)+" MilliSec");


	}

}



