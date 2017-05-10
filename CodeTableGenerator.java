import java.util.ArrayList;
import java.util.HashMap;

public class CodeTableGenerator {
public void CodeTable(HashMap<Integer, StringBuilder> hMap,Datum Root, ArrayList<String>al) {
	Datum root=Root;
	if(root==null)
		return;
	else if(root.left==null && root.right==null){
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<al.size();i++)
		sBuilder.append(al.get(i));
		 hMap.put(root.key,sBuilder);
	}
	else{
		al.add("0");
        CodeTable(hMap,root.left,al);
        al.set(al.size()-1, "1");
        CodeTable(hMap,root.right,al);
        al.remove(al.size()-1);
	}
}

}
