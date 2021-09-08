/*
 * Copyright (c) EngineHub <https://enginehub.org>
 * Copyright (c) contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.enginehub.linbus.tree;

import org.enginehub.linbus.stream.visitor.LinLongArrayTagVisitor;

import java.nio.LongBuffer;
import java.util.function.Consumer;

class TreeLongArrayVisitor extends TreeVisitor<LinLongArrayTag> implements LinLongArrayTagVisitor {
    private LongBuffer data;

    protected TreeLongArrayVisitor(Consumer<LinLongArrayTag> tagConsumer) {
        super(tagConsumer);
    }

    @Override
    public void visitSize(int size) {
        data = LongBuffer.allocate(size);
    }

    @Override
    public void visitChunk(LongBuffer buffer) {
        data.put(buffer);
    }

    @Override
    public void visitEnd() {
        tagFinished(new LinLongArrayTag(data.array(), true));
        data = null;
    }
}