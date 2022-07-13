package br.com.gabrieldeoliveira.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrieldeoliveira.dsmeta.entities.Sale;
import br.com.gabrieldeoliveira.dsmeta.services.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@GetMapping
	public Page<Sale> getSales(Pageable pageable, 
			@RequestParam(value = "minDate", defaultValue = "") String minDate, 
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		return saleService.findSales(pageable, minDate, maxDate);
	}
}
