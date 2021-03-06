/*
 * Copyright (C) 2016 jilm
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * The idea is to create library which will typeset various messages of the
 * program into the plain text with respect to the typeset traditions (as much
 * as it will be possible.)
 * 
 * <p>TEXT, EMPHASIZE, CAPTION,  and STRONG are the only elements that doesn't contain
 * the END element.
 *
 * <p>LIST_ORDERED and LIST_UNORDERED may contain only ITEM elements
 *
 * <p>
 */
package cz.lidinsky.tools.text;
