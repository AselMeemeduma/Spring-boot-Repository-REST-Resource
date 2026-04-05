package com.example.repositoryrestresource.repository;

import com.example.repositoryrestresource.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AddressRepository extends JpaRepository<Address, Long>, PagingAndSortingRepository<Address, Long> {
}

