package net.yaliun.basic.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="UpdateMobileResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpdateMobileResVO {

	private String result;
	
	@XmlElement(name="session-id")
	private String sessionId;
	
	private String msisdn;
}
