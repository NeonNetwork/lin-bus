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

package org.enginehub.linbus.core;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;

/**
 * Represents an NBT tag, which has a canonical representation of type {@code T}.
 *
 * @param <T> the type of the canonical representation
 */
public abstract class LinTag<T, SELF extends LinTag<T, SELF>> implements ToLinTag<SELF> {
    /**
     * Gets the type of this tag.
     *
     * @return the type of this tag
     */
    // This is to be overriden directly to save memory in the tag itself
    // And also to provide the more specific return type
    public abstract LinTagType<?> type();

    /**
     * Gets the value of this tag.
     *
     * @return the value of the tag
     */
    public abstract T value();

    public final int coerceAsInt() {
        return value() instanceof Number number ? number.intValue() : 0;
    }

    /**
     * Write this tag to a byte array and return it.
     *
     * @return the serialized tag bytes
     */
    public final byte[] writeToArray() {
        return WriteUtil.writeToArray(this::writeTo);
    }

    /**
     * Write this tag to the output.
     *
     * @param output the output
     */
    public abstract void writeTo(DataOutput output) throws IOException;

    // all abiding implementations use SELF properly
    @SuppressWarnings("unchecked")
    @Override
    public @NonNull SELF toLinTag() {
        return (SELF) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinTag<?, ?> that = (LinTag<?, ?>) o;
        return Objects.equals(value(), that.value());
    }

    @Override
    public int hashCode() {
        return value().hashCode();
    }

    @Override
    public String toString() {
        return type().name() + "{" + value() + '}';
    }
}
