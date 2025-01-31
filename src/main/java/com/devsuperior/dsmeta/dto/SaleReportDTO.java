package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projection.SaleReportProjection;

import java.time.LocalDate;

public class SaleReportDTO {

	private Long id;
	private String name;
	private Double amount;
	private LocalDate date;

	public SaleReportDTO(String name, Double amount) {
		this.name = name;
		this.amount = amount;
	}

	public SaleReportDTO(Sale entity) {
		id = entity.getId();
		name = entity.getSeller().getName();
		amount = entity.getAmount();
		date = entity.getDate();
	}

	public SaleReportDTO(SaleReportProjection entity) {
		id = entity.getId();
		name = entity.getName();
		amount = entity.getAmount();
		date = entity.getDate();
	}

	public String getName() {
		return name;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}


    public Long getId() {
        return id;
    }

}
