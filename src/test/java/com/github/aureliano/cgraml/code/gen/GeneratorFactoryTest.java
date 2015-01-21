package com.github.aureliano.cgraml.code.gen;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.cgraml.code.gen.GeneratorFactory;
import com.github.aureliano.cgraml.code.gen.GeneratorType;
import com.github.aureliano.cgraml.code.gen.ModelGenerator;

public class GeneratorFactoryTest {

	@Test
	public void testCreateGenerator() {
		Assert.assertNull(GeneratorFactory.createGenerator(null));
		Assert.assertTrue(GeneratorFactory.createGenerator(GeneratorType.MODEL) instanceof ModelGenerator);
	}
}