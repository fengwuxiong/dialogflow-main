package com.dialogflow.dialogflow.interceptor;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Enumeration;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "authorization";
    final NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
    final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

    public AuthInterceptor() throws GeneralSecurityException, IOException {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws GeneralSecurityException, IOException {
        Enumeration headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                System.out.println("Header key:" + key + " ,Header value:" + value);
            }
        }

        String auth = request.getHeader(AUTHORIZATION).substring(7);


        GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                       // .setAudience(Collections.singletonList("101240584490371549616"))
                        .build();
        System.out.println("idToken:" + auth);
        GoogleIdToken idToken = verifier.verify(auth);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            System.out.println("email: " + email);
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            System.out.println("emailVerified: " + emailVerified);
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            System.out.println("hd:" + payload.getHostedDomain());
            // Use or store profile information
            // ...

        } else {
            System.out.println("Invalid ID token.");
            return false;
        }


        return true;
    }
}
