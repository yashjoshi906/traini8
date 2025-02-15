package co.traini8.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import co.traini8.entity.TrainingCenter;
import co.traini8.repository.TrainingCenterRepository;
import co.traini8.service.TrainingCenterService;
import co.traini8.util.CenterSpecs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingCenterServiceImpl implements TrainingCenterService {

	private final TrainingCenterRepository repository;

	@Override
	public CompletableFuture<TrainingCenter> createCenter(TrainingCenter trainingCenter) {

		return CompletableFuture.supplyAsync(() -> repository.save(trainingCenter));
	}

	@Override
	public CompletableFuture<List<TrainingCenter>> createCenters(List<TrainingCenter> trainingCenters) {

		return CompletableFuture.supplyAsync(() -> {  return repository.saveAll(trainingCenters); });
	}

	@Override
	public Page<TrainingCenter> searchCenter(String centerName, String centerCode, Pageable pageable) {
		Specification<TrainingCenter> spec = Specification.where(null);
		
		boolean hasNoFilters = 
			    (!StringUtils.hasLength(centerCode)) && 
			    (!StringUtils.hasLength(centerName))  
			    ;
		if(hasNoFilters) { return repository.findAll(pageable); }
		
		if (StringUtils.hasLength(centerName)) {
			spec = spec.or(CenterSpecs.containsCenterName(centerName));
		}
		if (StringUtils.hasLength(centerCode)) {
			spec = spec.or(CenterSpecs.containsCenterCode(centerCode));
		}
		
				
		return repository.findAll(spec, pageable);
	}

}
