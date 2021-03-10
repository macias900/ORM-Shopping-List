package pl.air.sl.model;



import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "shopping_items")
@AttributeOverride(column = @Column(name ="item_id"), name = "id")
@NoArgsConstructor @Getter @Setter @SuperBuilder @AllArgsConstructor
public class ShoppingItem extends BaseEntity {
	
	@NotBlank(message = "nazwa nie może być pusta")
	@Size(max = 30)
	@NotNull
	private String name;
	
	@NotNull
	@ManyToOne(cascade = {CascadeType.DETACH})
	@JoinColumn(name = "mst_id")
	private MeasurmentType mst;

}
