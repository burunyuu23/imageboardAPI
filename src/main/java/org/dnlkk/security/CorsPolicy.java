package org.dnlkk.security;

import com.dnlkk.dependency_injector.annotations.components.Component;
import com.dnlkk.security.CORSPolicy;
import com.dnlkk.security.annotations.CORS;

@CORS
@Component
public class CorsPolicy extends CORSPolicy {
    public CorsPolicy() {
        super(
                "*",
                "GET, POST, PUT, DELETE, PATCH",
                "Origin, X-Requested-With, Content-Type, Accept",
                "true"
        );
    }
}
