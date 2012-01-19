package cn.mibcxb.utility;

public class SystemInfos {
	// java.version Java 运行时环境版本
	// java.vendor Java 运行时环境供应商
	// java.vendor.url Java 供应商的 URL
	// java.home Java 安装目录
	// java.vm.specification.version Java 虚拟机规范版本
	// java.vm.specification.vendor Java 虚拟机规范供应商
	// java.vm.specification.name Java 虚拟机规范名称
	// java.vm.version Java 虚拟机实现版本
	// java.vm.vendor Java 虚拟机实现供应商
	// java.vm.name Java 虚拟机实现名称
	// java.specification.version Java 运行时环境规范版本
	// java.specification.vendor Java 运行时环境规范供应商
	// java.specification.name Java 运行时环境规范名称
	// java.class.version Java 类格式版本号
	// java.class.path Java 类路径
	// java.library.path 加载库时搜索的路径列表
	// java.io.tmpdir 默认的临时文件路径
	// java.compiler 要使用的 JIT 编译器的名称
	// java.ext.dirs 一个或多个扩展目录的路径
	// os.name 操作系统的名称
	// os.arch 操作系统的架构
	// os.version 操作系统的版本
	// file.separator 文件分隔符（在 UNIX 系统中是“/”）
	// path.separator 路径分隔符（在 UNIX 系统中是“:”）
	// line.separator 行分隔符（在 UNIX 系统中是“/n”）
	// user.name 用户的账户名称
	// user.home 用户的主目录
	// user.dir 用户的当前工作目录
	// user.country 用户的当前地区
	// user.language 用户的当前语言

	private static String javaVersion = System.getProperty("java.version");
	private static String javaVendor = System.getProperty("java.vendor");
	private static String javaVendorURL = System.getProperty("java.vendor.url");
	private static String javaHome = System.getProperty("java.home");
	private static String javaVMSpecificationVersion = System
			.getProperty("java.vm.specification.version");
	private static String javaVMSpecificationVendor = System
			.getProperty("java.vm.specification.vendor");
	private static String javaVMSpecificationName = System
			.getProperty("java.vm.specification.name");
	private static String javaVMVersion = System.getProperty("java.vm.version");
	private static String javaVMVendor = System.getProperty("java.vm.vendor");
	private static String javaVMName = System.getProperty("java.vm.name");
	private static String javaSpecificationVersion = System
			.getProperty("java.specification.version");
	private static String javaSpecificationVendor = System
			.getProperty("java.specification.vendor");
	private static String javaSpecificationName = System
			.getProperty("java.specification.name");
	private static String javaClassVersion = System
			.getProperty("java.class.version");
	private static String javaClassPath = System.getProperty("java.class.path");
	private static String javaLibraryPath = System
			.getProperty("java.library.path");
	private static String javaIOTmpDir = System.getProperty("java.io.tmpdir");
	private static String javaCompiler = System.getProperty("java.compiler");
	private static String javaExtDirs = System.getProperty("java.ext.dirs");
	private static String osName = System.getProperty("os.name");
	private static String osArch = System.getProperty("os.arch");
	private static String osVersion = System.getProperty("os.version");
	private static String fileSeparator = System.getProperty("file.separator");
	private static String pathSeparator = System.getProperty("path.separator");
	private static String lineSeparator = System.getProperty("line.separator");
	private static String userName = System.getProperty("user.name");
	private static String userHome = System.getProperty("user.home");
	private static String userDir = System.getProperty("user.dir");
	private static String userCountry = System.getProperty("user,country");
	private static String userLanguage = System.getProperty("user.language");
	
	//自定义系统信息
	private static String defaultEncoding = System.getProperty("sun.jnu.encoding");

	public SystemInfos() {
		super();
	}

	/**
	 * @return the javaVersion
	 */
	public static String getJavaVersion() {
		return javaVersion;
	}

	/**
	 * @param javaVersion
	 *            the javaVersion to set
	 */
	public static void setJavaVersion(String javaVersion) {
		SystemInfos.javaVersion = javaVersion;
	}

	/**
	 * @return the javaVendor
	 */
	public static String getJavaVendor() {
		return javaVendor;
	}

	/**
	 * @param javaVendor
	 *            the javaVendor to set
	 */
	public static void setJavaVendor(String javaVendor) {
		SystemInfos.javaVendor = javaVendor;
	}

	/**
	 * @return the javaVendorURL
	 */
	public static String getJavaVendorURL() {
		return javaVendorURL;
	}

	/**
	 * @param javaVendorURL
	 *            the javaVendorURL to set
	 */
	public static void setJavaVendorURL(String javaVendorURL) {
		SystemInfos.javaVendorURL = javaVendorURL;
	}

	/**
	 * @return the javaHome
	 */
	public static String getJavaHome() {
		return javaHome;
	}

	/**
	 * @param javaHome
	 *            the javaHome to set
	 */
	public static void setJavaHome(String javaHome) {
		SystemInfos.javaHome = javaHome;
	}

	/**
	 * @return the javaVMSpecificationVersion
	 */
	public static String getJavaVMSpecificationVersion() {
		return javaVMSpecificationVersion;
	}

	/**
	 * @param javaVMSpecificationVersion
	 *            the javaVMSpecificationVersion to set
	 */
	public static void setJavaVMSpecificationVersion(
			String javaVMSpecificationVersion) {
		SystemInfos.javaVMSpecificationVersion = javaVMSpecificationVersion;
	}

	/**
	 * @return the javaVMSpecificationVendor
	 */
	public static String getJavaVMSpecificationVendor() {
		return javaVMSpecificationVendor;
	}

	/**
	 * @param javaVMSpecificationVendor
	 *            the javaVMSpecificationVendor to set
	 */
	public static void setJavaVMSpecificationVendor(
			String javaVMSpecificationVendor) {
		SystemInfos.javaVMSpecificationVendor = javaVMSpecificationVendor;
	}

	/**
	 * @return the javaVMSpecificationName
	 */
	public static String getJavaVMSpecificationName() {
		return javaVMSpecificationName;
	}

	/**
	 * @param javaVMSpecificationName
	 *            the javaVMSpecificationName to set
	 */
	public static void setJavaVMSpecificationName(String javaVMSpecificationName) {
		SystemInfos.javaVMSpecificationName = javaVMSpecificationName;
	}

	/**
	 * @return the javaVMVersion
	 */
	public static String getJavaVMVersion() {
		return javaVMVersion;
	}

	/**
	 * @param javaVMVersion
	 *            the javaVMVersion to set
	 */
	public static void setJavaVMVersion(String javaVMVersion) {
		SystemInfos.javaVMVersion = javaVMVersion;
	}

	/**
	 * @return the javaVMVendor
	 */
	public static String getJavaVMVendor() {
		return javaVMVendor;
	}

	/**
	 * @param javaVMVendor
	 *            the javaVMVendor to set
	 */
	public static void setJavaVMVendor(String javaVMVendor) {
		SystemInfos.javaVMVendor = javaVMVendor;
	}

	/**
	 * @return the javaVMName
	 */
	public static String getJavaVMName() {
		return javaVMName;
	}

	/**
	 * @param javaVMName
	 *            the javaVMName to set
	 */
	public static void setJavaVMName(String javaVMName) {
		SystemInfos.javaVMName = javaVMName;
	}

	/**
	 * @return the javaSpecificationVersion
	 */
	public static String getJavaSpecificationVersion() {
		return javaSpecificationVersion;
	}

	/**
	 * @param javaSpecificationVersion
	 *            the javaSpecificationVersion to set
	 */
	public static void setJavaSpecificationVersion(
			String javaSpecificationVersion) {
		SystemInfos.javaSpecificationVersion = javaSpecificationVersion;
	}

	/**
	 * @return the javaSpecificationVendor
	 */
	public static String getJavaSpecificationVendor() {
		return javaSpecificationVendor;
	}

	/**
	 * @param javaSpecificationVendor
	 *            the javaSpecificationVendor to set
	 */
	public static void setJavaSpecificationVendor(String javaSpecificationVendor) {
		SystemInfos.javaSpecificationVendor = javaSpecificationVendor;
	}

	/**
	 * @return the javaSpecificationName
	 */
	public static String getJavaSpecificationName() {
		return javaSpecificationName;
	}

	/**
	 * @param javaSpecificationName
	 *            the javaSpecificationName to set
	 */
	public static void setJavaSpecificationName(String javaSpecificationName) {
		SystemInfos.javaSpecificationName = javaSpecificationName;
	}

	/**
	 * @return the javaClassVersion
	 */
	public static String getJavaClassVersion() {
		return javaClassVersion;
	}

	/**
	 * @param javaClassVersion
	 *            the javaClassVersion to set
	 */
	public static void setJavaClassVersion(String javaClassVersion) {
		SystemInfos.javaClassVersion = javaClassVersion;
	}

	/**
	 * @return the javaClassPath
	 */
	public static String getJavaClassPath() {
		return javaClassPath;
	}

	/**
	 * @param javaClassPath
	 *            the javaClassPath to set
	 */
	public static void setJavaClassPath(String javaClassPath) {
		SystemInfos.javaClassPath = javaClassPath;
	}

	/**
	 * @return the javaLibraryPath
	 */
	public static String getJavaLibraryPath() {
		return javaLibraryPath;
	}

	/**
	 * @param javaLibraryPath
	 *            the javaLibraryPath to set
	 */
	public static void setJavaLibraryPath(String javaLibraryPath) {
		SystemInfos.javaLibraryPath = javaLibraryPath;
	}

	/**
	 * @return the javaIOTmpDir
	 */
	public static String getJavaIOTmpDir() {
		return javaIOTmpDir;
	}

	/**
	 * @param javaIOTmpDir
	 *            the javaIOTmpDir to set
	 */
	public static void setJavaIOTmpDir(String javaIOTmpDir) {
		SystemInfos.javaIOTmpDir = javaIOTmpDir;
	}

	/**
	 * @return the javaCompiler
	 */
	public static String getJavaCompiler() {
		return javaCompiler;
	}

	/**
	 * @param javaCompiler
	 *            the javaCompiler to set
	 */
	public static void setJavaCompiler(String javaCompiler) {
		SystemInfos.javaCompiler = javaCompiler;
	}

	/**
	 * @return the javaExtDirs
	 */
	public static String getJavaExtDirs() {
		return javaExtDirs;
	}

	/**
	 * @param javaExtDirs
	 *            the javaExtDirs to set
	 */
	public static void setJavaExtDirs(String javaExtDirs) {
		SystemInfos.javaExtDirs = javaExtDirs;
	}

	/**
	 * @return the osName
	 */
	public static String getOsName() {
		return osName;
	}

	/**
	 * @param osName
	 *            the osName to set
	 */
	public static void setOsName(String osName) {
		SystemInfos.osName = osName;
	}

	/**
	 * @return the osArch
	 */
	public static String getOsArch() {
		return osArch;
	}

	/**
	 * @param osArch
	 *            the osArch to set
	 */
	public static void setOsArch(String osArch) {
		SystemInfos.osArch = osArch;
	}

	/**
	 * @return the osVersion
	 */
	public static String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion
	 *            the osVersion to set
	 */
	public static void setOsVersion(String osVersion) {
		SystemInfos.osVersion = osVersion;
	}

	/**
	 * @return the fileSeparator
	 */
	public static String getFileSeparator() {
		return fileSeparator;
	}

	/**
	 * @param fileSeparator
	 *            the fileSeparator to set
	 */
	public static void setFileSeparator(String fileSeparator) {
		SystemInfos.fileSeparator = fileSeparator;
	}

	/**
	 * @return the pathSeparator
	 */
	public static String getPathSeparator() {
		return pathSeparator;
	}

	/**
	 * @param pathSeparator
	 *            the pathSeparator to set
	 */
	public static void setPathSeparator(String pathSeparator) {
		SystemInfos.pathSeparator = pathSeparator;
	}

	/**
	 * @return the lineSeparator
	 */
	public static String getLineSeparator() {
		return lineSeparator;
	}

	/**
	 * @param lineSeparator
	 *            the lineSeparator to set
	 */
	public static void setLineSeparator(String lineSeparator) {
		SystemInfos.lineSeparator = lineSeparator;
	}

	/**
	 * @return the userName
	 */
	public static String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public static void setUserName(String userName) {
		SystemInfos.userName = userName;
	}

	/**
	 * @return the userHome
	 */
	public static String getUserHome() {
		return userHome;
	}

	/**
	 * @param userHome
	 *            the userHome to set
	 */
	public static void setUserHome(String userHome) {
		SystemInfos.userHome = userHome;
	}

	/**
	 * @return the userDir
	 */
	public static String getUserDir() {
		return userDir;
	}

	/**
	 * @param userDir
	 *            the userDir to set
	 */
	public static void setUserDir(String userDir) {
		SystemInfos.userDir = userDir;
	}

	/**
	 * @return the userCountry
	 */
	public static String getUserCountry() {
		return userCountry;
	}

	/**
	 * @param userCountry
	 *            the userCountry to set
	 */
	public static void setUserCountry(String userCountry) {
		SystemInfos.userCountry = userCountry;
	}

	/**
	 * @return the userLanguage
	 */
	public static String getUserLanguage() {
		return userLanguage;
	}

	/**
	 * @param userLanguage
	 *            the userLanguage to set
	 */
	public static void setUserLanguage(String userLanguage) {
		SystemInfos.userLanguage = userLanguage;
	}

	/**
	 * @return the defaultEncoding
	 */
	public static String getDefaultEncoding() {
		return defaultEncoding;
	}
}
