package net.helloworld.md.parser.impl;

/**
 * @author harry
 */
public class BoldParser extends AbstractWithEndTagParser {
    @Override
    public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.BOLD;
    }
}
