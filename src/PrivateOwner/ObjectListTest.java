package PrivateOwner;

import java.io.IOException;
 
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectListTest {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		Member list= new Member();
		try {
		   Member[] owners=mapper.readValue(list.get(), Member[].class);
		   for(int i=0;i<owners.length;i++)
				System.out.println(owners[i].toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
