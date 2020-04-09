package kr.or.ddit.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA512_SaltedHash {

	public String getSHA512(String input, String salt) {
		String saltedInput = input + salt;
		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(saltedInput.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 지워야함
		return toReturn.substring(0, toReturn.length() - 4);
	}

}
