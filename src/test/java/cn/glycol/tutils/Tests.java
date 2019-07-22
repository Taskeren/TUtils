package cn.glycol.tutils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;

import org.junit.Test;

import cn.glycol.tutils.annotation.TAnnotation;
import cn.glycol.tutils.annotation.TAnnotation.ScanResult;
import cn.glycol.tutils.tools.timer.Timer;

@Debugger("welcome to Vec3d")
@Sandbox("Tests")
public class Tests {
	
	@Sandbox("SB") public static final SandboxTest SB = new SandboxTest();

	@Test
	public void testSandbox() throws Exception {

		SB.setExecution(() -> {
			TAnnotation.scanMethod("cn.glycol.tutils", null, Sandbox.class).forEach(methodExecutor);
		}).execute();
		SB.setExecution(() -> {
			TAnnotation.scanClass("cn.glycol.tutils", Sandbox.class).forEach(classExecutor);
		}).execute();
		SB.setExecution(() -> {
			TAnnotation.scanField("cn.glycol.tutils", null, Sandbox.class).forEach(fieldExecutor);
		}).execute();

	}

	@Sandbox("method executor")
	public static final Consumer<ScanResult<Method, Sandbox>> methodExecutor = (p2) -> {
		try {
			System.out.println("Found @Sandbox method: "+p2.getAnnotation().value());
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Sandbox("class executor")
	public static final Consumer<ScanResult<Class<?>, Sandbox>> classExecutor = (p2) -> {
		try {
			System.out.println("Found @Sandbox class: "+p2.getAnnotation().value());
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	@Sandbox("field executor")
	public static final Consumer<ScanResult<Field, Sandbox>> fieldExecutor = (p2) -> {
		try {
			System.out.println("Found @Sandbox field: "+p2.getAnnotation().value());
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	@Sandbox("Telescope")
	public static void telescope() {
		System.out.println("Telescope");
	}

	@Sandbox("Undefined")
	public static void underfined() {
		System.out.println("Undefined");
	}

	@Sandbox("Sandbox")
	private static void sandbox() {
	}

	@Sandbox("SandboxTest")
	public static class SandboxTest {

		Timer timer = new Timer();

		Runnable execution;

		public SandboxTest setExecution(Runnable execution) {
			this.execution = execution;
			return this;
		}

		public SandboxTest execute() {
			timer.start();
			execution.run();
			timer.stop();
			System.out.println("执行完成，用时：" + timer.duration() + "ms。");
			return this;
		}

	}

}
