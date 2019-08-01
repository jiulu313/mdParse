package net.helloworld.md.parser.impl;

import net.helloworld.md.constant.magic.CHAR_SYMBOL;
import net.helloworld.md.parser.MarkParser;

/**
 * Created by harry on 2018/2/6.
 */
public class MarkdownParserComposite implements MarkParser {
    private static MarkdownParserComposite instance = new MarkdownParserComposite();

    private MarkdownParserComposite() {
    }

    public static MarkdownParserComposite getInstance() {
        return instance;
    }

    @Override public boolean detectStartMark(net.helloworld.md.mark.MarkContext markContext) {
        return false;
    }

    @Override
    public net.helloworld.md.mark.MarkEntity validate(net.helloworld.md.mark.MarkContext mark) {
        return null;
    }

    @Override
    public void parse(net.helloworld.md.mark.MarkContext markContext) {
        //if first char is not \n then fill
        if (markContext.getParentMark() == null&&markContext.getContent().charAt(0)!= CHAR_SYMBOL.ENTER) {
            markContext.setContent(CHAR_SYMBOL.ENTER + markContext.getContent());
        }
        do {
            //detect start mark
            if (markContext.getCurrentMark()!=null&&markContext.getCurrentMark().getNextEntity()!= null) {
                markContext.setCurrentMark(markContext.getCurrentMark().getNextEntity());
            } else {
                markContext.setCurrentMark(null);
                markContext.detectCurrentMark(markContext.getParentMark());
            }
            if (markContext.getCurrentMark() != null) {
                net.helloworld.md.mark.MarkContext.MARK_PARSER_MAP.get(markContext.getCurrentMark().getMark()).parse(markContext);
                continue;
            }

            MarkParser literaryParse = net.helloworld.md.mark.MarkContext.MARK_PARSER_MAP.get(net.helloworld.md.mark.MARK.LITERARY);
            net.helloworld.md.mark.MarkEntity markEntity = literaryParse.validate(markContext);
            if (markEntity != null) {
                markContext.setCurrentMark(markEntity);
                //按文本处理
                literaryParse.parse(markContext);
            }
        }
        while (markContext.getCurrentPointer() < markContext.getContentLength());
    }

    @Override
    public net.helloworld.md.mark.MARK mark() {
        return null;
    }
}
