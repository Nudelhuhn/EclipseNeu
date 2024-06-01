package oop.provider.impl;

import oop.provider.Service;
import oop.util.Helper;

public class ServiceImplementationB implements Service
{
	@Override
	public void use(String name) {
		System.out.println(Helper.toUpperCase("Einen wunderschönen Tag ") + name);
	}
}
