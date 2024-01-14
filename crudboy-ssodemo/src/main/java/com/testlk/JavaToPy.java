//package cn.iocoder.yudao.module;// ===========================  抽象类 包含调用Python以及获取返回值的方法！==================
//import org.python.core.PySyntaxError;
//import org.python.util.PythonInterpreter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.util.Properties;
//
//public abstract class JavaToPy {
//    public static void getFile(String filename) throws IOException, InterruptedException {
//        String param =filename;
//        String[] command = new String[] {"python","app.py"};
////        String[] command = new String[] {"echo", "a1234", "echo ", param};
//        String[] envp = new String[]{};
//        File dir = new File("D:\\d\\git\\gpt\\chatgpt-on-wechat-1.5.2\\");
//        final Process process = Runtime.getRuntime().exec(command, envp, dir);
//        printMessage(process.getErrorStream());
//        printMessage(process.getInputStream());
//    }
//
//    private static void printMessage(final InputStream input) {
//        new Thread(new Runnable() {
//            public void run() {
//                Reader reader = new InputStreamReader(input);
//                BufferedReader bf = new BufferedReader(reader);
//                String line = null;
//                try {
//                    while((line=bf.readLine())!=null) {
//
//                        System.out.println(line); // 返回值
//                    }
//                    reader.close();
//                    bf.close();
//                } catch (IOException e) {
//
//                    e.printStackTrace();
//
//                }
//
//            }
//        }).start();
//    }
//}
//
//// =============================== 主函数 调用上面抽象类的方法===========================
//class Main {
//    static Logger LOGGER = LoggerFactory.getLogger(Main.class);
//
//    public static void main2(String[] args) throws IOException, InterruptedException {
//        JavaToPy.getFile("Hello Java! I am Python"); // 我给 python传递的参数！
//    }
//
//    public static void main(String[] args) throws IOException {
//        Properties props = new Properties();
//
//        File dir = new File("D:\\d\\git\\gpt\\chatgpt-on-wechat-1.5.2\\");
//        props.put("python.home", "D:\\lk\\tool\\py\\");
//        props.put("python.console.encoding", "UTF-8");
//        props.put("python.security.respectJavaAccessibility", "false");
//        props.put("python.import.site", "false");
//        Properties preprops = System.getProperties();
//        PythonInterpreter.initialize(preprops, props, new String[0]);
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("print('hello')");
//
//        try {
//            // Your Jython code here
//            String filename = "D:\\d\\git\\gpt\\chatgpt-on-wechat-1.5.2\\testJava.py";
//            filename = "D:\\d\\git\\gpt\\chatgpt-on-wechat-1.5.2\\app.py";
//            interpreter.execfile(filename);
//        } catch (PySyntaxError e) {
//            // 10:04:19.074 [main] ERROR cn.iocoder.yudao.module.Main - Syntax error in Python code: SyntaxError: mismatched input 'from' expecting NEWLINE (os.py, line 419)
//            LOGGER.error("Syntax error in Python code: " + e.getMessage());
//        } catch (Exception e) {
//            LOGGER.error("Unexpected error: " + e.getMessage());
//        }
//    }
//
////————————————————
////    版权声明：本文为CSDN博主「stepondust」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
////    原文链接：https://blog.csdn.net/qq_44009891/article/details/102333621
//
//}
