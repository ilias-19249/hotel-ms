package com.example.learn_handling_exceptions.Repositories;

import com.example.learn_handling_exceptions.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Integer> {


}
