package com.github.aureliano.srvraml.gen;

import org.junit.Assert;
import org.junit.Test;

public class GeneratorFactoryTest {

	@Test
	public void testCreateGenerator() {
		Assert.assertNull(GeneratorFactory.createGenerator(null));
		Assert.assertTrue(GeneratorFactory.createGenerator(GeneratorType.MODEL) instanceof ModelGenerator);
	}
}