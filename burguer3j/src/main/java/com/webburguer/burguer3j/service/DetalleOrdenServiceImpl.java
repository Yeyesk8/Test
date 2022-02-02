package com.webburguer.burguer3j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webburguer.burguer3j.entity.DetalleOrden;
import com.webburguer.burguer3j.repository.DetalleOrdenRepository;



@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{
	
	@Autowired
	private DetalleOrdenRepository detalleOrdenRepository;

	@Override
	public DetalleOrden createDetalleOrden(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}
