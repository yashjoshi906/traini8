package co.traini8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.traini8.entity.TrainingCenter;

public interface TrainingCenterRepository
		extends JpaRepository<TrainingCenter, Long>, JpaSpecificationExecutor<TrainingCenter> {

}
