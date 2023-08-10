package controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {
	
	// uri 와 controller를 저장해야 하는소 Map을 사용 		// list는 같은 타입만
	private Map<String, Controller> mappings;

	public HandlerMapping(String propLoc) {
		mappings = new HashMap<>();
		
//		mappings.put("/login.do", LoginController);
//		mappings.put("/member/login.do", MyPageController);
		
		Properties prop = new Properties();
		
		try {
			InputStream is = new FileInputStream(propLoc);
			prop.load(is);
			
			Set<Object> keys = prop.keySet();
			for(Object key: keys) {
				System.out.println(key);
				String className = prop.getProperty(key.toString());
				System.out.println(key + " : " + className);
				
				Class<?> clz = Class.forName(className);
				Constructor<?> constructor = clz.getConstructor();
				
				mappings.put(key.toString(), (Controller)constructor.newInstance());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	public HandlerMapping() {
		mappings = new HashMap<>();
		
//		mappings.put("/login.do", LoginController);
//		mappings.put("/member/login.do", MyPageController);
		
		Properties prop = new Properties();
		
		try {
			InputStream is = new FileInputStream("C:\\Users\\User\\eclipse-workspaceJSP\\MyBanking\\bean.properties");
			prop.load(is);
			
			Set<Object> keys = prop.keySet();
			for(Object key: keys) {
				System.out.println(key);
				String className = prop.getProperty(key.toString());
				System.out.println(key + " : " + className);
				
				Class<?> clz = Class.forName(className);
				Constructor<?> constructor = clz.getConstructor();
				
				mappings.put(key.toString(), (Controller)constructor.newInstance());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	*/
	
	public Controller getController(String uri) {
		
		return mappings.get(uri);
	}
	
	/*
	public static void main(String[] args) throws Exception {
		
//		java.util.Random r = new java.util.Random();
//		java.util.Scanner r = new java.util.Scanner(System.in);
//		System.out.println(r.nextInt());
		
		Class<?> clz = Class.forName("java.util.Random");
		
//		Object obj = clz.newInstance(); //이젠 안씀
		Constructor<?> constructor = clz.getConstructor();
		Object obj = constructor.newInstance();
		System.out.println("난수 : " + ((java.util.Random)obj).nextInt());
		
	}
	*/

}
