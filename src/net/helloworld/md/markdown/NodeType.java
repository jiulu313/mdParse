package net.helloworld.md.markdown;

public class NodeType {
    /**
     * 文本元素
     * 没有任何标签
     */
    public static final int NT_TEXT = 0;
    /**
     * 一级标题
     * #
     */
    public static final int NT_TITLE_1 = 1;
    /**
     * 二级标题
     * ##
     */
    public static final int NT_TITLE_2 = 2;
    /**
     * 三级标题
     * ###
     */
    public static final int NT_TITLE_3 = 3;
    /**
     * 四级标题
     * ####
     */
    public static final int NT_TITLE_4 = 4;
    /**
     * 五级标题
     * #####
     */
    public static final int NT_TITLE_5 = 5;
    /**
     * 加粗
     * **test**
     */
    public static final int NT_BOLD = 6;
    /**
     * 斜体
     * *test*
     */
    public static final int NT_ITALIC = 7;
    /**
     * 加粗斜体
     */
    public static final int NT_BOLD_ITALIC = 8;
    /**
     * 分隔线
     * ---
     * ----
     * ***
     * *****
     */
    public static final int NT_DIVIDE_LINE = 9;
    /**
     * 图片
     * ![alt](htpp://xx.com/a.jpg "雪景")
     */
    public static final int NT_IMAGE = 10;

    /**
     * 超链接
     * [百度](http://baidu.com)
     */
    public static final int NT_LINK = 11;
    /**
     * 有序列表
     * 1. xx
     * 2. yy
     * 3. zz
     */
    public static final int NT_ORDER_LIST = 12;
    /**
     * 无序列表
     *  - + * 任何一个，后面跟空格
     */
    public static final int NT_UNORDER_LIST = 13;
    /**
     * 代码
     * ```
     * ```
     */
    public static final int NT_CODE = 14;
    /**
     * 内嵌代码
     * ``
     */
    public static final int NT_EMBED_CODE = 15;
    /**
     * 引用
     * >
     */
    public static final int NT_QUOTE = 16;


}
