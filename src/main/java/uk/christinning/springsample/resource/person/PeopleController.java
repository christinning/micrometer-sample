package uk.christinning.springsample.resource.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uk.christinning.springsample.model.Person;
import uk.christinning.springsample.model.PersonRepository;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@RequestMapping("/people")
class PeopleController {

    private final PersonRepository repository;
    private final PersonResourceAssembler resourceAssembler;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    PeopleController(PersonRepository repository, PersonResourceAssembler resourceAssembler,
                     ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.resourceAssembler = resourceAssembler;
        this.eventPublisher = eventPublisher;
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<PersonResource>> showAll() {
        return new HttpEntity<>(resourceAssembler.toResources(repository.findAll()));
    }

    @RequestMapping(value = "/{person}", method = RequestMethod.GET)
    public HttpEntity<PersonResource> show(@PathVariable Long person) {
        return repository.findById(person)
                .map(resourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{person}")
    public HttpEntity<PersonResource> delete(@PathVariable Long person) {
        repository.deleteById(person);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public HttpEntity<List<PersonResource>> create(@Validated @RequestBody PersonResource person) {
        Person entity = repository.save(new Person(null, person.firstname, person.lastname));

        eventPublisher.publishEvent(new PersonCreatedEvent(this));

        final URI location = linkTo(PeopleController.class).slash(entity).toUri();
        return ResponseEntity.created(location).build();
    }

    private static class PersonCreatedEvent extends ApplicationEvent{
        public PersonCreatedEvent(Object source) {
            super(source);
        }
    }

}
