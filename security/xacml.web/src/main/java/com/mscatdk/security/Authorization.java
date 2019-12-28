package com.mscatdk.security;

import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_1_0_ACCESS_SUBJECT;
import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_3_0_ACTION;
import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_3_0_RESOURCE;

import java.net.URL;
import java.util.Optional;

import org.ow2.authzforce.core.pdp.api.AttributeFqn;
import org.ow2.authzforce.core.pdp.api.AttributeFqns;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;
import org.ow2.authzforce.core.pdp.api.DecisionRequestBuilder;
import org.ow2.authzforce.core.pdp.api.value.AttributeBag;
import org.ow2.authzforce.core.pdp.api.value.AttributeDatatype;
import org.ow2.authzforce.core.pdp.api.value.Bags;
import org.ow2.authzforce.core.pdp.api.value.StandardDatatypes;
import org.ow2.authzforce.core.pdp.api.value.StringValue;
import org.ow2.authzforce.core.pdp.impl.BasePdpEngine;
import org.ow2.authzforce.core.pdp.impl.PdpEngineConfiguration;
import org.ow2.authzforce.xacml.identifiers.XacmlAttributeId;
import org.ow2.authzforce.xacml.identifiers.XacmlDatatypeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.interfaces.DecodedJWT;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.DecisionType;
import spark.Request;

public class Authorization {
	
	private static Logger logger = LoggerFactory.getLogger(Authorization.class);
	
	private final BasePdpEngine pdp;
	private static final AttributeDatatype<StringValue> CUSTOM_DATA_TYPE = new AttributeDatatype<>(StringValue.class, XacmlDatatypeId.STRING.value(), "http.method");
	private static Authorization authorization;
	
	static {
		try {
			System.setProperty("javax.xml.accessExternalSchema", "all");
			URL url = App.class.getClassLoader().getResource("pdp.xml");
			PdpEngineConfiguration pdpEngineConf = PdpEngineConfiguration.getInstance(url.toString());
			
			authorization = new Authorization(new BasePdpEngine(pdpEngineConf));
		} catch (Exception e) {
			throw new RuntimeException("Unable to create Authorization singleton", e);
		}
	}
	
	
	private Authorization(BasePdpEngine pdp) { 
		this.pdp = pdp;
	}
	
	
	public static Authorization getInstance() {
		return authorization;
	}
        	
	public boolean authorization(DecodedJWT jwt, Request httpRequest) {	
		final DecisionRequest request = generateRequest(jwt, httpRequest);
		return pdp.evaluate(request).getDecision() == DecisionType.PERMIT; 
		
	}


	private DecisionRequest generateRequest(DecodedJWT jwt, Request httpRequest) {
		/*
		 * Extract the values needed for the request
		 */
		String subject = jwt.getSubject();
		String role = jwt.getClaim("role").asString();
		String resource = httpRequest.pathInfo();
		String action = httpRequest.requestMethod();
		
		logger.debug("Subject: {}, Role: {}, Resource: {}, and action: {}", subject, role, resource, action);
		/*
		 * Package the request
		 */
		DecisionRequestBuilder<?> requestBuilder = pdp.newRequestBuilder(4, 4);
		// Add subject ID attribute
		final AttributeFqn subjectIdAttributeId = AttributeFqns.newInstance(XACML_1_0_ACCESS_SUBJECT.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_SUBJECT_ID.value());
		final AttributeBag<?> subjectIdAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(subject));
		requestBuilder.putNamedAttributeIfAbsent(subjectIdAttributeId, subjectIdAttributeValues);

		// Add subject role(s) attribute
		final AttributeFqn subjectRoleAttributeId = AttributeFqns.newInstance(XACML_1_0_ACCESS_SUBJECT.value(), Optional.empty(), XacmlAttributeId.XACML_2_0_SUBJECT_ROLE.value());
		final AttributeBag<?> roleAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(role));
		requestBuilder.putNamedAttributeIfAbsent(subjectRoleAttributeId, roleAttributeValues);

		// Add resource ID attribute
		final AttributeFqn resourceIdAttributeId = AttributeFqns.newInstance(XACML_3_0_RESOURCE.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_RESOURCE_ID.value());
		final AttributeBag<?> resourceIdAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(resource));
		requestBuilder.putNamedAttributeIfAbsent(resourceIdAttributeId, resourceIdAttributeValues);

		// Add action ID attribute
		final AttributeFqn actionIdAttributeId = AttributeFqns.newInstance(XACML_3_0_ACTION.value(), Optional.empty(), "http.method");
		final AttributeBag<?> actionIdAttributeValues = Bags.singletonAttributeBag(CUSTOM_DATA_TYPE, new StringValue(action));
		requestBuilder.putNamedAttributeIfAbsent(actionIdAttributeId, actionIdAttributeValues);

		return requestBuilder.build(false);
	}

}
