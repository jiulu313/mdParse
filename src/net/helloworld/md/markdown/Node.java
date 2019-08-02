package net.helloworld.md.markdown;

import java.util.List;

/**
 * 节点
 */
public abstract class Node {
    protected int nodeType;
    protected List<Node> children;
    protected String content;
    protected String tag;

    public abstract String getName();

    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "{" + content + "}";
    }
}
