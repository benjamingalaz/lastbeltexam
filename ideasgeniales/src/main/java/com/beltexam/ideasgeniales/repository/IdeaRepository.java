package com.beltexam.ideasgeniales.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beltexam.ideasgeniales.models.Idea;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {

    Idea findByNombreIdea(String nombreIdea);
    Iterable<Idea> findByOrderByContadorLikesAsc();
    Iterable<Idea> findByOrderByContadorLikesDesc();
}
