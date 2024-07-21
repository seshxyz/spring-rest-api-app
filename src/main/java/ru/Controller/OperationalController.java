package ru.Controller;

import ru.DTO.FileDTO;
import ru.Entity.FileCell;
import ru.FileNotFoundInStorageException;
import ru.Repository.FilePagAndSortRepository;
import ru.Service.ServiceClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;


@RestController
public class OperationalController {

    ServiceClass serviceClass;
    FilePagAndSortRepository filePagAndSortRepository;

    public OperationalController(ServiceClass serviceClass, FilePagAndSortRepository filePagAndSortRepositor) {
        this.serviceClass = serviceClass;
        this.filePagAndSortRepository = filePagAndSortRepositor;
    }

    @PostMapping(value = ("/upload"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> uploadFile(@RequestParam(name = "filecontent") MultipartFile filecontent, String description) throws IOException
    {
        String base64content = new String(Base64.getEncoder().encode((filecontent.getBytes())));
        return serviceClass.saveFile(new FileCell(filecontent.getOriginalFilename(), base64content, description));
    }

    @GetMapping(value = "/view/{id}")
    public ResponseEntity<FileDTO> getFile(@PathVariable("id") Long id)
    {
        FileCell dto = serviceClass.findOneById(id).map((dao)->new FileCell(dao.getId(),dao.getTitle(),dao.getFilecontent(),dao.getDescription(),dao.getCreatedAt())).
                orElseThrow(()->new FileNotFoundInStorageException("File not found"));
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDTO(dto.getId(), dto.getTitle(), dto.getFilecontent(), dto.getDescription(), dto.getCreatedAt()));
    }

    @GetMapping(value = "/view/all")
    public Page<FileCell> getAllFiles(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortRule", required = false) String sortRule) {
        if ( ((sortRule !=null || !sortRule.isEmpty()) & (page!=null || page>=0) & (size!=null || size>=1))) {
            switch (sortRule) {
                case "Asc":
                    return filePagAndSortRepository.findAll(PageRequest.of(page,size,Sort.by("createdAt").ascending()));
                case "Desc":
                    return filePagAndSortRepository.findAll(PageRequest.of(page,size,Sort.by("createdAt").descending()));
                default:
                    return filePagAndSortRepository.findAll(PageRequest.of(0,20));
            }
        }
        return filePagAndSortRepository.findAll(PageRequest.of(0,20));
    }

}
