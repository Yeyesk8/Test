package com.webburguer.burguer3j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webburguer.burguer3j.entity.DetalleOrden;



@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {

}
