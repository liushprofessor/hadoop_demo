/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.liu;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Sample repository managing customers to show projecting functionality of Spring Data MongoDB.
 *  具体Springdata例子可查看activti6 的activiti-web-app工程
 * @author Oliver Gierke
 */
interface CustomerRepository extends CrudRepository<Customer, ObjectId> {
    //Spring Data存储库基础结构中内置的查询构建器机制对于构建对存储库实体的约束查询非常有用。该机制条前缀find…By，read…By，query…By，count…By，和get…By从所述方法和开始分析它的其余部分。

    //Spring Data存储库基础结构中内置的查询构建器机制对于构建对存储库实体的约束查询非常有用。
    // 该机制条前缀find…By，read…By，query…By，count…By，和get…By从所述方法和开始分析它的其余部分。
    // introduction子句可以包含更多表达式，例如Distinct在要创建的查询上设置不同的标志。
    // 但是，第一个By用作分隔符以指示实际标准的开始。
    // 在最基本的层面上，您可以在实体属性上定义条件，并将它们与And和它们连接起来Or。以下示例显示了如何创建大量查询：


   /* List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);

    // Enables the distinct flag for the query
    List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname, String firstname);
    List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);

    // Enabling ignoring case for an individual property
    List<Person> findByLastnameIgnoreCase(String lastname);
    // Enabling ignoring case for all suitable properties
    List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

    // Enabling static ORDER BY for a query
    List<Person> findByLastnameOrderByFirstnameAsc(String lastname);
    List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
}
;*/

    //且查询
    List<Customer> findByLastnameAndFirstname(String lastname,String firstName);


    //查询lastname根据first升序排序
    List<Customer> findByLastnameOrderByFirstnameAsc(String lastname);


    //查询lastname根据first降序排序
    List<Customer> findByLastnameOrderByFirstnameDesc(String lastname);

    //分页查询，并且指定排序
    Page<Customer> findPagedBy(Pageable pageable);




    /**
     * 将查询到的结果转换成dto
     * @return
     */
    List<CustomerDto> findAllBy();

    //根据ID查找对象
    Customer findCustimerById(ObjectId id);










    /**
     * Dynamic projection for a single entity.
     * 动态查询 根据传入的class生成对象
     * @param id
     * @param projection
     * @return
     */
    <T> T findProjectedByFirstname(String firstName, Class<T> projection);
}
