package ru.Repository;

import ru.Entity.FileCell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilePagAndSortRepository extends PagingAndSortingRepository<FileCell, Long>  {

    @Override
    Page<FileCell> findAll(Pageable pageable);

}
