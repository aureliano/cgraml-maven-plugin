package com.github.aureliano.cgraml.helper;

import java.util.List;
import java.util.Map;

import com.github.aureliano.cgraml.code.gen.Generator;


public final class GeneratorHelper {

	private GeneratorHelper() {
		super();
	}
	
	public static Object getDataFromCurrentRamlHelper(List<String> chainedKeys) {
		Object value = null;
		
		if ((chainedKeys == null) || (chainedKeys.isEmpty())) {
			return value;
		}
		
		for (String key : chainedKeys) {
			if (value == null) {
				value = Generator.currentRamlMap.get(key);
				if (value == null) {
					return null;
				}
			} else {
				value = ((Map<?, ?>) value).get(key);
				if (value == null) {
					return null;
				}
			}
		}
		
		return value;
	}
}