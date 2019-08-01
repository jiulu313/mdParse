package net.helloworld.md.parser;

import net.helloworld.md.mark.MARK;
import net.helloworld.md.mark.MarkContext;
import net.helloworld.md.mark.MarkEntity;

/**
 * @author harry
 * @date 2018/2/6
 */
public interface MarkParser {
    boolean detectStartMark(MarkContext markContext);
    MarkEntity validate(MarkContext mark);
    void parse(MarkContext markContext);
    MARK mark();
}
