package cipher;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.nio.file.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



public class Coder{

	

	static public void encode(String file_name, String password) throws  NoSuchPaddingException, BadPaddingException , IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, FileNotFoundException{
		byte[] array = Files.readAllBytes(Paths.get(file_name));
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");

		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		Base64.Encoder enc = Base64.getEncoder();
		byte [] bytes = enc.encode(cipher.doFinal(array));

		FileOutputStream f=new FileOutputStream("tmp_"+file_name);
		f.write(bytes, 0, bytes.length);

	}

	static public void decode(String file_name, String password) throws  NoSuchPaddingException, BadPaddingException , IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, FileNotFoundException{
		Base64.Decoder dec = Base64.getDecoder();
		byte[] array =  dec.decode(Files.readAllBytes(Paths.get(file_name)));
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");

		cipher.init(Cipher.DECRYPT_MODE, key);

		byte [] bytes = cipher.doFinal(array);
		FileOutputStream f=new FileOutputStream(file_name);
		f.write(bytes, 0, bytes.length);

	}




}
