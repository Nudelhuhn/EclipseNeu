package oop.provider.impl;

import oop.provider.Service;

public class ServiceImplementationA implements Service
{
	@Override
	public void use(String name) {
		System.out.println("Hallo " + name);
	}
}
