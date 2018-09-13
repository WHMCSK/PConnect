package cn.bgenius.pconnect.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class FileUtil {
	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

	public static final long FILE_MAX = 1024L * 1024 * 1024 * 1024;// unit byte

	private static final int BUFFER_SIZE = 4096;

	private static final String[] FILE_SIZE_UNIT_ARR = new String[] { "Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };

	private FileUtil() {
	}

	// 最大处理的文件大小
	// 对拷贝文件不起作用

	public static String toWebPath(String path) {
		if (StrUtil.isBlank(path)) {
			return "";
		}
		path = path.replaceAll("\\\\", "/").trim();
		while (path.indexOf("//") > -1) {
			path = path.replaceAll("//", "/");
		}

		int indexTemp = -1;
		String strTemp = "";
		if (File.separatorChar != '/') {
			indexTemp = path.indexOf(File.separatorChar);
			strTemp = "";
			while (indexTemp > -1) {
				strTemp = path.substring(0, indexTemp);
				path = strTemp + "/" + path.substring(indexTemp + 1);
				indexTemp = path.indexOf(File.separatorChar);
			}
		}
		return path;
	}

	/**
	 * 自动创建父路径
	 * 
	 * @param absolutePath
	 */
	public static void createFile(String absolutePath) {
		String absolutePathParent = PathUtil.toWebPath(absolutePath);
		if (absolutePathParent.endsWith("/")) {
			absolutePathParent = absolutePathParent.substring(0, absolutePathParent.length() - 1);
		}
		int index = absolutePathParent.lastIndexOf("/");
		if (index > 0) {// 非windows盘符 或非linux的跟目录
			absolutePathParent = absolutePathParent.substring(0, index);
			if (!absolutePathParent.endsWith(":") && !absolutePathParent.equals("/")) {
				File fParent = new File(PathUtil.toPath(absolutePathParent));
				if (!fParent.isDirectory()) {
					fParent.mkdirs();
				}
			}
		}
		File f = new File(absolutePath);
		if (!f.isFile()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	/**
	 * 创建文件夹
	 * 
	 * @param absolutePathDir
	 */
	public static void createDir(String absolutePathDir) {
		File f = new File(absolutePathDir);
		if (!f.isDirectory()) {
			f.mkdirs();
		}
	}

	/**
	 * 创建一个含有内容的文件 会把已经存在的文件覆盖掉
	 * 
	 * @param absolutePath
	 * @param content
	 *
	 * @return
	 */
	public static void createFile(String absolutePath, byte[] content) {
		if (content == null || content.length < 1) {
			createFile(absolutePath);
			return;
		}
		FileOutputStream fos = null;
		try {
			absolutePath = absolutePath.replaceAll("\\\\", "/");
			File fileDir = new File(absolutePath.substring(0, absolutePath.lastIndexOf("/")));
			if(!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File f = new File(absolutePath);
			fos = new FileOutputStream(f);
			fos.write(content);
			fos.flush();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (Exception e) {
				if (LOG.isDebugEnabled()) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * 创建一个含有内容的文件 会把已经存在的文件覆盖掉
	 * 
	 * @param absolutePath
	 * @param content
	 * @param charset
	 * @return
	 */
	public static void createFile(String absolutePath, String content, String charset) {
		FileOutputStream fos = null;
		OutputStreamWriter out = null;
		try {
			absolutePath = absolutePath.replaceAll("\\\\", "/");
			File fileDir = new File(absolutePath.substring(0, absolutePath.lastIndexOf("/")));
			if(!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File f = new File(absolutePath);
			fos = new FileOutputStream(f);
			if (StrUtil.isBlank(charset)) {
				out = new OutputStreamWriter(fos);
			} else {
				out = new OutputStreamWriter(fos, charset);
			}
			out.write(content); // 写入文件内容
			out.flush();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
				if (LOG.isDebugEnabled()) {
					LOG.error(e.getMessage(), e);
				}
			} finally {
				try {
					if (fos != null) {
						fos.close();
						fos = null;
					}
				} catch (Exception e) {
					if (LOG.isDebugEnabled()) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		}
	}

	public static void createFile(String absolutePath, String content) {
		createFile(absolutePath, content, null);
	}

	public static void renameTo(String absolutePathSource, String absolutePathDest) {
		if (StrUtil.isBlank(absolutePathSource)) {
			throw new NullPointerException("absolutePathSource=" + absolutePathSource);
		}
		if (StrUtil.isBlank(absolutePathDest)) {
			throw new NullPointerException("absolutePathDest=" + absolutePathDest);
		}
		File fileSource = new File(absolutePathSource);
		if (!fileSource.exists()) {
			throw new RuntimeException("FileNotFoundException absolutePathSource=" + absolutePathSource);
		}
		fileSource.renameTo(new File(absolutePathDest));
	}

	/**
	 * 在文件中追加最后一个内容(String),没有这个文件的话返回 false
	 * 
	 * @param absolutePath
	 * @param content
	 * @param charset
	 * @param append
	 *            true=在文件后追加 false=清空原来的，添加新的内容
	 * @return
	 */
	public static void addContentToFile(String absolutePath, String content, String charset, boolean append) {
		File f = new File(absolutePath);
		FileOutputStream fos = null;
		OutputStreamWriter out = null;

		if (!f.isFile()) {
			if (f.exists()) {
				//new StringBuilder(absolutePath).append(" isn't file").toString()
				throw new RuntimeException();
			} else {
				//new StringBuilder(absolutePath).append(" isn't exist").toString()
				throw new RuntimeException();
			}
		}

		if (f.length() > FILE_MAX) {
			throw new RuntimeException(new StringBuilder(absolutePath).append(" too big,").append(f.length() / (1024 * 1024)).toString());
		}

		try {
			fos = new FileOutputStream(f, append); // true 追加
			if (StrUtil.isBlank(charset)) {
				out = new OutputStreamWriter(fos);
			} else {
				out = new OutputStreamWriter(fos, charset);
			}
			out.write(content); // 写入文件内容
			out.flush(); // 追加文件
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
				if (LOG.isDebugEnabled()) {
					LOG.error(e.getMessage(), e);
				}
			} finally {
				try {
					if (fos != null) {
						fos.close();
						fos = null;
					}
				} catch (Exception e) {
					if (LOG.isDebugEnabled()) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		}
	}

	/**
	 * 在文件中追加最后一个内容(String),没有这个文件的话返回 false
	 * 
	 * @param absolutePath
	 * @param content
	 * @param append
	 * @return
	 */
	public static void addContentToFile(String absolutePath, String content, boolean append) {
		addContentToFile(absolutePath, content, null, append);
	}

	/**
	 * 读取一个文件,返回 String 的文件内容
	 * 
	 * @param absolutePath
	 * @param charset
	 * @return
	 */

	public static String readFile(String absolutePath, String charset) {

		File f = new File(absolutePath);
		if (!f.isFile()) {
			if (f.exists()) {
				throw new RuntimeException(new StringBuilder(absolutePath).append(" isn't file").toString());
			} else {
				throw new RuntimeException(new StringBuilder(absolutePath).append(" isn't exist").toString());
			}
		}
		if (f.length() > FILE_MAX) {
			throw new RuntimeException(new StringBuilder(absolutePath).append(" too big,").append(f.length() / (1024 * 1024)).append("MB").append(" FILE_MAX=").append(FILE_MAX / (1024 * 1024)).append("MB").toString());
		}
		FileInputStream fis = null;
		InputStreamReader isr = null;

		try {
			fis = new FileInputStream(f); // 读取原文件
			int lenght = (int) f.length(); // 文件字符总数
			if (StrUtil.isBlank(charset)) {
				isr = new InputStreamReader(fis);
			} else {
				isr = new InputStreamReader(fis, charset);
			}
			char[] charArr = new char[lenght];
			isr.read(charArr, 0, lenght);
			return new String(charArr); // 把文件内容转换为字符串
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (isr != null) {
					isr.close();
					isr = null;
				}
			} catch (Exception e) {
				if (LOG.isDebugEnabled()) {
					LOG.error(e.getMessage(), e);
				}
			} finally {
				try {
					if (fis != null) {
						fis.close();
						fis = null;
					}
				} catch (Exception e) {
					if (LOG.isDebugEnabled()) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		}
	}

	public static String readFile(String absolutePath) {
		return readFile(absolutePath, null);
	}

	public static ArrayList<String> readFileLines(String absolutePath, String charset) {
		File f = new File(absolutePath);
		if (!f.isFile()) {
			if (f.exists()) {
				throw new RuntimeException(new StringBuilder(absolutePath).append(" isn't file").toString());
			} else {
				throw new RuntimeException(new StringBuilder(absolutePath).append(" isn't exist").toString());
			}
		}
		if (f.length() > FILE_MAX) {
			throw new RuntimeException(new StringBuilder(absolutePath).append(" too big,").append(f.length() / (1024 * 1024)).append("MB").append(" FILE_MAX=").append(FILE_MAX / (1024 * 1024)).append("MB").toString());
		}
		ArrayList<String> resultList = new ArrayList<String>(500);
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream(absolutePath);
			if (StrUtil.isBlank(charset)) {
				inputStreamReader = new InputStreamReader(fileInputStream);
			} else {
				inputStreamReader = new InputStreamReader(fileInputStream, charset);
			}
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {// 一行一行读取
				resultList.add(line);
			}
			bufferedReader.close();
			bufferedReader = null;
			inputStreamReader.close();
			inputStreamReader = null;
			fileInputStream.close();
			fileInputStream = null;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
					bufferedReader = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				try {
					if (inputStreamReader != null) {
						inputStreamReader.close();
						inputStreamReader = null;
					}
				} catch (IOException e) {
					if (LOG.isDebugEnabled()) {
						LOG.error(e.getMessage(), e);
					}
				} finally {
					try {
						if (fileInputStream != null) {
							fileInputStream.close();
							fileInputStream = null;
						}
					} catch (IOException e) {
						if (LOG.isDebugEnabled()) {
							LOG.error(e.getMessage(), e);
						}
					}
				}
			}
		}
		return resultList;
	}

	public static ArrayList<String> readFileLines(String absolutePath) {
		return readFileLines(absolutePath, null);
	}

	/**
	 * 替换文件中的某个字符串
	 * 
	 * @param absolutePath
	 * @param sOld
	 * @param sNew
	 * @param charset
	 * @param replaceModel
	 *            使用indexof截断式函数替换 1-3,5 1=仅仅替换第一个 2=替换最后一个 3=替换全部的
	 *            使用java函数replace 4,6 4=仅仅替换第一个 5=替换最后一个 6=替换全部的
	 * @return
	 */
	public static void replaceFileContent(String absolutePath, String sOld, String sNew, String charset, int replaceModel) {
		if (replaceModel < 0 || replaceModel > 5) {
			throw new RuntimeException(new StringBuilder("replaceModel is wrong,Must in [1-5]").toString());
		}
		if (sOld == null || "".equals(sOld)) {
			throw new RuntimeException("contentOld == null || \"\".equals(contentOld)");
		}
		if (sNew == null) {
			throw new NullPointerException("contentNew == null");
		}

		File f = new File(absolutePath);
		if (!f.isFile()) {
			if (f.exists()) {
				throw new RuntimeException(new StringBuilder(absolutePath).append(" isn't file").toString());
			} else {
				throw new RuntimeException(new StringBuilder(absolutePath).append(" isn't exist").toString());
			}
		}
		if (f.length() > FILE_MAX) {
			throw new RuntimeException(new StringBuilder(absolutePath).append(" too big,").append(f.length() / (1024 * 1024)).append("MB").append(" FILE_MAX=").append(FILE_MAX / (1024 * 1024)).append("MB").toString());
		}

		FileInputStream fis = null;
		InputStreamReader isr = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(f); // 读取原文件
			int lenght = (int) f.length(); // 文件字符字节
			if (StrUtil.isBlank(charset)) {
				isr = new InputStreamReader(fis);
			} else {
				isr = new InputStreamReader(fis, charset);
			}
			char[] charArr = new char[lenght];
			isr.read(charArr, 0, lenght);
			isr.close();
			isr = null;
			fis.close();
			fis = null;

			String fileContent = new String(charArr); // 把文件内容转换为字符串

			switch (replaceModel) {
			case 1:
				fileContent = StrUtil.replaceFirst(fileContent, sOld, sNew);
				break;
			case 2:
				fileContent = StrUtil.replaceLast(fileContent, sOld, sNew);
				break;
			case 3:
				fileContent = StrUtil.replaceAll(fileContent, sOld, sNew);
				break;
			case 4:
				fileContent = fileContent.replaceFirst(sOld, sNew);
				break;
			case 5:
				fileContent = StrUtil.replaceLast(fileContent, sOld, sNew);
				break;
			case 6:
				fileContent = fileContent.replaceAll(sOld, sNew);
				break;
			default:
				break;
			}

			fos = new FileOutputStream(absolutePath);

			byte bytesTemp[] = null;
			if (StrUtil.isBlank(charset)) {
				bytesTemp = fileContent.getBytes();
			} else {
				bytesTemp = fileContent.getBytes(charset);
			}
			fos.write(bytesTemp);
			fos.flush();
			fos.close();
			fos = null;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (isr != null) {
					isr.close();
					isr = null;
				}
			} catch (Exception e) {
				if (LOG.isDebugEnabled()) {
					LOG.error(e.getMessage(), e);
				}
			} finally {
				try {
					if (fos != null) {
						fos.close();
						fos = null;
					}
				} catch (Exception e) {
					if (LOG.isDebugEnabled()) {
						LOG.error(e.getMessage(), e);
					}
				} finally {
					try {
						if (fis != null) {
							fis.close();
							fis = null;
						}
					} catch (Exception e) {
						if (LOG.isDebugEnabled()) {
							LOG.error(e.getMessage(), e);
						}
					}
				}
			}
		}
	}

	public static void replaceFileContent(String absolutePath, String contentOld, String contentNew, int replaceModel) {
		replaceFileContent(absolutePath, contentOld, contentNew, null, replaceModel);
	}

	public static void copyFile(File f, String absolutePathDest) {
		copyFile(f, absolutePathDest, null, null);
	}

	/**
	 * 仅仅拷贝文件
	 * 
	 * @param f
	 * @param absolutePathDest
	 *            目标的完整路径
	 * @param pathAllowed
	 *            允许拷贝的路径，路径中含有该字符串才允许
	 * @param pathForbid
	 *            禁止拷贝的路径，路径中含有该字符串的都不允许
	 * @return
	 */
	public static void copyFile(File f, String absolutePathDest, String[] pathAllowed, String[] pathForbid) {

		if (f == null) {
			throw new NullPointerException("f == null");
		}
		if (!f.isFile()) {
			if (f.exists()) {
				throw new RuntimeException(new StringBuilder(f.getName()).append(" isn't file").toString());
			} else {
				throw new RuntimeException(new StringBuilder(f.getName()).append(" isn't exist").toString());
			}
		}
		String filePath = f.getName();

		if (pathAllowed != null && pathAllowed.length > 0 && !isAllowedPath(filePath, pathAllowed)) {
			throw new RuntimeException(new StringBuilder(f.getName()).append(" isn't allowed pathAllowed[]=").append(Arrays.deepToString(pathAllowed)).toString());
		}

		if (pathForbid != null && pathForbid.length > 0 && isForbidPath(filePath, pathForbid)) {
			throw new RuntimeException(new StringBuilder(f.getName()).append(" is forbid pathForbid[]=").append(Arrays.deepToString(pathForbid)).toString());
		}

		FileInputStream fis = null; // 以字节流的形式读取文件内容
		FileOutputStream fos = null;
		try {

			fis = new FileInputStream(f);
			File fileNew = new File(absolutePathDest); // 新的文件夹路径与名称
			fos = new FileOutputStream(fileNew);

			int bytesRead = 0;
			byte[] buffer = new byte[BUFFER_SIZE];

			while ((bytesRead = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}
			fos.flush();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (Exception e) {
				if (LOG.isDebugEnabled()) {
					LOG.error(e.getMessage(), e);
				}
			} finally {
				try {
					if (fis != null) {
						fis.close();
						fis = null;
					}
				} catch (Exception e) {
					if (LOG.isDebugEnabled()) {
						LOG.error(e.getMessage(), e);
					}
				}
			}
		}
	}

	/**
	 * 拷贝文件 以及文件夹
	 * 
	 * @param absolutePathSource
	 *            源文件夹，要保证这个文件夹存在
	 * @param absolutePathDest
	 *            目标文件夹
	 * @param pathAllowed
	 *            允许的文件路径或文件名，可以使用正则表达式
	 * @param pathForbid
	 *            禁止的文件路径或文件名，可以使用正则表达式
	 * @return
	 */
	public static int copy(String absolutePathSource, String absolutePathDest, String[] pathAllowed, String[] pathForbid) {
		int countFile = 0; // 被成功拷贝的文件个数

		if (absolutePathSource == null || absolutePathDest == null) {
			throw new NullPointerException(new StringBuilder("absolutePathSource=").append(absolutePathSource).append(" absolutePathDest=").append(absolutePathDest).toString());
		}

		File f = new File(absolutePathSource);
		if (!f.exists()) {
			throw new RuntimeException(new StringBuilder("absolutePathSource=").append(absolutePathSource).append(" isn't exists").toString());
		}

		if (f.isFile()) {// 是文件的拷贝就拷贝单个文件
			try {
				copyFile(f, absolutePathDest, pathAllowed, pathForbid);
				countFile++;
			} catch (Exception e) {
				LOG.debug("", e);
			}
		} else if (f.isDirectory()) {

			if (!absolutePathSource.endsWith("/") && !absolutePathSource.endsWith("\\")) {
				absolutePathSource = absolutePathSource + "/";
			}
			if (!absolutePathDest.endsWith("/") && !absolutePathDest.endsWith("\\")) {
				absolutePathDest = absolutePathDest + "/";
			}

			try {

				if (!isAllowedPath(absolutePathSource, pathAllowed)) {
					return countFile;
				}

				if (isForbidPath(absolutePathSource, pathForbid)) {
					return countFile;
				}

				try {
					// 创建一个新的文件夹
					createDir(absolutePathDest);
					countFile++;
					File[] fileArr = f.listFiles();
					for (int i = 0; i < fileArr.length; i++) {
						if (fileArr[i].isDirectory()) { // 拷贝
							countFile = countFile + copy(absolutePathSource + fileArr[i].getName(), absolutePathDest + fileArr[i].getName(), pathAllowed, pathForbid);
						} else { // 拷贝文件
							try {
								copyFile(fileArr[i], absolutePathDest + fileArr[i].getName(), pathAllowed, pathForbid);
								countFile++;
							} catch (Exception e) {
								LOG.debug("", e);
							}
						}
					}
				} catch (Exception e) {
					LOG.debug("", e);
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return countFile;
	}

	public static int copy(String absolutePathSource, String absolutePathDest) {
		return copy(absolutePathSource, absolutePathDest, null, null);
	}

	/**
	 * 递归删除多个文件 与文件夹
	 * 
	 * @param f
	 * 
	 * @param pathAllowed
	 * @param pathForbid
	 * @param extAllowed
	 * @param extForbid
	 * @param filePathForbid
	 * @param filePathAllowed
	 * @return
	 */
	private static int deleteCore(File f, String[] pathAllowed, String[] pathForbid, String[] extAllowed, String[] extForbid, String[] filePathForbid, String[] filePathAllowed) {
		// 类似copy的过滤
		int result = 0; // 被成功删除的文件个数

		if (!f.exists()) {
			return result;
		}

		File fArr[] = f.listFiles();
		int fArrLength = fArr != null ? fArr.length : 0;
		String filePath = "";
		for (int i = 0; i < fArrLength; i++) {
			if (fArr[i].isDirectory()) {
				result = result + deleteCore(fArr[i], pathAllowed, pathForbid, extAllowed, extForbid, filePathForbid, filePathAllowed);
			} else {
				// 判断是否允许删除文件夹
				filePath = fArr[i].getAbsolutePath();
				if (!isAllowedPath(filePath, pathAllowed)) {
					continue;
				}

				if (isForbidPath(filePath, pathForbid)) {
					continue;
				}
				if (fArr[i].delete()) {
					result++; // 文件删除成功
				}
			}

		}

		// 判断是否允许删除文件夹
		filePath = f.getAbsolutePath();
		if (!isAllowedPath(filePath, pathAllowed)) {
			return result;
		}
		if (isForbidPath(filePath, pathForbid)) {
			return result;
		}
		f.delete();
		result++; // 成功删除文件夹
		return result;
	}

	/**
	 * 删除文件夹与文件
	 * 
	 * @param absolutePath
	 * @param pathAllowed
	 * @param pathForbid
	 * @param extAllowed
	 * @param extForbid
	 * @param filePathForbid
	 * @param filePathAllowed
	 * @return
	 */
	public static int delete(String absolutePath, String[] pathAllowed, String[] pathForbid, String[] extAllowed, String[] extForbid, String[] filePathForbid, String[] filePathAllowed) {
		if (absolutePath == null) {
			throw new NullPointerException("absolutePath==null");
		}
		File f = new File(absolutePath);
		if (!f.exists()) {
			return 0;
		}
		return deleteCore(f, pathAllowed, pathForbid, extAllowed, extForbid, filePathForbid, filePathAllowed);
	}

	public static int delete(String absolutePath, String[] pathAllowed) {
		return delete(absolutePath, pathAllowed, null, null, null, null, null);
	}

	/**
	 * 删除所有文件，包括所有的文件以及文件夹，以及absolutePath
	 * 
	 * @param absolutePath
	 * @return
	 */
	public static int delete(String absolutePath) {
		return delete(absolutePath, null, null, null, null, null, null);
	}

	public static String getPath(String absolutePath) {
		if (StrUtil.isBlank(absolutePath)) {
			return "";
		}
		absolutePath = absolutePath.trim();
		int index = absolutePath.lastIndexOf("/");
		if (index < 0) {
			index = absolutePath.lastIndexOf("\\");
		}
		if (index < 0 || (index + 1) >= absolutePath.length()) {
			return absolutePath;
		}
		return absolutePath.substring(0, index + 1);
	}

	/**
	 * 返回文件名包括扩展名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		if (StrUtil.isBlank(filePath)) {
			return "";
		}
		filePath = filePath.trim();

		int index = filePath.lastIndexOf("/");
		if (index < 0) {
			index = filePath.lastIndexOf("\\");
		}
		if (index < 0) {
			return filePath;
		} else if ((index + 1) >= filePath.length()) {
			return "";
		}
		String fileName = filePath.substring(index + 1, filePath.length());
		return fileName;
	}

	/**
	 * 返回文件的扩展名 不包括"."
	 * 
	 * @param fileName
	 * @return abc.jpg=jpg
	 */
	public static String getFileExt(String fileName) {
		if (StrUtil.isBlank(fileName)) {
			return "";
		}
		fileName = fileName.trim();
		int index = fileName.lastIndexOf(".");
		if (index < 0 || fileName.endsWith(".")) {
			return "";
		}
		return fileName.substring(index + 1, fileName.length());
	}

	/**
	 * 返回文件名 不包括扩展名
	 * 
	 * @param fileNameFull
	 * @return
	 */
	public static String getFileNameNoExt(String fileNameFull) {
		if (fileNameFull == null)
			return "";
		String fileName = getFileName(fileNameFull);
		String fileExt = getFileExt(fileName);
		if ("".equals(fileExt)) {
			return fileName;
		} else {
			return fileName.substring(0, fileName.length() - fileExt.length() - 1);
		}
	}

	public static boolean isAllowedPath(String filePath, String[] pathAllowed) {
		if (StrUtil.isBlank(filePath)) {
			throw new NullPointerException("filePath == null || \"\".equals(filePath)");
		}
		if (pathAllowed == null || pathAllowed.length < 1) {
			return true;
		}
		int pathAllowedLength = pathAllowed.length;
		filePath = toWebPath(filePath);
		String pathAllowedTmp = "";
		for (int i = 0; i < pathAllowedLength; i++) {
			pathAllowedTmp = toWebPath(pathAllowed[i]);

			if (StrUtil.isBlank(pathAllowedTmp)) {
				throw new NullPointerException("pathAllowed[" + i + "] == null || \"\".equals(pathAllowed[" + i + "])");
			}
			if (filePath.indexOf(pathAllowedTmp) > -1 || pathAllowedTmp.indexOf(filePath) > -1) {// 注意这个
				return true;
			}

			if (RegexUtil.regexMatcher(pathAllowed[i], filePath)) {
				// 正则表达式不要转换
				return true;
			}
		}
		return false;
	}

	public static boolean isForbidPath(String filePath, String[] pathForbid) {
		if (filePath == null || "".equals(filePath)) {
			throw new NullPointerException("filePath == null || \"\".equals(filePath)");
		}
		if (pathForbid == null || pathForbid.length < 1) {
			return false;
		}
		int pathForbidLength = pathForbid.length;
		filePath = toWebPath(filePath);
		String pathForbidTmp = "";
		for (int i = 0; i < pathForbidLength; i++) {
			pathForbidTmp = toWebPath(pathForbid[i]);
			if (pathForbid[i] == null || "".equals(pathForbid[i])) {
				throw new NullPointerException("pathForbid[" + i + "] == null || \"\".equals(pathForbid[" + i + "])");
			}

			if (filePath.indexOf(pathForbidTmp) > -1) {// 注意这个
				return true;
			}

			if (RegexUtil.regexMatcher(pathForbid[i], filePath)) {
				// 正则表达式不要转换
				return true;
			}
		}
		return false;
	}

	public static boolean isFileType(String fileName, String[] typeArr) {
		if (typeArr == null || typeArr.length < 1) {
			throw new NullPointerException("typeArr == null || typeArr.length < 1");
		}
		StringBuilder typesSb = new StringBuilder();
		int typesArrLength = typeArr.length;
		for (int i = 0; i < typesArrLength; i++) {
			typesSb.append(typeArr[i]).append(",");
		}
		return isFileType(fileName, typesSb.toString());
	}

	public static boolean isImg(String fileName) {
		return isFileType(fileName, "jpg,jpeg,gif,png,bmp");
	}

	/**
	 * 判断文件格式
	 * 
	 * @param fileName
	 * @param types
	 *            如：jpg,gif,png,txt 或 jpg|gif|png|txt 或 jpg^gif^png^txt 或
	 *            .jpg,.gif,.png,.txt 或 .jpg|.gif|.png|.txt 或
	 *            .jpg^.gif^.png^.txt
	 * @return
	 */
	public static boolean isFileType(String fileName, String types) {
		if (types == null || types.equals("")) {
			throw new NullPointerException("types == null || types.equals(\"\")");
		}
		String fileExt = getFileExt(fileName);
		if (fileExt.equals("")) {
			return false;
		}
		types = types.replaceAll("\\.", "");

		if (types.indexOf(",") > -1) {
			types = "," + types.toLowerCase() + ",";
			fileExt = "," + fileExt.toLowerCase() + ",";
		} else if (types.indexOf("|") > -1) {
			types = "|" + types.toLowerCase() + "|";
			fileExt = "|" + fileExt.toLowerCase() + "|";
		} else if (types.indexOf("，") > -1) {
			types = "，" + types.toLowerCase() + "，";
			fileExt = "，" + fileExt.toLowerCase() + "，";
		} else if (types.indexOf("^") > -1) {
			types = "^" + types.toLowerCase() + "^";
			fileExt = "^" + fileExt.toLowerCase() + "^";
		} else {
			types = "," + types.toLowerCase() + ",";
			fileExt = "," + fileExt.toLowerCase() + ",";
		}
		if (types.indexOf(fileExt) > -1) {
			return true;
		} else {
			return false;
		}
	}

	public static String formatFileSize(long bytes, String pattern) {
		if (bytes < 1024) {
			return bytes + FILE_SIZE_UNIT_ARR[0];
		}
		int index = 0;
		double result = bytes / Math.pow(1024, (index = (int) (Math.log(bytes) / Math.log(1024))));
		return MathUtil.formatDouble(result, pattern) + FILE_SIZE_UNIT_ARR[index];
	}

	/**
	 * 获取文件路径
	 * 
	 * @author Kaifang Wu
	 * @date 2015年5月21日 下午3:56:51
	 * @param name
	 * @return
	 */
	public static String getFilePath(String name) {
	    return getFilePath(name, null);
	}
	
	/**
	 * 获取文件路径
	 * 
	 * @author Kaifang Wu
	 * @date 2015年5月21日 下午4:28:19
	 * @param name
	 * @param loader
	 * @return
	 */
	public static String getFilePath(String name, ClassLoader loader) {
		if (null == name) {
			name = ""; 
		}
	    String path = "";
		try {
			URL url = null;
			if (null != loader) {
				url = loader.getResource(name);
			} else {
				url = FileUtil.class.getResource(name);
			}
			path = new File(url.toURI()).getCanonicalPath();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	    return path;
	}

	/**
	 * @param fileName 文件名 如qwerqwe.5345.apk,wqrqw_231231.apk
	 * 文件名过滤:过滤掉文件名"."号或"_"后面的内容(扩展名除外)
	 */
	public static String fileNameFilter(String fileName) {
		if(StrUtil.isBlank(fileName)) {
			return "";
		}
		String fileExt = "";
		String filePre = "";
		int index = fileName.lastIndexOf(".");
		if(index > 0) {
			fileExt = fileName.substring(index);
			filePre = fileName.substring(0, index);
		}
		//过滤掉"."或"_"号的后半部分
		index = filePre.indexOf(".");
		int index1 = filePre.indexOf("_");
		if(index > 0) {
			filePre = filePre.substring(0, index);
		}
		else if(index1 > 0) {
			filePre = filePre.substring(0, index1);
		}
		return filePre + fileExt;
	}
	public static String getStandardFileName(String fileName) {
		return fileName.replaceAll("[?\"\'\\*\\@\\#\\%\\`\\~\\^\\& ]", "");
	}
	public static void main(String[] args) {
		try {
			createFile("D:/aaa/bbb/ccc/1.txt","asdfasdfasd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}