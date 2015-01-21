package com.github.aureliano.cgraml.helper;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.cgraml.helper.GeneratorHelper;

public class GeneratorHelperTest {

	@Test
	public void testWebApplicationConfigurationTemplate() {
		Assert.assertNotNull(GeneratorHelper.webApplicationConfigurationTemplate());
	}
}