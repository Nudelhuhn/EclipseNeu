package oop.provider;

import oop.provider.impl.*;

public interface Service
{
	public void use(String name);
	
	public static Service makeServiceObject() {
		if(Math.random() < 0.5) {
			return new ServiceImplementationA();
		}else {
			return new ServiceImplementationB();
		}
	}

	public static void main(String[] args) {
		System.out.println("Hallo");
	}
}
