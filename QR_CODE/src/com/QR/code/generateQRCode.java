package com.QR.code;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.Format;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class generateQRCode {

	public static void main(String[] args) throws WriterException,IOException  
	{
		String dataString = "www.QR.com";
		String pathString = "D:\\Numetry Technologies\\QR_CODE\\QR\\QR.jpg";
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(dataString, BarcodeFormat.QR_CODE, 500, 500);
		
		MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(pathString));
		
		System.out.println("QR Code Successfully Created.");
		
	}

}
