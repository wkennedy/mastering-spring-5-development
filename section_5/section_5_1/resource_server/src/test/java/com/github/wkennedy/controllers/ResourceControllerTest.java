//package com.github.wkennedy.controllers;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.hateoas.MediaTypes;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.codec.Base64;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.web.context.WebApplicationContext;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class ResourceControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private FilterChainProxy filterChain;
//
//    private MockMvc mvc;
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//
//    @Before
//    public void setUp() throws Exception {
//        this.mvc = webAppContextSetup(this.context).addFilters(this.filterChain).build();
//        SecurityContextHolder.clearContext();
//    }
//
//    @Test
//    public void index() throws Exception {
//        String header = "Basic " + new String(Base64.encode("foo:bar".getBytes()));
//        MvcResult result = this.mvc
//                .perform(post("oauth/token").header("Authorization", header)
//                        .param("grant_type", "password").param("scope", "read")
//                        .param("username", "user").param("password", "pwd"))
//                .andExpect(status().isOk()).andDo(print()).andReturn();
//        Object accessToken = this.objectMapper
//                .readValue(result.getResponse().getContentAsString(), Map.class)
//                .get("access_token");
//    }
//
//}