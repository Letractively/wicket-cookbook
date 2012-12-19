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

/* Admin Page */

package org.berlin.wicket.admin;

/*
 * ===========================================================================
 (C) Copyright Berlin Brown 2000, 2012. All rights reserved.
 * ===========================================================================
 * @(#).java    1.00 03/12/2012
 */

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.berlin.wicket.components.LoadableDetachableList;
import org.berlin.wicket.service.HospitalDAO;
import org.berlin.wicket.util.NullRef;

/**
 * Wicket based Homepage for admin page.
 */
public class AdminHospitalPage extends WebPage {

    // Tested with : Wicket Version: apache-wicket-6.4.0 / http://code.google.com/p/wicket-cookbook/ / Tested With Server:  Apache Tomcat/7.0.34
    
    /**
     * Serial version id.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public AdminHospitalPage() {
        this(new PageParameters());
    }

    /**
     * Constructor that is invoked when page is invoked without a session.
     * 
     * @param parameters
     *            Page parameters
     */
    public AdminHospitalPage(final PageParameters parameters) {
        super(parameters);
        // CreateDoctorData is a simple POJO bean with a few fields including the name field
        // This object is passed to the wicket compound property model.
        // A model in wicket is essentially an "accessor" object, for accessing the model object
        // A compound property model can: use the component's name as the property expression to retrieve
        // properties on the nested model object.
        // Pass the model, the compound property model to the 'form' component.
        final CreateDoctorData data = new CreateDoctorData();
        final CompoundPropertyModel<CreateDoctorData> model = new CompoundPropertyModel<CreateDoctorData>(data);
        final Form<CreateDoctorData> form = new Form<CreateDoctorData>("form", model) {
            private static final long serialVersionUID = 1L;
            @Override
            protected void onSubmit() {
                // The form component has a onSubmit handler method.
                // OnSubmit, respond to this method
                System.out.println("Submitting<2> ... " + data.getOperation());
                if (data.getOperation().equalsIgnoreCase("UpdateAllStatic")) {
                    update(data);
                } else if (data.getOperation().equalsIgnoreCase("Change")) {
                    update(data);
                } else {
                    update(data);
                }
            } // End of the method onSubmit //
        };
        
        // Add the 'form' component to the page.
        this.add(form);

        // Build a drop down wicket object
        final DropDownChoice<String> operation = new DropDownChoice<String>("operation", 
                Arrays.asList(new String[] { "Add New Name" }));
        form.add(operation);

        // Build a drop down wicket object
        final DropDownChoice<String> provider = new DropDownChoice<String>("provider", 
                Arrays.asList(new String[] { "Blue Cross", "Aetna" }));
        form.add(provider);

        final DropDownChoice<String> agent = new DropDownChoice<String>("name", 
                Arrays.asList(new String[] { "Bob Runner", "Xu Chin" }));
        form.add(agent);

        final Button button = new Button("submit") {
            private static final long serialVersionUID = 1L;
            public void onSubmit() {
                // The Wicket on submit button does not have any handler logic
            }
        };
        form.add(button);
        
        // Add rows //
        this.add(new Rows());
        
    } // End of constructor //

    public static class CreateDoctorData implements Serializable {
        /**
         * Serial version id.
         */
        private static final long serialVersionUID = 1L;
        private String operation = "";
        private String name = "";
        private String provider = "";
        
        public String getOperation() {
            return operation;
        }
        public void setOperation(String operation) {
            this.operation = operation;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getProvider() {
            return provider;
        }
        public void setProvider(String provider) {
            this.provider = provider;
        }                
    } // End of the class //

    public void update(final CreateDoctorData d) {
        if (d == null) {
            System.out.println("Exiting ... ");
            return;
        }
        if (NullRef.nil(d.operation)) {
            System.out.println("Exiting ... invalid ");
            return;
        }        
        final Random r = new Random(System.currentTimeMillis());
        final HospitalDAO dao = new HospitalDAO();
        final String name = d.name + r.nextInt(10000);
        dao.insertName(name);
    } // End of the method //

    private static class Rows extends ListView<CreateDoctorData> { 
        // A ListView is a repeater that makes it easy to display/work with lists.
        private static final long serialVersionUID = 6715537216006650768L;        
        public Rows() {
            super("rows", data());
        }
        private static final LoadableDetachableList<CreateDoctorData> data() {
            
            // LoadableDetachableModel holds a temporary, transient model object, that is
            // set when getObject is called by calling abstract method
            // 'load', 
            
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
} // End of the Class //