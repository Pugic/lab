package steg.filereading;

import java.io.*;
import java.io.FileInputStream;
import java.util.*;

import steg.ByteUtility;

public class FileReader 
{
	
	private FileInputStream fileInStream;
	private String fileName;
	private int currentByte;
	private int currentPos = 0;
	private final int byteLength = 8;
	private File file; 
	private List<Integer> sbits;
	private List<Integer> extBits;
	private Iterator <Integer> sBitsIt;
	private Iterator <Integer> extBitsIt;
	private boolean success = true;
	
	public FileReader(String f) {
		
		file = new File(f);
		fileName = f;
		setUpStream();	
		sbits = new ArrayList<Integer>();
		extBits = new ArrayList<Integer>();
			
		populateSizeBits();
		populateExtensionBits();

		try {
			getNextByte();
			
		} 
		catch (IOException e) {
			System.err.println("The file " + fileName + " has no bytes," +
					" please try again");
					
		}
	}

	
	private void setUpStream() {
		
		try {
			fileInStream = new FileInputStream(file);
		} 
		catch (FileNotFoundException e) {
			System.err.println("The file " + fileName + " cannot be found, please" +
					"try again");		
			success = false;
		}
	}
	
	private String getExtension() {
		
		String ext = "";
		int pos = fileName.lastIndexOf('.');
		ext = fileName.substring(pos);
		return ext;
	}
	
	private boolean getNextByte() throws IOException {
			
		currentByte = fileInStream.read();
		
		if (currentByte != -1) {
			return true;
		}
		
		else {	
			return false;
		}
	}
		
	private int getCurrentByte() {
		return currentByte;
	}
	
	public boolean hasNextBit() {
		boolean hasNext = false;
		if (sBitsIt.hasNext()) {
			hasNext = true;
		}
		else if(extBitsIt.hasNext()){
			hasNext = true;
		}
		else if(currentPos > (byteLength - 1)) {
			try {
				if(getNextByte()) {
					
					currentPos = 0;	
					hasNext = true;
				}	
				else {
					
					hasNext = false;
				}
			} 	
			catch (IOException e)  {
				System.err.println("The file " + fileName + " has no more bits," +
						" please try again");
				
				success = false;
			}
		}
		else {
			hasNext = true;
		}
			 
		return hasNext;
	}
	
	public int getNextBit() {
		int bit = 0;
		if(sBitsIt.hasNext()) {
			bit = sBitsIt.next();
		}
		else if(extBitsIt.hasNext()) {
			bit = extBitsIt.next();
		}
		else {
			bit = getCurrentByte()>>7-currentPos;
			bit &= 0x1;
			currentPos++;
		}
		return bit;	
	}
	
	public int getFileSize() {
		return (int)file.length() * byteLength;
	}
	
	private void populateSizeBits() {
		int numOfBits = this.getFileSize();
		byte[] sizeInBytes = new byte[] {
				(byte) (numOfBits >>> 24), 
				(byte) (numOfBits >>> 16), 
				(byte) (numOfBits >>> 8), 
				(byte) (numOfBits)
		};
		
		int[] bits;
		for (int i = 0; i < sizeInBytes.length; i++) {
			bits = ByteUtility.getBits(sizeInBytes[i]);
			for (int j = 0; j < bits.length; j++) {
				sbits.add(bits[j]);
			}
		}
		sBitsIt = sbits.iterator();
	}
	
	private void populateExtensionBits() {
		byte[] extensionInBytes = this.getExtension().getBytes();
		
		int paddingByteCount = 8 - extensionInBytes.length;
		if (paddingByteCount > 0) {
			for (int i = 0; i < paddingByteCount; i++)
				for (int j = 0; j < 8; j++) {
					extBits.add(0);
				}
		}
		
		int[] bits;
		for (int i = paddingByteCount; i < 8; i++) {
			bits = ByteUtility.getBits(extensionInBytes[i - paddingByteCount]);
			for (int j = 0; j < bits.length; j++) {
				extBits.add(bits[j]);
			}
		}
		
		extBitsIt = extBits.iterator();
	}
	
	
	public boolean getSuccessBool() {
		return success;
	}
	
	
	public List<Integer> getSizeBits() {
		return sbits;	
	}
	
	public List<Integer> getExtBits() {
		return extBits;
	}

}
