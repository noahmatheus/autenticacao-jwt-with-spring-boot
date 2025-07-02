package com.startjwtproject.util;

import org.json.JSONObject;

public class Util {

	// valida erros no json
	public static String jsonError() {
		JSONObject obj = new JSONObject();
		obj.put("error", true);
		obj.put("data", "");

		return obj.toString();

	}

}
