package klu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import klu.model.Fund;

@Repository
public interface FundRepo extends JpaRepository<Fund, Long>
{
	 Optional<Fund> findById(Long id);
}
