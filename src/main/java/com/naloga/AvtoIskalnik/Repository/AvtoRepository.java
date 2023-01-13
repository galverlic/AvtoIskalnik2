package com.naloga.AvtoIskalnik.Repository;

import com.naloga.AvtoIskalnik.Model.Avto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AvtoRepository extends JpaRepository<Avto, Long> {

    List<Avto> findByActiveTrue();


}

