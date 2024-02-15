package com.qr.controller;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.qr.model.user;

@Controller
@RequestMapping("/qr")
public class qrController {
	
	@ModelAttribute("qr")
	public user user()
	{
		return new user();
	}
	
	@GetMapping
	public String homePage()
	{
		return "index";
	}
	
	@PostMapping
	public String generateQrCode(@ModelAttribute("qr") user user, Model model)
	{
		try {
			
			BufferedImage bufferedImage = generateQrCodeImage(user);
			
			File output = new File("D:\\Numetry Technologies\\QR_Code_Generator\\src\\main\\resources\\static "+user.getFirstName()+".jpg");
			
			ImageIO.write(bufferedImage, "jpg", output);
			
			model.addAttribute("qr", user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/qr?success";
	}

	public BufferedImage generateQrCodeImage(user user) throws WriterException {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder = stringBuilder.append("First Name:").append(user.getFirstName()).append("| |")
							.append("Last Name:").append(user.getLastName()).append("| |")
							.append("City:").append(user.getCity()).append("| |")
							.append("State:").append(user.getState()).append("| |")
							.append("Zip Code:").append(user.getZipCode());
		
		QRCodeWriter codeWriter = new QRCodeWriter();
			
		
		BitMatrix bitMatrix = codeWriter.encode(stringBuilder.toString(), BarcodeFormat.QR_CODE, 400, 400);	
		
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
}
