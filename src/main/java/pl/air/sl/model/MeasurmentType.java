package pl.air.sl.model;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "measurment_types")
@AttributeOverride(column = @Column(name = "mst_id"), name = "id")
@NoArgsConstructor @Getter @Setter @SuperBuilder @AllArgsConstructor
public class MeasurmentType extends BaseEntity {
	
	@NotBlank(message = "pole nie może być puste")
	@Size(max = 50)
	@NotNull
	private String measurmentType;


}
