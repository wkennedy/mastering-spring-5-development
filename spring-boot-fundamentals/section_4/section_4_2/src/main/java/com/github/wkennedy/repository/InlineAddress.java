package com.github.wkennedy.repository;

import com.github.wkennedy.entity.AddressEntity;
import com.github.wkennedy.entity.PersonEntity;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineAddress", types = { PersonEntity.class })
interface InlineAddress {

    String getFirstName();

    String getLastName();

    AddressEntity getAddress();
}
