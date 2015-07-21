package com.tankers.smile.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
	public static String encryptSHA256(String st) {
		if (st == null)
			return "";

		String encryptedData = "";
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			sha.update(st.getBytes());
			byte[] digest = sha.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digest.length; i++)
				sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
			encryptedData = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			encryptedData = null;
		}

		return encryptedData;
	}

	public static String shortify(String str) {
		if (str == null)
			return "";

		String res = "";

		if (str.length() > 12)
			res = str.substring(0, 12) + "...";
		else
			res = str;

		return res;
	}

	public static String shortifyCleverly(String str) {
		if (str == null)
			return "";

		String res = "";

		if (str.length() > 50) {
			res = str.substring(0, 50) + "....";
		} else {
			int i;
			int lc = -1;

			for (i = 0; i < 4; i++) {
				int j = str.indexOf('\n', lc + 1);
				if (j == -1)
					break;
				else
					lc = j;
			}

			if (i > 3)
				res = str.substring(0, lc) + "....";
			else
				res = str;
		}

		return res;
	}
}
