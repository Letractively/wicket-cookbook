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

public class HospitalDAODriver {

	public static void main(final String [] args) throws Exception {
		
		/*
		 * ------------
		 * Setup Query:		 
		 * ------------
		 * 	DROP TABLE IF EXISTS TEST;
		 *	CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));
		 *	INSERT INTO TEST VALUES(1, 'Hello');
		 *	INSERT INTO TEST VALUES(2, 'World');
		 *	SELECT * FROM TEST ORDER BY ID;
		 *	UPDATE TEST SET NAME='Hi' WHERE ID=1;
		 *  DELETE FROM TEST WHERE ID=2;
		 * --------------		
		 * 	DROP TABLE IF EXISTS DOCTORS;
		 *	CREATE TABLE DOCTORS(ID INT PRIMARY KEY, NAME VARCHAR(255));
		 *	INSERT INTO DOCTORS VALUES(1, 'John Taylor');
		 *	INSERT INTO DOCTORS VALUES(2, 'Mary Reno');
		 *	SELECT * FROM DOCTORS ORDER BY ID;
		 *
		 * DROP TABLE IF EXISTS PATIENTS;
		 * CREATE TABLE PATIENTS(ID INT PRIMARY KEY, NAME VARCHAR(255));
		 * INSERT INTO PATIENTS VALUES(1, 'John Taylor');
		 * INSERT INTO PATIENTS VALUES(2, 'Mary Reno');
		 * SELECT * FROM PATIENTS ORDER BY ID;
		 */
		
		final HospitalDAO dao = new HospitalDAO(); 
		dao.testConnection();
	} // End of the class //
	
} // End of the class //