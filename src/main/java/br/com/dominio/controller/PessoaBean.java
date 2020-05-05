package br.com.dominio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.dominio.dao.PessoaDAO;
import br.com.dominio.model.Pessoa;

@Named()
@SessionScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoa pessoa;
	
	private PessoaDAO dao;
	private List<Pessoa> pessoas;
	private String estadoTela;
	
	public PessoaBean() {
		super();
		dao = new PessoaDAO();
		pessoas = new ArrayList<>();
		pessoas = dao.findAll();
		estadoTela = "adicionar";
	}

	public String salvar() {
		if ("adicionar".equals(this.estadoTela)) {
			dao.create(pessoa);
		}else if ("editar".equals(this.estadoTela)) {
			dao.update(pessoa);
		}
		atualizarPessoas();
		this.estadoTela = "adicionar";
		return null;
	}
	
	public String alterar(Pessoa pessoa) {
		this.pessoa = pessoa;
		this.estadoTela = "editar";
		return null;
	}
	
	public String excluir(Pessoa pessoa) {
		dao.delete(pessoa);
		atualizarPessoas();
		return null;
	}
	
	private void limparCampos() {
		pessoa = new Pessoa();
	}
	
	private void atualizarPessoas() {
		limparCampos();
		pessoas = dao.findAll();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String getEstadoTela() {
		return estadoTela;
	}

	public void setEstadoTela(String estadoTela) {
		this.estadoTela = estadoTela;
	}

	
}
