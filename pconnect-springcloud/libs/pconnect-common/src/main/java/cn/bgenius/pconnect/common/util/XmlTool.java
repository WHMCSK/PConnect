package cn.bgenius.pconnect.common.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class XmlTool {
	private Document doc = null;
	InputStream in = null;
	Reader reader = null;

	public XmlTool(String filePath) {
		this(new File(filePath));
	}
	public XmlTool(File file) {
		try {
			in = new FileInputStream(file);
			reader = new InputStreamReader(in, "utf-8");
			SAXReader sax = new SAXReader();
			doc = sax.read(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public XmlTool(InputStream in) {
		try {
			reader = new InputStreamReader(in, "utf-8");
			SAXReader sax = new SAXReader();
			doc = sax.read(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public XmlTool(Reader reader) {
		try {
			SAXReader sax = new SAXReader();
			doc = sax.read(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Element getRootElement() {
		return this.doc.getRootElement();
	}

	public Document getDoc() {
		return this.doc;
	}
}
