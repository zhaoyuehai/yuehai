#Android组件化开发Demo





**注意**

1. common组件：被其他所有组件依赖（common组件含有base基类）。
2. app壳组件：仅在集成开发模式下用的着， 里面只含有一个全局的MyApplication,当然该MyApplication类也继承了BaseApplication基类，所以app壳组件也依赖common组件。

          







**遇到的问题：**

1. 一些文件，如style/dimens等，在跨组件引用时，在布局文件中无法自动提示，视图无法也更新。

2. 每个组件中有两个AndroidManifest.xml文件，都需要维护。

3. 在集成开发模式下，各个组件属于library，R文件id不是final常量
   所以类似@Bind(viewId) 这种ButterKnife注解无法使用。
   在library中无法使用switch方法，需要用if-else代替。
4. 使用ARouter：

   ` javaCompileOptions {
                annotationProcessorOptions {
                    arguments = [ moduleName : project.getName() ]
                }
            }`
    在每个模块组件都要定义，为了让ARouter知道这个模块用来区别于其他模块的名字是什么。
    
    `compile 'com.alibaba:arouter-api:1.3.1'`
    只需要在公共common组件中定义即可。
    
    `annotationProcessor 'com.alibaba:arouter-compiler:1.1.4'`
    需要在每个使用了ARouter注解的模块组件都要定义。
    
