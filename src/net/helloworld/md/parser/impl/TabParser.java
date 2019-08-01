package net.helloworld.md.parser.impl;

/**
 * Created by harry on 2018/2/7.
 */
public class TabParser extends AbstractWithEndTagParser {

    @Override public net.helloworld.md.mark.MarkEntity validate(net.helloworld.md.mark.MarkContext markContext) {
        String line;
        //-4 represent tab key
        int pointer=markContext.getCurrentPointer()+this.mark().getStart().length()-4;
        int start=pointer;
        while ((line = markContext.readLine(pointer)).startsWith("    ")) {
            pointer += line.length();
        }
        net.helloworld.md.mark.MarkEntity markEntity = net.helloworld.md.mark.MarkEntity.createCurrentMark(this.mark(), pointer);
        markEntity.setContent(markContext.getContent().substring(start, pointer).replaceAll("\n+","<br/>"));
        return markEntity;
    }

    @Override
    public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.TAB;
    }
}
