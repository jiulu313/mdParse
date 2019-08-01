/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.helloworld.md.parser.impl;

import net.helloworld.md.constant.CONSTANT;
import net.helloworld.md.constant.magic.CHAR_SYMBOL;
import net.helloworld.md.mark.TagListEntity;
import net.helloworld.md.utility.CollectionsUtility;
import net.helloworld.md.utility.StringUtility;

import java.util.List;

/**
 * @author harry
 */
public class OrderedListParser extends AbstractListParser {

    @Override
    public boolean detectStartMark(net.helloworld.md.mark.MarkContext markContext) {
        int tempPointer = markContext.getCurrentPointer();
        String content = markContext.getContent();

        int firstBlankIndex = markContext.detectFirstBlank(this.mark(), tempPointer);
        if (firstBlankIndex == -1) {
            return false;
        }
        //the next letter must be digit
        tempPointer = firstBlankIndex + 1;
        String digit = StringUtility.getDigit(content, tempPointer);
        if (digit.length() == 0) {
            return false;
        }
        tempPointer += digit.length();
        //the next letter must be .
        if (content.charAt(tempPointer) != CHAR_SYMBOL.DOT) {
            return false;
        }
        //next letter must by ' '
        tempPointer++;
        if (content.charAt(tempPointer) != CHAR_SYMBOL.BLANK) {
            return false;
        }
        return true;
    }

    @Override
    protected net.helloworld.md.mark.TagListEntity validate(net.helloworld.md.mark.MarkContext markContext, net.helloworld.md.mark.TagListEntity currentEntity, String line) {
        if (line.equals(CONSTANT.ENTER_TEXT_N)) {
            return currentEntity;
        }

        String innerLine = line.trim();
        String digit = StringUtility.getDigit(innerLine, 0);
        //not start with digit
        if (digit.length() == 0) {
            currentEntity.setContent(currentEntity.getContent() + markContext.getInnerHtml(this.mark(), innerLine));
            return currentEntity;
        }

        //all digital
        if (digit.length() == innerLine.length()) {
            currentEntity.setContent(currentEntity.getContent() + markContext.getInnerHtml(this.mark(), innerLine));
            return currentEntity;
        }

        int tempPointer=digit.length();
        //the next letter must be .
        if (tempPointer>=innerLine.length()||innerLine.charAt(tempPointer) != CHAR_SYMBOL.DOT) {
            currentEntity.setContent(currentEntity.getContent() + markContext.getInnerHtml(this.mark(), innerLine));
            return currentEntity;
        }

        //the next letter must be ' '
        tempPointer++;
        if (tempPointer>=innerLine.length()||innerLine.charAt(tempPointer) != CHAR_SYMBOL.BLANK) {
            currentEntity.setContent(currentEntity.getContent() + markContext.getInnerHtml(this.mark(), innerLine));
            return currentEntity;
        }

        int indent = StringUtility.getPrefixCount(line, "   ");
        net.helloworld.md.mark.TagListEntity parent = this.getParent(currentEntity, indent);
        if (parent == null) {
            currentEntity.setContent(currentEntity.getContent() + innerLine);
            return currentEntity;
        }

        net.helloworld.md.mark.TagListEntity newEntity = new net.helloworld.md.mark.TagListEntity();
        newEntity.setParent(parent);
        newEntity.setIndent(indent);
        newEntity.setTitle(digit);

        String innerContent = innerLine.substring(digit.length() + 1).trim();

        if (innerContent.length() > 2) {
            String innerHTML = markContext.getInnerHtml(this.mark(), innerContent);
            newEntity.setContent(innerHTML);
        } else {
            newEntity.setContent(innerContent);
        }
        parent.getChildren().add(newEntity);
        return newEntity;
    }

    private String parseTagList(List<TagListEntity> tags, Integer intent) {
        StringBuilder ol = new StringBuilder();
        for (net.helloworld.md.mark.TagListEntity tag : tags) {
            ol.append(String.format("<li>%1$s</li>\n", tag.getContent()));
            if (!CollectionsUtility.isNullOrEmpty(tag.getChildren())) {
                ol.append(this.parseTagList(tag.getChildren(), tag.getIndent()));
            }
        }
        if (ol.length() > 0) {
            ol.insert(0, String.format("<ol class=\"ol%1$s\">\n", intent == null ? "" : "_" + intent));
            ol.append("</ol>\n");
        }
        return ol.toString();
    }

    @Override
    public void parse(net.helloworld.md.mark.MarkContext markContext) {
        List<TagListEntity> tagListEntities = markContext.getCurrentMark().getTagListEntities();
        markContext.append(this.parseTagList(tagListEntities, null));
        markContext.setPointer(markContext.getCurrentMark().getEnd());
    }

    @Override
    public net.helloworld.md.mark.MARK mark() {
        return net.helloworld.md.mark.MARK.ORDERED_LIST;
    }
}
