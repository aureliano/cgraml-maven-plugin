package com.github.aureliano.srvraml.helper;

import org.junit.Assert;
import org.junit.Test;

public class GeneratorHelperTest {

	@Test
	public void testWebApplicationConfigurationTemplate() {
		Assert.assertNotNull(GeneratorHelper.webApplicationConfigurationTemplate());
	}
}