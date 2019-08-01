package net.helloworld.md.parser.impl;

/**
 * @author harry
 * @date 2018/2/6
 */
public class QuoteParser extends AbstractWithEndTagParser {
    @Override public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.QUOTE;
    }
}
