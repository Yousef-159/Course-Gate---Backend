package org.jo.studentback;
import org.springframework.data.jpa.repository.JpaRepository;
public interface adminRepository extends JpaRepository<admin, Integer> {
    admin findByCode(String code);
}
