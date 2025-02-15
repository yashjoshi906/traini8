package co.traini8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {
	private String detailedAddress;
	private String city;
	private String state;
	private String pincode;

}
