package com.wellsfargo.training.lms.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wellsfargo.training.lms.model.ItemCard;
import com.wellsfargo.training.lms.repository.ItemCardRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemCardService {
	
	@Autowired
	private ItemCardRepository icrepo;
	
	public ItemCard saveItemCard(ItemCard i) {
		return icrepo.save(i);   // Invokes save() method predefined in JPA repo
	}
	public List<ItemCard> listAll(){
 		
 		return icrepo.findAll(); //Define in JPA repo.
 	}
	public Optional<ItemCard> getSingleItemCard(long id){
		return icrepo.findById(id);
	}
	public void deleteItemCard(long id){
		icrepo.deleteById(id);
	}
}
