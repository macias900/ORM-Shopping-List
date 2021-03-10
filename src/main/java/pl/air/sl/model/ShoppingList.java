package pl.air.sl.model;


import java.util.List;


import javax.persistence.AttributeOverride;

import javax.persistence.CascadeType;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "shopping_lists")
@AttributeOverride(column = @Column(name = "sl_id"), name = "id")
@NoArgsConstructor @Getter @Setter @SuperBuilder @AllArgsConstructor
public class ShoppingList extends BaseEntity{
	
	@NotBlank(message = "nazwa nie może być pusta")
	@Size(max = 50)
	@NotNull
	private String name;
	
	

	@ManyToMany(cascade  = {CascadeType.DETACH})
	@JoinTable(name = "shopping_product",joinColumns = @JoinColumn(name = "sl_id", referencedColumnName = "sl_id"),
	inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"))
	@NotEmpty
	private List<ShoppingItem> shoppingList ;

}
