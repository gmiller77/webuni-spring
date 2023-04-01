package hu.webuni.airport.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.airport.dto.AirportDto;
import hu.webuni.airport.mapper.AirportMapper;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.service.AirportService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

	@Autowired
	AirportService airportService;
	
	@Autowired
	AirportMapper airportMapper;
	
//	private Map<Long, AirportDto> airports = new HashMap<>();
//	
//	{
//		airports.put(1L, new AirportDto(1, "Ferihegy", "BUD"));
//		airports.put(2L, new AirportDto(2, "Fiumicino", "ROM"));
//		airports.put(3L, new AirportDto(3, "Heathrow", "LON"));
//	}
//	
	@GetMapping
	public List<AirportDto> getAll() {		
		return airportMapper.airportToDtos(airportService.findAll());
	}
	
	@GetMapping("/{id}")
	public AirportDto getById(@PathVariable long id) {
		Airport airport = airportService.findById(id);
		if (airport != null)
			return airportMapper.airportToDto(airport);
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
	}
//
//	/* korábbi megoldás - week 2-3
//	@GetMapping("/{id}")
//	public ResponseEntity<AirportDto> getById(@PathVariable long id) {
//		AirportDto airportDto = airports.get(id);
//		if (airportDto != null)
//			return ResponseEntity.ok(airportDto);
//		else
//			return ResponseEntity.notFound().build(); 
//	}
//	*/
//	
	@PostMapping
	public AirportDto createAirport(@RequestBody @Valid AirportDto airportDto) {
		Airport airport = airportService.save(airportMapper.dtoToAirport(airportDto));
		return airportMapper.airportToDto(airport);
	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<AirportDto> modifyAirport(@PathVariable long id, @RequestBody AirportDto airportDto) {
//		checkUniqueIata(airportDto.getIata());
//		if (!airports.containsKey(id)) {
//			return ResponseEntity.notFound().build();
//		}
//		
//		airportDto.setId(id);
//		airports.put(id, airportDto);		
//		return ResponseEntity.ok(airportDto);
//	}
//	
//	private void checkUniqueIata(String iata) {
//		Optional<AirportDto> airportWithSameIata = airports.values()
//				.stream()
//				.filter(a -> a.getIata().equals(iata))
//				.findAny();
//		if (airportWithSameIata.isPresent())
//			throw new NonUniqueIataException(iata);
//	}
//
//	@DeleteMapping("/{id}")
//	public void deleteAirport(@PathVariable long id) {
//		airports.remove(id);
//	}
	
}
