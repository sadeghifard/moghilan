package com.sadeghifard.moghilan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadeghifard.moghilan.enums.PaymentType;
import com.sadeghifard.moghilan.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	Optional<Payment> findByPaymentCode(Long paymentCode);
	Optional<Iterable<Payment>> findByPaymentType(PaymentType paymentType);
	void deleteByPaymentCode(Long paymentCode);

}
