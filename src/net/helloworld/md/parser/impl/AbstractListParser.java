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
import net.helloworld.md.parser.MarkParser;

/**
 * @author by harry
 */
public abstract class AbstractListParser implements MarkParser {
    protected abstract net.helloworld.md.mark.TagListEntity validate(net.helloworld.md.mark.MarkContext markContext, net.helloworld.md.mark.TagListEntity currentEntity, String line);


    @Override
    public net.helloworld.md.mark.MarkEntity validate(net.helloworld.md.mark.MarkContext markContext) {
        net.helloworld.md.mark.TagListEntity current = new net.helloworld.md.mark.TagListEntity();
        current.setIndent(-1);
        net.helloworld.md.mark.TagListEntity parentEntity = current;
        //skip first enter
        markContext.skipPointer(1);
        do {
            String line = markContext.readLine(markContext.getCurrentPointer());
            if (line.equals(CONSTANT.ENTER_TEXT_N) && markContext.getCurrentPointer() > 0 && markContext.getContent().charAt(markContext.getCurrentPointer() - 1) == CHAR_SYMBOL.ENTER) {
                markContext.skipPointer(line.length());
                break;
            }
            if (markContext.detectNextMark(this.mark())) {
                break;
            }
            markContext.skipPointer(line.length());
            current = this.validate(markContext, current, line);
        }
        while (true);
        net.helloworld.md.mark.MarkEntity markEntity = net.helloworld.md.mark.MarkEntity.createCurrentMark(this.mark(), markContext.getCurrentPointer());
        markEntity.setTagListEntities(parentEntity.getChildren());
        markEntity.setNextEntity(markContext.getTempNextMark());
        markContext.setTempNextMark(null);
        return markEntity;
    }

    public net.helloworld.md.mark.TagListEntity getParent(net.helloworld.md.mark.TagListEntity current, Integer intent) {
        //brother
        if (intent == current.getIndent()) {
            return current.getParent();
        }
        //parent
        if (intent < current.getIndent()) {
            int count = current.getIndent() - intent;
            do {
                current = current.getParent();
            }
            while (count-- > 0);
            return current;
        }
        //children
        if (intent - current.getIndent() == 1) {
            return current;
        }
        return null;
    }
}
