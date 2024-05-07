package com.gapsy.fs.service;

import java.util.List;

import com.gapsy.fs.dto.SupplierDto;

/**
 * Se genera interfaz del Service para evitar el alto acoplamiento entre clases
 */
public interface SupplierService {

	SupplierDto saveSupplier(SupplierDto supplierDto);
	
	SupplierDto updateSupplier(SupplierDto supplierDto, Integer id);
	
	SupplierDto deleteSupplier(SupplierDto supplierDto, Integer id);
	
	void getById();
	
	List<SupplierDto> getAll();
}
