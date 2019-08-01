package net.helloworld.md.parser.impl;

import net.helloworld.md.parser.MarkParser;

/**
 * @author harry
 */
public class LiteraryParser implements MarkParser {

    @Override public boolean detectStartMark(net.helloworld.md.mark.MarkContext markContext) {
        return true;
    }

    @Override
    public net.helloworld.md.mark.MarkEntity validate(net.helloworld.md.mark.MarkContext markContext) {
        int originalPointer = markContext.getCurrentPointer();
        while (markContext.getCurrentPointer()<markContext.getContentLength()&&!markContext.detectNextMark(this.mark())){
            markContext.skipPointer(1);
        }
        net.helloworld.md.mark.MarkEntity currentMark = net.helloworld.md.mark.MarkEntity.createCurrentMark(this.mark(), markContext.getCurrentPointer());
        markContext.setPointer(originalPointer);
        if(markContext.getTempNextMark()!=null){
            currentMark.setNextEntity(markContext.getTempNextMark());
            markContext.setTempNextMark(null);
        }
        return currentMark;
    }

    @Override
    public void parse(net.helloworld.md.mark.MarkContext markContext) {
        String content = markContext.getContent().substring(markContext.getCurrentPointer(), markContext.getCurrentMark().getEnd());
        markContext.setPointer(markContext.getCurrentMark().getEnd());
        markContext.append(String.format(this.mark().getFormat(), content.replaceAll("\n+", "<br/>")));
    }

    @Override
    public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.LITERARY;
    }
}
