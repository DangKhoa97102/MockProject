package com.mockproject.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto implements Serializable{

	private static final long serialVersionUID = -4879107258819494620L;
	
	private Long orderId;
	private Long productID;
	private Double price;
	private Integer quantity;
	private String name;
	private String slug;
	private String imgUrl;

}
