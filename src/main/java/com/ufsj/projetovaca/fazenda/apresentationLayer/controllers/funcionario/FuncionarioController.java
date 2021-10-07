package com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.funcionario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufsj.projetovaca.fazenda.applicationLayer.applicationService.CadastroFuncionario;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioInput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.DTO.FuncionarioOutput;
import com.ufsj.projetovaca.fazenda.apresentationLayer.controllers.interfaces.ICrudController;
import com.ufsj.projetovaca.fazenda.domainLayer.domainServices.VerificaSePossuiFuncaoFuncionario;
import com.ufsj.projetovaca.fazenda.domainLayer.models.Funcionario;
import com.ufsj.projetovaca.fazenda.domainLayer.repositories.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController extends ICrudController<FuncionarioInput,FuncionarioOutput,Funcionario> {
	
	
	Class<FuncionarioOutput> Output = FuncionarioOutput.class;

	
	
	public FuncionarioController() {
		
		super();
		
		super.criarConversores(Output);
	}
	
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	
	@Autowired
	VerificaSePossuiFuncaoFuncionario verificaSePossuiFuncaoFuncionario; 
	
	@Autowired
	CadastroFuncionario cadastroFuncionario;
	
	
	@GetMapping
	@Override
	public ResponseEntity<?> listar() {
		
		try {
			
			List<FuncionarioOutput> funcionariosOutput = cadastroFuncionario.listar();
			
			return RespostaStatus(HttpStatus.OK, funcionariosOutput);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
		
		
		
	}
	
	@PostMapping
	@Override
	public ResponseEntity<?> criar(@RequestBody FuncionarioInput funcionarioInput) {
		try {
			
			FuncionarioOutput funcionarioOutput = cadastroFuncionario.salvar(funcionarioInput);
			
			return RespostaStatus(HttpStatus.OK, funcionarioOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
		}
		
		
	}
	
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		try {
			
			FuncionarioOutput funcionarioOutput = cadastroFuncionario.deletar(id);
			
			return RespostaStatus(HttpStatus.OK, funcionarioOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
	}

	@PutMapping("/{id}")
	@Override
	public ResponseEntity<?> atualizar(@RequestBody FuncionarioInput funcionarioInput,@PathVariable Long id) {
		try {
			
			FuncionarioOutput funcionarioOutput = cadastroFuncionario.atualizar(funcionarioInput, id);
			
			
			
			return RespostaStatus(HttpStatus.OK, funcionarioOutput);
			
		}catch(Exception e) {
			
			return erroServidor(e);
			
		}
	}
}
