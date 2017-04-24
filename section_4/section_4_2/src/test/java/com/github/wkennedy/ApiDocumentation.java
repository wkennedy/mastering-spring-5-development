package com.github.wkennedy;

import com.github.wkennedy.entity.PersonEntity;
import com.github.wkennedy.repository.AddressRepository;
import com.github.wkennedy.repository.PersonRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ApiDocumentation {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("target/generated-snippets");

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void indexExample() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(document("index-example",
                        links(halLinks(),
                                linkWithRel("person").description("The <<resources-person,Person resource>>"),
                                linkWithRel("address").description("The <<resources-tags,Address resource>>"),
                                linkWithRel("profile").description("The ALPS profile for the service")),
                        responseFields(
                                subsectionWithPath("_links").description("<<resources-index-links,Links>> to other resources"))));

    }

    @Test
    public void person() throws Exception {
        createPerson("John", "Doe");
        createPerson("Jane", "Doe");

        this.mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andDo(document("person-list-example",
                        links(halLinks(),
                                linkWithRel("self").description("Canonical link for this resource"),
                                linkWithRel("profile").description("The ALPS profile for this resource")),
                        responseFields(
                                subsectionWithPath("_embedded.person").description("An array of <<resources-person, Person resources>>"),
                                subsectionWithPath("_links").description("<<resources-tags-list-links, Links>> to other resources"))));
    }

    private void createPerson(String firstName, String lastName) {
        PersonEntity person = new PersonEntity();
        person.setFirstName(firstName);
        person.setLastName(lastName);

        this.personRepository.save(person);
    }


}
