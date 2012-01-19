package cn.mibcxb.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class CompressUtils {
	private static String fileSeparator = SystemInfos.getFileSeparator();
	public static final String ZIP = "zip";
	public static final String ENCODE_GBK = "GBK";// 字符编码
	public static final String ENCODE_UTF8 = "UTF8";

	/**
	 * 
	 * @param srcPath
	 * @param outputPath
	 * @param archiveType
	 * @throws IOException
	 */
	public static void extract(String srcPath, String outputPath,
			String archiveType) throws IOException {
		if (StringUtils.isNotBlank(outputPath)
				&& StringUtils.isNotBlank(srcPath)) {
			srcPath = FileUtils.checkPath(srcPath);
			outputPath = FileUtils.checkPath(outputPath);
			String encoding = SystemInfos.getDefaultEncoding();
			if (StringUtils.equalsIgnoreCase(ZIP, archiveType)) {
				ZipFile zipFile = new ZipFile(srcPath, encoding);
				File outputDirectory = null;
				if (FileUtils.isNotExists(outputPath)) {
					outputDirectory = FileUtils.createDirectory(outputPath);
				} else {
					outputDirectory = new File(outputPath);
				}
				extractFromZip(zipFile, outputDirectory);
			} else {
				// TODO support other archive format
				System.out.println("This library only support zip file now.");
			}
		}
	}

	/**
	 * 
	 * @param srcPath
	 * @param filePath
	 * @param archiveType
	 * @throws IOException
	 */
	public static void compress(String srcPath, String filePath,
			String archiveType) throws IOException {
		compress(srcPath, filePath, archiveType, false);
	}

	/**
	 * 
	 * @param srcPath
	 * @param filePath
	 * @param archiveType
	 * @param isContainsSrcDir
	 * @throws IOException
	 */
	public static void compress(String srcPath, String filePath,
			String archiveType, boolean isContainsSrcDir) throws IOException {
		if (StringUtils.isBlank(srcPath)) {
			System.out.println("Please input the sources' path.");
			return;
		} else {
			srcPath = FileUtils.checkPath(srcPath);
		}
		if (StringUtils.isBlank(filePath)) {
			System.out.println("Please input the destination file's path.");
			return;
		} else {
			filePath = FileUtils.checkPath(filePath);
		}
		if (StringUtils.isBlank(archiveType)) {
			archiveType = ZIP;
		}
		String tempPath = "";
		if (isContainsSrcDir) {
			tempPath = StringUtils.removeEndIgnoreCase(
					StringUtils.substringAfterLast(srcPath, fileSeparator), "."
							+ ZIP);
		}
		if (StringUtils.equalsIgnoreCase(ZIP, archiveType)) {
			if (!StringUtils.endsWithIgnoreCase(filePath, "." + ZIP)) {
				filePath += "." + ZIP;
			}
			File zipFile = new File(filePath);
			if (zipFile.exists()) {
				System.out.println("The file is already exists.");
				return;
			} else {
				String directoryPath = StringUtils.substringBeforeLast(
						filePath, fileSeparator);
				if (FileUtils.isNotExists(directoryPath)) {
					FileUtils.createDirectory(directoryPath);
				}
			}
			ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(
					new FileOutputStream(zipFile));
			zaos.setEncoding(SystemInfos.getDefaultEncoding());
			compressToZip(zaos, new File(srcPath), tempPath);
			zaos.close();
		} else {
			// TODO support other archive format
			System.out.println("This library only support zip file now.");
		}
	}

	/**
	 * 
	 * @param fileList
	 * @param filePath
	 * @param archiveType
	 * @throws IOException
	 */
	public static void compress(ArrayList<File> fileList, String filePath,
			String archiveType) throws IOException {
		compress(fileList, null, filePath, archiveType);
	}

	/**
	 * 
	 * @param fileList
	 * @param superPath
	 * @param filePath
	 * @param archiveType
	 * @throws IOException
	 */
	public static void compress(ArrayList<File> fileList, String superPath,
			String filePath, String archiveType) throws IOException {
		if (StringUtils.isBlank(filePath)) {
			System.out.println("Please input the destination file's path.");
			return;
		} else {
			filePath = FileUtils.checkPath(filePath);
		}
		if (StringUtils.isBlank(archiveType)) {
			archiveType = ZIP;
		}
		if (StringUtils.isBlank(superPath)) {
			superPath = "";
		}
		ArrayList<File> tempList = new ArrayList<File>();
		if (fileList.isEmpty()) {
			System.out.println("There is nothing to compress.");
			return;
		} else {
			for (File file : fileList) {
				if (null != file) {
					tempList.add(file);
				}
			}
		}
		if (StringUtils.equalsIgnoreCase(ZIP, archiveType)) {
			if (!StringUtils.endsWithIgnoreCase(filePath, "." + ZIP)) {
				filePath += "." + ZIP;
			}
			File zipFile = new File(filePath);
			if (zipFile.exists()) {
				System.out.println("The file is already exists.");
				return;
			} else {
				String directoryPath = StringUtils.substringBeforeLast(
						filePath, fileSeparator);
				if (FileUtils.isNotExists(directoryPath)) {
					FileUtils.createDirectory(directoryPath);
				}
			}
			ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(
					new FileOutputStream(zipFile));
			zaos.setEncoding(SystemInfos.getDefaultEncoding());
			compressToZip(zaos, tempList, superPath);
			zaos.close();
		} else {
			// TODO support other archive format
			System.out.println("This library only support zip file now.");
		}
	}

	/**
	 * 
	 * @param srcPath
	 * @param archiveType
	 * @return
	 * @throws IOException
	 */
	public static List<String> listFiles(String srcPath, String archiveType)
			throws IOException {
		return listFiles(srcPath, "", archiveType);
	}

	/**
	 * 
	 * @param srcPath
	 * @param superPath
	 * @param archiveType
	 * @return
	 * @throws IOException
	 */
	public static List<String> listFiles(String srcPath, String superPath,
			String archiveType) throws IOException {
		srcPath = FileUtils.checkPath(srcPath);
		if (StringUtils.equalsIgnoreCase(ZIP, archiveType)) {
			return listFileFromZip(srcPath, superPath);
		} else {
			// TODO support other archive format
			System.out.println("This library only support zip file now.");
			return null;
		}
	}

	/**
	 * 
	 * @param srcPath
	 * @param archiveType
	 * @return
	 * @throws IOException
	 */
	public static List<String> listDirectories(String srcPath,
			String archiveType) throws IOException {
		return listDirectories(srcPath, "", archiveType);
	}

	/**
	 * 
	 * @param srcPath
	 * @param superPath
	 * @param archiveType
	 * @return
	 * @throws IOException
	 */
	public static List<String> listDirectories(String srcPath,
			String superPath, String archiveType) throws IOException {
		srcPath = FileUtils.checkPath(srcPath);
		if (StringUtils.equalsIgnoreCase(ZIP, archiveType)) {
			return listDirectoryFromZip(srcPath, superPath);
		} else {
			// TODO support other archive format
			System.out.println("This library only support zip file now.");
			return null;
		}
	}

	/**
	 * 
	 * @param srcPath
	 * @param archiveType
	 * @return
	 * @throws IOException
	 */
	public static List<String> listAll(String srcPath, String archiveType)
			throws IOException {
		return listAll(srcPath, "", archiveType);
	}

	/**
	 * 
	 * @param srcPath
	 * @param superPath
	 * @param archiveType
	 * @return
	 * @throws IOException
	 */
	public static List<String> listAll(String srcPath, String superPath,
			String archiveType) throws IOException {
		srcPath = FileUtils.checkPath(srcPath);
		if (StringUtils.equalsIgnoreCase(ZIP, archiveType)) {
			return listAllFromZip(srcPath, superPath);
		} else {
			// TODO support other archive format
			System.out.println("This library only support zip file now.");
			return null;
		}
	}

	/**
	 * 
	 * @param zipFile
	 * @param outputDirectory
	 * @throws IOException
	 */
	private static void extractFromZip(ZipFile zipFile, File outputDirectory)
			throws IOException {
		@SuppressWarnings("unchecked")
		// clear a warning of zipFile.getEntries()
		Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
		while (entries.hasMoreElements()) {
			ZipArchiveEntry entry = entries.nextElement();
			if (entry.isDirectory()
					&& FileUtils.isNotExists(outputDirectory.getAbsolutePath()
							+ fileSeparator + entry.getName())) {
				FileUtils.createDirectory(outputDirectory.getAbsolutePath()
						+ fileSeparator + entry.getName());
			} else {
				if (StringUtils.contains(entry.getName(), fileSeparator)) {
					String subDirectory = StringUtils.substringBeforeLast(
							entry.getName(), fileSeparator);
					if (FileUtils.isNotExists(subDirectory)) {
						FileUtils.createDirectory(subDirectory);
					}
				}
				FileOutputStream fos = new FileOutputStream(
						outputDirectory.getAbsolutePath() + fileSeparator
								+ entry.getName());
				InputStream is = zipFile.getInputStream(entry);
				IOUtils.copy(is, fos);
				is.close();
				fos.flush();
				fos.close();
			}
		}
	}

	/**
	 * 
	 * @param aos
	 * @param source
	 * @param tempPath
	 * @throws IOException
	 */
	private static void compressToZip(ArchiveOutputStream aos, File source,
			String tempPath) throws IOException {
		if (source.isDirectory()) {
			File[] fileList = source.listFiles();
			tempPath = tempPath.length() == 0 ? "" : tempPath + fileSeparator;
			for (int i = 0; i < fileList.length; i++) {
				compressToZip(aos, fileList[i],
						tempPath + fileList[i].getName());
			}
		} else {
			aos.putArchiveEntry(new ZipArchiveEntry(tempPath));
			IOUtils.copy(new FileInputStream(source), aos);
			aos.closeArchiveEntry();
		}
	}

	/**
	 * 
	 * @param aos
	 * @param tempList
	 * @param superPath
	 * @throws IOException
	 */
	private static void compressToZip(ArchiveOutputStream aos,
			ArrayList<File> tempList, String superPath) throws IOException {
		if (StringUtils.isBlank(superPath)) {
			superPath = "";
		} else {
			if (!StringUtils.endsWithIgnoreCase(superPath, fileSeparator)) {
				superPath += fileSeparator;
			}
		}
		String entryPath = null;
		for (File file : tempList) {
			entryPath = superPath
					+ StringUtils.substringBeforeLast(file.getName(), ".")
					+ "."
					+ StringUtils.lowerCase(StringUtils.substringAfterLast(
							file.getName(), "."));
			aos.putArchiveEntry(new ZipArchiveEntry(entryPath));
			IOUtils.copy(new FileInputStream(file), aos);
			aos.closeArchiveEntry();
		}
	}

	/**
	 * 
	 * @param srcPath
	 * @param superPath
	 * @return
	 * @throws IOException
	 */
	private static List<String> listFileFromZip(String srcPath, String superPath)
			throws IOException {
		if (StringUtils.isBlank(srcPath)) {
			System.out.println("Please input the archive file's path.");
			return null;
		}
		String encoding = SystemInfos.getDefaultEncoding();
		// get the OS's default encoding
		ZipFile zipFile = new ZipFile(srcPath, encoding);
		List<String> list = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		// clear a warning of zipFile.getEntries()
		Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
		if (StringUtils.isNotBlank(superPath)) {
			superPath = FileUtils.checkPath(superPath);
			if (StringUtils.startsWith(superPath, fileSeparator))
			/* to eliminate the super directory (include itself) */
			{
				superPath = StringUtils.substring(superPath,
						StringUtils.indexOfAnyBut(superPath, fileSeparator));
			}
			if (!StringUtils.endsWith(superPath, fileSeparator)) {
				superPath += fileSeparator;
			}
			/*
			 * remove the start separator of superPath and add end separator to
			 * superPath
			 */
			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				String entryName = FileUtils.checkPath(entry.getName());
				if (!entry.isDirectory()
						&& StringUtils.startsWith(entryName, superPath))
				/* to eliminate the super directory (include itself) */
				{
					if (-1 == StringUtils.indexOf(
							StringUtils.substringAfter(entryName, superPath),
							fileSeparator))
					/* to eliminate the file or directory in the sub directory */
					{
						list.add(entryName);
					}
				}
			}
		} else {
			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				if (!entry.isDirectory()
						&& -1 == StringUtils.indexOf(
								FileUtils.checkPath(entry.getName()),
								fileSeparator))
				/* to eliminate the file or directory in the sub directory */
				{
					list.add(FileUtils.checkPath(entry.getName()));
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param srcPath
	 * @param superPath
	 * @return
	 * @throws IOException
	 */
	private static List<String> listDirectoryFromZip(String srcPath,
			String superPath) throws IOException {
		if (StringUtils.isBlank(srcPath)) {
			System.out.println("Please input the archive file's path.");
			return null;
		}
		String encoding = SystemInfos.getDefaultEncoding();
		// get the OS's default encoding
		List<String> list = new ArrayList<String>();
		ZipFile zipFile = new ZipFile(srcPath, encoding);
		@SuppressWarnings("unchecked")
		// clear a warning of zipFile.getEntries()
		Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
		if (StringUtils.isNotBlank(superPath)) {
			superPath = FileUtils.checkPath(superPath);
			if (StringUtils.startsWith(superPath, fileSeparator))
			/* to eliminate the super directory (include itself) */
			{
				superPath = StringUtils.substring(superPath,
						StringUtils.indexOfAnyBut(superPath, fileSeparator));
			}
			if (!StringUtils.endsWith(superPath, fileSeparator)) {
				superPath += fileSeparator;
			}
			/*
			 * remove the start separator of superPath and add end separator to
			 * superPath
			 */
			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				String entryName = FileUtils.checkPath(entry.getName());
				if (StringUtils.startsWith(entryName, superPath))
				/* to eliminate the super directory (include itself) */
				{
					if (1 == StringUtils.split(
							StringUtils.substringAfter(entryName, superPath),
							fileSeparator).length)
					/*
					 * to eliminate the file or directory in the sub directory
					 */
					{
						if (entry.isDirectory())
						/* to eliminate the file */
						{
							list.add(entryName);
						}
					}
				}
			}
		} else {
			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				if (1 == StringUtils.split(StringUtils.substringAfter(
						FileUtils.checkPath(entry.getName()), superPath),
						fileSeparator).length)
				/*
				 * to eliminate the file or directory in the sub directory
				 */
				{
					if (entry.isDirectory())
					/* to eliminate the file */
					{
						list.add(FileUtils.checkPath(entry.getName()));
					}
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * @param srcPath
	 * @param superPath
	 * @return
	 * @throws IOException
	 */
	private static List<String> listAllFromZip(String srcPath, String superPath)
			throws IOException {
		if (StringUtils.isBlank(srcPath)) {
			System.out.println("Please input the archive file's path.");
			return null;
		}
		String encoding = SystemInfos.getDefaultEncoding();
		// get the OS's default encoding
		List<String> list = new ArrayList<String>();
		ZipFile zipFile = new ZipFile(srcPath, encoding);
		@SuppressWarnings("unchecked")
		// clear a warning of zipFile.getEntries()
		Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
		if (StringUtils.isNotBlank(superPath)) {
			if (StringUtils.isNotBlank(superPath)
					&& StringUtils.equals(FileUtils.checkPath(superPath),
							fileSeparator))
			/* to estimate the superPath is not blank and equals with root */
			{
				while (entries.hasMoreElements()) {
					ZipArchiveEntry entry = entries.nextElement();
					String entryName = FileUtils.checkPath(entry.getName());
					if (1 == StringUtils.split(entryName, fileSeparator).length)
					/* to eliminate the file or directory in the sub directory */
					{
						list.add(entryName);
					}
				}
			} else {
				superPath = FileUtils.checkPath(superPath);
				if (StringUtils.startsWith(superPath, fileSeparator)) {
					superPath = StringUtils
							.substring(superPath, StringUtils.indexOfAnyBut(
									superPath, fileSeparator));
				}
				if (!StringUtils.endsWith(superPath, fileSeparator)) {
					superPath += fileSeparator;
				}
				/*
				 * remove the start separator of superPath and add end separator
				 * to superPath
				 */
				while (entries.hasMoreElements()) {
					ZipArchiveEntry entry = entries.nextElement();
					String entryName = FileUtils.checkPath(entry.getName());
					if (StringUtils.startsWith(entryName, superPath)
							&& !StringUtils.equals(superPath, entryName))
					/* to eliminate the super directory (include itself) */
					{
						if (-1 == StringUtils.indexOf(StringUtils.removeEnd(
								StringUtils
										.substringAfter(entryName, superPath),
								fileSeparator), fileSeparator))
						/*
						 * to eliminate the file or directory in the sub
						 * directory
						 */
						{
							list.add(entryName);
						}
					}
				}
			}
		} else {
			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				list.add(FileUtils.checkPath(entry.getName()));
			}
		}
		return list;
	}
}
