package org.example.repository;
import org.example.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    public List<File> findAllByDirectory_DirectoryId(Long directoryId);
}