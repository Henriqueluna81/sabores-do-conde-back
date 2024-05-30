package br.com.crud.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crud.crud.model.CrudModel;

public interface CrudRepository extends JpaRepository<CrudModel, Long> {
    
}
