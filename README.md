# 卫星菜单

## 预览
![img](https://github.com/mzyq/AroundMenu/blob/master/img/preview.gif)

[下载APK](https://fir.im/rf1s)

## 方法介绍

* ```setCentBtnView(View view)```:设置中间按钮，如果不设置会有默认的

* ```setMenuViewList(List<T extends View> buttonList)```:菜单按钮
> 必须调用，否则没有菜单功能

* ```setMenuOrientation(int menuOrientation)```:设置菜单显示的方向

* ```openMenu();|closeMenu();```:打开|关闭菜单

* ```getMenuOrientation();```:获取当前方向 <br> ```isShowing();```:获取当前状态

* ```setMenuClick(OnAroundMenuClick onAroundMenuClick)```:回调
 ```
    public interface OnAroundMenuClick {
            void onCenterClick(boolean isShowing);

            void onMenuClick(int position);
    }
  ```

## attrs
### 默认中间按钮相关
* menuBtnSize:默认按钮的大小
* centerBtnColor:默认按钮的颜色

### 方向
* menuOrientation

 | Orientatio | Value |
 |:----------:|:-----:|
 |   TOP      |   1   |
 |   BOTTOM   |   2   |
 |   LEFT     |   3   |
 |   RIGHT    |   4   |
 |  LEFT_TOP  |   5   |
 |LEFT_BOTTOM |   6   |
 |  RIGHT_TOP |   7   |
 |RIGHT_BOTTOM|   8   |

