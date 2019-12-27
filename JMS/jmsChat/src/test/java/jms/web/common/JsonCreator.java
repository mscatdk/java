package jms.web.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;

public class JsonCreator {

	@Test
	public void createJson() {
		Gson gson = new Gson();
		
		List<String> list = new ArrayList<String>();
	
		MyObj myObj = new MyObj();
		
		myObj.setList(list);
		
		list.add("Test1");
		list.add("Test2");
		
		
		System.out.println(gson.toJson(myObj));
				
	}
	
}

class MyObj {
	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}