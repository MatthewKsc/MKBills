package com.matthewksc.billlogic.Dao;

import com.matthewksc.billlogic.Dao.entity.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {
}
