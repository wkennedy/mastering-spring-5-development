package com.github.wkennedy;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

public class SimpleEndpoint extends AbstractEndpoint<String> {

    public SimpleEndpoint(String id) {
        super(id);
    }

    public SimpleEndpoint(String id, boolean sensitive) {
        super(id, sensitive);
    }

    public SimpleEndpoint(String id, boolean sensitive, boolean enabled) {
        super(id, sensitive, enabled);
    }

    @Override
    public String invoke() {
        return "SimpleEndpoint invoked";
    }
}
