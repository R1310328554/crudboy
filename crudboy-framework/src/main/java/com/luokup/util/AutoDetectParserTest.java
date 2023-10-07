//package com.testlk.util;
//
//import jdk.internal.org.xml.sax.SAXException;
//import org.apache.tika.Tika;
//import org.apache.tika.config.TikaConfig;
//import org.apache.tika.detect.Detector;
//import org.apache.tika.exception.TikaException;
//import org.apache.tika.metadata.Metadata;
//import org.apache.tika.parser.AutoDetectParser;
//import org.apache.tika.parser.ParseContext;
//import org.apache.tika.parser.Parser;
//import org.apache.tika.sax.BodyContentHandler;
//import org.apache.tika.sax.WriteOutContentHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//
//import java.io.*;
//
//public class AutoDetectParserTest {
//
//
//	@Bean
//	public static Tika tika() throws TikaException, IOException, SAXException, Exception {
//		ResourceLoader resourceLoader = null;
////		Resource resource = resourceLoader.getResource("classpath:tika-config.xml");
////		Resource resource = resourceLoader.getResource("D:\\d\\git\\wx-java-mp-demo-master\\test00999\\src\\main\\resources\\tika-config.xml");
////		InputStream inputStream = resource.getInputStream();
//		InputStream inputStream = new FileInputStream("D:\\d\\git\\wx-java-mp-demo-master\\test00999\\src\\main\\resources\\tika-config.xml");
//
//		TikaConfig config = new TikaConfig(inputStream);
//		Detector detector = config.getDetector();
//		Parser autoDetectParser = new AutoDetectParser(config);
//		return new Tika(detector, autoDetectParser);
//	}
////	版权声明：本文为CSDN博主「不可食用盐」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
////	原文链接：https://blog.csdn.net/qq_43672627/article/details/131553883
//
//	public static void main(String[] args) throws Exception {
//
//		String pathname = "D:\\BaiduNetdiskDownload\\电子线路试卷\\电技(2).doc";
//		pathname = "C:\\Users\\xd\\Documents\\WXWork\\1688851320333114\\Cache\\File\\2019-08\\多多进宝结算逻辑.docx";
//		File file = new File(pathname);
////		String s = tika().parseToString(file);
//		String s = getContent(file);
//
//		System.out.println("s = " + s);
//	}
//
//	public static String getContent(File file) throws IOException, TikaException {
//		Tika tika = new Tika();
//		return  tika.parseToString(file);
//	}
//
//	public static String getMimeType(File file)
//			throws IOException {
//		Tika tika = new Tika();
//		return  tika.detect(file);
//	}
//
//
//	public static void main2(String[] args) {
//		InputStream is = null;
//		try {
//			//创建解析器,使用AutoDetectParser可以自动检测一个最合适的解析器
//			Parser parser  = new AutoDetectParser();
//			String pathname = "D:\\BaiduNetdiskDownload\\电子线路试卷\\电技(2).doc";
//			pathname = "C:\\Users\\xd\\Documents\\WXWork\\1688851320333114\\Cache\\File\\2019-08\\多多进宝结算逻辑.docx";
//			File file = new File(pathname);
//			//指定解析文件中的文档内容
////			 当文件大于100000时，new BodyContentHandler(1024*1024*1024);
////			BodyContentHandler handler = new BodyContentHandler();
////			BodyContentHandler handler = new BodyContentHandler(1024*1024*1024);
//			//WriteOutContentHandler就是用来处理文件大小的
//			BodyContentHandler handler = new BodyContentHandler(new WriteOutContentHandler(1024*1024));
//
//			Metadata metadata =  new  Metadata();
//			//指定最基本的变量信息(即存放一个所使用的解析器对象）
//			ParseContext parsecontext = new ParseContext();
//			is = new FileInputStream(file);
//			//InputStream-----指定文件输入流
//            //ContentHandler--指定要解析文件的哪一个内容,它有一个实现类叫做BodyContentHandler,即专门用来解析文档内容的
//            //Metadata--------指定解析文件时,存放解析出来的元数据的Metadata对象
//            //ParseContext----该对象用于存放一些变量信息,该对象最少也要存放所使用的解析器对象,这也是其存放的最基本的变量信息
//			parser.parse(is, handler, metadata, parsecontext);
//			//打印元数据
//			for(String name:metadata.names()) {
//				System.out.println(name+":===="+metadata.get(name));
//			}
//
//			System.out.println();
//			//打印解析到的文档内容
//			System.out.println(handler.toString());
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (TikaException e) {
//			e.printStackTrace();
//		} catch (org.xml.sax.SAXException e) {
//			throw new RuntimeException(e);
//		} finally{
//			if(is!=null){
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
//
//}