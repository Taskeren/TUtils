package cn.glycol.tutils;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import cn.glycol.tutils.annotation.TAnnotation;
import cn.glycol.tutils.tools.timer.Timer;

@Debugger("welcome to Vec3d")
@Sandbox()
public class Tests {
	
	public static final Timer TIMER = new Timer();
	
	@Test
	public void testSandbox() throws Exception {
		
		TIMER.start();
		for(int i=0; i<100; i++) perform();
		TIMER.stop();
		System.out.println("执行这个方法使用了："+TIMER.duration()+"毫秒。");
		
		TIMER.start();
		for(int i=0; i<100; i++) {
			sandbox();
			underfined();
			telescope();
		}
		TIMER.stop();
		System.out.println("执行这个方法使用了："+TIMER.duration()+"毫秒。");
		
	}
	
	void perform() {
		TAnnotation.scan("cn.glycol.tutils", Sandbox.class, Sandbox.class).forEach(m -> {
			try {
				m.invoke(null, new Object[0]);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Sandbox()
	public static void telescope() {
		System.out.println("Telescope");
	}
	
	@Sandbox()
	public static void underfined() {
		System.out.println("Undefined");
	}
	
	@Sandbox()
	public static void sandbox() {
		System.out.println("Sandbox!");
	}
	
}
