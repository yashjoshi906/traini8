package co.traini8.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "training_center")	
public class TrainingCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "center_id", unique = true, nullable = false)
	private long centerId;
	@Column(name = "student_capacity", nullable = false)
	private int studentCapacity;
	@Column(name = "center_code", unique = true, nullable = false)
	private String centerCode;
	@Column(name = "contact_email", unique = true, nullable = false)
	private String contactEmail;
	@Column(name = "contact_phone", unique = true, nullable = false)
	private String contactPhone;
	@Column(name = "center_name", unique = true, nullable = false)
	private String centerName;
	@Column(name = "created_on", nullable = false)
	private long createdOn = System.currentTimeMillis();
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	private Address address;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "courses_offered", joinColumns = @JoinColumn(name = "center_id"))
	private List<String> courses;

}
