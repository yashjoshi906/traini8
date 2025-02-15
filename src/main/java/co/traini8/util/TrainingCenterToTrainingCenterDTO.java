package co.traini8.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import co.traini8.dto.AddressDTO;
import co.traini8.dto.TrainingCenterDTO;
import co.traini8.entity.TrainingCenter;

@Component
public class TrainingCenterToTrainingCenterDTO implements Converter<TrainingCenter, TrainingCenterDTO> {

	@Override
	public TrainingCenterDTO convert(TrainingCenter source) {
		
		TrainingCenterDTO dto = new TrainingCenterDTO();
		
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setCity(source.getAddress().getCity());
		addressDTO.setDetailedAddress(source.getAddress().getDetailedAddress());
		addressDTO.setState(source.getAddress().getState());
		addressDTO.setPincode(source.getAddress().getPincode());
		
		dto.setAddressDTO(addressDTO);
		dto.setCenterCode(source.getCenterCode());
		dto.setCenterName(source.getCenterName());
		dto.setContactEmail(source.getContactEmail());
		dto.setContactPhone(source.getContactPhone());
		dto.setCourses(source.getCourses());
		dto.setStudentCapacity(source.getStudentCapacity());
		
		return dto;
	}

}
