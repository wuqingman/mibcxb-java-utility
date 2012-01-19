package cn.mibcxb.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("unused")
public class FileUtils extends org.apache.commons.io.FileUtils {
	private static String osName;
	private static String osArch;
	private static String fileSeparator;
	private static String pathSeparator;
	private static String lineSeparator;
	private static String userName;
	private static String userHome;
	private static String userDir;
	static {
		osName = SystemInfos.getOsName();
		osArch = SystemInfos.getOsArch();
		fileSeparator = SystemInfos.getFileSeparator();
		pathSeparator = SystemInfos.getPathSeparator();
		lineSeparator = SystemInfos.getLineSeparator();
		userName = SystemInfos.getUserName();
		userHome = SystemInfos.getUserHome();
		userDir = SystemInfos.getUserDir();
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static File createFile(String path) throws IOException {
		File file = null;
		if (StringUtils.isNotBlank(path)) {
			path = checkPath(path);
			file = new File(path);
			if (file.exists()) {
				System.out.println("The file is already exists!");
			} else {
				File directory = new File(StringUtils.substringBeforeLast(path,
						fileSeparator));
				if (directory.exists()) {
					file.createNewFile();
				} else {
					directory.mkdirs();
					file.createNewFile();
				}
			}
		} else {
			System.out.println("Please input the path!");
		}
		return file;
	}

	/**
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void createFile(File file) throws IOException {
		if (null != file) {
			if (file.exists()) {
				System.out.println("The file is already exists!");
			} else {
				File directory = new File(StringUtils.substringBeforeLast(
						file.getAbsolutePath(), fileSeparator));
				if (directory.exists()) {
					file.createNewFile();
				} else {
					directory.mkdirs();
					file.createNewFile();
				}
			}
		} else {
			System.out.println("The file can not be null!");
		}
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static File createDirectory(String path) {
		File directory = null;
		if (StringUtils.isNotBlank(path)) {
			path = checkPath(path);
			directory = new File(path);
			if (directory.exists()) {
				System.out.println("The file is already exists!");
			} else {
				directory.mkdirs();
			}
		} else {
			System.out.println("Please input the path!");
		}
		return directory;
	}

	/**
	 * 
	 * @param directory
	 */
	public static void createDirectory(File directory) {
		if (null != directory) {
			if (directory.exists()) {
				System.out.println("The file is already exists!");
			} else {
				directory.mkdirs();
			}
		} else {
			System.out.println("The directory can not be null!");
		}
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String checkPath(String path) {
		if (StringUtils.containsNone(path, fileSeparator)) {
			if (StringUtils.equals(fileSeparator, "/")) {
				path = StringUtils.replace(path, "\\", fileSeparator);
			}
			if (StringUtils.equals(fileSeparator, "\\")) {
				path = StringUtils.replace(path, "/", fileSeparator);
			}
		}
		return path;
	}

	/**
	 * 
	 * @param filePath
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String readProperties(String filePath, String key)
			throws IOException {
		String value = "";
		if (StringUtils.endsWithIgnoreCase(filePath, ".properties")) {
			filePath = checkPath(filePath);
			File file = new File(filePath);
			if (file.exists() && file.isFile()) {
				Properties props = new Properties();
				FileInputStream fis = new FileInputStream(file);
				props.load(fis);
				value = (String) props.get(key);
				fis.close();
			}
		}
		return value;
	}

	/**
	 * 
	 * @param filePath
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public static void writeProperties(String filePath, String key, String value)
			throws IOException {
		if (StringUtils.endsWithIgnoreCase(filePath, ".properties")) {
			filePath = checkPath(filePath);
			File file = new File(filePath);
			if (!file.exists()) {
				createFile(filePath);
			}
			if (file.isFile()) {
				Properties props = new Properties();
				FileInputStream fis = new FileInputStream(file);
				props.load(fis);
				fis.close();
				props.setProperty(key, value);
				FileOutputStream fos = new FileOutputStream(file);
				props.store(fos, null);
				fos.close();
			}
		}
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExists(String path) {
		if (StringUtils.isNotBlank(path)) {
			return ((new File(path)).exists());
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isNotExists(String path) {
		if (StringUtils.isNotBlank(path)) {
			return !((new File(path)).exists());
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param srcPath
	 * @param destPath
	 * @param preserveFileDate
	 */
	public static void copyFile(String srcPath, String destPath,
			boolean preserveFileDate, boolean coverExistFile)
			throws IOException {
		if (StringUtils.isNotBlank(srcPath) && StringUtils.isNotBlank(destPath)) {
			srcPath = checkPath(srcPath);
			destPath = checkPath(destPath);
			if (coverExistFile) {
				copyFile(new File(srcPath), new File(destPath),
						preserveFileDate);
			} else {
				if (isNotExists(destPath)) {
					copyFile(new File(srcPath), new File(destPath),
							preserveFileDate);
				} else {
					System.out.println("The file is already exists!");
				}
			}
		} else {
			System.out.println("Please check your input.");
		}
	}

	public static void copyFile(String srcPath, String destPath)
			throws IOException {
		copyFile(srcPath, destPath, false, false);
	}

	public static void rename(String srcPath, String newName,
			boolean preserveFileDate, boolean coverExistFile, boolean delSrc)
			throws IOException {
		if (StringUtils.isNotBlank(srcPath) && StringUtils.isNotBlank(newName)) {
			srcPath = checkPath(srcPath);
			String parentPath = StringUtils.substringBeforeLast(srcPath,
					fileSeparator);
			String newPath = checkPath(parentPath + fileSeparator + newName);
			copyFile(srcPath, newPath, preserveFileDate, coverExistFile);
			if (delSrc) {
				new File(srcPath).delete();
			}
		} else {
			System.out.println("Please check your input.");
		}
	}
}
