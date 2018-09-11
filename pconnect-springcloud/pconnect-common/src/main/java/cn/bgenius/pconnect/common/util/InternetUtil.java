package cn.bgenius.pconnect.common.util;

import java.util.concurrent.ConcurrentHashMap;

public class InternetUtil {
	// private static final Logger LOG =
	// LoggerFactory.getLogger(InternetUtil.class);

	private InternetUtil() {
	}

	private static final ConcurrentHashMap<String, String> httpHeadContentTypeMap = new ConcurrentHashMap<String, String>();

	/**
	 * 尽量获取最合适的扩展名
	 * 
	 * @param mimeType
	 * @return fileExt + "." + fileExtArr[indexBest]
	 */
	public static String getFileExtBest(String mimeType) {
		String fileExt = getFileExt(mimeType);
		if (fileExt == null) {
			return null;
		}
		String[] fileExtArr = fileExt.split("\\,");
		int fileExtArrLength = fileExtArr != null ? fileExtArr.length : 0;
		if (fileExtArrLength <= 1) {
			return fileExtArr[0];
		}
		int indexBest = 0;
		for (int i = 0; i < fileExtArrLength; i++) {
			if (mimeType.indexOf(fileExtArr[i]) > -1) {
				indexBest = i;
				break;
			}
		}
		return fileExt + "." + fileExtArr[indexBest];
	}

	/**
	 * 多个扩展名使用","分隔开，一个mimeType对应多个fileExt
	 * 
	 * @param mimeType
	 * @return 扩展名，没有点开头
	 */
	public static String getFileExt(String mimeType) {
		if (StrUtil.isBlank(mimeType)) {
			return null;
		}
		int index = mimeType.indexOf(";");
		if (index > -1) {
			mimeType = mimeType.substring(0, index);
		}

		mimeType = mimeType.toLowerCase();
		index = mimeType.indexOf(";");
		if (index > -1) {
			mimeType = mimeType.substring(0, index);
		}
		if (mimeType.indexOf("javascript") > -1) {// js特殊处理
			return "js";
		}
		return httpHeadContentTypeMap.get(mimeType);
	}

	/**
	 * jsp中有对应的方法application.getMimeType()
	 * 
	 * @param fileExt
	 * @return
	 */
	public static String getMimeType(String fileExt) {
		if (StrUtil.isBlank(fileExt)) {
			return null;
		}
		int index = fileExt.lastIndexOf('.');
		if (index > -1) {
			fileExt = fileExt.substring(index + 1, fileExt.length());
		}
		return httpHeadContentTypeMap.get(fileExt.toLowerCase());
	}

	public static String getCharset(String contentType) {
		if (StrUtil.isBlank(contentType)) {
			return null;
		}
		int indexStart = contentType.toLowerCase().indexOf("charset");
		if (indexStart < 0) {
			return null;
		}

		int indexEnd = contentType.toLowerCase().indexOf(";", indexStart);
		if (indexEnd < 0) {
			indexEnd = contentType.length();
		}
		String remoteCharset = contentType.substring(indexStart + 7, indexEnd);
		remoteCharset = remoteCharset.replaceAll("\\=", "").trim();
		remoteCharset = "".equals(remoteCharset) ? null : remoteCharset;
		return remoteCharset;
	}

	public static String getFileNameByContentDisposition(String contentDisposition) {
		String fileNameNoExt = "";
		if (StrUtil.isBlank(contentDisposition)) {
			return fileNameNoExt;
		}
		String contentDispositionFileName = contentDisposition;
		int index = contentDispositionFileName.indexOf("filename");
		if (index < 0) {
			return fileNameNoExt;
		}
		index = contentDispositionFileName.indexOf("=", index);
		if (index < 0) {
			return fileNameNoExt;
		}
		contentDispositionFileName = contentDispositionFileName.substring(index + 1).trim();
		// 去掉前后的双引号
		contentDispositionFileName = contentDispositionFileName.replaceAll("\"", "");
		index = contentDispositionFileName.lastIndexOf("'");
		if (index > -1) {
			contentDispositionFileName = contentDispositionFileName.substring(index + 1);
		}
		// 判断是否经过转码的
		if (contentDispositionFileName.indexOf("%") > -1) {
			fileNameNoExt = StrUtil.urlDecode(contentDispositionFileName, "UTF-8");
		}
		if (fileNameNoExt.indexOf("?") > -1) {
			fileNameNoExt = StrUtil.urlDecode(contentDispositionFileName, "GBK");
		}
		if (fileNameNoExt.indexOf("?") > -1) {// 最终还是乱码,就使用原本的
			fileNameNoExt = contentDispositionFileName;
		}
		return fileNameNoExt.trim();
	}

	// MIMEType FileExtension
	static {
		// text/html;charset=UTF-8
		// text/javascript
		// text/css

		httpHeadContentTypeMap.put("*", "application/octet-stream");
		// httpHeadContentTypeMap.put("application/octet-stream",
		// "*,bin,class,dms,exe,lha,lzh");
		httpHeadContentTypeMap.put("application/octet-stream", "bin,class,dms,exe,lha,lzh");

		httpHeadContentTypeMap.put("323", "text/h323");
		httpHeadContentTypeMap.put("text/h323", "323");

		httpHeadContentTypeMap.put("acx", "application/internet-property-stream");
		httpHeadContentTypeMap.put("application/internet-property-stream", "acx");

		httpHeadContentTypeMap.put("ai", "application/postscript");
		httpHeadContentTypeMap.put("application/postscript", "ai,eps,ps");

		httpHeadContentTypeMap.put("aif", "audio/x-aiff");
		httpHeadContentTypeMap.put("audio/x-aiff", "aif,aifc,aiff");

		httpHeadContentTypeMap.put("aifc", "audio/x-aiff");

		httpHeadContentTypeMap.put("aiff", "audio/x-aiff");

		httpHeadContentTypeMap.put("asf", "video/x-ms-asf");
		httpHeadContentTypeMap.put("video/x-ms-asf", "asf,asr,asx");

		httpHeadContentTypeMap.put("asr", "video/x-ms-asf");

		httpHeadContentTypeMap.put("asx", "video/x-ms-asf");

		httpHeadContentTypeMap.put("au", "audio/basic");
		httpHeadContentTypeMap.put("audio/basic", "au,snd");

		httpHeadContentTypeMap.put("avi", "video/x-msvideo");
		httpHeadContentTypeMap.put("video/x-msvideo", "avi");

		httpHeadContentTypeMap.put("axs", "application/olescript");
		httpHeadContentTypeMap.put("application/olescript", "axs");

		httpHeadContentTypeMap.put("bas", "text/plain");
		httpHeadContentTypeMap.put("text/plain", "bas,c,h,txt");

		httpHeadContentTypeMap.put("bcpio", "application/x-bcpio");
		httpHeadContentTypeMap.put("application/x-bcpio", "bcpio");

		httpHeadContentTypeMap.put("bin", "application/octet-stream");

		httpHeadContentTypeMap.put("bmp", "image/bmp");
		httpHeadContentTypeMap.put("image/bmp", "bmp");

		httpHeadContentTypeMap.put("c", "text/plain");

		httpHeadContentTypeMap.put("cat", "application/vnd.ms-pkiseccat");
		httpHeadContentTypeMap.put("application/vnd.ms-pkiseccat", "cat");

		httpHeadContentTypeMap.put("cdf", "application/x-netcdf");
		httpHeadContentTypeMap.put("application/x-netcdf", "cdf,nc");

		httpHeadContentTypeMap.put("cdf", "application/x-netcdf");

		httpHeadContentTypeMap.put("cer", "application/x-x509-ca-cert");
		httpHeadContentTypeMap.put("application/x-x509-ca-cert", "cer,crt,der");

		httpHeadContentTypeMap.put("class", "application/octet-stream");

		httpHeadContentTypeMap.put("clp", "application/x-msclip");
		httpHeadContentTypeMap.put("application/x-msclip", "clp");

		httpHeadContentTypeMap.put("cmx", "image/x-cmx");
		httpHeadContentTypeMap.put("image/x-cmx", "cmx");

		httpHeadContentTypeMap.put("cod", "image/cis-cod");
		httpHeadContentTypeMap.put("image/cis-cod", "cod");

		httpHeadContentTypeMap.put("cpio", "application/x-cpio");
		httpHeadContentTypeMap.put("application/x-cpio", "cpio");

		httpHeadContentTypeMap.put("crd", "application/x-mscardfile");
		httpHeadContentTypeMap.put("application/x-mscardfile", "crd");

		httpHeadContentTypeMap.put("crl", "application/pkix-crl");
		httpHeadContentTypeMap.put("application/pkix-crl", "crl");

		httpHeadContentTypeMap.put("crt", "application/x-x509-ca-cert");

		httpHeadContentTypeMap.put("csh", "application/x-csh");
		httpHeadContentTypeMap.put("application/x-csh", "csh");

		httpHeadContentTypeMap.put("css", "text/css");
		httpHeadContentTypeMap.put("text/css", "css");

		httpHeadContentTypeMap.put("dcr", "application/x-director");
		httpHeadContentTypeMap.put("application/x-director", "dcr,dir,dxr");

		httpHeadContentTypeMap.put("der", "application/x-x509-ca-cert");

		httpHeadContentTypeMap.put("dir", "application/x-director");

		httpHeadContentTypeMap.put("dll", "application/x-msdownload");
		httpHeadContentTypeMap.put("application/x-msdownload", "dll");

		httpHeadContentTypeMap.put("dms", "application/octet-stream");

		httpHeadContentTypeMap.put("doc", "application/msword");
		httpHeadContentTypeMap.put("application/msword", "doc,dot");

		httpHeadContentTypeMap.put("dot", "application/msword");

		httpHeadContentTypeMap.put("dvi", "application/x-dvi");
		httpHeadContentTypeMap.put("application/x-dvi", "dvi");

		httpHeadContentTypeMap.put("dxr", "application/x-director");

		httpHeadContentTypeMap.put("eps", "application/postscript");

		httpHeadContentTypeMap.put("etx", "text/x-setext");
		httpHeadContentTypeMap.put("text/x-setext", "etx");

		httpHeadContentTypeMap.put("evy", "application/envoy");
		httpHeadContentTypeMap.put("application/envoy", "evy");

		httpHeadContentTypeMap.put("exe", "application/octet-stream");

		httpHeadContentTypeMap.put("fif", "application/fractals");
		httpHeadContentTypeMap.put("application/fractals", "fif");

		httpHeadContentTypeMap.put("flr", "x-world/x-vrml");
		httpHeadContentTypeMap.put("x-world/x-vrml", "flr,vrml,wrl,wrz,xaf,xof");

		httpHeadContentTypeMap.put("gif", "image/gif");
		httpHeadContentTypeMap.put("image/gif", "gif");

		httpHeadContentTypeMap.put("gtar", "application/x-gtar");
		httpHeadContentTypeMap.put("application/x-gtar", "gtar");

		httpHeadContentTypeMap.put("gz", "application/x-gzip");
		httpHeadContentTypeMap.put("application/x-gzip", "gz");

		httpHeadContentTypeMap.put("h", "text/plain");

		httpHeadContentTypeMap.put("hdf", "application/x-hdf");
		httpHeadContentTypeMap.put("application/x-hdf", "hdf");

		httpHeadContentTypeMap.put("hlp", "application/winhlp");
		httpHeadContentTypeMap.put("application/winhlp", "hlp");

		httpHeadContentTypeMap.put("hqx", "application/mac-binhex40");
		httpHeadContentTypeMap.put("application/mac-binhex40", "hqx");

		httpHeadContentTypeMap.put("hta", "application/hta");
		httpHeadContentTypeMap.put("application/hta", "hta");

		httpHeadContentTypeMap.put("htc", "text/x-component");
		httpHeadContentTypeMap.put("text/x-component", "htc");

		httpHeadContentTypeMap.put("htm", "text/html");
		httpHeadContentTypeMap.put("text/html", "htm,html,stm");

		httpHeadContentTypeMap.put("html", "text/html");

		httpHeadContentTypeMap.put("htt", "text/webviewhtml");
		httpHeadContentTypeMap.put("text/webviewhtml", "htt");

		httpHeadContentTypeMap.put("ico", "image/x-icon");
		httpHeadContentTypeMap.put("image/x-icon", "ico");

		httpHeadContentTypeMap.put("ief", "image/ief");
		httpHeadContentTypeMap.put("image/ief", "ief");

		httpHeadContentTypeMap.put("iii", "application/x-iphone");
		httpHeadContentTypeMap.put("application/x-iphone", "iii");

		httpHeadContentTypeMap.put("ins", "application/x-internet-signup");
		httpHeadContentTypeMap.put("application/x-internet-signup", "ins,isp");

		httpHeadContentTypeMap.put("isp", "application/x-internet-signup");

		httpHeadContentTypeMap.put("jfif", "image/pipeg");
		httpHeadContentTypeMap.put("image/pipeg", "jfif");

		httpHeadContentTypeMap.put("jpe", "image/jpeg");
		httpHeadContentTypeMap.put("image/jpeg", "jpe,jpeg,jpg");

		httpHeadContentTypeMap.put("jpeg", "image/jpeg");

		httpHeadContentTypeMap.put("jpg", "image/jpeg");

		// www.klipdas.com/blog/?p=mime-type-javascript-text/javascript%2Capplication/javascript%2C-and-appliation/x-javascript
		/*
		 * The traditional MIME type for JavaScript programs is
		 * "text/javascript". Another type that has been used is
		 * "application/x-javascript" (the "x" prefix indicates that it is an
		 * experimental, nonstandard type). RFC 4329 standardized the
		 * "text/javascript" type because it is in common use. However, because
		 * JavaScript programs are not really text documents, it marks this type
		 * as obsolete and recommends "application/javascript" (without the
		 * "x-") instead. At the time of this writing, "application/javascript"
		 * is not well supported, however. That might be the reason why
		 * "application/x-javascript" is used by a lot of webpages.
		 * 
		 * 大致意思是：传统的javascript程序的MIME类型是“text/javascript”，其他使用的还有
		 * "application/x-javascript"
		 * （x前缀表示这是实验性的，不是标准的类型），RFC4329规定了“text/javascript
		 * ”类型，因为它普遍被使用。然而，javascript程序并不是真正的文本文件
		 * ，这就表示这个类型已经意味着过时了，而推荐使用"application/javascript"
		 * （去除x前缀）。然而，在写程序的时候，"application/javascript"
		 * 没有很好的支持。这也行就是"application/x-javascript"被使用在很多网页中的原因
		 * 
		 * application/x-javascript是后端动态生成后response返回的脚本内容，不是通过script标签引入的。
		 * 在jsp,servlet中设置contentType="application/x-javascript;
		 * charset=UTF-8"后可以动态生成js角本，可以应用在比如动态菜单，可定制的前端的模块等等
		 */
		httpHeadContentTypeMap.put("js", "text/javascript");// application/x-javascript,
		httpHeadContentTypeMap.put("application/x-javascript", "js");
		httpHeadContentTypeMap.put("text/javascript", "js");
		httpHeadContentTypeMap.put("application/javascript", "js");

		httpHeadContentTypeMap.put("latex", "application/x-latex");
		httpHeadContentTypeMap.put("application/x-latex", "latex");

		httpHeadContentTypeMap.put("lha", "application/octet-stream");

		httpHeadContentTypeMap.put("lsf", "video/x-la-asf");
		httpHeadContentTypeMap.put("video/x-la-asf", "lsf,lsx");

		httpHeadContentTypeMap.put("lsx", "video/x-la-asf");

		httpHeadContentTypeMap.put("lzh", "application/octet-stream");

		httpHeadContentTypeMap.put("m13", "application/x-msmediaview");
		httpHeadContentTypeMap.put("application/x-msmediaview", "m13,m14,mvb");

		httpHeadContentTypeMap.put("m14", "application/x-msmediaview");

		httpHeadContentTypeMap.put("m3u", "audio/x-mpegurl");
		httpHeadContentTypeMap.put("audio/x-mpegurl", "m3u");

		httpHeadContentTypeMap.put("man", "application/x-troff-man");
		httpHeadContentTypeMap.put("application/x-troff-man", "man");

		httpHeadContentTypeMap.put("mdb", "application/x-msaccess");
		httpHeadContentTypeMap.put("application/x-msaccess", "mdb");

		httpHeadContentTypeMap.put("me", "application/x-troff-me");
		httpHeadContentTypeMap.put("application/x-troff-me", "me");

		httpHeadContentTypeMap.put("mht", "message/rfc822");
		httpHeadContentTypeMap.put("message/rfc822", "mht,mhtml,nws");

		httpHeadContentTypeMap.put("mhtml", "message/rfc822");

		httpHeadContentTypeMap.put("mid", "audio/mid");
		httpHeadContentTypeMap.put("audio/mid", "mid,rmi");

		httpHeadContentTypeMap.put("mny", "application/x-msmoney");
		httpHeadContentTypeMap.put("application/x-msmoney", "mny");

		httpHeadContentTypeMap.put("mov", "video/quicktime");
		httpHeadContentTypeMap.put("video/quicktime", "mov,qt");

		httpHeadContentTypeMap.put("movie", "video/x-sgi-movie");
		httpHeadContentTypeMap.put("video/x-sgi-movie", "movie");

		httpHeadContentTypeMap.put("mp2", "video/mpeg");
		httpHeadContentTypeMap.put("video/mpeg", "mp2,mpa,mpe,mpeg,mpg,mpv2");

		httpHeadContentTypeMap.put("mp3", "audio/mpeg");
		httpHeadContentTypeMap.put("audio/mpeg", "mp3");

		httpHeadContentTypeMap.put("mpa", "video/mpeg");

		httpHeadContentTypeMap.put("mpe", "video/mpeg");

		httpHeadContentTypeMap.put("mpeg", "video/mpeg");

		httpHeadContentTypeMap.put("mpg", "video/mpeg");

		httpHeadContentTypeMap.put("mpp", "application/vnd.ms-project");
		httpHeadContentTypeMap.put("application/vnd.ms-project", "mpp");

		httpHeadContentTypeMap.put("mpv2", "video/mpeg");

		httpHeadContentTypeMap.put("ms", "application/x-troff-ms");
		httpHeadContentTypeMap.put("application/x-troff-ms", "ms");

		httpHeadContentTypeMap.put("msg", "application/vnd.ms-outlook");
		httpHeadContentTypeMap.put("application/vnd.ms-outlook", "msg");

		httpHeadContentTypeMap.put("mvb", "application/x-msmediaview");

		httpHeadContentTypeMap.put("nc", "application/x-netcdf");

		httpHeadContentTypeMap.put("nws", "message/rfc822");

		httpHeadContentTypeMap.put("oda", "application/oda");
		httpHeadContentTypeMap.put("application/oda", "oda");

		httpHeadContentTypeMap.put("p10", "application/pkcs10");
		httpHeadContentTypeMap.put("application/pkcs10", "p10");

		httpHeadContentTypeMap.put("p12", "application/x-pkcs12");
		httpHeadContentTypeMap.put("application/x-pkcs12", "p12,pfx");

		httpHeadContentTypeMap.put("p7b", "application/x-pkcs7-certificates");
		httpHeadContentTypeMap.put("application/x-pkcs7-certificates", "p7b,spc");

		httpHeadContentTypeMap.put("p7c", "application/x-pkcs7-mime");
		httpHeadContentTypeMap.put("application/x-pkcs7-mime", "p7c,p7m");

		httpHeadContentTypeMap.put("p7m", "application/x-pkcs7-mime");

		httpHeadContentTypeMap.put("p7r", "application/x-pkcs7-certreqresp");
		httpHeadContentTypeMap.put("application/x-pkcs7-certreqresp", "p7r");

		httpHeadContentTypeMap.put("p7s", "application/x-pkcs7-signature");
		httpHeadContentTypeMap.put("application/x-pkcs7-signature", "p7s");

		httpHeadContentTypeMap.put("pbm", "image/x-portable-bitmap");
		httpHeadContentTypeMap.put("image/x-portable-bitmap", "pbm");

		httpHeadContentTypeMap.put("pdf", "application/pdf");
		httpHeadContentTypeMap.put("application/pdf", "pdf");

		httpHeadContentTypeMap.put("pfx", "application/x-pkcs12");

		httpHeadContentTypeMap.put("pgm", "image/x-portable-graymap");
		httpHeadContentTypeMap.put("image/x-portable-graymap", "pgm");

		httpHeadContentTypeMap.put("pko", "application/ynd.ms-pkipko");
		httpHeadContentTypeMap.put("application/ynd.ms-pkipko", "pko");

		httpHeadContentTypeMap.put("pma", "application/x-perfmon");
		httpHeadContentTypeMap.put("application/x-perfmon", "pma,pmc,pml,pmr,pmw");

		httpHeadContentTypeMap.put("pmc", "application/x-perfmon");

		httpHeadContentTypeMap.put("pml", "application/x-perfmon");

		httpHeadContentTypeMap.put("pmr", "application/x-perfmon");

		httpHeadContentTypeMap.put("pmw", "application/x-perfmon");

		httpHeadContentTypeMap.put("pnm", "image/x-portable-anymap");
		httpHeadContentTypeMap.put("image/x-portable-anymap", "pnm");

		httpHeadContentTypeMap.put("pot", "application/vnd.ms-powerpoint");
		httpHeadContentTypeMap.put("application/vnd.ms-powerpoint", "pot,pps,ppt");

		httpHeadContentTypeMap.put("ppm", "image/x-portable-pixmap");
		httpHeadContentTypeMap.put("image/x-portable-pixmap", "ppm");

		httpHeadContentTypeMap.put("pps", "application/vnd.ms-powerpoint");

		httpHeadContentTypeMap.put("ppt", "application/vnd.ms-powerpoint");

		httpHeadContentTypeMap.put("prf", "application/pics-rules");
		httpHeadContentTypeMap.put("application/pics-rules", "prf");

		httpHeadContentTypeMap.put("ps", "application/postscript");

		httpHeadContentTypeMap.put("pub", "application/x-mspublisher");
		httpHeadContentTypeMap.put("application/x-mspublisher", "pub");

		httpHeadContentTypeMap.put("qt", "video/quicktime");

		httpHeadContentTypeMap.put("ra", "audio/x-pn-realaudio");
		httpHeadContentTypeMap.put("audio/x-pn-realaudio", "ra,ram");

		httpHeadContentTypeMap.put("ram", "audio/x-pn-realaudio");

		httpHeadContentTypeMap.put("ras", "image/x-cmu-raster");
		httpHeadContentTypeMap.put("image/x-cmu-raster", "ras");

		httpHeadContentTypeMap.put("rgb", "image/x-rgb");
		httpHeadContentTypeMap.put("image/x-rgb", "rgb");

		httpHeadContentTypeMap.put("rmi", "audio/mid");

		httpHeadContentTypeMap.put("roff", "application/x-troff");
		httpHeadContentTypeMap.put("application/x-troff", "roff,t,tr");

		httpHeadContentTypeMap.put("rtf", "application/rtf");
		httpHeadContentTypeMap.put("application/rtf", "rtf");

		httpHeadContentTypeMap.put("rtx", "text/richtext");
		httpHeadContentTypeMap.put("text/richtext", "rtx");

		httpHeadContentTypeMap.put("scd", "application/x-msschedule");
		httpHeadContentTypeMap.put("application/x-msschedule", "scd");

		httpHeadContentTypeMap.put("sct", "text/scriptlet");
		httpHeadContentTypeMap.put("text/scriptlet", "sct");

		httpHeadContentTypeMap.put("setpay", "application/set-payment-initiation");
		httpHeadContentTypeMap.put("application/set-payment-initiation", "setpay");

		httpHeadContentTypeMap.put("setreg", "application/set-registration-initiation");
		httpHeadContentTypeMap.put("application/set-registration-initiation", "setreg");

		httpHeadContentTypeMap.put("sh", "application/x-sh");
		httpHeadContentTypeMap.put("application/x-sh", "sh");

		httpHeadContentTypeMap.put("shar", "application/x-shar");
		httpHeadContentTypeMap.put("application/x-shar", "shar");

		httpHeadContentTypeMap.put("sit", "application/x-stuffit");
		httpHeadContentTypeMap.put("application/x-stuffit", "sit");

		httpHeadContentTypeMap.put("snd", "audio/basic");

		httpHeadContentTypeMap.put("spc", "application/x-pkcs7-certificates");

		httpHeadContentTypeMap.put("spl", "application/futuresplash");
		httpHeadContentTypeMap.put("application/futuresplash", "spl");

		httpHeadContentTypeMap.put("src", "application/x-wais-source");
		httpHeadContentTypeMap.put("application/x-wais-source", "src");

		httpHeadContentTypeMap.put("sst", "application/vnd.ms-pkicertstore");
		httpHeadContentTypeMap.put("application/vnd.ms-pkicertstore", "sst");

		httpHeadContentTypeMap.put("stl", "application/vnd.ms-pkistl");
		httpHeadContentTypeMap.put("application/vnd.ms-pkistl", "stl");

		httpHeadContentTypeMap.put("stm", "text/html");

		httpHeadContentTypeMap.put("sv4cpio", "application/x-sv4cpio");
		httpHeadContentTypeMap.put("application/x-sv4cpio", "sv4cpio");

		httpHeadContentTypeMap.put("sv4crc", "application/x-sv4crc");
		httpHeadContentTypeMap.put("application/x-sv4crc", "sv4crc");

		httpHeadContentTypeMap.put("svg", "image/svg+xml");
		httpHeadContentTypeMap.put("image/svg+xml", "svg");

		httpHeadContentTypeMap.put("swf", "application/x-shockwave-flash");
		httpHeadContentTypeMap.put("application/x-shockwave-flash", "swf");

		httpHeadContentTypeMap.put("t", "application/x-troff");

		httpHeadContentTypeMap.put("tar", "application/x-tar");
		httpHeadContentTypeMap.put("application/x-tar", "tar");

		httpHeadContentTypeMap.put("tcl", "application/x-tcl");
		httpHeadContentTypeMap.put("application/x-tcl", "tcl");

		httpHeadContentTypeMap.put("tex", "application/x-tex");
		httpHeadContentTypeMap.put("application/x-tex", "tex");

		httpHeadContentTypeMap.put("texi", "application/x-texinfo");
		httpHeadContentTypeMap.put("application/x-texinfo", "texi,texinfo");

		httpHeadContentTypeMap.put("texinfo", "application/x-texinfo");

		httpHeadContentTypeMap.put("tgz", "application/x-compressed");
		httpHeadContentTypeMap.put("application/x-compressed", "tgz");

		httpHeadContentTypeMap.put("tif", "image/tiff");
		httpHeadContentTypeMap.put("image/tiff", "tif,tiff");

		httpHeadContentTypeMap.put("tiff", "image/tiff");

		httpHeadContentTypeMap.put("tr", "application/x-troff");

		httpHeadContentTypeMap.put("trm", "application/x-msterminal");
		httpHeadContentTypeMap.put("application/x-msterminal", "trm");

		httpHeadContentTypeMap.put("tsv", "text/tab-separated-values");
		httpHeadContentTypeMap.put("text/tab-separated-values", "tsv");

		httpHeadContentTypeMap.put("txt", "text/plain");

		httpHeadContentTypeMap.put("uls", "text/iuls");
		httpHeadContentTypeMap.put("text/iuls", "uls");

		httpHeadContentTypeMap.put("ustar", "application/x-ustar");
		httpHeadContentTypeMap.put("application/x-ustar", "ustar");

		httpHeadContentTypeMap.put("vcf", "text/x-vcard");
		httpHeadContentTypeMap.put("text/x-vcard", "vcf");

		httpHeadContentTypeMap.put("vrml", "x-world/x-vrml");

		httpHeadContentTypeMap.put("wav", "audio/x-wav");
		httpHeadContentTypeMap.put("audio/x-wav", "wav");

		httpHeadContentTypeMap.put("wcm", "application/vnd.ms-works");
		httpHeadContentTypeMap.put("application/vnd.ms-works", "wcm,wdb,wks,wps");

		httpHeadContentTypeMap.put("wdb", "application/vnd.ms-works");

		httpHeadContentTypeMap.put("wks", "application/vnd.ms-works");

		httpHeadContentTypeMap.put("wmf", "application/x-msmetafile");
		httpHeadContentTypeMap.put("application/x-msmetafile", "wmf");

		httpHeadContentTypeMap.put("wps", "application/vnd.ms-works");

		httpHeadContentTypeMap.put("wri", "application/x-mswrite");
		httpHeadContentTypeMap.put("application/x-mswrite", "wri");

		httpHeadContentTypeMap.put("wrl", "x-world/x-vrml");

		httpHeadContentTypeMap.put("wrz", "x-world/x-vrml");

		httpHeadContentTypeMap.put("xaf", "x-world/x-vrml");

		httpHeadContentTypeMap.put("xbm", "image/x-xbitmap");
		httpHeadContentTypeMap.put("image/x-xbitmap", "xbm");

		httpHeadContentTypeMap.put("xla", "application/vnd.ms-excel");
		httpHeadContentTypeMap.put("application/vnd.ms-excel", "xla,xlc,xlm,xls,xlt,xlw");

		httpHeadContentTypeMap.put("xlc", "application/vnd.ms-excel");

		httpHeadContentTypeMap.put("xlm", "application/vnd.ms-excel");

		httpHeadContentTypeMap.put("xls", "application/vnd.ms-excel");

		httpHeadContentTypeMap.put("xlt", "application/vnd.ms-excel");

		httpHeadContentTypeMap.put("xlw", "application/vnd.ms-excel");

		httpHeadContentTypeMap.put("xof", "x-world/x-vrml");

		httpHeadContentTypeMap.put("xpm", "image/x-xpixmap");
		httpHeadContentTypeMap.put("image/x-xpixmap", "xpm");

		httpHeadContentTypeMap.put("xwd", "image/x-xwindowdump");
		httpHeadContentTypeMap.put("image/x-xwindowdump", "xwd");

		httpHeadContentTypeMap.put("z", "application/x-compress");
		httpHeadContentTypeMap.put("application/x-compress", "z");

		httpHeadContentTypeMap.put("zip", "application/zip");
		httpHeadContentTypeMap.put("application/zip", "zip");
	}
}