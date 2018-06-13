package data;

import java.util.ArrayList;

public class DList extends ArrayList<String>{
	String a="";
	@Override
	public String toString() {
		a="";
		forEach(e->a+=e.toString()+", ");
		a=a.substring(0,a.length()-2);
		return a;
	}
	public void addAll(String string) {
		removeAll();
		a="";
		while(true) {
			add(string.substring(0, string.indexOf(",")<0 ? string.length() : string.indexOf(",")));
			string=string.substring(string.indexOf(",")<0 ? string.length() : string.indexOf(",")+1,string.length());
			if(string.length()<=2) break;
		}
		
	}
	public void removeAll() {
		int k=0;
		a="";
		for(int i=0;i<this.size();i++) {
			remove(k);
		}
		
	}
	
}
