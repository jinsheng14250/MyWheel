package org.guangyu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 文件处理工具类
 */
public class FileManager {

	/**
	 * 将文件转成base64 字符串
	 * 
	 * @param path文件路径
	 * @return *
	 * @throws Exception
	 */
	public static String encodeBase64File(String path) throws Exception {
		File file = new File(path);
		if(file.exists()){
			FileInputStream inputFile = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
			return new BASE64Encoder().encode(buffer);
		}else{
			return "NoFile";
		}
	}

	/**
	 *  * 将base64字符解码保存文件  * @param base64Code  * @param targetPath  * @throws
	 * Exception
	 */

	public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
		byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
		FileOutputStream out = new FileOutputStream(targetPath);
		out.write(buffer);
		out.close();
	}

	/**
	 * 删除文件
	 * 
	 * @param pathname
	 * @return
	 * @throws IOException
	 */
	public static void deleteFileByPath(String pathname) {
		File file = new File(pathname);
		if (file.exists()) {
			file.delete();
		}

	}

	/**
	 * 文件转存
	 * @param oldPathname
	 * @param newPathname
	 * @return
	 * @throws Exception 
	 * @throws IOException
	 */
	public static void fileTransfer(String oldPathname,String newPathname) throws Exception {
		String base64 = encodeBase64File(oldPathname);
		if(!"NoFile".equals(base64)){
			deleteFileByPath(oldPathname);
			decoderBase64File(base64, newPathname);
		}
	}
}
