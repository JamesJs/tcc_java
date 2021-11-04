package com.ufsj.projetovaca.animal.applicationLayer.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufsj.projetovaca.animal.domainLayer.models.Animal;
import com.ufsj.projetovaca.animal.domainLayer.repositories.AnimalRepository;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import javassist.NotFoundException;
@Service

public class VendeAnimais implements Observer<List<Long>> {
	
	@Autowired
	AnimalRepository animalRepository;

	@Override
	public void onSubscribe(@NonNull Disposable d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNext(@NonNull List<Long> ids) {
		System.out.println("VENDENDO ANIMAIS");
		try {
			List<Animal> animais =  animalRepository.getByIdIn(ids);
			
			if(animais.size() < ids.size()) {
				throw new NotFoundException("Não foram encontrados todos os animais");
			}
			animais.stream().forEach((animal)->{
				animal.vender();
				animalRepository.save(animal);
			});
		}catch(Exception e) {
			System.out.println("VENDENDO ANIMAIS");
			throw new RuntimeException(e.getMessage());
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
