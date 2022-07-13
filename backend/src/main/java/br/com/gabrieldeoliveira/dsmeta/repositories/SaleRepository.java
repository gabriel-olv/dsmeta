package br.com.gabrieldeoliveira.dsmeta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrieldeoliveira.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
