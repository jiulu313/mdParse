package net.helloworld.md.markdown;

import java.util.ArrayList;
import java.util.List;

public class MarkdownComplier {
    private List<Node> nodeList;
    private String content;

    public MarkdownComplier() {
        nodeList = new ArrayList<>();
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void parse() {
        if (content == null || "".equals(content)) {
            return;
        }

        if (nodeList == null) {
            nodeList = new ArrayList<>();
        } else {
            nodeList.clear();
        }

        parseContent();
    }

    private void parseContent() {
        String str = content;
        int p1 = 0;
        int p2 = 0;
        int end = str.length() - 1;
        char ch;

        while (p1 <= end) {
            ch = str.charAt(p1);

            if (ch == '\n') {
                nodeList.add(new EnterNode());
                p1++;
                continue;
            } else if (ch == '#') {//标题
                if (p1 + 1 < end && str.charAt(p1 + 1) == ' ') { //一级标题的特征
                    p2 = p1 + 1;

                    boolean hasEnter = false;
                    while (p2 < end) {
                        if (str.charAt(p2) != '\n') {
                            p2++;
                        } else {
                            hasEnter = true;
                            break;
                        }
                    }

                    TitleNode node = new TitleNode();
                    node.setNodeType(NodeType.NT_TITLE_1);
                    node.setContent(str.substring(p1 + 1, p2));
                    nodeList.add(node);

                    if (hasEnter) {
                        EnterNode enterNode = new EnterNode();
                        nodeList.add(enterNode);
                    }

                    p2++;
                    p1 = p2;
                    continue;
                } else if (p1 + 2 < end && str.charAt(p1 + 1) == '#' && str.charAt(p1 + 2) == ' ') { //二级标题的特征
                    p2 = p1 + 2;

                    boolean hasEnter = false;
                    while (p2 < end) {
                        if (str.charAt(p2) != '\n') {
                            p2++;
                        } else {
                            hasEnter = true;
                            break;
                        }
                    }

                    TitleNode node = new TitleNode();
                    node.setNodeType(NodeType.NT_TITLE_2);
                    node.setContent(str.substring(p1 + 2, p2));
                    nodeList.add(node);

                    if (hasEnter) {
                        EnterNode enterNode = new EnterNode();
                        nodeList.add(enterNode);
                    }

                    p2++;
                    p1 = p2;
                    continue;
                } else if (p1 + 3 < end && str.charAt(p1 + 1) == '#' && str.charAt(p1 + 2) == '#' && str.charAt(p1 + 3) == ' ') {//三级标题的特征
                    p2 = p1 + 3;

                    boolean hasEnter = false;
                    while (p2 < end) {
                        if (str.charAt(p2) != '\n') {
                            p2++;
                        } else {
                            hasEnter = true;
                            break;
                        }
                    }

                    TitleNode node = new TitleNode();
                    node.setNodeType(NodeType.NT_TITLE_3);
                    node.setContent(str.substring(p1 + 3, p2));
                    nodeList.add(node);

                    if (hasEnter) {
                        EnterNode enterNode = new EnterNode();
                        nodeList.add(enterNode);
                    }

                    p2++;
                    p1 = p2;
                    continue;
                }
            } else if (p1 + 4 < end && str.charAt(p1 + 1) == '#' && str.charAt(p1 + 2) == '#' && str.charAt(p1 + 3) == '#' && str.charAt(p1 + 4) == ' ') {//四级标题
                p2 = p1 + 4;

                boolean hasEnter = false;
                while (p2 < end) {
                    if (str.charAt(p2) != '\n') {
                        p2++;
                    } else {
                        hasEnter = true;
                        break;
                    }
                }

                TitleNode node = new TitleNode();
                node.setNodeType(NodeType.NT_TITLE_4);
                node.setContent(str.substring(p1 + 4, p2));
                nodeList.add(node);

                if (hasEnter) {
                    EnterNode enterNode = new EnterNode();
                    nodeList.add(enterNode);
                }

                p2++;
                p1 = p2;
                continue;
            } else if (p1 + 5 < end && str.charAt(p1 + 1) == '#' && str.charAt(p1 + 2) == '#' && str.charAt(p1 + 3) == '#' && str.charAt(p1 + 4) == '#' && str.charAt(p1 + 5) == ' ') {//五级标题
                p2 = p1 + 5;

                boolean hasEnter = false;
                while (p2 < end) {
                    if (str.charAt(p2) != '\n') {
                        p2++;
                    } else {
                        hasEnter = true;
                        break;
                    }
                }

                TitleNode node = new TitleNode();
                node.setNodeType(NodeType.NT_TITLE_5);
                node.setContent(str.substring(p1 + 5, p2));
                nodeList.add(node);

                if (hasEnter) {
                    EnterNode enterNode = new EnterNode();
                    nodeList.add(enterNode);
                }

                p2++;
                p1 = p2;
                continue;
            } else if (ch == '*') {//斜体,粗体,斜粗体
                if (p1 + 1 < end && str.charAt(p1 + 1) != '*') {//斜体
                    p2 = p1 + 1;

                    boolean bFind = false;
                    while (p2 < end) {
                        if (str.charAt(p2) == '*') {
                            bFind = true;
                            break;
                        } else {
                            p2++;
                        }
                    }

                    if (bFind) {
                        ItalicNode node = new ItalicNode();
                        node.setNodeType(NodeType.NT_ITALIC);
                        node.setContent(str.substring(p1 + 1, p2));
                        nodeList.add(node);
                        p2++;
                        p1 = p2;
                    }

                    continue;
                }


            } else if (ch == '*') {//斜体

            } else if (ch == '*' && (p1 + 2) < end && str.charAt(p1 + 1) == '*' && str.charAt(p1 + 2) == '*') {//粗斜体,分隔线

            } else if (ch == '-' && (p1 + 2 < end && str.charAt(p1 + 1) == '-' && str.charAt(p1 + 2) == '-')) {//下划线

            } else if (ch == '_' && (p1 + 2 < end && str.charAt(p1 + 1) == '_' && str.charAt(p1 + 2) == '_')) {//下划线

            } else if (ch == '[') {

            } else if (ch == '!') {

            } else if (ch == '`') {

            } else if (ch == '>') {

            } else if (ch == '-' || ch == '+' || ch == '*') {//无序列表

            } else if (Character.isDigit(ch)) { //有序列表

            } else {
                //普通文本
            }

            p1++;
        }//end while
    }


}
