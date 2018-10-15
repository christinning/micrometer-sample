package uk.christinning.springsample.resource.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;

public class PersonResource extends ResourceSupport {

    @NotBlank
    @JsonProperty
    String firstname;

    @NotBlank
    @JsonProperty
    String lastname;
}
