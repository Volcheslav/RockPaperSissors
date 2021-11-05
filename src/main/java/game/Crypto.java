package game;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


public class Crypto {
	
	public static String getKey () throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecureRandom random = new SecureRandom();
        keyGen.init(random);
        SecretKey secretKey = keyGen.generateKey();
        String s = new BigInteger(1, secretKey.getEncoded()).toString(16);
        return s;
	}
	
	public static int getCompStep(int n) {
		
		SecureRandom secureRandom = new SecureRandom();
		int a = secureRandom.nextInt(n)+1;
		return a;
	}
	
	public static String hMAC(String key, String choise) throws Exception {

	    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	    sha256_HMAC.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
	    byte[] result = sha256_HMAC.doFinal(choise.getBytes());
	    return DatatypeConverter.printHexBinary(result); 
	    }
		
		
	}
	
	


