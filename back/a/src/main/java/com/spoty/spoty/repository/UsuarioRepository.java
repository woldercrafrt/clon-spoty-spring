package com.spoty.spoty.repository;

import com.spoty.spoty.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    List<Usuario> findByPlan(String plan);
    
    List<Usuario> findByRol(String rol);
    
    @Query("SELECT u FROM Usuario u WHERE u.plan = :plan AND u.rol = :rol")
    List<Usuario> findByPlanAndRol(@Param("plan") String plan, @Param("rol") String rol);
    
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.plan = :plan")
    long countByPlan(@Param("plan") String plan);
}