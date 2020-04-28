package com.matthewksc.billlogic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "*")
public interface BillRepository extends CrudRepository<Bill, Long> {
}
