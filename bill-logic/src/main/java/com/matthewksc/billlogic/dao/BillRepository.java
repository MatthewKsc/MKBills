package com.matthewksc.billlogic.dao;

import com.matthewksc.billlogic.dao.entity.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "*")
public interface BillRepository extends CrudRepository<Bill, Long> {
}
