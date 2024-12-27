package aurionpro.erp.ipms.utility;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtility implements AttributeConverter<String, String> {
	
	private static final Logger logger = LoggerFactory.getLogger(EncryptionUtility.class);
	private final static String mobileKey = "2019SCS@FT9876MOB9098762";
	private final static String mobileIv = "drowssapdrowssap";
	private final static String secretKey = "Hsrtc@2022";


	public static String getAESEncryptedString(String key, String iv, String inputText) throws Exception {
		try {
			byte[] plaintext = inputText.getBytes();
			Cipher c3des = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);
			byte[] cipherText = c3des.doFinal(plaintext);
			/* byte salt[] = getSalt(); */
			Encoder encoder = Base64.getEncoder();
			String result = new String(encoder.encode(cipherText));
			return result;
		} catch (Exception e) {
			logger.error("Error occured while decrypting the Password", e);
			return null;
		}

	}

	public static byte[] encrypt(String plainText) {
		return encrypt(plainText.getBytes(Charset.forName("UTF-8")));
	}

	public static byte[] encrypt(byte[] plaintext) {
		try {
			Cipher c3des = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(mobileKey.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(mobileIv.getBytes());

			c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);
			byte[] cipherText = c3des.doFinal(plaintext);

			return Base64.getEncoder().encode(cipherText);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDecryptedString(String encryptedText) {
		try {
			byte[] cipher;
			cipher = Base64.getDecoder().decode(encryptedText.trim().getBytes(Charset.forName("UTF-8")));
			Cipher c3des = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c3des.init(Cipher.DECRYPT_MODE, new SecretKeySpec(mobileKey.getBytes(), "AES"),
					new IvParameterSpec(mobileIv.getBytes()));
			byte[] plaintext = c3des.doFinal(cipher);
			return new String(plaintext);
		} catch (Exception e) {
			logger.error("Error occured while decrypting the Password", e);
			return null;
		}

	}


	@Override
	public String convertToDatabaseColumn(String attribute) {
		if (attribute != null) {
			return new String(encrypt(attribute));
		} else {
			return null;
		}

	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		if (dbData != null) {
			return getDecryptedString(dbData);
		} else {
			return null;
		}
	}



	public static String getEncryptedData(String data) {
		byte bytes[] = encrypt(data);
		String str = new String(bytes, StandardCharsets.UTF_8);
		return str;
	}
	
	public static String getDecryptedData(String data) {
		byte bytes[] = encrypt(data);
		String str = new String(bytes, StandardCharsets.UTF_8);
		String decryptStr = getDecryptedString(str);
		return decryptStr;
	}
	

}
