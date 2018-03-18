package net.yaliun.basic.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="SetAccountProductRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class SetAccountProductReqVO {

	@XmlElement(name="request-id")
	private String requestId;
	
	private String msisdn;
	
	@XmlElement(name="product-code")
	private String productCode;
}
