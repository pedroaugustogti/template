package br.com.template.util.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public final class CriptografiaUtil {
	
	private static String keySecretSpec = null;
	private static String keyIvParameterSpec = null;
	
	private static String ALGORITMO_CRIPTOGRAFICA = "AES/CBC/PKCS5PADDING";
	private static String TIPO_CRIPTOGRAFIA_AES = "AES";
	
	private static String ENCODING = "UTF-8";
	
	private static int HASH_16_BYTES = 16;
	
	static {
		keySecretSpec = randomKey();
		keyIvParameterSpec = randomKey();
	}
	
	private CriptografiaUtil(){
		//Evita instanciação
	}
	
	private static String randomKey() {
		UUID uuid = UUID.randomUUID();  
		String myRandom = uuid.toString();  
		return myRandom.substring(0,HASH_16_BYTES);
	}
	
	
    public static String criptografar(String valor) {
    	
    	String valorCriptografado = null;
    	
        try {
           
            byte[] criptByte = cipher(Cipher.ENCRYPT_MODE).doFinal(valor.getBytes());
            
            valorCriptografado = Base64.encodeBase64String(criptByte);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return valorCriptografado;
    }

	public static String descriptografar(String criptografia) {
		
		String valorOriginal = null;
		
        try {
        	
            byte[] originalByte = cipher(Cipher.DECRYPT_MODE).doFinal(Base64.decodeBase64(criptografia));

            valorOriginal = new String(originalByte);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return valorOriginal;
    }
	
	private static Cipher cipher(final int encryptMode) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException {
    	
   	 IvParameterSpec iv = new IvParameterSpec(keyIvParameterSpec.getBytes(ENCODING));
        SecretKeySpec skeySpec = new SecretKeySpec(keySecretSpec.getBytes(ENCODING),TIPO_CRIPTOGRAFIA_AES);
        Cipher cipher = Cipher.getInstance(ALGORITMO_CRIPTOGRAFICA);
        cipher.init(encryptMode, skeySpec, iv);
        
		return cipher;
	}
}