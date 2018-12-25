package com.javaee.fabiola.acoes.controllers.v1;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.javaee.fabiola.acoes.domain.Investidor;
import com.javaee.fabiola.acoes.domain.InvestidorRest;
import com.javaee.fabiola.acoes.services.InvestidorService;

@RestController
@RequestMapping(InvestidorController.BASE_URL)
public class InvestidorController {

	public static final String BASE_URL = "/api/v1/Investidores";

	@Autowired
	private InvestidorService InvestidorService;

	// GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Set<InvestidorRest> getAll() {
		Set<InvestidorRest> InvestidorsRest = new HashSet<>();
		InvestidorService.getAll().forEach((Investidor Investidor) -> {
			InvestidorsRest.add(new InvestidorRest(Investidor));
		});
		return InvestidorsRest;
	}

	@GetMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public InvestidorRest getById(@PathVariable String id) {
		return new InvestidorRest(InvestidorService.getById(id));
	}

	// POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InvestidorRest createNew(@RequestBody Investidor buyer) {
		return new InvestidorRest(InvestidorService.createNew(buyer));
	}

	// PUT
	@PutMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public InvestidorRest update(@PathVariable String id, @RequestBody Investidor buyer) {
		return new InvestidorRest(InvestidorService.save(id, buyer));
	}

	// DELETE
	@DeleteMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String id) {
		InvestidorService.deleteById(id);
	}
}
