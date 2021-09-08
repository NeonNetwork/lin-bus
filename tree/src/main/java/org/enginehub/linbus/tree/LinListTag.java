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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class LinListTag<T extends @NotNull LinTag<?, ?>> extends LinTag<@NotNull List<T>, LinListTag<T>> {
    public static <T extends @NotNull LinTag<?, ?>> LinListTag<T> empty(LinTagType<T> elementType) {
        return builder(elementType).build();
    }

    public static <T extends @NotNull LinTag<?, ?>> Builder<T> builder(LinTagType<T> elementType) {
        return new Builder<>(elementType);
    }

    public static final class Builder<T extends @NotNull LinTag<?, ?>> {
        private final LinTagType<T> elementType;
        private final List<T> collector;

        private Builder(LinTagType<T> elementType) {
            this.elementType = elementType;
            this.collector = new ArrayList<>();
        }

        private Builder(LinListTag<T> base) {
            this.elementType = base.elementType;
            this.collector = new ArrayList<>(base.value);
        }

        public Builder<T> add(T tag) {
            if (tag.type() != elementType) {
                throw new IllegalArgumentException("Element is not of type " + elementType.name() + " but "
                    + tag.type().name());
            }
            this.collector.add(tag);
            return this;
        }

        public LinListTag<T> build() {
            return new LinListTag<>(this.elementType, List.copyOf(this.collector), false);
        }
    }

    private final LinTagType<T> elementType;
    private final List<T> value;

    public LinListTag(LinTagType<T> elementType, List<T> value) {
        this(elementType, List.copyOf(value), true);
    }

    private LinListTag(LinTagType<T> elementType, List<T> value, boolean check) {
        Objects.requireNonNull(value, "value is null");
        if (check) {
            for (T t : value) {
                if (t.type() != elementType) {
                    throw new IllegalArgumentException("Element is not of type " + elementType.name() + " but "
                        + t.type().name());
                }
            }
        }
        if (!value.isEmpty() && elementType == LinTagType.endTag()) {
            throw new IllegalArgumentException("A non-empty list cannot be of type 'end'");
        }
        this.elementType = elementType;
        this.value = value;
    }

    @Override
    public LinTagType<LinListTag<T>> type() {
        return LinTagType.listTag();
    }

    public LinTagType<T> elementType() {
        return elementType;
    }

    @Override
    public @NotNull List<T> value() {
        return value;
    }

    /**
     * Direct shorthand for {@link #value() value()}{@code .}{@link List#get(int) get(index)}.
     */
    public T get(int index) {
        return value.get(index);
    }
}