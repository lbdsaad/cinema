package org.sid.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.sid.cinema.entities.Categorie;

@CrossOrigin("*")

@RepositoryRestResource
public interface CategorieRepository extends
JpaRepository<Categorie,Long> {
}
