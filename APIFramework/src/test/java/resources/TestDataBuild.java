package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language, String address)
	{
		System.out.println("Hello1");
		System.out.println("Hello2");
		System.out.println("Hello3");
		System.out.println("Hello4");
		
		AddPlace p =new AddPlace();
		
		p.setAccuracy(50);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		
		p.setName(name);
		p.setLanguage(language);
		p.setAddress(address);
			
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayLoad(String placeId) {
		return "{\r\n\"place_id\":\"" + placeId + "\"\r\n}";
		
	}
	
	public void updatePlace() {
		System.out.println("update place");		
	}
}
