package co.traini8.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.traini8.dto.TrainingCenterDTO;
import co.traini8.entity.Address;
import co.traini8.entity.TrainingCenter;
import co.traini8.error.BusinessException;
import co.traini8.service.TrainingCenterService;
import co.traini8.util.TrainingCenterDTOToTrainingCenter;
import co.traini8.util.TrainingCenterToTrainingCenterDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/traini8")
public class TrainingCenterController {
	
	private final Faker faker;
	private final TrainingCenterService service;
	private final TrainingCenterDTOToTrainingCenter trainingCenterDTOToTrainingCenter;
	private final TrainingCenterToTrainingCenterDTO trainingCenterToTrainingCenterDTO;
	
	@PostMapping("/add-center")
	public CompletableFuture<ResponseEntity<TrainingCenterDTO>> createCenter(@Valid @RequestBody TrainingCenterDTO dto) {
		
		var c1 = trainingCenterDTOToTrainingCenter.convert(dto);
		
		
		return service.createCenter(c1)
		        .thenCompose(e -> {
		            log.info("address {}", e.getAddress().getCity());
		            log.info(" thread is virtual {}", Thread.currentThread().isVirtual());
		            return CompletableFuture
		                    .supplyAsync(() ->
		                            ResponseEntity.ok().body(trainingCenterToTrainingCenterDTO.convert(e)));
		        })
		        .exceptionallyCompose(ex -> {
		            log.error("Error creating center", ex);
		            CompletableFuture<ResponseEntity<TrainingCenterDTO>> failedFuture = new CompletableFuture<>();
		            failedFuture.completeExceptionally(new BusinessException("Failed to create training center because " + ex.getMessage(), ex));
		            return failedFuture;
		        });
		
		
		
	}
	
	@PostMapping("/seed-centers")
	public CompletableFuture<ResponseEntity<List<TrainingCenterDTO>>> createCenter() {
		
		List<TrainingCenter> trainingCenters = new ArrayList<>();
		
		
		for(int i =0; i<3; i++) {
			Address address = new Address();
			address.setCity(faker.address().city());
			address.setPincode(faker.address().postcode());
			address.setState(faker.address().state());
			address.setDetailedAddress(faker.address().fullAddress());
			
			
			List<String> lStrings  = new ArrayList<>();
			lStrings.add(faker.name().femaleFirstName());
			lStrings.add(faker.name().femaleFirstName());
			lStrings.add(faker.name().femaleFirstName());
			
			TrainingCenter saveCenter = new TrainingCenter();
			
			saveCenter.setStudentCapacity(12);
			saveCenter.setCourses(lStrings);
			saveCenter.setCenterCode(faker.random().hex()+faker.number().positive()+"09");
			saveCenter.setCenterName(faker.camera().brand());
			saveCenter.setContactEmail(faker.name().lastName()+"@gmail.com");
			saveCenter.setContactPhone(faker.phoneNumber().cellPhone());
			saveCenter.setAddress(address);
			trainingCenters.add(saveCenter);
		}
		
		
	     return service.createCenters(trainingCenters) 
	                .thenApply(savedCenters -> {  
	                    List<TrainingCenterDTO> dtoList = savedCenters.stream()
	                            .map(trainingCenterToTrainingCenterDTO::convert) 
	                            .collect(Collectors.toList());  
	                    return ResponseEntity.ok().body(dtoList); 
	                })
	                 .exceptionally(ex -> {
	                     return ResponseEntity.internalServerError().body(null); 
	                 });
		
		
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<TrainingCenterDTO>> searchCar(@RequestParam(required = false) String centerName,
			@RequestParam(required = false) String centerCode,
			Pageable pageable) {
		Page<TrainingCenter> center = service.searchCenter(centerName, centerCode, pageable);
		Page<TrainingCenterDTO> pageDto = center.map(trainingCenterToTrainingCenterDTO::convert);
		return new ResponseEntity<>(pageDto, HttpStatus.OK);
	}
	
	

}
