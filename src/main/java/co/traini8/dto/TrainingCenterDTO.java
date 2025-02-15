package co.traini8.dto;

import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrainingCenterDTO {

	@Positive(message = "Student Capacity must be a positive value")
	@NotNull(message = "Student Capacity cannot be null")
	@Min(10)
	@Max(140)
	private int studentCapacity;
	
	@NotBlank(message = "Center Code cannot be blank")
	@Size(min = 12, max = 12, message = "Center Code must be exactly 12 characters")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "Invalid center code type")
	private String centerCode;

	@NotBlank(message = "Email cannot be blank")
	@Size(min = 12, max = 254, message = "Email must be exactly 12 characters")
	@Pattern(regexp = "[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\-]+(\\.[A-Za-z0-9\\-]+)*\\.[A-Za-z]{2,}(?<!\\.\\.)", message = "Invalid email type")
	private String contactEmail;

	@NotBlank(message = "Phone cannot be blank")
	@Size(min = 10, max = 10, message = "Phone must be exactly 12 characters")
//	@Pattern(regexp = "^[6789](?!(\\d)\\1{11})\\d{11}$", message = "Invalid phone type")
	@Pattern(regexp = "^[789]\\d{9}$", message = "Invalid phone type")
	private String contactPhone;
	
	@NotBlank(message = "name cannot be blank")
	@Size(min = 2, max = 12, message = "name must be exactly 12 characters")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid name type")
	private String centerName;

	private AddressDTO addressDTO;
	
	private List<String> courses;

}
