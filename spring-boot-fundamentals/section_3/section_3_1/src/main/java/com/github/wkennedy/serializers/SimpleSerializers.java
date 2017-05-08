package com.github.wkennedy.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.wkennedy.dto.Order;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.IOException;

@JsonComponent //automatically registers with Jackson in the ApplicationContext
public class SimpleSerializers {

    public static class OrderSerializer extends JsonObjectSerializer<Order> {

        @Override
        protected void serializeObject(Order value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStringField("LastName", value.getCustomer().getLastName());
            jgen.writeStringField("FirstName", value.getCustomer().getFirstName());
            jgen.writeNumberField("amount", value.getQuantity());
            jgen.writeStringField("Product", value.getProductName());
        }
    }


}
