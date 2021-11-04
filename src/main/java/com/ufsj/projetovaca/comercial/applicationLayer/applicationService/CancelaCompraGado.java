package com.ufsj.projetovaca.comercial.applicationLayer.applicationService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.comercial.domainLayer.models.CompraGado;
import com.ufsj.projetovaca.comercial.domainLayer.repositories.CompraGadoRepository;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
@Service
public class CancelaCompraGado implements Observer<Long>{
	@Autowired
	CompraGadoRepository compraGadoRepository;
	
	@Override
	public void onSubscribe(@NonNull Disposable d) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNext(@NonNull Long id) {
		try {
			Optional<CompraGado> opCompraGado	= compraGadoRepository.findById(id);
			if(opCompraGado.isEmpty()) {
				return;
			}
			System.out.println("Cancelando compra");
			CompraGado compraGado = opCompraGado.get();
			compraGado.setCancelada(true);
			compraGadoRepository.save(compraGado);
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void onError(@NonNull Throwable e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}
}
