package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projection.SaleSummaryProjection;

public class SaleSummaryDTO {

	private String name;
	private Double total;

	public SaleSummaryDTO(String name, Double amount) {
		this.name = name;
		this.total = amount;
	}

	public SaleSummaryDTO(Sale entity) {
		name = entity.getSeller().getName();
		total = entity.getAmount();
	}

	public SaleSummaryDTO(SaleSummaryProjection entity) {
		name = entity.getName();
		total = entity.getSum();
	}

	public String getName() {
		return name;
	}

	public Double getTotal() {
		return total;
	}

}
