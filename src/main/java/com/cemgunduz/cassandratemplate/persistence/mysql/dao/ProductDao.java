package com.cemgunduz.cassandratemplate.persistence.mysql.dao;

import com.cemgunduz.cassandratemplate.persistence.mysql.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cem.gunduz on 22.06.2015.
 *
 * No implementation is not magical =)
 * Just Spring data putting a default implementation for the classes with no provided impl
 */
public interface ProductDao extends JpaRepository<Product, Long> {

}
