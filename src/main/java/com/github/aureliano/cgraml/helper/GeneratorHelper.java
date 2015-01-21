package com.github.aureliano.cgraml.helper;


public final class GeneratorHelper {

	private GeneratorHelper() {
		super();
	}
	
	public static String webApplicationConfigurationTemplate() {
		return new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
			.append("<!-- This web.xml file is not required when using Servlet 3.0 container,\n")
			.append("     see implementation details http://jersey.java.net/nonav/documentation/latest/jax-rs.html -->\n")
			.append("<web-app version=\"2.5\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\">\n")
			.append("    <servlet>\n")
			.append("        <servlet-name>${title}</servlet-name>\n")
			.append("        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>\n")
			.append("        <init-param>\n")
			.append("            <param-name>jersey.config.server.provider.packages</param-name>\n")
			.append("            <param-value>${resourcesPackage}</param-value>\n")
			.append("        </init-param>\n")
			.append("        <load-on-startup>1</load-on-startup>\n")
			.append("    </servlet>\n")
			.append("    <servlet-mapping>\n")
			.append("        <servlet-name>${title}</servlet-name>\n")
			.append("        <url-pattern>${baseUri}</url-pattern>\n")
			.append("    </servlet-mapping>\n")
			.append("</web-app>")
			.toString();
	}
}