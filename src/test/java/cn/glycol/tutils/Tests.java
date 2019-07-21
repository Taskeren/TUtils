package cn.glycol.tutils;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import cn.glycol.tutils.annotation.TAnnotation;

@Debugger("welcome to Vec3d")
@Sandbox()
public class Tests {
	
	@Test
	public void testSandbox() throws Exception {
		
		TAnnotation.scan("cn.glycol.tutils", Sandbox.class, Sandbox.class).forEach(m -> {
			try {
				m.invoke(null, new Object[0]);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
		
	}
	
	@Sandbox()
	public static void sanbox() {
		System.out.println("Telescope");
	}
	
	@Sandbox()
	public static void underfined() {
		System.out.println("Undefined");
	}
	
	@Sandbox()
	public static void objects() {
		System.out.println("Sandbox!");
	}
	
}
