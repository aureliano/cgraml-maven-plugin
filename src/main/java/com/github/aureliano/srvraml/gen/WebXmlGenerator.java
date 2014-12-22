package com.github.aureliano.srvraml.gen;

import com.github.aureliano.srvraml.helper.GeneratorHelper;

public class WebXmlGenerator extends AbstractGenerator {

	public WebXmlGenerator() {
		super();
	}
	
	@Override
	public void execute() {
		super.logger.debug("Generating web.xml file.");
		String template = GeneratorHelper.webApplicationConfigurationTemplate();
		
		template = template.replaceAll("\\$\\{title\\}", raml.getTitle());
		template = template.replaceAll("\\$\\{resourcesPackage\\}", super.resourcesPackage);
		template = template.replaceAll("\\$\\{baseUri\\}", raml.getBaseUri());
		
		super.logger.debug(template);
		super.saveFile(template);
	}

	@Override
	public String outputPath() {
		return "web.xml";
	}
}