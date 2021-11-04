package com.ufsj.projetovaca.financeiro.applicationLayer.applicationService;

import java.lang.reflect.Field;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ufsj.projetovaca.financeiro.domainLayer.models.Receita;
import com.ufsj.projetovaca.financeiro.domainLayer.repositories.ReceitaRepository;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
@Service
public class CriaReceitaVendaGado implements Observer<Object> {
	@Autowired
	ReceitaRepository receitaRepository;

	@Override
	public void onSubscribe(@NonNull Disposable d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNext(@NonNull Object Compra) {
		try {
			System.out.println("oi");
			Receita receita = new Receita();
			Class<?> c = Compra.getClass();
			Field valor = c.getDeclaredField("valor");
			valor.setAccessible(true);
			Field data = c.getDeclaredField("data");
			data.setAccessible(true);
			receita.setDataRecebimento((Date)ReflectionUtils.getField(data, Compra));
			receita.setValor((float)ReflectionUtils.getField(valor, Compra));
			receita.getTipo().setIdTipoReceita(2);
			System.out.println("Criando receita");
			receitaRepository.save(receita);		
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		
		} catch (Exception e) {
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
