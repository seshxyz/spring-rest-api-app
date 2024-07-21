package ru.Service;

import ru.Entity.FileCell;
import ru.FileNotFoundInStorageException;
import ru.Repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiceClass {

    FileRepository fileRepository;

    public ServiceClass(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public Optional<FileCell> findOneById(Long id)
    {
        try
        {
            return fileRepository.findById(id);
        }
        catch (NoSuchElementException ex)
        {
            throw new FileNotFoundInStorageException("File not found.");
        }
    }

    public Map<String, Long> saveFile(FileCell fileCell)
    {
        fileRepository.save(fileCell);
        return Map.of("id",fileCell.getId());
    }

}
