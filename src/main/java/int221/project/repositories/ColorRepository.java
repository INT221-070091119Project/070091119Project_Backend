package int221.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import int221.project.models.Color;

public interface ColorRepository extends JpaRepository<Color, Integer>  {

}
