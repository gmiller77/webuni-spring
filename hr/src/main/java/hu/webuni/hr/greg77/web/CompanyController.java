package hu.webuni.hr.greg77.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.greg77.dto.CompanyDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companies = new HashMap<>();

	{
		companies.put(1L, new CompanyDto(1L, "11-11-111111", "Alma Kft.", "1234 Budapest"));
		companies.put(2L, new CompanyDto(2L, "22-22-222222", "Béta Kft.", "2345 Futapest"));
		companies.put(3L, new CompanyDto(3L, "33-33-333333", "Cirokseprű Kft.", "3456 Cegléd"));
		companies.put(4L, new CompanyDto(4L, "44-44-444444", "Dalmata Kft.", "4567 Dinnyés"));

	}

	@GetMapping
	public List<CompanyDto> getAll() {
		return new ArrayList<>(companies.values());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable long id) {
		CompanyDto companyDto = companies.get(id);
		if (companyDto != null)
			return ResponseEntity.ok(companyDto);
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		companies.put(companyDto.getId(), companyDto);
		return companyDto;
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if (!companies.containsKey(id)) {
			return ResponseEntity.notFound().build();
		}
		companyDto.setId(id);
		companies.put(id, companyDto);
		return ResponseEntity.ok(companyDto);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable long id) {
		companies.remove(id);
	}

}
