package cn.mibcxb.utility.graphic;

import java.io.File;
import java.util.Hashtable;

import cn.mibcxb.utility.FileUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class ImageUtils {
	public static final int DEFAULT_WIDTH = 480;
	public static final int DEFAULT_HEIGHT = 480;
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String FORMAT_PNG = "PNG";
	public static final String FORMAT_JPG = "JPG";
	public static final String FORMAT_GIF = "GIF";

	public static void generateQRCode(String contents, String outPath)
			throws Exception {
		generateQRCode(contents, outPath, FORMAT_PNG);
	}

	public static void generateQRCode(String contents, String outPath,
			String format) throws Exception {
		generateQRCode(contents, outPath, format, DEFAULT_WIDTH,
				DEFAULT_HEIGHT, DEFAULT_CHARSET, false);
	}

	public static void generateQRCode(String contents, String outPath,
			String format, int width, int height, String charset,
			boolean isCover) throws Exception {
		File file = new File(outPath);
		boolean checkFile = false;
		if (FileUtils.isNotExists(outPath)) {
			file = FileUtils.createFile(outPath);
			if (null != file && file.exists()) {
				checkFile = true;
			}
		} else {
			checkFile = isCover;
			if (null == file || !file.exists()) {
				checkFile = false;
			}
		}

		if (checkFile) {
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, charset);
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix bitMatrix = writer.encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);

			MatrixToImageWriter.writeToFile(bitMatrix, format, file);
		} else {
			System.out.println("The file is already exists!");
		}
	}
}
