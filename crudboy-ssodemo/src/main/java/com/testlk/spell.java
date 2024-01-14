package cn.iocoder.yudao.module;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class spell {
 public static void main(String[] args) throws IOException, InterruptedException { 
//  Scanner in = new Scanner(System.in);
//  String str1 = in.next();
  String str1 = "in.next()";
  String exe = "python";
  String command = "D:\\d\\git\\gpt\\chatgpt-on-wechat-1.5.2\\testJava.py";
  // command = "E:\\python\\spell.py";
  String[] cmdArr = new String[] {exe,command,str1};
  Process process = Runtime.getRuntime().exec(cmdArr);
  InputStream is = process.getInputStream();
  DataInputStream dis = new DataInputStream(is);
  String str2 = dis.readLine();
  System.out.println(str2);
  process.waitFor();
 }
}