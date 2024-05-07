package com.gapsy.fs.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.gapsy.fs.dto.SupplierDto;
import com.gapsy.fs.repository.impl.SupplierRepositoryImpl;
import com.gapsy.fs.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{

	private SupplierRepositoryImpl supplierRepository;
	
	public SupplierServiceImpl(SupplierRepositoryImpl supplierRepository) {
		this.supplierRepository = supplierRepository;
	}
	
	@Override
	public SupplierDto saveSupplier(SupplierDto supplierDto) {
		SupplierDto existSupplier = supplierRepository.getByName(supplierDto.getName());
		if(Objects.isNull(existSupplier)) {
			return supplierRepository.saveSupplier(supplierDto);
		}else {
			return null;
		}
		
	}

	@Override
	public SupplierDto updateSupplier(SupplierDto supplierDto, Integer id) {
		SupplierDto updatedSupplier = supplierRepository.getById(id);
		if(Objects.isNull(updatedSupplier)) {
			return null;
		}
		
		return supplierRepository.updateSupplier(supplierDto);
		
	}
	
	@Override
	public SupplierDto deleteSupplier(SupplierDto supplierDto, Integer id) {
		SupplierDto updatedSupplier = supplierRepository.getById(id);
		if(Objects.isNull(updatedSupplier)) {
			return null;
		}
		
		return supplierRepository.deleteSupplier(supplierDto);
		
	}

	@Override
	public void getById() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SupplierDto> getAll() {
		return supplierRepository.getAll();
	}

}
