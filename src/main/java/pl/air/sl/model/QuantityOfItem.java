package pl.air.sl.model;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "quantity_of_items")
@AttributeOverride(column = @Column(name ="quantity_id"), name = "id")
@NoArgsConstructor @Getter @Setter @SuperBuilder @AllArgsConstructor
public class QuantityOfItem extends BaseEntity {
	
	@NotNull
	@Min( value = 0)
	private Integer quantity;
	
	@NotNull
	@ManyToOne(cascade = {CascadeType.DETACH})
	@JoinColumn(name = "item_id")
	private ShoppingItem shoppingItem;
	
	@NotNull
	@ManyToOne(cascade = {CascadeType.DETACH})
	@JoinColumn(name = "sl_id")
	private ShoppingList shoppingList;

}
