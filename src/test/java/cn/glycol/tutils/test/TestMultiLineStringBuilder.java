package cn.glycol.tutils.test;

import cn.glycol.tutils.MultiLineStringBuilder;
import org.junit.Test;

public class TestMultiLineStringBuilder {

	@Test
	public void testMLSB() {

		final MultiLineStringBuilder sb = new MultiLineStringBuilder();

		sb.append("This is line 0").append(Boolean.TRUE, 2).atLine(1).append("AtLine 1").nextLine().previousLine().substr(3);

		System.out.println(sb);

	}

}
