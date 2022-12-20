import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.nio.file.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class Test{

	public static  void main(String args[]) throws  NoSuchPaddingException, BadPaddingException , IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, FileNotFoundException {
		Coder.encode("test.txt", "1111111111111111");
		Coder.decode("../tmp_test.txt", "1111111111111111");	
	
	}

}
