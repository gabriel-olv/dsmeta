package br.com.gabrieldeoliveira.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gabrieldeoliveira.dsmeta.entities.Sale;
import br.com.gabrieldeoliveira.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;

	public Page<Sale> findSales(Pageable pageable, String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate min = minDate.equals("") ? today.minusYears(1) : LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		
		return this.saleRepository.findSales(pageable, min, max);
	}
}
