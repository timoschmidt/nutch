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


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.indexer.IndexWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class SearchperienceIndexWriter implements IndexWriter {
  public static Logger LOG = LoggerFactory.getLogger(SearchperienceIndexWriter.class);

  private static final int DEFAULT_MAX_BULK_DOCS = 250;
  private static final int DEFAULT_MAX_BULK_LENGTH = 2500500;

  private Client client;
  private Node node;
  private String defaultIndex;

  private Configuration config;


  @Override
  public void open(JobConf job, String name) throws IOException {
	  //create connection to rest api
  }

  @Override
  public void write(NutchDocument doc) throws IOException {
	  //build searchperience xml document from nutch document
	  //push over restapi
  }

  @Override
  public void delete(String key) throws IOException {
	  //delete by rest api
  }

  @Override
  public void update(NutchDocument doc) throws IOException {
	  //update by restapi
    write(doc);
  }

  @Override
  public void commit() throws IOException {

  }

  @Override
  public void close() throws IOException {

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
