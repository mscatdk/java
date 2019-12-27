package com.mscatdk.security;

import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_1_0_ACCESS_SUBJECT;
import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_3_0_ACTION;
import static org.ow2.authzforce.xacml.identifiers.XacmlAttributeCategory.XACML_3_0_RESOURCE;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import org.ow2.authzforce.core.pdp.api.AttributeFqn;
import org.ow2.authzforce.core.pdp.api.AttributeFqns;
import org.ow2.authzforce.core.pdp.api.DecisionRequest;
import org.ow2.authzforce.core.pdp.api.DecisionRequestBuilder;
import org.ow2.authzforce.core.pdp.api.DecisionResult;
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
	
	public Authorization() {
		
	}
	
	public boolean authorization(DecodedJWT jwt, Request httpRequest) throws IllegalArgumentException, IOException {
		URL url = App.class.getClassLoader().getResource("pdp.xml");
		PdpEngineConfiguration pdpEngineConf = PdpEngineConfiguration.getInstance(url.toString());
		
		AttributeDatatype<StringValue> MY = new AttributeDatatype<>(StringValue.class, XacmlDatatypeId.STRING.value(), "demo.test.nu");
		
		logger.info("Subject: {}, Role: {}, Resource: {}, and action: {}", jwt.getSubject(), jwt.getClaim("role").asString(), httpRequest.pathInfo(), httpRequest.requestMethod());
		
		try (BasePdpEngine pdp = new BasePdpEngine(pdpEngineConf)) {		
			DecisionRequestBuilder<?> requestBuilder = pdp.newRequestBuilder(-1, -1);
			// Add subject ID attribute
			final AttributeFqn subjectIdAttributeId = AttributeFqns.newInstance(XACML_1_0_ACCESS_SUBJECT.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_SUBJECT_ID.value());
			final AttributeBag<?> subjectIdAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(jwt.getSubject()));
			requestBuilder.putNamedAttributeIfAbsent(subjectIdAttributeId, subjectIdAttributeValues);
	
			// Add subject role(s) attribute
			final AttributeFqn subjectRoleAttributeId = AttributeFqns.newInstance(XACML_1_0_ACCESS_SUBJECT.value(), Optional.empty(), XacmlAttributeId.XACML_2_0_SUBJECT_ROLE.value());
			final AttributeBag<?> roleAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(jwt.getClaim("role").asString()));
			requestBuilder.putNamedAttributeIfAbsent(subjectRoleAttributeId, roleAttributeValues);
	
			// Add resource ID attribute
			final AttributeFqn resourceIdAttributeId = AttributeFqns.newInstance(XACML_3_0_RESOURCE.value(), Optional.empty(), XacmlAttributeId.XACML_1_0_RESOURCE_ID.value());
			final AttributeBag<?> resourceIdAttributeValues = Bags.singletonAttributeBag(StandardDatatypes.STRING, new StringValue(httpRequest.pathInfo()));
			requestBuilder.putNamedAttributeIfAbsent(resourceIdAttributeId, resourceIdAttributeValues);
	
			// Add action ID attribute
			final AttributeFqn actionIdAttributeId = AttributeFqns.newInstance(XACML_3_0_ACTION.value(), Optional.empty(), "demo.test.nu");
			final AttributeBag<?> actionIdAttributeValues = Bags.singletonAttributeBag(MY, new StringValue(httpRequest.requestMethod()));
			requestBuilder.putNamedAttributeIfAbsent(actionIdAttributeId, actionIdAttributeValues);
	
			final DecisionRequest request = requestBuilder.build(false);
			final DecisionResult result = pdp.evaluate(request);
			return result.getDecision() == DecisionType.PERMIT; 
		}
	}

}
