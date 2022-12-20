package run;

import java.io.IOException;

import steg.Steg;
import cipher.Coder;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.nio.file.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class StegRunner {
	public static void main(String[] args) throws   NoSuchPaddingException, BadPaddingException , IOException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, FileNotFoundException{
		if ("hide".equals(args[0])){
			if(args.length == 3){
				System.out.println("file to hide " + args[1]+" img " + args[2]);
				System.out.println(Steg.hideFile(args[1], args[2]));
			}
			else if (args.length == 4){
				Coder.encode(args[1], args[3]);
				System.out.println(Steg.hideFile("tmp_"+args[1] , args[2]));
			}

		}
		else if ("extract".equals(args[0])){
			if (args.length == 2){
				System.out.println(Steg.extractFile(args[1]));
			}
			if (args.length == 3){
				System.out.println(Steg.extractFile(args[1]));
				Coder.decode("recovered.txt", args[2]);
			
			}

		}
		else{
			System.out.println("Wrong args, usage: java run.StegRunner  <hide or extract> <file to hide or extract> <img.bmp to cover> <optional password>");
		}
		//System.out.println(Steg.hideString("How you doooiinnng?", "minions_freeze.bmp"));
		//System.out.println(Steg.extractString("stego_image_string.bmp"));
	}
}
