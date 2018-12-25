package com.javaee.fabiola.acoes.services;

import java.util.Set;

import com.javaee.fabiola.acoes.domain.Empresa;
import com.javaee.fabiola.acoes.domain.Acao;

public interface EmpresaService {
	Set<Empresa> getAll();

	Empresa getById(String id);

	Empresa createNew(Empresa empresa);

	Empresa save(String id, Empresa empresa);

	void deleteById(String id);

	Empresa addAcao(String id, Acao acao);

	Set<Acao> getAllAcaos(String empresaId);

	Acao getAcaoById(String empresaId, String acaoId);

	Set<Acao> getAllAcoes(String empresaId);
}
