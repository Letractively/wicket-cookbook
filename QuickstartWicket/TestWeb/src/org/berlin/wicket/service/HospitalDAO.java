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
package org.berlin.wicket.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HospitalDAO {

	public void testConnection() {
		try {
			Class.forName("org.h2.Driver");
	        final Connection conn = connect();
	        selectTest(conn);
	        insertTest(conn);
	        updateTest(conn);
	        selectTest(conn);
	        conn.close();		
		} catch(final Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Connection connect() throws Exception {
		Class.forName("org.h2.Driver");
        final Connection conn = DriverManager.getConnection("jdbc:h2:/home/berlin/hospital", "sa", "");        
        return conn;
	}
	
	private static void selectTest(final Connection conn) {
		System.out.println("[OUTPUT FROM SELECT]");
		String query = "SELECT ID, NAME FROM DOCTORS";
		try {
			final Statement st = conn.createStatement();
			final ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				final String id = rs.getString("ID");
				final String name = rs.getString("name");
				System.out.println(id + ": " + name);
			}
		} catch (final SQLException ex) {
			System.err.println(ex.getMessage());
		} // End of the try - catch //
	} // End of the method //

	private static void insertTest(final Connection conn) {
		System.out.println("[Performing INSERT] ... ");
		try {
			final Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO DOCTORS " + "VALUES (3, 'Bill Runner')");
		} catch (final SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	private static void updateTest(final Connection conn) {
		System.out.println("[Performing UPDATE] ... ");
		try {
			final Statement st = conn.createStatement();
			st.executeUpdate("UPDATE DOCTORS SET NAME='Ro Adams' WHERE ID=1");
		} catch (final SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
} // End of the class //
