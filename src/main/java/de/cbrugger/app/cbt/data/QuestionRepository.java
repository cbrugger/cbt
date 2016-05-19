/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.cbrugger.app.cbt.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.cbrugger.app.cbt.model.Question;

import java.util.List;



@ApplicationScoped
public class QuestionRepository {

    @Inject
    private EntityManager em;

    public Question findById(Long id) {
        return em.find(Question.class, id);
    }

    public Question findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Question> criteria = cb.createQuery(Question.class);
        Root<Question> question = criteria.from(Question.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(question).where(cb.equal(question.get(Member_.email), email));
        criteria.select(question).where(cb.equal(question.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Question> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Question> criteria = cb.createQuery(Question.class);
        Root<Question> question = criteria.from(Question.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(question).orderBy(cb.asc(question.get(Question_.name)));
        criteria.select(question).orderBy(cb.asc(question.get("name")));
        return em.createQuery(criteria).getResultList();
    }
}
