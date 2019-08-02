package net.helloworld.md;


import net.helloworld.md.markdown.MarkdownComplier;
import net.helloworld.md.markdown.Node;

import java.util.List;

public class Main {

    public static String str = "# 你好吗?\n" +
            "\n" +
            "## hello\n" +
            "\n" +
            "```\n" +
            "void main(){\n" +
            "    printf(\"hello,world\");\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "北京欢迎你**小明**是一个好孩子，编程最好学`java`语言";

    public static void main(String[] args) {
        MarkdownComplier complie = new MarkdownComplier();
        complie.setContent(Main.str);
        complie.parse();

        List<Node> list = complie.getNodeList();
        System.out.println(list.toString());

    }
}
