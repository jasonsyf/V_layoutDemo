 #  Alibaba Vlayout框架测试demo
 
 ### 可选布局样式
 
 1：LinearLayoutHelper: 线性布局   
 2：GridLayoutHelper: Grid布局， 支持横向的colspan   
 3：FixLayoutHelper: 固定布局，始终在屏幕固定位置显示  
 3：FixLayoutHelper: 固定布局，始终在屏幕固定位置显示   
 4：ScrollFixLayoutHelper: 固定布局，但之后当页面滑动到该图片区域才显示, 可以用来做返回顶部或其他书签等   
 5：FloatLayoutHelper: 浮动布局，可以固定显示在屏幕上，但用户可以拖拽其位置   
 6：ColumnLayoutHelper: 栏格布局，都布局在一排，可以配置不同列之间的宽度比值   
 7：SingleLayoutHelper: 通栏布局，只会显示一个组件View   
 8：OnePlusNLayoutHelper: 一拖N布局，可以配置1-5个子元素   
 9：StickyLayoutHelper: stikcy布局， 可以配置吸顶或者吸底   
 10：StaggeredGridLayoutHelper: 瀑布流布局，可配置间隔高度/宽度  
 
 ## app
 
Github找的一个 [demo](https://github.com/Carson-Ho/VLayout-Guide) ，adapter写的比较简单，如果Item的类型比较多，或者是item  里面的控件比较复杂就不能实现。
 ## tnkgdemo
 
 在APP基础上封装了一个util包，拆分MyAdapter内部Viewholder，修改MyAdapter位抽象类，  增加抽象方法setListener，setData
 用于自定义的设置数据和点击事件。
 
Tags:暂时只测试了  
                ColumnLayoutHelper  
               StickyLayoutHelper  
               LinearLayoutHelper  
               SingleLayoutHelper  
               
     bug：自定义的轮播图复用问题，Vlayout stickylayouthelper有Bug，老是乱晃，感觉走了个三角形？



   
   
   
   
   
   
   
   