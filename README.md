# createHtmlDemo
由于项目中想要实现图文混排的功能，如果用editText的话可能会比较麻烦，于是我就想将 editText里的文本信息生成htmL文件，这样就可以添加图片，并实现一定的排版效果了
，和markdown很类似，两者都是生成html页面。
### 主要内容：
1. 点击按钮，调用加载图片库并选择图片的 意图
2. 获取选中的图片地址，然后将容量比较大的图片压缩，最终生成html文件，并将所有html所需资源压缩成zip文件并上传到服务器；