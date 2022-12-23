package steg;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import steg.filereading.FileReader;;

public class Steg {
	
	
	private final static int byteLength = 8;
	protected final static int sizeBitsLength = 32;
	protected final static int extBitsLength = 64;

	public Steg() {
	}
	
	public static String hideFile(String file_payload, String cover_image) {
		String result = "Fail";
		
		FileReader fr = new FileReader(file_payload);
			
		BufferedImage img;
		try {
			img = ImageIO.read(new File(cover_image));
			int w = img.getWidth();
			int h = img.getHeight();
			
			if (fr.getFileSize() > w * h) {
				System.err.println("ERROR: File size is too large");
				return result;
			}
			
			byte[] rgb;
			int currentBit;
			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					if (fr.hasNextBit()) {
						currentBit = fr.getNextBit();
						rgb = ByteUtility.getRGBFromPixel(img.getRGB(i, j));
						rgb[j%3] = (byte) swapLsb(currentBit, rgb[j%3]);
						img.setRGB(i, j, ByteUtility.getPixel(rgb));
					}
					else {
						
						String stegoImageName = "stego_image_file.bmp";
						ImageIO.write(img, "bmp", new File(stegoImageName));
						result = stegoImageName;
						return result;
					}
				}
			}
			
		} catch (IOException e) {
			System.err.println("ERROR: Cover image does not exist");
		}	

		return result;
	}
	

	public static String extractFile(String stego_image) {
		String result = "Fail";
		try {
			BufferedImage img = ImageIO.read(new File(stego_image));
			int width = img.getWidth();
			int height = img.getHeight();
			String extension = null;
			List<String> bitBuffer = new ArrayList<String>();
			int pixel, loopCounter, payloadBitSize = 0;
			byte[] rgb;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					pixel = img.getRGB(i, j);
					loopCounter = i * height + j;
					
					if (loopCounter == sizeBitsLength) {
						StringBuilder sb = new StringBuilder();
						for (int k = 0; k < bitBuffer.size(); k++) {
							sb.append(bitBuffer.get(k));
						}
						payloadBitSize = Integer.parseInt(sb.toString(), 2);	
						bitBuffer.clear();
					}
					else if (loopCounter == sizeBitsLength + extBitsLength) {
						byte[] extensionRawByteArray = ByteUtility.getByteArrayFromBuffer(bitBuffer);
						int zeroCount = 0;
						for (int k = 0; k < extensionRawByteArray.length; k++) {
							if (extensionRawByteArray[k] == 0)
								zeroCount++;
						}
						byte[] extensionByteArray = Arrays.copyOfRange(extensionRawByteArray, zeroCount, extensionRawByteArray.length);
						extension = new String(extensionByteArray);
						
						
						bitBuffer.clear();
					}
					else if (loopCounter == sizeBitsLength + extBitsLength + payloadBitSize) {
						
						File file = new File("recovered" + extension);
						byte[] resultByteArray = ByteUtility.getByteArrayFromBuffer(bitBuffer);
						FileOutputStream out = new FileOutputStream(file);
						out.write(resultByteArray);
						out.close();
						
						return "recovered" + extension;
					}
					
					rgb = ByteUtility.getRGBFromPixel(pixel);
					bitBuffer.add(Integer.toString(rgb[j%3] & 1));
				}
			}
			
		} catch (IOException e) {
			System.err.println("Error: Couldn't find stego image.");
			result = "Fail";
		}
		
		return result;
	}
	
	
	public static int swapLsb(int bitToHide, int byt) {
		int lsb = byt & 1;
		if (lsb == bitToHide)
			return byt;
		else
			return byt ^ 1;
	}
}
