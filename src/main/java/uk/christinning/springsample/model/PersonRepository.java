package uk.christinning.springsample.model;

import io.micrometer.core.annotation.Timed;
import org.springframework.data.repository.CrudRepository;

@Timed
public interface PersonRepository extends CrudRepository<Person, Long> {

}
