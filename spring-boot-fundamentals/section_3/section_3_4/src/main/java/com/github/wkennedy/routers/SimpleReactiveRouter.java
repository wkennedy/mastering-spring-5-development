package com.github.wkennedy.routers;


import com.github.wkennedy.handlers.PersonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class SimpleReactiveRouter {

    private final PersonHandler personHandler;

    @Autowired
    public SimpleReactiveRouter(PersonHandler personHandler) {
        this.personHandler = personHandler;
    }

    public RouterFunction<?> routerFunction() {

        return nest(path("/react/functional/"),
                nest(accept(APPLICATION_JSON),
                        route(GET("/persons"), personHandler::getPersons)
                ).andRoute(GET("/person"), personHandler::getPerson));
    }
}
