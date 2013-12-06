package org.apache.nutch.indexwriter.searchperience;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: timo.schmidt
 * Date: 29.11.13
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class SearchperienceDocument {

	protected String foreignId;

	@XmlElement(name = "foreign_id")
	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}
}
