package br.com.gabrieldeoliveira.dsmeta.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrieldeoliveira.dsmeta.entities.Sale;
import br.com.gabrieldeoliveira.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;

	public List<Sale> findSales() {
		return this.saleRepository.findAll();
	}
}
