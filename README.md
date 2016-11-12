demo演示：

![](https://github.com/Sbingo/CustomPopupWindow/raw/master/gif/custom_popup_window.gif) 
###**效果说明**

 - 提供了六种不同的弹出方式，后面会有详细说明

 - 默认给弹出view加上了灰色背景，but背景位置不支持更改。
 
 - 弹出view默认在灰色背景中的正中间，也可以自己设置，并可微调
 
 - 可设置弹出和消失style，如动画效果
 - 点击灰色背景外和背景中非view区弹窗都会消失
###**技术分析**

 - 将传入的view作为子view放入一个灰色的父view（FrameLayout）中

```
frameLayout.removeAllViews();
frameLayout.addView(contentView, new ViewGroup.LayoutParams(width, height));
```

 - 获取目标view的位置，结合想实现的效果，就可以确定父view（FrameLayout）的位置
 
```
int[] location = new int[2];
parentView.getLocationOnScreen(location);
```

 - 确定传入的view在父view中的位置：

```
FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) contentView.getLayoutParams();
tryClearGravity();
hasSetGravity = false;
lp.gravity = gravity;
if ((gravity & Gravity.RIGHT) == Gravity.RIGHT) {
     lp.rightMargin = -xOff;
} else {
     lp.leftMargin = xOff;
}
if ((gravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
     lp.bottomMargin = -yOff;
} else {
    lp.topMargin = yOff;
}
contentView.setLayoutParams(lp);
```

 - 最后用`PopupWindow`的`showAsDropDown`或`showAtLocation`方法显示弹窗。
 
```
popupWindow.showAsDropDown(parentView, 0, 0);
```
或者

```
popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, location[0], location[1] - popupWindow.getHeight());
```

###**Usage**

#### **1.**   在项目的`build.gradle`文件最后添加：

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
#### **2.** 在`module`的`build.gradle`文件中添加：

   

```
 dependencies {
    compile 'com.github.Sbingo:CustomPopupWindow:v1.0.0'
 }
```
####**3.** 开始使用

 - 新建`CustomPopupWindow`对象
 
  `customPopupWindow = new CustomPopupWindow(PopupWindowActivity.this);`
  
 - 创建要弹出的view
 
 ` popupTestView = new PopupTestView(PopupWindowActivity.this);`
 - 弹出view
```
customPopupWindow.showOnBottom(v, popupTestView, 0, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 100);
```
####**可选配置**

 - 设置弹窗消失的监听器
 

```
customPopupWindow.setDismissListener(new CustomPopupWindow.DismissCallBack() {
      @Override
      public void dismissCallBack() {
            /** do sth you like here */
       }
});
```

  - 设置弹出view在灰色背景中的位置（默认在正中间）
  

```
customPopupWindow.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
```
   可以同时结合各方法中的偏移进行微调。

 - 设置弹出和消失style

	对应于每个show（）方法中的style参数，详见下面的说明
###**方法参数说明**
以 `showOnBottom(View parentView, View contentView, int style, int width, int height, int xOff, int yOff)`方法为例，它的作用是在目标view下方弹出view，参数说明：

 - `parentView` 
 
  用于确定弹出位置或（和）获取`windowToken`的view，最好传入目标view。
  
 - `contentView` 
  
 弹出的view，在演示中为灰色背景中的白色区域。
 - `style ` 
 
 弹出和消失时的效果，-1时默认效果，0时无效果，也可传入style资源id，可参照demo。
 
 - `width` 
 
 弹出view的宽度，可以是
 `ViewGroup.LayoutParams.WRAP_CONTENT`、
 `ViewGroup.LayoutParams.MATCH_PARENT`、
或者固定像素大小

 - height
 
  弹出view的高度，可以是
 `ViewGroup.LayoutParams.WRAP_CONTENT`、
 `ViewGroup.LayoutParams.MATCH_PARENT`、
或者固定像素大小

 - xOff
 
 弹出view在灰色背景中的水平偏移
 - yOff
 
 弹出view在灰色背景中的竖直偏移

其余还有5个方法，分别是：
`showOnTop`、`showOnLeft`、`showOnRight`、`showOnScreenBottom`、`showFullScreen`，
相信看了名字就知道它们的作用，各参数的意义也和上面的说明一样。

如果觉得有用，还请给个**Star**，谢谢。
