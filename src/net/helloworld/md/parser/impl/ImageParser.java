package net.helloworld.md.parser.impl;

/**
 * ![image](url)
 *
 * @author harry
 */
public class ImageParser extends AbstractWithUrlParser {
    @Override
    public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.IMAGE;
    }
}
