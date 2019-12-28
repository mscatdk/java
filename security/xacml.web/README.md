# XACML web

This project demonstrates authentication and authorization based on JWT and XACML. The JWT can be generated using the jwt.util project or you can use the following token:

````jwt
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZSI6InVzZXIiLCJpc3MiOiJGYWtlVG9rZW4ifQ.WmidfA8pQ_PAIYdOoxX5LYoE43i2A6Jmpl--mhcMRpZvIz71gDBE6QibwtA1hqBvENxm6E_Y_NHzbJ-AigxPsk2NlEKZ6HJM6K-iV6ZtKQl9lkIxeCXKHrNaVViPTWs0aMufbm1QOJsSsIZ-u3EPmy411KLMiHCzOFOh6EhdY_fx30G13rncvmaJM5pFtO77mfWZnNzJ6bifgkz5qV-jcLbfw1sZYTnZ6jUritE0Yabr6tFnWUmeRbQQ3uiHW8YZG6Hh9oN9Fu8rMWnwxL5CKhpjDycKxiFIPl6KIfH2LLALYqUaIrtcrEyFMLZbKJzMOoA7yTs4eOJdoWVUefNT4g
````

The JWT token must be provided in the Authorization HTTP header. The payload of the JWT, provided above, contain the following:

````json
{
  "sub": "Mike",
  "role": "user",
  "iss": "FakeToken"
}
````

The XACML implementation is based on [AuthzForce](https://github.com/authzforce/core) and the Policy Enforcement Point (PEP) generate a request to the Policy Decision Point (PDP) with the following mapping

| Attribute | Source |
| --------- | ------ |
| urn:oasis:names:tc:xacml:1.0:subject:subject-id | The sub claim of the JWT |
| urn:oasis:names:tc:xacml:2.0:subject:role | The role claom of the JWT |
| urn:oasis:names:tc:xacml:1.0:resource:resource-id | The relative URL from the request object |
| http.method | The HTTP method from the request object |

The application has two end-points namely /hello and /secret. The above JWT provide access to /hello, but can't access /secret. The policy is saved on the resources folder in the file policy.xml.
