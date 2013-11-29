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


import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.nutch.indexer.IndexWriter;
import org.apache.nutch.indexer.NutchDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

import java.io.IOException;

/**
 */
public class SearchperienceIndexWriter implements IndexWriter {
  public static Logger LOG = LoggerFactory.getLogger(SearchperienceIndexWriter.class);

  private Configuration config;

  private WebClient client;

	@Override
  public void open(JobConf job, String name) throws IOException {
	  //create connection to rest api
		try {
			client = WebClient.create("http://api.saascluster.local/qvc/documents/");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

  }

  @Override
  public void write(NutchDocument doc) throws IOException {
	  System.out.println("SEARCHPERIENCE WRITE");
	  try {
		  String document = new String();
		  System.out.println("SEARCHPERIENCE ONE");
		  Response response = client.post(new String(""));
	//	  System.out.println(response);
	  } catch (Exception e) {
		  System.out.println(e.getMessage());
	  }

	  System.out.println("SEARCHPERIENCE TEST");
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
	  client.close();

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
