package net.helloworld.md.parser.impl;

/**
 * [link](url)
 *
 * @author harry
 */
public class HyperLinkParser extends AbstractWithUrlParser {

    @Override
    public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.HYPER_LINK;
    }
}
