package com.luokup.util;

import java.io.File;
import org.apache.tika.Tika;

public class SimpleTextExtractor {
  public static void main(String[] args) throws Exception {
    // Create a Tika instance with the default configuration

    args = new String[]{"D:\\d\\hisun\\cobol\\大丰银行主机应用系统下移项目- doc\\77 工具清单\\convScripts\\bancsTest\\bnpcif01.sjl"};
    Tika tika = new Tika();
    // Parse all given files and print out the extracted text content
    for (String file : args) {
      String text = tika.parseToString(new File(file));
      System.out.print(text);
    }
  }
}