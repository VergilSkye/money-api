package com.java.vergilmoney.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.java.vergilmoney.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
