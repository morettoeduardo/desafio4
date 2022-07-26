/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saks.imobiliaria.controller;

import br.com.saks.imobiliaria.model.Cliente;
import br.com.saks.imobiliaria.model.Imovel;
import br.com.saks.imobiliaria.repository.ClienteRepository;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 7839677
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Cacheable("listarTodos")
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    @Cacheable("listarPeloId")
    @GetMapping(value="/{id}")
    public Cliente listarPeloId(@PathVariable Long id) {
        Optional<Cliente> clienteResponse = clienteRepository.findById(id);
        Cliente cliente = clienteResponse.get(); 
        return cliente;
    }
    
    @PostMapping
    public Cliente adicionar(@RequestBody Cliente cliente) {
        cliente.setSenha(getSHA256(cliente.getSenha()));
        return clienteRepository.save(cliente);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteRepository.findById(id)
                .map(record -> {
                    record.setNome(cliente.getNome());
                    record.setEmail(cliente.getEmail());
                    record.setSenha(cliente.getSenha());
                    record.setTelefone(cliente.getTelefone());
                    Cliente clienteUpdated = clienteRepository.save(record);
                    return ResponseEntity.ok().body(clienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(record -> {
                    clienteRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    
    public static String getSHA256(String senha){

	String toReturn = null;
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    digest.reset();
	    digest.update(senha.getBytes("utf8"));
	    toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return toReturn;
    }
}