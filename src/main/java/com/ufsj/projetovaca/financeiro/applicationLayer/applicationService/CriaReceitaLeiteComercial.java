package com.ufsj.projetovaca.financeiro.applicationLayer.applicationService;

import java.lang.reflect.Field;
import java.util.Date;

import com.ufsj.projetovaca.config.Events;
import com.ufsj.projetovaca.financeiro.domainLayer.models.Receita;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.ReceitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

@Service
public class CriaReceitaLeiteComercial implements Observer<Object> {
	
	@Autowired
	ReceitaRepository receitaRepository;
	
	@Autowired
	Events events;
	
	Long idCompra;
	
	@Override
	public void onSubscribe(@NonNull Disposable d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNext(@NonNull Object Compra) {
		try {
			Receita receita = new Receita();
			Class<?> c = Compra.getClass();
			Field valor = c.getDeclaredField("valor");
			valor.setAccessible(true);
			Field data = c.getDeclaredField("data");
			data.setAccessible(true);
			receita.setDataRecebimento((Date)ReflectionUtils.getField(data, Compra));
			receita.setValor((float)ReflectionUtils.getField(valor, Compra));
			receita.getTipo().setIdTipoReceita(1);
			System.out.println(receita);
			receitaRepository.save(receita);		
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException("Error");
		
		} catch (Exception e) {
			throw new RuntimeException("Error");
		}
		
		
		
	}

	@Override
	public void onError(@NonNull Throwable e) {
		
	}

	@Override
	public void onComplete() {
		
		
	}
}
