package org.ssdut.japanese.oes.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * 
 *完成信息加密 
 * 
 */

public class MD5 {
	static MessageDigest alg;

	public static String md5(String info) throws NoSuchAlgorithmException {
		alg = MessageDigest.getInstance("MD5");
		alg.update(info.getBytes());
		byte[] digest = alg.digest();
		String hs = "";
		String stmp = "";
		for (int i = 0; i < digest.length; i++) {
			stmp = (java.lang.Integer.toHexString(digest[i] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs;
	}

	public static boolean isEqual(byte[] digA, byte[] digB) {
		return MessageDigest.isEqual(digA, digB);
	}

	public static byte[] toBytes(String hex) {
		byte[] bytes = new byte[16];
		for (int i = 0, j = 0; i < hex.length(); i = i + 2, j++) {
			bytes[j] = Integer.valueOf(hex.substring(i, i + 2), 16).byteValue();
		}
		return bytes;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException{
		System.out.println(MD5.md5("123456"));
	}
}
