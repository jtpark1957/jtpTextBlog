package com.jtp.jtpTextBlog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	public static String encryptSHA256(String str) {

		String sha = "";

		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			sha = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
			System.out.println("Encrypt Error - NoSuchAlgorithmException");
			sha = null;
		}

		return sha;
	}

	public static int getAsInt(Object value, int defaultValue) {
		if ( value instanceof Integer ) {
			return (int)value;
		}
		else if ( value instanceof Long ) {
			return Long.valueOf((long)value).intValue();
		}
		else if ( value instanceof Float ) {
			return Float.valueOf((float)value).intValue();
		}
		else if ( value instanceof Double ) {
			return Double.valueOf((double)value).intValue();
		}
		else if ( value instanceof String ) {
			try {
				return Integer.parseInt((String)value);
			}
			catch ( NumberFormatException e ) {
			}
		}

		return defaultValue;
	}
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (obj instanceof String) {
			if (((String) obj).trim().length() == 0) {
				return true;
			}
		}

		return false;
	}
}
