package com.github.aureliano.srvraml.helper;

import java.io.File;

import org.raml.model.Raml;
import org.raml.parser.visitor.RamlDocumentBuilder;

public final class RamlHelper {

	private RamlHelper() {
		super();
	}
	
	public static Raml parseModel(String path) {
		return RamlHelper.parseModel(new File(path));
	}
	
	public static Raml parseModel(File file) {
		String location = file.getPath().replaceFirst(new File("").getAbsolutePath(), "").replaceFirst("^/", "");
		return new RamlDocumentBuilder().build(location);
	}
}