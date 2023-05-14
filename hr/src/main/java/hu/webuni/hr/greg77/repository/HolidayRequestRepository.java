package hu.webuni.hr.greg77.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.webuni.hr.greg77.model.HolidayRequest;

public interface HolidayRequestRepository extends JpaRepository<HolidayRequest, Long>, JpaSpecificationExecutor<HolidayRequest> {
}
