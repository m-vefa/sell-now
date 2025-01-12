package org.zsell.agentgateway.configuration.feign.listing;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ListingClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new Default().decode(methodKey, response);
    }
}