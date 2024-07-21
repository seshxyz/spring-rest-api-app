package ru.Repository;

import ru.Entity.FileCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileCell, Long> {

        public Optional<FileCell> findById(Long id);
        public List<FileCell> findAll();
}
