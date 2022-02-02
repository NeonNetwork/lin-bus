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

import org.enginehub.linbus.common.internal.Iterators;
import org.enginehub.linbus.stream.token.LinToken;
import org.enginehub.linbus.tree.impl.LinTagReader;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * Represents a short tag.
 */
public final class LinShortTag extends LinNumberTag<@NotNull Short, LinShortTag> {
    /**
     * Read a short tag from the given stream.
     *
     * @param tokens the stream to read from
     * @return the short tag
     */
    public static LinShortTag readFrom(@NotNull Iterator<? extends @NotNull LinToken> tokens) {
        return LinTagReader.readValue(tokens, LinTagType.shortTag());
    }

    private final short value;

    /**
     * Create a new short tag.
     *
     * @param value the value
     */
    public LinShortTag(short value) {
        this.value = value;
    }

    @Override
    public @NotNull LinTagType<LinShortTag> type() {
        return LinTagType.shortTag();
    }

    @Override
    public @NotNull Short value() {
        return value;
    }

    /**
     * Get the value as a primitive short, to avoid boxing.
     *
     * @return the value
     */
    public short valueAsShort() {
        return value;
    }

    @Override
    public @NotNull Iterator<@NotNull LinToken> iterator() {
        return Iterators.of(new LinToken.Short(value));
    }
}
