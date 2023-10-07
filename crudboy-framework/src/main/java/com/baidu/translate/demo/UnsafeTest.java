//package com.baidu.translate.demo;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
//public final class UnsafeTest {
//  // 单例对象
//  private static Unsafe reflectGetUnsafe() {
//    try {
//      Field field = Unsafe.class.getDeclaredField("theUnsafe");
//      field.setAccessible(true);
//      return (Unsafe) field.get(null);
//    } catch (Exception e) {
//      System.out.println((e.getMessage() + e));
//      return null;
//    }
//  }
//
//  public static void main(String[] args) {
//     reflectGetUnsafe();
//  }
//}