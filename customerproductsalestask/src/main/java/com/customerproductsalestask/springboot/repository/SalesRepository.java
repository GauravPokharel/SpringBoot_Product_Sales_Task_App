package com.customerproductsalestask.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerproductsalestask.springboot.model.SalesModel;

@Repository
public interface SalesRepository extends JpaRepository<SalesModel, Integer> {

}
