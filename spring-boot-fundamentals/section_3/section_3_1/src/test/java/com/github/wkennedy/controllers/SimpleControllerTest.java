package com.github.wkennedy.controllers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SimpleController.class)
public class SimpleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebClient webClient;

    @Test
    public void getOrder() throws Exception {
        this.mvc.perform(get("/orders").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.LastName", is("Doe")))
                .andExpect(jsonPath("$.FirstName", is("John")));
    }

    @Test
    public void testIndex() throws Exception {
        HtmlPage page = this.webClient.getPage("/index.html");
        assertThat(page.getBody().getTextContent()).isEqualTo("\n" +
                "\n" +
                "    Mastering Spring!\n" +
                "    This is the index page.\n" +
                "\n" +
                "\n" +
                "");
    }
}
