package com.github.aureliano.cgraml.code.builder;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.cgraml.code.builder.CodeBuilder;
import com.github.aureliano.cgraml.code.builder.ModelBuilder;
import com.github.aureliano.cgraml.code.gen.GeneratorType;

public class CodeBuilderTest {

	@Test
	public void testCreate() {
		Assert.assertNull(CodeBuilder.create(null));
		Assert.assertTrue(CodeBuilder.create(GeneratorType.MODEL) instanceof ModelBuilder);
	}
}