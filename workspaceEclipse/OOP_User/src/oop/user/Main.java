package oop.user;

import oop.provider.Service;
import oop.util.Helper;

public class Main
{
	public static void main(String[] args) {		
		Service service = Service.makeServiceObject();
		System.out.println(service.getClass().getName());
		service.use(Helper.toUpperCase("liebe Studierende"));
	}
}
