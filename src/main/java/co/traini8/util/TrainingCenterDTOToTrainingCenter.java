package co.traini8.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.traini8.dto.TrainingCenterDTO;
import co.traini8.entity.Address;
import co.traini8.entity.TrainingCenter;

@Component
public class TrainingCenterDTOToTrainingCenter implements Converter<TrainingCenterDTO, TrainingCenter> {

	@Override
	public TrainingCenter convert(TrainingCenterDTO source) {
			
		TrainingCenter center = new TrainingCenter();
		
		Address address = new Address();
		
		address.setCity(source.getAddressDTO().getCity());
		address.setDetailedAddress(source.getAddressDTO().getDetailedAddress());
		address.setState(source.getAddressDTO().getState());
		address.setPincode(source.getAddressDTO().getPincode());
		
		center.setAddress(address);
		
		center.setCenterCode(source.getCenterCode());
		center.setCenterName(source.getCenterName());
		center.setContactEmail(source.getContactEmail());
		center.setContactPhone(source.getContactPhone());
		center.setCourses(source.getCourses());
		center.setStudentCapacity(source.getStudentCapacity());
		
		
		return center;
	}

	

}
