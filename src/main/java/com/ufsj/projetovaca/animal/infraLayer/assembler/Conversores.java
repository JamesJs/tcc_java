package com.ufsj.projetovaca.animal.infraLayer.assembler;


public class Conversores<Input,Output,Entidade> {
		
	
	public AssemblerAdapter<Input, Entidade> criarConversorInput(Class<Input> Input){
		Assembler<Input, Entidade> assemblerInput = 
				new Assembler<Input,Entidade>(Input);
		AssemblerAdapter<Input, Entidade> conversorInput =
				new AssemblerAdapter<Input,Entidade>(assemblerInput);
		return conversorInput;
	}
	
	public AssemblerAdapter<Output, Entidade> criarConversorOutput(Class<Output> Output){
		Assembler<Output, Entidade> assemblerOutput = 
				new Assembler<Output,Entidade>(Output);
		AssemblerAdapter<Output, Entidade> conversorOutput =
				new AssemblerAdapter<Output,Entidade>(assemblerOutput);
		return conversorOutput;
	}
	
	public AssemblerAdapter<Entidade,Input> criarConversorEntidade(Class<Entidade> Entidade){
		 Assembler<Entidade, Input> assemblerEntidade = 
				new Assembler<Entidade,Input>(Entidade);
		AssemblerAdapter<Entidade, Input> conversorEntidade = 
				new AssemblerAdapter<Entidade,Input>(assemblerEntidade);
		return conversorEntidade;
		
	}

}