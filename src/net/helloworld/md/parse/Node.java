package net.helloworld.md.parse;

import java.util.List;

public abstract class Node {
    //节点类型
    protected int type;
    //标签
    protected String tag;
    //作用域，单行与多行
    protected int scope;
    //子Node
    protected List<Node> children;


    public Node(int type){
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }




}
