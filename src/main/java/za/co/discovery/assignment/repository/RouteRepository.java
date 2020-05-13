package za.co.discovery.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
}
