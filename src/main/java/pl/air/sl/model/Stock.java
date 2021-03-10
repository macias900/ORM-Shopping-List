package pl.air.sl.model;

import javax.persistence.AttributeOverride;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "stocks")
@AttributeOverride(column = @Column(name = "st_id"), name = "id")
@NoArgsConstructor @Getter @Setter @SuperBuilder @AllArgsConstructor
public class Stock extends BaseEntity{
	
	//@ManyToOne(cascade = {CascadeType.ALL})
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "item_id")
	private ShoppingItem shopItem;
	
	
	@NotNull	
	@Min( value = 0)
	private Integer quantity;

}
