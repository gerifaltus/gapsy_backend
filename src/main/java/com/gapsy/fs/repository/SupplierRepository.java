package com.gapsy.fs.repository;

import java.util.List;

import com.gapsy.fs.dto.SupplierDto;

/**
 * Se genera interfaz del DAO para evitar el alto acoplamiento entre clases
 */
public interface SupplierRepository {

	
	SupplierDto saveSupplier(SupplierDto supplierDto);
	
	SupplierDto updateSupplier(SupplierDto supplierDto);
	
	SupplierDto deleteSupplier(SupplierDto supplierDto);
	
	SupplierDto getById(Integer id);
	
	SupplierDto getByName(String name);
	
	List<SupplierDto> getAll();
}
