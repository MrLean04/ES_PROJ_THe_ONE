package ES.flysite;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ES.flysite.Value;

public interface PlaneRepo extends CrudRepository<Value,Long> {

    //List<Value> findBycallsignValues(String callsign);

    Value findById(long id);
}