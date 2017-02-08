package com.rcgl.utils;

import java.io.File;
import java.io.FileOutputStream;

public class UploadImageUtil {
	
	public static void uploadImage(String file, String filepath, String filename){
		if(file!=null){
			byte[] b= Base64Coder.decodeLines(file);
			File file1=new File(filepath);
			if(!file1.exists())
				file1.mkdirs();
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file1.getPath()+"/"+filename);
				fos.write(b);
				fos.flush();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
