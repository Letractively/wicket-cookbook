/**
 * Copyright (c) 2006-2012 Berlin Brown.  All Rights Reserved
 *
 * http://www.opensource.org/licenses/bsd-license.php
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * + Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * + Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * + Neither the name of the Botnode.com (Berlin Brown) nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Date: 12/12/2012
 *  
 * Project: http://code.google.com/p/wicket-cookbook/
 * Wicket Version: apache-wicket-6.4.0 
 * Environment: Eclipse Juno 
 * Java Compiler and Runtime: 1.6
 * Tested With Server:  Apache Tomcat/7.0.34
 * Author: Berlin Brown (berlin dot brown at gmail.com)
 *
 * Home Page: http://code.google.com/u/berlin.brown/
 * 
 * Contact: Berlin Brown <berlin dot brown at gmail.com>
 */

/* Html Comment */

package org.berlin.wicket.components;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Dynamic HTML Comment, this component replaces its body with the String
 * version of its model object returned by getModelObjectAsString().
 */
public class HtmlComment extends Label {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * 
     * @param id
     *            See Component
     */
    public HtmlComment(final String id) {
        super(id);
        this.setRenderBodyOnly(true);
    }

    /**
     * Convenience constructor. Same as Label(String, new
     * Model&lt;String&gt;(String))
     * 
     * @param id
     *            See Component
     * @param label
     *            The label text
     * 
     * @see org.apache.wicket.Component#Component(String, IModel)
     */
    public HtmlComment(final String id, final String label) {
        this(id, new Model<String>(label));
        this.setRenderBodyOnly(true);
    }

    /**
     * @param id
     * @param model
     * @see org.apache.wicket.Component#Component(String, IModel)
     */
    public HtmlComment(final String id, final IModel<?> model) {
        super(id, model);
        this.setRenderBodyOnly(true);
    }
    
    /**
     * @see org.apache.wicket.Component#onComponentTagBody(org.apache.wicket.markup.MarkupStream,
     *      org.apache.wicket.markup.ComponentTag)
     */
    @Override
    public void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {    
        replaceComponentTagBody(markupStream, openTag, "<!-- " + getDefaultModelObjectAsString() + " -->");
    }

    /**
     * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag)
     */
    @Override
    protected void onComponentTag(final ComponentTag tag) {    
        super.onComponentTag(tag);
        tag.setType(XmlTag.TagType.OPEN);
    }
    
} // End of the class

