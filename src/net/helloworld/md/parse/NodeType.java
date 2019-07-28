package net.helloworld.md.parse;

/**
 * Node的类型
 */
public final class NodeType {
    // 普通的文本，没有任何标签
    public static final int NT_TEXT = 0;
    //标题 # ## ### #### #####
    public static final int NT_TITLE = 1;
    //引用 >
    public static final int NT_QUOTE = 2;
    //列表   无序：- * +    有序：1. 2. 3. 等
    public static final int NT_LIST = 3;
    //代码 ```  ```
    public static final int NT_CODE = 4;
    //斜体  * *  _ _
    public static final int NT_ITALIC = 5;
    //加粗  ** **   __ __
    public static final int NT_BOLD = 6;
    //分隔线 ---
    public static final int NT_DIVIDE = 7;
    //图片
    public static final int NT_IMAGE = 8;
    //链接
    public static final int NT_LINK = 9;
    //行内引用
    public static final int NT_INLINE_QUOTE = 10;



}
