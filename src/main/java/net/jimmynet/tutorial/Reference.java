package net.jimmynet.tutorial;

import java.util.Random;

public class Reference {
	// CONSTANTS
	public static final String MOD_ID = "tm";
	public static final String MOD_NAME = "Mary and James' Tutorial Mod";
	public static final String VERSION = "@VERSION@";
	public static final String DEPENDENCIES = "required-after:forge@[13.19.1.2188,)";
	
	public static final String RESOURCE_PREFIX = MOD_ID + ":";
	
	//VARIABLES
	public static Random random = new Random();
	
	public static final String CLIENT_PROXY_CLASS = "net.jimmynet.tutorial.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "net.jimmynet.tutorial.proxy.CommonProxy";
}
