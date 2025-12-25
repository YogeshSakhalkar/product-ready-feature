package com.example.prod_ready_feature.controller;

import com.example.prod_ready_feature.entity.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/audit")
public class AduitController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    List<PostEntity> getPostRevison(@PathVariable Long postId){
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revision = reader.getRevisions(PostEntity.class, postId);
        return revision.stream()
                .map(revisionNumber->reader.find(PostEntity.class, postId, revisionNumber))
                .collect(Collectors.toList());
    }
}
