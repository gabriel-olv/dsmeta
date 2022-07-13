package br.com.gabrieldeoliveira.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gabrieldeoliveira.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT sale FROM Sale sale WHERE sale.date BETWEEN :min AND :max ORDER BY sale.amount DESC")
	Page<Sale> findSales(Pageable pageable, LocalDate min, LocalDate max);
}
