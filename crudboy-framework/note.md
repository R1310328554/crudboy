# CaptchaServiceFactory spi 方式创建服务。 即 ServiceLoader.load 的方式。 如何做到的？ 
    static {
        ServiceLoader<CaptchaCacheService> cacheServices = ServiceLoader.load(CaptchaCacheService.class);
        Iterator var1 = cacheServices.iterator();

        while(var1.hasNext()) {
            CaptchaCacheService item = (CaptchaCacheService)var1.next();
            cacheService.put(item.type(), item);
        }

        logger.info("supported-captchaCache-service:{}", cacheService.keySet().toString());
        ServiceLoader<CaptchaService> services = ServiceLoader.load(CaptchaService.class);
        Iterator var5 = services.iterator();

        while(var5.hasNext()) {
            CaptchaService item = (CaptchaService)var5.next();
            instances.put(item.captchaType(), item);
        }

        logger.info("supported-captchaTypes-service:{}", instances.keySet().toString());
    }


Too many modules require recompilation, forcing full project rebuild


--- 编译一次就是3min， 谁受得了啊！！

Executing pre-compile tasks...
Too many modules require recompilation, forcing full project rebuild
Output path D:\d\git\crudboy\crudboy-emall\target\generated-sources\annotations intersects with a source root. Only files that were created by build will be cleaned.
Cleaning output directories…
Output path D:\d\git\crudboy\crudboy-framework\target\generated-sources\annotations intersects with a source root. Only files that were created by build will be cleaned.
Running 'before' tasks
Checking sources
Copying resources... [crudboy-ssodemo]
Copying resources... [crudboy-framework]
Copying resources... [crudboy-emall]
Parsing java… [crudboy-framework]
Writing classes… [crudboy-framework]
Updating dependency information… [crudboy-framework]
Adding nullability assertions… [crudboy-framework]
Adding threading assertions… [crudboy-framework]
Adding pattern assertions… [crudboy-framework]
Parsing java… [tests of crudboy-framework]
Writing classes… [tests of crudboy-framework]
Updating dependency information… [tests of crudboy-framework]
Adding nullability assertions… [tests of crudboy-framework]
Adding threading assertions… [tests of crudboy-framework]
Adding pattern assertions… [tests of crudboy-framework]
Parsing java… [crudboy-ssodemo]
Writing classes… [crudboy-ssodemo]
Updating dependency information… [crudboy-ssodemo]
Adding nullability assertions… [crudboy-ssodemo]
Adding threading assertions… [crudboy-ssodemo]
Adding pattern assertions… [crudboy-ssodemo]
Parsing java… [tests of crudboy-ssodemo]
Writing classes… [tests of crudboy-ssodemo]
Updating dependency information… [tests of crudboy-ssodemo]
Adding nullability assertions… [tests of crudboy-ssodemo]
Adding threading assertions… [tests of crudboy-ssodemo]
Adding pattern assertions… [tests of crudboy-ssodemo]
Parsing java… [crudboy-emall]
Writing classes… [crudboy-emall]
Updating dependency information… [crudboy-emall]
Adding nullability assertions… [crudboy-emall]
Adding threading assertions… [crudboy-emall]
Adding pattern assertions… [crudboy-emall]
Parsing java… [tests of crudboy-emall]
Writing classes… [tests of crudboy-emall]
Updating dependency information… [tests of crudboy-emall]
Adding nullability assertions… [tests of crudboy-emall]
Adding threading assertions… [tests of crudboy-emall]
Adding pattern assertions… [tests of crudboy-emall]
Running 'after' tasks
javac 14.0.1 was used to compile java sources
Finished, saving caches…
Executing post-compile tasks...
Finished, saving caches…
Synchronizing output directories...
07/10/2023 12:22 - Build completed successfully with 209 warnings in 3 min



---- 每次都下载， 烦不烦啊！ 太慢了啊！

PS D:\d\git\sefl\yudao-ui-admin-vue3> pnpm run i

> yudao-ui-admin-vue3@1.8.2-snapshot i D:\d\git\sefl\yudao-ui-admin-vue3
> pnpm install

WARN  deprecated stable@0.1.8: Modern JS already guarantees Array#sort() is a stable sort, so this library is deprecated. See the compatibility table on MDN: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort#browser_compatibility
Downloading registry.npmjs.org/element-plus/2.3.14: 8.30 MB/8.30 MB, done
 WARN  deprecated source-map-resolve@0.5.3: See https://github.com/lydell/source-map-resolve#deprecated
 WARN  deprecated source-map-url@0.4.1: See https://github.com/lydell/source-map-url#deprecated
 WARN  deprecated urix@0.1.0: Please see https://github.com/lydell/urix#deprecated
 WARN  deprecated resolve-url@0.2.1: https://github.com/lydell/resolve-url#deprecated
Downloading registry.npmjs.org/typescript/5.2.2: 7.23 MB/7.23 MB, done
Progress: resolved 1142, reused 998, downloaded 112, added 0
Downloading registry.npmjs.org/@iconify/json/2.2.119: 7.29 MB/65.53 MB


--- 
务必一个窗口就只打开一个maven工程， 专一专注！ 避免。。
