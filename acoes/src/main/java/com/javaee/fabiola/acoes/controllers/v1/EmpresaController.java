package com.javaee.fabiola.acoes.controllers.v1;

import java.util.HashSet;
import java.util.Set;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.fabiola.acoes.domain.Empresa;
import com.javaee.fabiola.acoes.domain.EmpresaRest;
import com.javaee.fabiola.acoes.services.EmpresaService;

@RestController
@RequestMapping(EmpresaController.BASE_URL)
public class EmpresaController {

	public static final String BASE_URL = "/api/v1/empresas";


	private EmpresaService EmpresaService;

	// GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Set<EmpresaRest> getAll() {
		Set<EmpresaRest> empresasRest = new HashSet<>();
		EmpresaService.getAll().forEach((Empresa empresa) -> {
			empresasRest.add(new EmpresaRest(empresa));
		});
		return empresasRest;
	}

	@GetMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public EmpresaRest getById(@PathVariable String id) {
		return new EmpresaRest(EmpresaService.getById(id));
	}

	
	// POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaRest createNew(@RequestBody Empresa Empresa) {
		return new EmpresaRest(EmpresaService.createNew(Empresa));
	}



	// PUT
	@PutMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public EmpresaRest update(@PathVariable String id, @RequestBody Empresa Empresa) {
		Empresa comp = EmpresaService.getById(id);
		comp.setNome(Empresa.getNome());
		comp.setEmail(Empresa.getEmail());
		return new EmpresaRest(EmpresaService.save(id, comp));
	}

	// DELETE
	@DeleteMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String id) {
		EmpresaService.deleteById(id);
	}
}
