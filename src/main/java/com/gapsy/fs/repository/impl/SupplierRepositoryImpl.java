package com.gapsy.fs.repository.impl;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gapsy.fs.dto.SupplierDto;
import com.gapsy.fs.repository.SupplierRepository;

import jakarta.annotation.PostConstruct;

/***
 * Aqui se implementa el patron DAO o Repository quien se encarga de todas las operaciones del crud
 */
@Repository
public class SupplierRepositoryImpl implements SupplierRepository{
	
	private List<SupplierDto> listSuppliers = new ArrayList<>();
	private ObjectMapper mapper = new ObjectMapper();

	public SupplierRepositoryImpl() {
		//constructor
	}
	
	/**
	 * Guarda proveedores
	 * @param supplierDto
	 * @return SupplierDto
	 */
	public SupplierDto saveSupplier(SupplierDto supplierDto) {
		generateId(supplierDto);
		
		listSuppliers.add(supplierDto);
		this.saveJson(listSuppliers);
		return supplierDto;
	}
	
	
	/**
	 * Actualiza Proveedores
	 * @param supplierDto
	 * @return SupplierDto
	 */
	public SupplierDto updateSupplier(SupplierDto supplierDto) {
		
		for(SupplierDto update : listSuppliers) {
			if(update.getId().compareTo(supplierDto.getId()) == 0) {
				update.setRazonSocial(supplierDto.getRazonSocial());
				update.setDireccion(supplierDto.getDireccion());
				update.setName(supplierDto.getName());
			}
		}
		
		this.saveJson(listSuppliers);
		return supplierDto;
	}
	
	/**
	 * Eliminar proveedores
	 * @param supplierDto
	 * @return SupplierDto
	 */
	public SupplierDto deleteSupplier(SupplierDto supplierDto) {
		
		List<SupplierDto> listSupplierLocal = listSuppliers
				.stream()
				.filter(item -> (item.getId().compareTo(supplierDto.getId()) != 0))
				.toList();
		
		this.saveJson(listSupplierLocal);
		return supplierDto;
	}
	
	/**
	 * Busca por id de proveedor
	 * @param id
	 * @return
	 */
	public SupplierDto getById(Integer id) {
		Optional<SupplierDto> supplier = listSuppliers
				.stream()
				.filter(item -> Objects.equals(item.getId(), id))
				.findFirst();
		
		return supplier.orElse(null);
	}
	
	/**
	 * Busca por nombre de proveedor
	 * @param name
	 * @return
	 */
	public SupplierDto getByName(String name) {
		
		if(listSuppliers.isEmpty()) {
			return null;
		}
		
		Optional<SupplierDto> supplier = listSuppliers
				.stream()
				.filter(item -> Objects.equals(item.getName(), name))
				.findFirst();
		
		return supplier.orElse(null);
	}
	
	/**
	 * Devuelve todos los proveedores
	 * @return
	 */
	public List<SupplierDto> getAll() {
		this.getJson();
		return listSuppliers;
	}
	
	/**
	 * Carga automáticamente todos los proveedores en una lista
	 */
	@PostConstruct
	private void getJson() {
		try (InputStream isJson = getClass().getClassLoader().getResourceAsStream("bd.json");){
			listSuppliers = mapper.readerForListOf(SupplierDto.class).readValue(isJson);
		} catch (Exception e) {
			System.out.println("Error al cargar el archivo bd.json");
		}
	}
	
	/**
	 * Guarda los proveedores en el archivo bd.json
	 * @param listSuppliers
	 */
	private void saveJson(List<SupplierDto> listSuppliers) {
		try {
			URI urlJson = getClass().getClassLoader().getResource("bd.json").toURI();
			mapper.writeValue(Path.of(urlJson).toFile(), listSuppliers);
		} catch (Exception e) {
			System.out.println("Error al guardar el registro en el archivo bd.json");
		}
	}
	
	/**
	 * Genera Id incremental para cada proveedor
	 * @param supplierDto
	 */
	private void generateId(SupplierDto supplierDto) {
		supplierDto.setId(listSuppliers.size()+1);
	}
	
}
