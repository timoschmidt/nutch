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
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.nutch.indexer.IndexWriter;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.net.protocols.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 */
public class SearchperienceIndexWriter implements IndexWriter {
  public static Logger LOG = LoggerFactory.getLogger(SearchperienceIndexWriter.class);

  private Configuration config;

  private Client client;

	@Override
  public void open(JobConf job, String name) throws IOException {
		System.out.println("SEARCHPERIENCE BEFORE OPEN");
		try {

			//create connection to rest api
			client = Client.create();
		} catch (Exception e) {
			if( e instanceof IOException) {
				IOException ioe = (IOException) e;
			} else {
				System.out.println(e.getClass());
			}
			e.printStackTrace();

			System.out.println("Open catch");
		}
		System.out.println("SEARCHPERIENCE AFTER OPEN");

  }

	@Override
	public void write(NutchDocument doc) throws IOException {
		System.out.println("SEARCHPERIENCE WRITE");
		SearchperienceDocument o;
		try {
		//	SearchperienceDocument document = new SearchperienceDocument();
		//	document.setForeignId("dsds");
			WebResource resource = client.resource("http://url/qvc/documents");
			client.addFilter(new HTTPBasicAuthFilter("user", "password"));

			resource.queryParam("source", "source");
			resource.queryParam("foreignId", "sasasa");
			resource.queryParam("url", "http://foo.vbox/foo");
			
			System.out.println(resource.getRequestBuilder().toString());
			String response = resource.post(String.class,"<foo>one</foo>");


		} catch (UniformInterfaceException e) {




			System.out.println("Write catch 1");

			System.out.println(e.getResponse().getClientResponseStatus());
			System.out.println(e.getResponse().getEntityInputStream());
			System.out.println(e.getClass());

			System.out.println(e.getMessage());
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