package cn.glycol.tutils.test;

import cn.glycol.tutils.MultiLineStringBuilder;
import org.junit.Test;

public class TestMultiLineStringBuilder {

	@Test
	public void testMLSB() {

		final MultiLineStringBuilder sb = new MultiLineStringBuilder();

		sb.append("This is line 1").append(Boolean.TRUE, 3).atLine(2).append("AtLine 2").nextLine().substr(2);

		System.out.println(sb);

	}

}
