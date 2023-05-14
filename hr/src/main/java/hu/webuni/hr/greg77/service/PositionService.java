package hu.webuni.hr.greg77.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.greg77.model.Employee;
import hu.webuni.hr.greg77.model.Position;
import hu.webuni.hr.greg77.repository.PositionRepository;

@Service
public class PositionService {

	@Autowired
	PositionRepository positionRepository;
	
	public void setPosition(Employee employee) {	
		
		String positionName = employee.getPosition().getName();
		Position position = null;
		if(positionName != null) {
			List<Position> positions = positionRepository.findByName(positionName);
			
			if(positions.isEmpty()) {
				position = positionRepository.save(new Position(positionName, null));
			} else {
				position = positions.get(0);
			}
			employee.setPosition(position);
		}		
	}
}
