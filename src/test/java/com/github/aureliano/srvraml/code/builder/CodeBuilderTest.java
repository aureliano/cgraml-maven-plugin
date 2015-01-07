package com.github.aureliano.srvraml.code.builder;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.srvraml.code.gen.GeneratorType;

public class CodeBuilderTest {

	@Test
	public void testCreate() {
		Assert.assertNull(CodeBuilder.create(null));
		Assert.assertTrue(CodeBuilder.create(GeneratorType.MODEL) instanceof ModelBuilder);
	}
}