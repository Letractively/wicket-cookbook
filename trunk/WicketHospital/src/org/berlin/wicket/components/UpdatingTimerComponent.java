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



package org.berlin.wicket.components;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

/**
 * Send basic message to to server at timed intervals. A send message component
 * sends an automatic request back to the component. The request can be used to
 * determine request time between server and client.
 */
public class UpdatingTimerComponent extends Label {

    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1618434691872092675L;

    public static final String ID = "sendMessageLogLabel";
    private final static Logger LOGGER = Logger.getLogger(UpdatingTimerComponent.class);
    private final AjaxSelfUpdatingTimerBehavior updatingTimerBehaviorHeartbeat;
    private int counter = 0;
    private int maxNumberIntervals = 5;

    /**
     * Constructor for core send message component.
     */
    public UpdatingTimerComponent() {
        this(ID, 220);
    }

    public UpdatingTimerComponent(final int durationSeconds) {
        this(ID, durationSeconds);
    }

    public UpdatingTimerComponent(final String id, final int durationSeconds) {
        super(id, new Model<String>(""));
        counter = 0;
        this.updatingTimerBehaviorHeartbeat = new AjaxSelfUpdatingTimerBehavior(Duration.seconds(durationSeconds)) {
            private static final long serialVersionUID = 1L;
            @Override
            protected void onPostProcessTarget(final org.apache.wicket.ajax.AjaxRequestTarget target) {
                LOGGER.info("Received response from client [hb51] - ctr=" + counter);
                if (counter >= getMaxNumberIntervals()) {
                    // Remove from component
                    try {
                        this.stop(target);
                        UpdatingTimerComponent.this.remove(this);
                    } catch (Exception e) {
                        LOGGER.warn("Error removing timer behavior from component, non-critical warning", e);
                    }
                    counter = 0;
                } // End of if, check for removal
                counter++;
            };
        };
        this.add(updatingTimerBehaviorHeartbeat);
    }

    /**
     * @return the maxNumberIntervals
     */
    public int getMaxNumberIntervals() {
        return maxNumberIntervals;
    }

    /**
     * @param maxNumberIntervals
     *            the maxNumberIntervals to set
     */
    public void setMaxNumberIntervals(int maxNumberIntervals) {
        this.maxNumberIntervals = maxNumberIntervals;
    }

} // End of the Class //
