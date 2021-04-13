import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ES.flysite.Value;

@DataJpaTest
public class PlaneRepoTests {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository customers;

	@Test
	public void testFindBycallsign() {
		Value plane = new Value("icao24","firstSeen","estDepartureAirport","lastSeen","estArrivalAirport","callsign","estDepartureAirportHorizDistance","estDepartureAirportVertDistance","estArrivalAirportHorizDistance","estArrivalAirportVertDistance","departureAirportCandidatesCount","arrivalAirportCandidatesCount");
		entityManager.persist(plane);

		List<Value> findBycalsign = plane.findBycallsign(plane.getCallsign());

		assertThat(findBycalsign).extracting(plane::getCallsign).containsOnly(plane.getCallsign());
	}
}