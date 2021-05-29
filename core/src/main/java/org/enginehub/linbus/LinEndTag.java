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

package org.enginehub.linbus;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public final class LinEndTag extends LinTag<Void> {
    public static LinEndTag readFrom(DataInput input) throws IOException {
        return instance();
    }

    private static final LinEndTag INSTANCE = new LinEndTag();

    public static LinEndTag instance() {
        return INSTANCE;
    }

    private LinEndTag() {
    }

    @Override
    public LinTagType<LinEndTag> type() {
        return LinTagType.endTag();
    }

    @Override
    public Void value() {
        return null;
    }

    @Override
    public void writeTo(DataOutput output) throws IOException {
        // nothing is written here
    }
}