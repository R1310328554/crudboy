package com.testlk.util;

import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;
//import org.springframework.web.servlet.mvc.support.AbstractControllerUrlHandlerMapping;

/**
 * ClassName:MyControllerClassNameHandlerMapping <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:   TODO ADD REASON. <br/>
 * Date:     2015年10月25日 下午5:03:01 <br/>
 * @author   qiyongkang
 * @version  
 * @since    JDK 1.6
 * @see      
 */
public class MyControllerClassNameHandlerMapping extends AbstractUrlHandlerMapping {

    /**
     * 是否区分大小写
     */
    private boolean caseSensitive = false;

    /**
     * ctrl所在包路径的前缀
     */
    private String[] frameworkPackagePrefixs = new String[] {"com.qiyongkang."};

    /**
     * ctrl的类后缀
     */
    private String[] actionClassSuffixs = new String[]{"Ctrl", "Controller"};

    /**
     * ctrl所在包路径的后缀
     */
    private String[] actionPackageSuffixs = new String[] {"ctrl", "controller"};

    /**
     * 构造器
     */
    public MyControllerClassNameHandlerMapping() {
        super();
    }

    @SuppressWarnings("rawtypes")
//    @Override
    protected String[] buildUrlsForHandler(String beanName, Class beanClass) {
        System.out.println("beanName:" + beanName + ",beanClass:" + beanClass.getName());
        //请求路径前缀
        StringBuilder pathMapping = buildPathPrefix(beanClass);
        System.out.println("pathMapping:" + pathMapping);

        //获取类名
        String className = ClassUtils.getShortName(beanClass);
        //请求路径后缀
        String path = getEndWithSuffixPath(className, actionClassSuffixs);
        System.out.println("path:" + path);

        if (path.length() > 0) {
            if (this.caseSensitive) {
                pathMapping.append(path.substring(0, 1).toLowerCase()).append(path.substring(1));
            }
            else {
                pathMapping.append(path.toLowerCase());
            }
        }

        //最终转换后的请求url路径
        String[] finalPath = null;
        if (isMultiActionControllerType(beanClass)) {
            finalPath = new String[] {pathMapping.toString(), pathMapping.toString() + "/*"};
        }
        else {
            finalPath = new String[] {pathMapping.toString() + "*"};
        }
        for (String str : finalPath) {
            System.out.println(str);
        }
        return finalPath;
    }

    private boolean isMultiActionControllerType(Class beanClass) {
        return false;
    }

    /**
     * 
     * buildPathPrefix:生成请求前缀. <br/>
     *
     * @author qiyongkang
     * @param beanClass
     * @return
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    private StringBuilder buildPathPrefix(Class beanClass) {
        StringBuilder pathMapping = new StringBuilder();
        pathMapping.append("/");
        //在这里加入自己的URL映射逻辑规范
        pathMapping.append(buildPathByBeanClass(beanClass, frameworkPackagePrefixs, actionPackageSuffixs, caseSensitive));
        return pathMapping;
    }

    /**
     * 
     * buildPathByBeanClass:根据类名称和包名称来组成自定义的URL映射. <br/>
     *
     * @author qiyongkang
     * @param beanClass
     * @param frameworkPackagePrefixs
     * @param actionPackageSuffixs
     * @param caseSensitive
     * @return
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    private String buildPathByBeanClass(Class beanClass, String[] frameworkPackagePrefixs, String[] actionPackageSuffixs, boolean caseSensitive) {
        String packageName = ClassUtils.getPackageName(beanClass);
        if (StringUtils.isEmpty(packageName)) {
            return "";
        }
        String actionPackageSuffix = "";
        for (String packageSuffix : actionPackageSuffixs) {
            if (packageName.endsWith(packageSuffix)) {
                actionPackageSuffix = packageSuffix;
                break;
            }
        }
        String newpackageName="";
        for (String frameworkPackagePrefix : frameworkPackagePrefixs) {
            if (packageName.startsWith(frameworkPackagePrefix)) {
                newpackageName = StringUtils.replace(packageName, frameworkPackagePrefix, "");
//              break;
            }
        }
        newpackageName = StringUtils.replace(newpackageName, actionPackageSuffix, "");
        newpackageName = StringUtils.replace(newpackageName, ".", "/");
        return (caseSensitive ? newpackageName : newpackageName.toLowerCase()) + "/";
    }

    /**
     * 
     * getEndWithSuffixPath:根据类后缀数组筛选出path的结果. <br/>
     *
     * @author qiyongkang
     * @param className
     * @param suffixs
     * @return
     * @since JDK 1.6
     */
    private String getEndWithSuffixPath(String className, String[] suffixs) {
        String path = "";
        for (String suffix : suffixs) {
            if (className.endsWith(suffix)) {
                path = className.substring(0, className.lastIndexOf(suffix));
                break;
            }
        }
        return StringUtils.isEmpty(path) ? className : path;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public String[] getFrameworkPackagePrefixs() {
        return frameworkPackagePrefixs;
    }

    public void setFrameworkPackagePrefixs(String[] frameworkPackagePrefixs) {
        this.frameworkPackagePrefixs = frameworkPackagePrefixs;
    }

    public String[] getActionClassSuffixs() {
        return actionClassSuffixs;
    }

    public void setActionClassSuffixs(String[] actionClassSuffixs) {
        this.actionClassSuffixs = actionClassSuffixs;
    }

    public String[] getActionPackageSuffixs() {
        return actionPackageSuffixs;
    }

    public void setActionPackageSuffixs(String[] actionPackageSuffixs) {
        this.actionPackageSuffixs = actionPackageSuffixs;
    }



}

