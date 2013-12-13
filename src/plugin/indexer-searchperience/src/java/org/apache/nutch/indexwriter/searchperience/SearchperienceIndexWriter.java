/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.nutch.indexwriter.searchperience;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.nutch.indexer.IndexWriter;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.metadata.Metadata;

import java.io.IOException;
import java.net.URLEncoder;

/**
 */
public class SearchperienceIndexWriter implements IndexWriter {

    private Configuration config;

    private Client client;

	private String endpoint;
	
	private String username;
	
	private String password;
	
    @Override
    public void open(JobConf job, String name) throws IOException {
        System.out.println("SEARCHPERIENCE BEFORE OPEN");



        try {
			endpoint 	= job.get(SearchperienceConstants.ENDPOINT);
			username	= job.get(SearchperienceConstants.USERNAME);
			password 	= job.get(SearchperienceConstants.PASSWORD);

            //create connection to rest api
            client = Client.create();
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Open catch");
        }
        System.out.println("SEARCHPERIENCE AFTER OPEN");

    }

    /**/
    @Override
    public void write(NutchDocument doc) throws IOException {
        System.out.println("SEARCHPERIENCE WRITE");
        LoggingFilter lg = new LoggingFilter();

        try {
   			String fullContent = doc.getField("content").toString();
 			String content = URLEncoder.encode(fullContent);


			String url = endpoint + "/documents?source=nutch&foreignId=test" + (int) Math.random() + "&mimeType=text/html&content=" + content;
			WebResource resource = client.resource(url);
            client.addFilter(new HTTPBasicAuthFilter(username,password));

            resource.addFilter(lg);
            System.out.println(resource.getURI());

            resource.post(String.class,"");
        } catch (UniformInterfaceException e) {
            System.out.println("Write catch 1");
            String rawResponse = e.getResponse().getEntity(String.class);
            int status = e.getResponse().getClientResponseStatus().getStatusCode();

            System.out.println("Error during request execution");
            System.out.println("Status: " + status);
            System.out.println("Content: " + rawResponse);

            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getClass());

            System.out.println("Write catch 2");
            System.out.println(e.getMessage());

            e.printStackTrace();
        }
    }

    @Override
    public void delete(String key) throws IOException {
        System.out.println("SEARCHPERIENCE DELETE");
        //delete by rest api
    }

    @Override
    public void update(NutchDocument doc) throws IOException {
        System.out.println("SEARCHPERIENCE UPDATE");
        //update by restapi
        write(doc);
    }

    @Override
    public void commit() throws IOException {
        System.out.println("SEARCHPERIENCE COMMIT");

    }

    @Override
    public void close() throws IOException {
//	  client.close();

        commit();
        System.out.println("SEARCHPERIENCE CLOSE");
    }

    @Override
    public String describe() {
        StringBuffer sb = new StringBuffer("SearchperienceIndexWriter\n");

        return sb.toString();
    }

    @Override
    public void setConf(Configuration conf) {
        config = conf;
    }

    @Override
    public Configuration getConf() {
        return config;
    }
} 
