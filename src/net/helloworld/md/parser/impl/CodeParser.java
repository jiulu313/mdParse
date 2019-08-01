package net.helloworld.md.parser.impl;

/**
 * @author harry
 * @date 2018/2/6
 */
public class CodeParser extends AbstractWithEndTagParser {

    @Override
    public net.helloworld.md.mark.MarkEntity validate(net.helloworld.md.mark.MarkContext markContext) {
        int startIndex = markContext.getCurrentPointer() + this.mark().getStart().length();
        int endMarkIndex = markContext.getContent().indexOf(this.mark().getEnd(), startIndex);
        if (endMarkIndex <= 1) {
            return null;
        }
        if (endMarkIndex <= startIndex) {
            return null;
        }
        String content = markContext.getContent().substring(markContext.getCurrentPointer()
            + this.mark().getStart().length(), endMarkIndex);
        net.helloworld.md.mark.MarkEntity markEntity = net.helloworld.md.mark.MarkEntity.createCurrentMark(this.mark(), endMarkIndex);
        markEntity.setContent(content);
        return markEntity;
    }

    @Override
    public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.CODE;
    }
}
