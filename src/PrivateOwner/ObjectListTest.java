package PrivateOwner;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectListTest {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		Member list= new Member();
		//System.out.println(list.get());
		try {
		  // Member[] owners=mapper.readValue(list.get(), Member[].class);
		   List<Member> listMember = mapper.readValue(list.get(), new TypeReference<List<Member>>(){});
		 /*  for(int i=0;i<owners.length;i++)
				System.out.println(owners[i].toString());
				*/
		   System.out.println(listMember);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
