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

/* Loadable List */

package org.berlin.wicket.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.model.util.GenericBaseModel;

/**
 * LoadableDetachableModel holds a temporary, transient model object, that is
 * set when {@link #getObject(Component)} is called by calling abstract method
 * 'load', and that will be reset/ set to null on {@link #detach()}.
 *  
 * This version of the loadable detachable list works with the non serializable list object.
 * 
 * @param <T>
 *            The Model Object type
 */
public abstract class LoadableDetachableList<T> extends GenericBaseModel<List<? extends T>> {
    
    // Tested with : Wicket Version: apache-wicket-6.4.0 / http://code.google.com/p/wicket-cookbook/ / Tested With Server:  Apache Tomcat/7.0.34
    
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;

    /** keeps track of whether this model is attached or detached */
    private transient boolean attached = false;

    /** temporary, transient object. */
    private transient List<? extends T> transientModelObject;

    /**
     * Construct.
     */
    public LoadableDetachableList() {
    }

    /**
     * This constructor is used if you already have the object retrieved and
     * want to wrap it with a detachable model.
     * 
     * @param object
     *            retrieved instance of the detachable object
     */
    public LoadableDetachableList(List<? extends T> object) {
        this.transientModelObject = !(object instanceof Serializable) ? this.createSerializableVersionOf(object) : object;
        attached = true;
    }

    /**
     * Create serializable version of object.
     * 
     * @param object
     * @return
     */
    protected List<? extends T> createSerializableVersionOf(List<? extends T> object) {
        if (object == null) {
            return null;
        }
        return new ArrayList<T>(object);
    }

    /**
     * @see org.apache.wicket.model.IDetachable#detach()
     */
    public void detach() {
        if (attached) {
            try {
                onDetach();
            } finally {
                attached = false;
                transientModelObject = null;
            }
        }
    }

    /**
     * @see org.apache.wicket.model.IModel#getObject()
     */    
    public List<? extends T> getObject() {
        
        if (!attached) {
            attached = true;
            transientModelObject = load();
            onAttach();
        }
        return transientModelObject;
    }

    /**
     * Gets the attached status of this model instance
     * 
     * @return true if the model is attached, false otherwise
     */
    public final boolean isAttached() {
        return attached;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append(":attached=").append(attached).append(":tempModelObject=[").append(this.transientModelObject).append("]");
        return sb.toString();
    }

    /**
     * Loads and returns the (temporary) model object.
     * 
     * @return the (temporary) model object
     */
    protected abstract List<? extends T> load();

    /**
     * Attaches to the current request. Implement this method with custom
     * behavior, such as loading the model object.
     */
    protected void onAttach() {
    }

    /**
     * Detaches from the current request. Implement this method with custom
     * behavior, such as setting the model object to null.
     */
    protected void onDetach() {
    }

    /**
     * Manually loads the model with the specified object. Subsequent calls to
     * {@link #getObject()} will return {@code object} until {@link #detach()}
     * is called.
     * 
     * @param object
     *            The object to set into the model
     */
    public void setObject(final List<? extends T> object) {
        attached = true;
        transientModelObject = !(object instanceof Serializable) ? this.createSerializableVersionOf(object) : object;
    }

} // End of class //
