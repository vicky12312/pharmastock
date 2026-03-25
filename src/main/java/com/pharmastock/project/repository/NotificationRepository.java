package com.pharmastock.project.repository;
import com.pharmastock.project.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdAndStatus(Long userId, com.pharmastock.project.entity.enums.NotificationStatus status);
}
