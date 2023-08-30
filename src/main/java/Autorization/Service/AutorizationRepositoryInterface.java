package Autorization.Service;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.jpa.repository.JpaRepository;

import Entity.AutorizationUserEntity;

public interface AutorizationRepositoryInterface extends JpaRepository<AutorizationUserEntity,Integer> {

	List<AutorizationUserEntity> findByEmail(String email);

	List<AutorizationUserEntity> findByPhoneAndCountry_preflix(String phone,String country_Preflix);

	boolean existsByEmail(String email);
	boolean existsByPhoneAndCountry_preflix(String phone,String countryPreflix);
	default boolean existsByEmailOrPhoneAndCountryPreflix(String email, String phone, String countryPreflix) {
		 return existsByEmail(email)||this.existsByPhoneAndCountry_preflix(phone, countryPreflix);
	 }
	 
}
