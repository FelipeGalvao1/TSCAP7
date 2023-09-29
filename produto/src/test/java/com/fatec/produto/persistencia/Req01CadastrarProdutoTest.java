package com.fatec.produto.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.produto.model.Produto;
import com.fatec.produto.model.ProdutoRepository;

@SpringBootTest
class Req01CadastrarProdutoTest {
	@Autowired
	ProdutoRepository repository;

	@Test
	void ct01_cadastrar_produto_com_sucesso() {
		// dado que não esxiste em um registro cadastrado
		repository.deleteAll();
		// quando o usuario cadastrar o produto
		Produto produto = new Produto();
		produto.setDescricao("Eletrobomba");
		produto.setCategoria("Máquina de lavar");
		produto.setCusto(51.66);
		produto.SetQuantidadeNoEstoque(10);
		Produto p = repository.save(produto);
		// então o produto fica disponivel para consulta
		assertEquals(1, repository.count());
		assertNotNull(p);
	}

	@Test
	void ct02_cadastrar_produto_com_descricao_invalida() {
		try {
			Produto produto = new Produto();
			produto.setDescricao("");
			fail("Deveria disparar uma expection");
		} catch (IllegalArgumentException e) {
			System.err.println(e);
			assertEquals("A descrição nao pode ser branco", e.getMessage());
		}
	}
}