package net.helloworld.md.parser.impl;

/**
 * @author harry
 * @date 2018/2/6
 */
public class HorizontalLineParser extends AbstractWithEndTagParser {


    @Override
    public net.helloworld.md.mark.MarkEntity validate(net.helloworld.md.mark.MarkContext markContext) {
        String title = markContext.readLine(markContext.getCurrentPointer() + 1);
        String line = markContext.readLine(markContext.getCurrentPointer()+ title.length(),2);
        if (line.equals(this.mark().getEnd())) {
            net.helloworld.md.mark.MarkEntity markEntity= net.helloworld.md.mark.MarkEntity.createCurrentMark(this.mark(),markContext.getCurrentPointer()+title.length());
            markEntity.setTitle(title);
            return markEntity;
        }
        return null;
    }

    @Override
    public void parse(net.helloworld.md.mark.MarkContext markContext) {
        String title=markContext.getContent().substring(markContext.getCurrentPointer()+1,markContext.getCurrentMark().getEnd());
        markContext.append(String.format(this.mark().getFormat(), title));
        markContext.setPointer(markContext.getCurrentMark().getEnd()+this.mark().getEnd().length());
    }


    @Override
    public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.HORIZONTAL_LINE;
    }
}
