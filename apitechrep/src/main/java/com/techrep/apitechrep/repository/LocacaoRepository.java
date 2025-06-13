/*package com.techrep.apitechrep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techrep.apitechrep.entity.LocacaoEntity;

public interface LocacaoRepository extends JpaRepository<LocacaoEntity, Integer> {

}
*/
package com.techrep.apitechrep.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techrep.apitechrep.entity.LocacaoEntity;

public interface LocacaoRepository extends JpaRepository<LocacaoEntity, Integer> {
	
	  List<LocacaoEntity> findByProdutoIdAndDataFimAfterAndDataInicioBefore(
		        int produtoId,
		        LocalDateTime dataInicio,
		        LocalDateTime dataFim
		    );

}

// Verifica se há locações existentes que conflitam com o período desejado para um mesmo produto

