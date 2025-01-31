package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projection.SaleReportProjection;
import com.devsuperior.dsmeta.projection.SaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportDTO> findReport(String minDate, String maxDate, String name, Pageable pageable) {

		maxDate = trataMaxDate(maxDate);
		minDate = trataMinDate(minDate, maxDate);

		Page<SaleReportProjection> lista = repository.searchReport(minDate, maxDate, name, pageable);

		return lista.map(x -> new SaleReportDTO(x));
	}

	public Page<SaleSummaryDTO> findSummary(String minDate, String maxDate, Pageable pageable) {

		maxDate = trataMaxDate(maxDate);
		minDate = trataMinDate(minDate, maxDate);

		Page<SaleSummaryProjection> lista = repository.searchSummary(minDate, maxDate, pageable);

		return lista.map(x -> new SaleSummaryDTO(x));
	}

	public String trataMaxDate(String maxDate){

		if(maxDate.isEmpty()){
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			maxDate = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
		}

		return maxDate;
	}

	public String  trataMinDate(String minDate, String maxDate){

		if(minDate.isEmpty()){
			LocalDate maxDateF = LocalDate.parse(maxDate); ;
			LocalDate result = maxDateF.minusYears(1L);
			minDate = result.format(DateTimeFormatter.ISO_LOCAL_DATE);
		}

		return  minDate;
	}
}
