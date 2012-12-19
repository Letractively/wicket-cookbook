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

/* Quick Start Page */

package org.berlin.wicket;

/*
 * ===========================================================================
 (C) Copyright Berlin Brown 2000, 2012. All rights reserved.
 * ===========================================================================
 * @(#).java    1.00 03/12/2012
 */

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.berlin.wicket.admin.AdminHospitalPage;
import org.berlin.wicket.admin.AdminHospitalPage.CreateDoctorData;
import org.berlin.wicket.components.LoadableDetachableList;
import org.berlin.wicket.service.HospitalDAO;

/**
 * Base class for HTML pages. This subclass of Page simply returns HTML when asked for its markup
 * type. It also has a method which subclasses can use to retrieve a bookmarkable link to the
 * application's home page. 
 */
public class QuickstartPage extends WebPage {

    // Tested with : Wicket Version: apache-wicket-6.4.0 / http://code.google.com/p/wicket-cookbook/ / Tested With Server:  Apache Tomcat/7.0.34
    
	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 1L;
	
	/** Logging object. */
	private static final Logger LOG = Logger.getLogger(QuickstartPage.class);
	
	/**
	 * Constructor.
	 */
	public QuickstartPage() {
		this(new PageParameters());		 
	}
	/**
	 * Constructor.
	 * 
	 * @param pageParameters
	 */
	public QuickstartPage(final PageParameters pageParameters) {
	    
	    // The quickstart page is our entry page for this web-application.
	    // This is where everything starts 
	    
		super(pageParameters);
		LOG.info("Loading constructor");
		
		// Add a link component to this page
		final Link<Void> link = new Link<Void>("adminLink") {
            /**
             * Serial version id.
             */
            private static final long serialVersionUID = 1L;
            @Override
            public void onClick() {
                // On click, move to this AdminHospitalPage
                setResponsePage(AdminHospitalPage.class);
            }		    
		};
		this.add(new Label("message", "Dynamic Wicket Label - Hello World / QuickStart"));
		this.add(link);
		
        // Add rows //
        this.add(new Rows());
        
	} // End of the method //
 
	/**
	 * Construct.
	 * 
	 * @param model
	 */
	public QuickstartPage(final IModel<?> model) {
		super(model);
	}
	
	@Override
	protected void onBeforeRender() {
		LOG.info("At onBeforeRender for QuickStartPage (2)");
		super.onBeforeRender();
	}	
	
	 private static class Rows extends ListView<CreateDoctorData> {                
	        private static final long serialVersionUID = 6715537216006650768L;        
	        public Rows() {
	            super("rows", data());
	        }
	        private static final LoadableDetachableList<CreateDoctorData> data() {
	            return new LoadableDetachableList<CreateDoctorData>() {                
	                private static final long serialVersionUID = 1L;
	                @Override
	                protected List<? extends CreateDoctorData> load() {                    
	                    final HospitalDAO dao = new HospitalDAO();
	                    return dao.select();
	                }
	            };                    
	        } // End of the method //
	        @Override
	        protected void populateItem(final ListItem<CreateDoctorData> item) {
	            item.setDefaultModel(new CompoundPropertyModel<CreateDoctorData>(item.getModelObject()));
	            item.add(new Label("name"));
	        }        
	 } // End of the listview class //    
	
} // End of the class //
