package com.javaee.fabiola.acoes.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaee.fabiola.acoes.domain.Empresa;
import com.javaee.fabiola.acoes.domain.Acao;
import com.javaee.fabiola.acoes.repositories.EmpresaRepository;

@Service
public abstract class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private AcaoService acaoService;


	@Override
	public Set<Empresa> getAll() {
		Set<Empresa> empresas = new HashSet<>();
		empresaRepository.findAll().iterator().forEachRemaining(empresas::add);
		return empresas;
	}

	@Override
	public Empresa getById(String id) {
		return getEmpresaById(id);
	}

	private Empresa getEmpresaById(String id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);

		if (!empresaOptional.isPresent()) {
			throw new IllegalArgumentException("Empresa não encontrada pelo ID value: " + id.toString());
		}

		return empresaOptional.get();
	}

	@Override
	public Empresa createNew(Empresa empresa) {
		Empresa empresaInd0;
		try {
			empresaInd0 = empresaRepository.findByEmail(empresa.getEmail()).get(0);
		} catch (Exception e) {
			return empresaRepository.save(empresa);			
		}
		throw new IllegalArgumentException("Empresajá existe com o ID: " + empresaInd0.getId());
	}

	@Override
	public Empresa save(String id, Empresa empresa) {
		empresa.setId(id);
		return empresaRepository.save(empresa);
	}

	@Override
	public void deleteById(String id) {
		empresaRepository.deleteById(id);
	}

	@Override
	public Empresa addAcao(String empresaId, Acao acao) {
		Empresa empresa = getEmpresaById(empresaId);
		Set<Acao> acaos = empresa.getAcoes();
		acaos.remove(null);
		acaos.add(acaoService.createNew(acao));
		empresa.setAcoes(acaos);
		return save(empresaId, empresa);
	}

	@Override
	public Set<Acao> getAllAcoes(String empresaId) {
		return getEmpresaById(empresaId).getAcoes();
	}

	@Override
	public Acao getAcaoById(String empresaId, String acaoId) {
		for (Acao acao : getEmpresaById(empresaId).getAcoes()) {
			if (acao.getId().equals(acaoId)) {
				return acao;
			}
		}
		throw new IllegalArgumentException("Acao não encontrada pelo id value: " + acaoId.toString() + " para a Empresa " + empresaId.toString());
	}

}
