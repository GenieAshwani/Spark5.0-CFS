package com.apnishadi.ai;

public interface AiTextClient {
	boolean isConfigured();
	String model();
	String provider();
	String createTextResponse(String instructions, String input);
}
