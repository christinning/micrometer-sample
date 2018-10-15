package uk.christinning.springsample.resource.person;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import uk.christinning.springsample.model.Person;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class PersonResourceAssembler extends ResourceAssemblerSupport<Person, PersonResource> {


    public PersonResourceAssembler(){
        super(PeopleController.class, PersonResource.class);
    }

    @Override
    public PersonResource toResource(Person person) {
        PersonResource resource = new PersonResource();
        resource.firstname = person.getFirstName();
        resource.lastname = person.getLastName();
        resource.add(linkTo(PeopleController.class).withRel("people"));
        resource.add(linkTo(PeopleController.class).slash(person).withSelfRel());
        return resource;
    }
}
