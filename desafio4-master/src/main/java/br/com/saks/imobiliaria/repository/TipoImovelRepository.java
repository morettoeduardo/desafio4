/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.imobiliaria.repository;

import br.com.saks.imobiliaria.model.TipoImovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 7839677
 */
@Repository
public interface TipoImovelRepository extends JpaRepository<TipoImovel, Long>{
    
}
