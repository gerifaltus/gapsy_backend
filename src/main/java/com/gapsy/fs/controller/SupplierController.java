package com.gapsy.fs.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gapsy.fs.dto.SupplierDto;
import com.gapsy.fs.service.SupplierService;

/**
 * Se implementa patron MVC en el controler para separar las capas de la aplicación
 */
@CrossOrigin()
@RestController
@RequestMapping("/api/v1/gapsy")
public class SupplierController {

	private SupplierService supplierService;
	
	public SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	/**
	 * Devuelve la lista de proveedores disponibles
	 * @return List<SupplierDto>
	 */
	@GetMapping("/suppliers")
	public List<SupplierDto> getAll(){
		return supplierService.getAll();
	}
	
	/**
	 * Guarda un proveedor
	 * @param supplierDto
	 * @return ResponseEntity<Object>
	 */
	@PostMapping("/suppliers")
	public ResponseEntity<Object> saveSupplier(@RequestBody SupplierDto supplierDto) {
		SupplierDto supplier = supplierService.saveSupplier(supplierDto);
		
		if (Objects.isNull(supplier)) {
			return ResponseEntity.badRequest().body("Registro duplicado, verifique la información");
		}
		
		return ResponseEntity.ok().body(supplier);
	}
	
	/**
	 * Actualiza un proveedor
	 * @param supplierDto
	 * @param id
	 * @return ResponseEntity<Object> 
	 */
	@PutMapping("/suppliers/{id}")
	public ResponseEntity<Object> updateSupplier(@RequestBody SupplierDto supplierDto, @PathVariable Integer id) {
		SupplierDto supplier = supplierService.updateSupplier(supplierDto, id);
		
		if (Objects.isNull(supplier)) {
			return ResponseEntity.badRequest().body("Registro no encontrado, verifique la información");
		}
		
		return ResponseEntity.ok().body(supplier);
	}
	
	/**
	 * Eliminar un proveedor
	 * @param supplierDto
	 * @param id
	 * @return
	 */
	@DeleteMapping("/suppliers/{id}")
	public ResponseEntity<Object> deleteSupplier(@RequestBody SupplierDto supplierDto, @PathVariable Integer id) {
		SupplierDto supplier = supplierService.deleteSupplier(supplierDto, id);
		
		if (Objects.isNull(supplier)) {
			return ResponseEntity.badRequest().body("Imposible eliminar el proveedor, no existe. Verifique por favor.");
		}
		
		return ResponseEntity.ok().body(null);
	}
	
	
}
