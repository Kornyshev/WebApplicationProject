package me.kornyshev.WebCalculator.repositories;

import me.kornyshev.WebCalculator.models.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Integer> {
}