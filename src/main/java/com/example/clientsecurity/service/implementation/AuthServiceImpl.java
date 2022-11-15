package com.example.clientsecurity.service.implementation;

import com.example.clientsecurity.model.AuthToken;
import com.example.clientsecurity.service.AuthService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {

    public static final String HOST = "http://api.tigerhrm.tigerit.com";
    public static final String PORT = ":8080/tigerit-hrm-api";
    public static final String AUTH_URI = HOST + PORT + "/oauth/token";
    public static final String QPM_PASSWORD_GRANT = "?grant_type=password";

    @Override
    public ResponseEntity<AuthToken> getAuthentication(String userName, String password) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AuthToken> response = null;

        ParameterizedTypeReference<AuthToken> typeReference = new ParameterizedTypeReference<AuthToken>() {};

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("grant_type", "password");
        requestMap.add("username", userName);
        requestMap.add("password", password);

        try{
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestMap, getHeadersWithClientCredentials());
            response = restTemplate.exchange(AUTH_URI + QPM_PASSWORD_GRANT, HttpMethod.POST, requestEntity, typeReference);
        }catch(Exception e){
            e.printStackTrace();
        }


        return response;
    }

    @Override
    public HttpHeaders getHeadersWithClientCredentials() {
        String clientCredential = "tiger-hrm-webapp-rw:tiger-hrm-webapp-rw-1234";
        String base64clientCredential = new String(Base64.encodeBase64(clientCredential.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(new MediaType("application", "x-www-form-urlencoded"));
        headers.add("Authorization", "Basic "+ base64clientCredential);

        return headers;
    }
}
