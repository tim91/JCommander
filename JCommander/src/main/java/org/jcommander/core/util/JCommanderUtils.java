package org.jcommander.core.util;


public class JCommanderUtils {

	
	public static String ExtractDeviceLabel(String deviceName){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(deviceName.charAt(deviceName.indexOf("(")+1));
		
		return sb.toString();
	}
	
	public static String ExtractDeviceName(String deviceName){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(deviceName.subSequence(0, deviceName.indexOf("(")-1));
		
		return sb.toString();
	}
	
}
