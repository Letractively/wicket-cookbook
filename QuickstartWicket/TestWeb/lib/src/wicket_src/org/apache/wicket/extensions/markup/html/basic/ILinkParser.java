/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.extensions.markup.html.basic;

/**
 * <code>ILinkParser</code> parses an input text and performs modifications according to its render
 * strategies.
 * 
 * @author Gerolf Seitz
 */
public interface ILinkParser
{
	/**
	 * Parses the <code>text</code> and changes it according to the provided
	 * <code>ILinkRenderStrategy</code> implementations.
	 * 
	 * @param text
	 *            the input text which should be modified.
	 * @return the modified input text.
	 */
	String parse(String text);
}