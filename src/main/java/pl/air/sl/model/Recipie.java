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
@Table(name = "recipies")
@AttributeOverride(column = @Column(name = "rec_id"), name = "id")
@NoArgsConstructor @Getter @Setter @SuperBuilder @AllArgsConstructor
public class Recipie extends BaseEntity{
	
	@NotBlank(message = "nazwa nie może być pusta")
	@Size(max = 50)
	@NotNull
	private String name;
	
	@NotBlank(message = "opis nie może być pusty")
	private String description;
	
	@ManyToOne(cascade = {CascadeType.DETACH})
	@JoinColumn(name = "sl_id")
	@NotNull
	private ShoppingList shoppingList;

}
